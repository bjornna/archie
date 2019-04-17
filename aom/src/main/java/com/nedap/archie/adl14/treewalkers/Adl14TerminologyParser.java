package com.nedap.archie.adl14.treewalkers;

import com.nedap.archie.adl14.aom14.ArchetypeOntology;
import com.nedap.archie.adl14.aom14.ConstraintBindingsList;
import com.nedap.archie.adl14.aom14.TermBindingsList;
import com.nedap.archie.adl14.aom14.TermCodeList;
import com.nedap.archie.adlparser.antlr.Adl14Parser.Terminology_sectionContext;
import com.nedap.archie.adlparser.treewalkers.BaseTreeWalker;
import com.nedap.archie.antlr.errors.ANTLRParserErrors;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.base.terminology.TerminologyCode;
import com.nedap.archie.paths.PathSegment;
import com.nedap.archie.query.APathQuery;
import com.nedap.archie.serializer.odin.OdinObjectParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Parser for the terminology section of an archetype
 *
 * Created by pieter.bos on 19/10/15.
 */
public class Adl14TerminologyParser extends BaseTreeWalker {

    private static final Logger logger = LoggerFactory.getLogger(Adl14TerminologyParser.class);

    public Adl14TerminologyParser(ANTLRParserErrors errors) {
        super(errors);
    }

    public ArchetypeTerminology parseTerminology(Terminology_sectionContext context) {
        ArchetypeOntology ontology = OdinObjectParser.convert(context.odin_text().getText(), ArchetypeOntology.class);
        ArchetypeTerminology terminology = new ArchetypeTerminology();
        for(String language:ontology.getTermDefinitions().keySet()) {
            terminology.getTermDefinitions().put(language, ontology.getTermDefinitions().get(language).getItems());
        }
        convertTermBindings(ontology, terminology);

        convertConstraintBindings(ontology, terminology);
        //terminologies available is deprecated and should not be included - no longer necessary
        return terminology;
    }

    private void convertConstraintDefinitions(ArchetypeOntology ontology, ArchetypeTerminology terminology) {
        if(ontology.getConstraintDefinitions() != null) {
            for(Map.Entry<String, TermCodeList> constraintDefinitions:ontology.getConstraintDefinitions().entrySet()) {
                String language = constraintDefinitions.getKey();
                if(terminology.getTermDefinitions().get(language) == null) {
                    terminology.getTermDefinitions().put(language, new LinkedHashMap<>());
                }
                terminology.getTermDefinitions().get(language).putAll(constraintDefinitions.getValue().getItems());
            }
        }
    }

    private void convertConstraintBindings(ArchetypeOntology ontology, ArchetypeTerminology terminology) {
        if(ontology.getConstraintBindings() != null) {
            for(Map.Entry<String, ConstraintBindingsList> constraintBinding:ontology.getConstraintBindings().entrySet()) {
                Map<String, URI> newBindings = new LinkedHashMap<>();
                ensureTermBindingKeyExists(terminology, constraintBinding.getKey());
                terminology.getTermBindings().get(constraintBinding.getKey()).putAll(new LinkedHashMap<>(constraintBinding.getValue().getItems()));
            }
        }
    }

    private void convertTermBindings(ArchetypeOntology ontology, ArchetypeTerminology terminology) {
        if(ontology.getTermBindings() != null) {
            for(Map.Entry<String, TermBindingsList> termBinding:ontology.getTermBindings().entrySet()) {
                ensureTermBindingKeyExists(terminology, termBinding.getKey());
                Map<String, URI> newBindings = terminology.getTermBindings().get(termBinding.getKey());

                for(Map.Entry<String, TerminologyCode> oldBinding:termBinding.getValue().getItems().entrySet()) {
                    try {
                        URI newBindingValue = convertToUri(oldBinding.getValue());
                        newBindings.put(oldBinding.getKey(), newBindingValue);
                        //So this is an old path, will be converted later
                        //not inside te parser, obviously
                        //URIs need to be converted to even fit into the new model
                    } catch (URISyntaxException e) {
                        //TODO: add to conversion notes/messages/warnings
                        logger.warn("error converting term binding to URI", e);
                    }
                }
            }
        }
    }

    private void ensureTermBindingKeyExists(ArchetypeTerminology terminology, String key) {
        if(!terminology.getTermBindings().containsKey(key)) {
            terminology.getTermBindings().put(key, new LinkedHashMap<>());
        }
    }

    private URI convertToUri(TerminologyCode value) throws URISyntaxException {
        //TODO: this should convert known terminology schemes into their corresponding standard URIs
        if(value.getUri() != null) {
            return value.getUri();
        } else if(value.getTerminologyVersion() != null) {
            return new URI(MessageFormat.format("http://{0}.org/ver/{1}/id/{2}", value.getTerminologyId(), value.getTerminologyVersion(), value.getCodeString()));
        }
        return new URI(MessageFormat.format("http://{0}.org/id/{1}", value.getTerminologyId(), value.getCodeString()));
    }


}
