package com.nedap.archie.adlparser;

import com.nedap.archie.adlparser.antlr.AdlParser;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.aom.terminology.ValueSet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Parser for the terminology section of an archetype
 *
 * Created by pieter.bos on 19/10/15.
 */
public class TerminologyParser {

    private final ADLParserErrors errors;

    public TerminologyParser(ADLParserErrors errors) {
        this.errors = errors;
    }

    public ArchetypeTerminology parseTerminology(AdlParser.Terminology_sectionContext terminologySectionContext) {
        AdlParser.Odin_textContext odinText = terminologySectionContext.odin_text();
        ArchetypeTerminology terminology = new ArchetypeTerminology();
        if(odinText != null) {
            AdlParser.Attr_valsContext values = odinText.attr_vals();
            if (values != null) {
                for (AdlParser.Attr_valContext value : values.attr_val()) {
                    switch (value.attribute_id().getText()) {
                        case "term_definitions":
                        case "term_definition":
                            terminology.setTermDefinitions(parseOdinMap(value.attribute_id().getText(), value.object_block(), TerminologyParser::parseOdinArchetypeTerm));
                            break;
                        case "term_bindings":
                        case "term_binding":
                            terminology.setTermBindings(parseOdinMap(value.attribute_id().getText(), value.object_block(), TerminologyParser::parseOdinUri));

                            break;
                        case "terminology_extracts":
                        case "terminology_extract":
                            terminology.setTerminologyExtracts(parseOdinMap(value.attribute_id().getText(), value.object_block(), TerminologyParser::parseOdinArchetypeTerm));
                            break;
                        case "value_sets":
                        case "value_set":
                            terminology.setValueSets(parseOdinValueSets(value.object_block()));
                            break;
                        default:
                            errors.addWarning(String.format("Unknown section found in archetype terminology: %s", value.attribute_id().getText()));

                    }
                }
            }
            if (odinText.object_value_block() != null) {
                //i don't think this is allowed here?
            }
        }
        return terminology;

    }

    private Map<String, ValueSet> parseOdinValueSets(AdlParser.Object_blockContext context) {
        //TODO: a proper odin parser would be nice. Jackson offers the possibility of relatively easily implementing JAXB-style data binding, See for example the CSV plugin
        Map<String, ValueSet> valueSets = new ConcurrentHashMap<>();
        AdlParser.Object_value_blockContext test = context.object_value_block();
        List<AdlParser.Keyed_objectContext> keyedContext = test.keyed_object();
        if(keyedContext == null) {
            errors.addWarning("in ArchetypeTerminology, Value set found, but no definition");
        }
        for(AdlParser.Keyed_objectContext innerContext:keyedContext) {
            String valueSetId = OdinValueParser.parseOdinStringValue(innerContext.primitive_value().string_value());
            AdlParser.Object_value_blockContext valueSetContext = innerContext.object_block().object_value_block();
            List<AdlParser.Keyed_objectContext> valueSetProperties = valueSetContext.keyed_object();
            ValueSet valueSet = new ValueSet();
            for(AdlParser.Keyed_objectContext property:valueSetProperties) {
                switch(property.primitive_value().string_value().getText()) {
                    case "id":
                        valueSet.setId(property.object_block().object_value_block().primitive_object().primitive_value().string_value().getText());
                        break;
                    case "members":
                        AdlParser.Primitive_objectContext membersContext = property.object_block().object_value_block().primitive_object();
                        valueSet.setMembers(OdinValueParser.parseListOfStrings(membersContext));
                }

            }
            valueSets.put(valueSetId, valueSet);

        }
        return valueSets;
    }

    private <T> Map<String, Map<String, T>> parseOdinMap(String attributeName, AdlParser.Object_blockContext context, BiFunction<Map<String, T>, AdlParser.Keyed_objectContext, Void> parseInner) {
        //TODO: a proper odin parser would be nice. Jackson offers the possibility of relatively easily implementing JAXB-style data binding, See for example the CSV plugin
        Map<String, Map<String, T>> terminology = new ConcurrentHashMap<>();
        AdlParser.Object_value_blockContext test = context.object_value_block();
        List<AdlParser.Keyed_objectContext> keyedContext = test.keyed_object();
        if(keyedContext == null) {
            errors.addWarning("In ArchetypeTerminology, empty " + attributeName + " found");
        }
        for(AdlParser.Keyed_objectContext languageContext:keyedContext) {
            String language = OdinValueParser.parseOdinStringValue(languageContext.primitive_value().string_value());
            Map<String, T> translations = new ConcurrentHashMap<>();
            terminology.put(language, translations);

            AdlParser.Object_blockContext blockContext = languageContext.object_block();

            AdlParser.Object_value_blockContext termsContext = blockContext.object_value_block();
            for(AdlParser.Keyed_objectContext termCodeContext:termsContext.keyed_object()) {
                parseInner.apply(translations, termCodeContext);

            }
        }
        return terminology;
    }

    private static Void parseOdinArchetypeTerm(Map<String, ArchetypeTerm> map, AdlParser.Keyed_objectContext context) {
        String termCode = OdinValueParser.parseOdinStringValue(context.primitive_value().string_value());
        List<AdlParser.Attr_valContext> attr_valContexts = context.object_block().object_value_block().attr_vals().attr_val();
        ArchetypeTerm archetypeTerm = new ArchetypeTerm();
        archetypeTerm.setCode(termCode);
        map.put(termCode, archetypeTerm);
        for(AdlParser.Attr_valContext value:attr_valContexts) {
            String attribute = value.attribute_id().getText();
            String stringValue = OdinValueParser.parseOdinStringValue(value.object_block().object_value_block().primitive_object().primitive_value().string_value());
            archetypeTerm.put(attribute, stringValue);
        }
        return null;
    }

    private static Void parseOdinUri(Map<String, URI> map, AdlParser.Keyed_objectContext context) {
        String termCode = OdinValueParser.parseOdinStringValue(context.primitive_value().string_value());
        try {
            URI uri = OdinValueParser.parseOdinUri(context.object_block().object_value_block().primitive_object().primitive_value().uri_value());
            if(uri != null) {
                map.put(termCode, uri);
            }
        } catch (URISyntaxException e) {
            //todo: log exception
            e.printStackTrace();
        }

        return null;
    }
}