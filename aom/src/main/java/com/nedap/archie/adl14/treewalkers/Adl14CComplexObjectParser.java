package com.nedap.archie.adl14.treewalkers;

import com.nedap.archie.adlparser.antlr.Adl14Parser.*;
import com.nedap.archie.adlparser.treewalkers.BaseTreeWalker;
import com.nedap.archie.antlr.errors.ANTLRParserErrors;
import com.nedap.archie.aom.*;
import com.nedap.archie.base.Cardinality;
import com.nedap.archie.base.MultiplicityInterval;
import com.nedap.archie.rules.Assertion;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser for the definition part of an archetype
 *
 * Created by pieter.bos on 15/10/15.
 */
public class Adl14CComplexObjectParser extends BaseTreeWalker {

    private final Adl14PrimitivesConstraintParser primitivesConstraintParser;

    public Adl14CComplexObjectParser(ANTLRParserErrors errors) {
        super(errors);
        primitivesConstraintParser = new Adl14PrimitivesConstraintParser(errors);
    }

    public RulesSection parseRules(Rules_sectionContext context) {
        RulesSection result = new RulesSection();

        result.setContent(context.getText());
        Adl14RulesParser rulesParser = new Adl14RulesParser(getErrors());
        for(AssertionContext assertion:context.assertion_list().assertion()) {
            result.addRule(rulesParser.parse(assertion));
        }

        return result;
    }

    public CComplexObject parseComplexObject(C_complex_objectContext context) {
        CComplexObject object = new CComplexObject();
        if(context.type_id() != null) {
            object.setRmTypeName(context.type_id().getText());
        }
        if(context.atTypeId() != null) {
            object.setNodeId(context.atTypeId().getText());
        }
        //TODO: object.setDeprecated(context.) ?;
        if (context.c_occurrences() != null) {
            object.setOccurrences(parseMultiplicityInterval(context.c_occurrences()));
        }
        for (C_attribute_defContext attribute : context.c_attribute_def()) {
            parseCAttribute(object, attribute);
        }
        return object;
    }

    private void parseCAttribute(CComplexObject parent, C_attribute_defContext attributeDefContext) {

        if (attributeDefContext.c_attribute() != null) {
            CAttribute attribute = new CAttribute();
            C_attributeContext attributeContext = attributeDefContext.c_attribute();
            if(attributeContext.attribute_id() != null) {
                attribute.setRmAttributeName(attributeContext.attribute_id().getText());
            } else {
                attribute.setDifferentialPath(attributeContext.ADL_PATH().getText());

                attribute.setRmAttributeName(getLastAttributeFromPath(attribute.getDifferentialPath()));
            }
            if (attributeContext.c_existence() != null) {
                attribute.setExistence(parseMultiplicityInterval(attributeContext.c_existence()));
            }

            if (attributeContext.c_cardinality() != null) {
                attribute.setCardinality(this.parseCardinalityInterval(attributeContext.c_cardinality()));
            }
            if (attributeContext.c_objects() != null) {
                attribute.setChildren(parseCObjects(attributeContext.c_objects()));
            } else if (attributeContext.CONTAINED_REGEXP() != null) {
                attribute.addChild(primitivesConstraintParser.parseRegex(attributeContext.CONTAINED_REGEXP()));
            }
            parent.addAttribute(attribute);
            if(attribute.getCardinality() != null) {
                attribute.setMultiple(true); //this seems to be the only way to set single or multiple without the RM. Maybe existence as well?
            }
        }
    }

    public static String getFirstAttributeOfPath(String path) {
        return path.substring(0, path.indexOf('/'));
    }

    public static String getPathMinusFirstAttribute(String path) {
        return path.substring(path.indexOf('/'));
    }

    public static String getLastAttributeFromPath(String path) {
       return path.substring(path.lastIndexOf('/')+1);
    }


    private List<CObject> parseCObjects(C_objectsContext objectsContext) {
        ArrayList<CObject> result = new ArrayList<>();

        if (objectsContext.c_primitive_object() != null) {
            if(objectsContext.c_primitive_object().c_ordinal() != null) {
                //TODO
                CAttributeTuple tuple = new CAttributeTuple();
            } else {
                result.add(primitivesConstraintParser.parsePrimitiveObject(objectsContext.c_primitive_object()));
            }
        } else {
            List<C_non_primitive_object_orderedContext> nonPrimitiveObjectOrderedContext = objectsContext.c_non_primitive_object_ordered();
            if (nonPrimitiveObjectOrderedContext != null) {

                for (C_non_primitive_object_orderedContext object : nonPrimitiveObjectOrderedContext) {

                    CObject cobject = parseNonPrimitiveObject(object.c_non_primitive_object());
                    Sibling_orderContext siblingOrderContext = object.sibling_order();
                    if(siblingOrderContext != null) {
                        SiblingOrder siblingOrder = new SiblingOrder();
                        if(siblingOrderContext.SYM_AFTER() != null) {
                            siblingOrder.setBefore(false);
                        } else if (siblingOrderContext.SYM_BEFORE() != null) {
                            siblingOrder.setBefore(true);
                        }
                        siblingOrder.setSiblingNodeId(siblingOrderContext.AT_CODE().getText());
                        cobject.setSiblingOrder(siblingOrder);
                    }

                    result.add(cobject);
                }
            }
        }
        return result;
    }

    private CObject parseNonPrimitiveObject(C_non_primitive_objectContext objectContext) {
        /*
          c_complex_object
        | c_archetype_root
        | c_complex_object_proxy
        | archetype_slot
        */
        if (objectContext.c_complex_object() != null) {
            return parseComplexObject(objectContext.c_complex_object());
        } else if (objectContext.c_archetype_root() != null) {
            return parseArchetypeRoot(objectContext.c_archetype_root());

        } else if (objectContext.c_complex_object_proxy() != null) {
            return parseCComplexObjectProxy(objectContext.c_complex_object_proxy());
        } else if (objectContext.archetype_slot() != null) {
            return parseArchetypeSlot(objectContext.archetype_slot());
        } else if(objectContext.domainSpecificExtension() != null) {
            CComplexObject result = new CComplexObject();
            result.setRmTypeName(objectContext.domainSpecificExtension().type_id().getText()); //TODO!
            return result;
        }
        throw new IllegalArgumentException("unknown non-primitive object: " + objectContext.getText());
    }

    private CComplexObjectProxy parseCComplexObjectProxy(C_complex_object_proxyContext proxyContext) {

        CComplexObjectProxy proxy = new CComplexObjectProxy();
        proxy.setOccurrences(this.parseMultiplicityInterval(proxyContext.c_occurrences()));
        proxy.setTargetPath(proxyContext.adl_path().getText());
        proxy.setRmTypeName(proxyContext.type_id().getText());
        if(proxyContext.AT_CODE() != null) {
            proxy.setNodeId(proxyContext.AT_CODE().getText());
        } //else we have to generate a node id :)
        return proxy;
    }

    private CArchetypeRoot parseArchetypeRoot(C_archetype_rootContext archetypeRootContext) {
        CArchetypeRoot root = new CArchetypeRoot();

        root.setRmTypeName(archetypeRootContext.type_id().getText());
        root.setNodeId(archetypeRootContext.AT_CODE().getText());
        if(archetypeRootContext.archetype_ref() != null) {
            root.setArchetypeRef(archetypeRootContext.archetype_ref().getText());
        }

        root.setOccurrences(this.parseMultiplicityInterval(archetypeRootContext.c_occurrences()));
        for (C_attribute_defContext attributeContext : archetypeRootContext.c_attribute_def()) {
            parseCAttribute(root, attributeContext);
        }
//((Archetype_slotContext) slotContext).start.getInputStream().getText(slotContext.getSourceInterval())
        return root;
    }

    private ArchetypeSlot parseArchetypeSlot(Archetype_slotContext slotContext) {
        ArchetypeSlot slot = new ArchetypeSlot();
        C_archetype_slot_headContext headContext = slotContext.c_archetype_slot_head();
        slot.setNodeId(headContext.c_archetype_slot_id().AT_CODE().getText());
        slot.setRmTypeName(headContext.c_archetype_slot_id().type_id().getText());
        if(headContext.c_archetype_slot_id().SYM_CLOSED() != null) {
            slot.setClosed(true);
        }
        if (headContext.c_occurrences() != null) {
            slot.setOccurrences(parseMultiplicityInterval(headContext.c_occurrences()));
        }
        Adl14RulesParser assertionParser = new Adl14RulesParser(getErrors());
        if (slotContext.c_excludes() != null) {
            for (AssertionContext assertionContext : slotContext.c_excludes().assertion()) {
                slot.getExcludes().add((Assertion) assertionParser.parse(assertionContext));
            }
        }
        if (slotContext.c_includes() != null) {
            for (AssertionContext assertionContext : slotContext.c_includes().assertion()) {
                slot.getIncludes().add((Assertion) assertionParser.parse(assertionContext));
            }
        }
        return slot;
    }


    private Cardinality parseCardinalityInterval(C_cardinalityContext context) {
        Cardinality cardinality = new Cardinality();
        MultiplicityInterval interval = parseMultiplicity(context.cardinality().multiplicity());
        cardinality.setInterval(interval);

        List<Multiplicity_modContext> modContexts = context.cardinality().multiplicity_mod();
        for(Multiplicity_modContext modContext:modContexts) {
            if(modContext.ordering_mod() != null) {
                cardinality.setOrdered(modContext.ordering_mod().SYM_ORDERED() != null);
            } else {
                cardinality.setOrdered(true);//TODO: this should retrieve it from the RM. This now matches the serializer, but both should be fixed!
            }
            if(modContext.unique_mod() != null) {
                cardinality.setUnique(true);
            }

        }
        return cardinality;
    }

    private MultiplicityInterval parseMultiplicityInterval(C_existenceContext existenceContext) {
        MultiplicityInterval interval = new MultiplicityInterval();
        List<TerminalNode> integers = existenceContext.existence().INTEGER();
        if(integers.size() == 1) {
            interval.setLower(Integer.parseInt(integers.get(0).getText()));
            interval.setUpper(interval.getLower());
        } else if (integers.size() == 2) {
            interval.setLower(Integer.parseInt(integers.get(0).getText()));
            interval.setUpper(Integer.parseInt(integers.get(1).getText()));
        }
        return interval;
    }

    private MultiplicityInterval parseMultiplicityInterval(C_occurrencesContext occurrencesContext) {
        if(occurrencesContext == null) {
            return null;
        }

        return parseMultiplicity(occurrencesContext.multiplicity());
    }

    private MultiplicityInterval parseMultiplicity(MultiplicityContext multiplicity) {
        if(multiplicity == null) {
            return null;
        }
        MultiplicityInterval interval = new MultiplicityInterval();
        List<TerminalNode> integers = multiplicity.INTEGER();
        if(multiplicity.SYM_INTERVAL_SEP() != null) {
            if(multiplicity.getText().contains("*")) {
                interval.setLower(Integer.parseInt(integers.get(0).getText()));
                interval.setUpperUnbounded(true);
            } else {
                interval.setLower(Integer.parseInt(integers.get(0).getText()));
                interval.setUpper(Integer.parseInt(integers.get(1).getText()));
            }
        } else {
            //one integer or *
            if(multiplicity.getText().contains("*")) {
                interval.setLowerUnbounded(false);
                interval.setLower(0);
                interval.setUpperUnbounded(true);
            } else {
                interval.setLower(Integer.parseInt(integers.get(0).getText()));
                interval.setUpper(interval.getLower());
            }
        }
        return interval;
    }


}
