package com.nedap.archie.adl14.treewalkers;

import com.nedap.archie.adl14.aom14.ArchetypeOntology;
import com.nedap.archie.adlparser.antlr.Adl14Parser.Terminology_sectionContext;
import com.nedap.archie.adlparser.treewalkers.BaseTreeWalker;
import com.nedap.archie.antlr.errors.ANTLRParserErrors;
import com.nedap.archie.aom.terminology.ArchetypeTerminology;
import com.nedap.archie.serializer.odin.OdinObjectParser;

/**
 * Parser for the terminology section of an archetype
 *
 * Created by pieter.bos on 19/10/15.
 */
public class Adl14TerminologyParser extends BaseTreeWalker {

    public Adl14TerminologyParser(ANTLRParserErrors errors) {
        super(errors);
    }

    public ArchetypeTerminology parseTerminology(Terminology_sectionContext context) {
        ArchetypeOntology terminology = OdinObjectParser.convert(context.odin_text().getText(), ArchetypeOntology.class);
        return new ArchetypeTerminology();//TODO
    }

}
