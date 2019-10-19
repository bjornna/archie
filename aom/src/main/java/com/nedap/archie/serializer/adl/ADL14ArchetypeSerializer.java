package com.nedap.archie.serializer.adl;


import com.nedap.archie.aom.AuthoredArchetype;
import com.nedap.archie.aom.terminology.ArchetypeTerm;

import java.util.Map;

public class ADL14ArchetypeSerializer extends ADLAuthoredArchetypeSerializer {
    private final ADL14DefinitionSerializer definitionSerializer;
    public ADL14ArchetypeSerializer(AuthoredArchetype archetype) {
        super(archetype);
        this.definitionSerializer = new ADL14DefinitionSerializer(builder);
    }
    @Override
    protected void appendDefinition() {
        if (archetype.getDefinition() == null) return;

        builder.newline().append("definition");
        definitionSerializer.appendCObject(archetype.getDefinition());
        builder.tryNewLine();
    }
    @Override
    protected void appendTerminology() {
        if (archetype.getTerminology() == null) return;
        builder.newline().append("ontology").newIndentedLine()
                .append("term_definitions = <").newIndentedLine();
        Map<String, Map<String, ArchetypeTerm>> terms = archetype.getTerminology().getTermDefinitions();
        for (String lang : terms.keySet()) {
            builder.append("[\"" + lang + "\"] = <").newIndentedLine();
            builder.append("items = <").newIndentedLine();
            Map<String, ArchetypeTerm> aterms = terms.get(lang);
            for (String nodeid : aterms.keySet()) {
                builder.append("[\"" + nodeid + "\"] = <").newIndentedLine()
                        .append("text = <\"" + aterms.get(nodeid).getText() + "\">")
                        .newline()
                        .append("description = <\"" + aterms.get(nodeid).getDescription() + "\">")
                        .newUnindentedLine()

                        .append(">")
                        .newline()
                //        .newUnindentedLine()
                ;
            }
            builder
                    .unindent()
                    .append(">")
            .newUnindentedLine().append(">")
            //        .newUnindentedLine()
            ;
        }

        builder.newUnindentedLine()
                .append(">")
        //        .newUnindentedLine()
        ;

    }

    @Override
    protected void appendLanguage() {
        builder.newline().append("concept").newIndentedLine()
                .append("[at0000]")
                .unindent();
        super.appendLanguage();

    }
}
