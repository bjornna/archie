package com.nedap.archie.serializer.adl14.constraints;

import com.google.common.base.Joiner;
import com.nedap.archie.aom.*;
import com.nedap.archie.base.Cardinality;
import com.nedap.archie.base.Interval;
import com.nedap.archie.serializer.adl.ADLDefinitionSerializer;
import com.nedap.archie.serializer.adl.constraints.CComplexObjectSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.nedap.archie.serializer.adl.ArchetypeSerializeUtils.buildOccurrences;


/**
 * @author Marko Pipan
 */
public class ADL14CComplexObjectSerializer<T extends CComplexObject> extends CComplexObjectSerializer<T> {
    public ADL14CComplexObjectSerializer(ADLDefinitionSerializer serializer) {
        super(serializer);
    }

    @Override
    public void serialize(T cobj) {
        if ("DV_QUANTITY".contentEquals(cobj.getRmTypeName())) {
            serializeDvQuantity(cobj);
        } else {
            serializeGeneric(cobj);
        }

    }

    private void serializeGeneric(T cobj) {

        builder.indent().newline();
        appendSiblingOrder(cobj);
        builder.append(cobj.getRmTypeName());
        if (cobj.getNodeId() != null) {
            builder.append("[").append(cobj.getNodeId()).append("]");
        }
        builder.append(" ");
        appendOccurrences(cobj);
        if (cobj.getAttributes().isEmpty() && cobj.getAttributeTuples().isEmpty()) {
            if ("DV_TEXT".contentEquals(cobj.getRmTypeName())) {
                builder.append("matches {*}");
            }
            if("DV_COUNT".contentEquals(cobj.getRmTypeName())){
                builder.append("matches {*}");
            }
            builder.lineComment(serializer.getTermText(cobj));
        } else {
            builder.append("matches {");
            builder.lineComment(serializer.getTermText(cobj));
            buildAttributesAndTuples(cobj);
            builder.append("}");
        }
        builder.unindent();
    }

    private void serializeDvQuantity(T cobj) {
        builder.indent().newline();
        appendSiblingOrder(cobj);
        builder.append("value matches {").newIndentedLine()
                .append("C_DV_QUANTITY <");
        buildAttributesAndTuplesOfDvQuantity(cobj);


    }


    protected void buildAttributesAndTuplesOfDvQuantity(T cobj) {
        builder.indent();

        cobj.getAttributes().stream().forEach(this::buildAttribute);
        final AtomicInteger group = new AtomicInteger(1);
        final List<String> tupleAttributeNames = getTupleAttributeNames(cobj);

        cobj.getAttributeTuples().forEach(t -> {
            builder.newline()
                    .append("list = <")
                    .indent();

            t.getTuples().forEach(ptuple -> {
                builder.newline()
                        .append("[\"" + group.getAndIncrement() + "\"] = <")
                        .indent();
                for (int i = 0; i < tupleAttributeNames.size(); i++) {
                    builder.newline()
                            .append(tupleAttributeNames.toArray()[i])
                            .append(" = ")
                    ;
                    buildPrimitiveObject(ptuple.getMember(i));

                }
                builder.unindent()
                        .newline()
                        .append(">");
            });
            builder.newUnindentedLine().append(">");


        });
        builder.newUnindentedLine()
                .append(">")
                .newUnindentedLine()
                .append("}");

        /*
        cobj.getAttributes().stream()
                .filter(a -> !tupleAttributes.contains(a.getRmAttributeName()))
                .forEach(this::buildAttribute);

        cobj.getAttributeTuples().forEach(this::buildTuple);
         */

        builder.unindent().newline();
    }

    private void buildPrimitiveObject(CPrimitiveObject member) {

        builder.append("<");
        member.getConstraint().forEach(c -> {
            if (c instanceof String) {
                builder.append("\"" + c + "\"");
            } else if (c instanceof Interval) {
                Interval i = (Interval) c;
                if (i.getLower().equals(i.getUpper())) {
                    builder.append("|").append(i.getLower()).append("|");
                } else {
                    builder.append("|")
                            .append(i.getLower())
                            .append("..")
                            .append(i.isUpperIncluded() ? "<" : "")
                            .append(i.getUpper())
                            .append("|");
                }
            } else {
                builder.append(c);
            }
        });

        builder.append(">");
    }


    private void buildMember(CPrimitiveObject m) {
        builder
                .newline()

                .append(m.getRmTypeName()).append(" = ").append("<");
    }

    private List<String> getTupleAttributeNames(T cobj) {
        return cobj.getAttributeTuples().stream()
                .flatMap(cat -> cat.getMembers().stream())
                .map(CAttribute::getRmAttributeName)
                .collect(Collectors.toList());
    }


    private void buildAttribute(CAttribute cattr) {
        builder.tryNewLine();
        if (cattr.getDifferentialPath() == null) {
            builder.append(cattr.getRmAttributeName());
        } else {
            builder.append(cattr.getDifferentialPath());
        }
        if (cattr.getExistence() != null) {
            builder.append(" existence matches {");
            buildOccurrences(builder, cattr.getExistence());
            builder.append("}");
        }
        if (cattr.getCardinality() != null) {
            builder.append(" cardinality matches {");
            appendCardinality(cattr.getCardinality());
            builder.append("}");
        }
        if (!cattr.getChildren().isEmpty()) {
            buildAttributeChildConstraints(cattr);
        }
    }


    private void buildTuple(CAttributeTuple cAttributeTuple) {
        builder.tryNewLine();
        builder.append("[");
        List<String> members = cAttributeTuple.getMembers().stream()
                .map(CAttribute::getRmAttributeName)
                .collect(Collectors.toList());
        builder.append(Joiner.on(", ").join(members));
        builder.append("] matches {");
        builder.indent();
        for (int i = 0; i < cAttributeTuple.getTuples().size(); i++) {
            CPrimitiveTuple cObjectTuple = cAttributeTuple.getTuples().get(i);
            builder.newline();
            builder.append("[");
            for (int j = 0; j < cObjectTuple.getMembers().size(); j++) {
                builder.append("{");
                serializer.appendCObject(cObjectTuple.getMembers().get(j));
                builder.append("}");
                if (j < cObjectTuple.getMembers().size() - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]");
            if (i < cAttributeTuple.getTuples().size() - 1) {
                builder.append(",");
            }

        }
        builder.unindent().newline();
        builder.append("}");
    }

    private void buildAttributeChildConstraints(CAttribute cattr) {
        List<CObject> children = filterNonEmptyChildren(cattr.getChildren());
        if (children.isEmpty()) {
            return;
        }

        builder.append(" = ");
        boolean indent = !children.isEmpty() &&
                (children.size() > 1 || !(children.get(0) instanceof CPrimitiveObject));
        builder.append("<");
        children.forEach(serializer::appendCObject);

        if (indent) {
            builder.newline();
        }

        builder.append(">");

        if (!indent && !children.isEmpty()) {
            String commentText = serializer.getSimpleCommentText(children.get(0));
            if (commentText != null) {
                builder.lineComment(commentText);
            }
        }
    }

    private List<CObject> filterNonEmptyChildren(List<CObject> children) {
        return children.stream()
                .filter(child -> !serializer.isEmpty(child))
                .collect(Collectors.toList());
    }

    private void appendCardinality(Cardinality card) {
        buildOccurrences(builder, card.getInterval());
        List<String> tags = new ArrayList<>();
        //TODO: this should compare against the RM and only serialize if different, OR we should make ordered nullable
        //and add a preprocessor that removes all default values
        if (!card.isOrdered()) {
            tags.add("unordered");
        }
        //TODO: this should compare against the RM and only serialize if different, OR we should make unique nullable
        //and add a preprocessor that removes all default values
        if (card.isUnique()) {
            tags.add("unique");
        }
        if (!tags.isEmpty()) {
            builder.append("; ").append(Joiner.on("; ").join(tags));
        }
    }


}
