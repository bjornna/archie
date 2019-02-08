package com.nedap.archie.adl14.treewalkers;

import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nedap.archie.adlparser.antlr.Adl14BaseListener;
import com.nedap.archie.adlparser.antlr.Adl14Parser.*;
import com.nedap.archie.antlr.errors.ANTLRParserErrors;
import com.nedap.archie.aom.*;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.serializer.odin.OdinObjectParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ANTLR listener for an ADLS file. Uses the listener construction for the topmost elements, switches to custom treewalker
 * for elements lower in the tree. This approach saves some code and complexity.
 *
 * Created by pieter.bos on 19/10/15.
 */
public class ADL14Listener extends Adl14BaseListener {

    private ANTLRParserErrors errors;

    private Archetype rootArchetype;

    private Archetype archetype;
    private Adl14CComplexObjectParser subTreeWalker;
    private Adl14TerminologyParser terminologyParser;

    public ADL14Listener(ANTLRParserErrors errors) {
        this.errors = errors;
        subTreeWalker = new Adl14CComplexObjectParser(errors);
        terminologyParser = new Adl14TerminologyParser(errors);
    }

    /** top-level constructs */
    @Override
    public void enterArchetype(ArchetypeContext ctx) {
        rootArchetype = new AuthoredArchetype();
        archetype = rootArchetype;
        parseArchetypeHRID(ctx.ARCHETYPE_HRID());
    }

    @Override
    public void exitArchetype(ArchetypeContext ctx) {
        rootArchetype.setDifferential(true); //TODO: not possible to check from the content of the archetype without spec change
    }

    private void parseArchetypeHRID(TerminalNode hrId) {
        if(hrId != null) {
            ArchetypeHRID archetypeID = new ArchetypeHRID(hrId.getText());
            archetype.setArchetypeId(archetypeID);
        }
    }

    public void enterMeta_data_item(Meta_data_itemContext ctx) {
        /*
         SYM_ADL_VERSION '=' VERSION_ID
        | SYM_UID '=' GUID
        | SYM_BUILD_UID '=' GUID
        | SYM_RM_RELEASE '=' VERSION_ID
        | SYM_IS_CONTROLLED
        | SYM_IS_GENERATED
        | identifier ( '=' meta_data_value )?

         */
        if(archetype instanceof AuthoredArchetype) {
            AuthoredArchetype authoredArchetype = (AuthoredArchetype) archetype;

            if(ctx.meta_data_tag_adl_version() != null) {
                if(ctx.VERSION_ID() != null) {
                    authoredArchetype.setAdlVersion(ctx.VERSION_ID().getText());
                } else if (ctx.REAL() != null) {
                    authoredArchetype.setAdlVersion(ctx.REAL().getText());
                }
            }
            if(ctx.meta_data_tag_build_uid() != null) {
                authoredArchetype.setBuildUid(ctx.GUID().getText());
            }
            if(ctx.meta_data_tag_rm_release() != null) {
                authoredArchetype.setRmRelease(ctx.VERSION_ID().getText());
            }
            if(ctx.meta_data_tag_is_controlled() != null) {
                authoredArchetype.setControlled(true);
            }
            if(ctx.meta_data_tag_is_generated() != null) {
                authoredArchetype.setGenerated(true);
            }
            if(ctx.meta_data_tag_uid() != null) {
                authoredArchetype.setUid(ctx.GUID().getText());
            }
            else if(ctx.identifier() != null) {
                authoredArchetype.addOtherMetadata(ctx.identifier().getText(), ctx.meta_data_value() == null ? null : ctx.meta_data_value().getText());
            }
        }

    }

    /**
     * one level below: definition, language, etc.
     */
    @Override
    public void enterDefinition_section(Definition_sectionContext ctx) {
        CComplexObject definition = subTreeWalker.parseComplexObject(ctx.c_complex_object());
        archetype.setDefinition(definition);
    }

    @Override
    public void enterLanguage_section(Language_sectionContext ctx) {
        archetype.setAuthoredResourceContent(OdinObjectParser.convert(ctx.odin_text().getText(), LanguageSection.class));
    }

    @Override
    public void enterTerminology_section(Terminology_sectionContext ctx) {
        archetype.setTerminology(terminologyParser.parseTerminology(ctx));
    }

    @Override
    public void enterDescription_section(Description_sectionContext ctx) {
        archetype.setDescription(OdinObjectParser.convert(ctx.odin_text().getText(), ResourceDescription.class));
    }

    @Override
    public void enterSpecialization_section(Specialization_sectionContext ctx) {
        if(ctx != null && ctx.archetype_ref() != null) {
            archetype.setParentArchetypeId(ctx.archetype_ref().getText());
        }
    }

    public void enterRules_section(Rules_sectionContext ctx) {
        archetype.setRules(subTreeWalker.parseRules(ctx));
    }

    /* getters for result */
    public Archetype getArchetype() {
        return rootArchetype;
    }

    public ANTLRParserErrors getErrors() {
        return errors;
    }


}
