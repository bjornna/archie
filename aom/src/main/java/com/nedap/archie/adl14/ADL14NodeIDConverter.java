package com.nedap.archie.adl14;

import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.ArchetypeSlot;
import com.nedap.archie.aom.CArchetypeRoot;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.aom.CComplexObject;
import com.nedap.archie.aom.CComplexObjectProxy;
import com.nedap.archie.aom.CObject;
import com.nedap.archie.aom.CPrimitiveObject;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.aom.utils.NodeIdUtil;

import java.util.Map;

public class ADL14NodeIDConverter {

    private final Archetype archetype;

    public ADL14NodeIDConverter(Archetype archetype) {
        this.archetype = archetype;
    }

    public void convert() {
        convert(archetype.getDefinition());

        generateMissingNodeIds(archetype.getDefinition());
    }

    private void generateMissingNodeIds(CObject cObject) {
        if(!(cObject instanceof CPrimitiveObject) && cObject.getNodeId() == null) {
            cObject.setNodeId(archetype.generateNextIdCode());
        }
        for(CAttribute attribute:cObject.getAttributes()) {
            generateMissingNodeIds(attribute);
        }
    }

    private void generateMissingNodeIds(CAttribute attribute) {
        for(CObject cObject:attribute.getChildren()) {
            generateMissingNodeIds(cObject);
        }
    }

    private void convert(CObject cObject) {

        if(cObject instanceof CComplexObject) {
            if (cObject instanceof CArchetypeRoot) {

            }
            calculateNewNodeId(cObject);
        } else if (cObject instanceof ArchetypeSlot) {
            calculateNewNodeId(cObject);
        } else if (cObject instanceof CComplexObjectProxy) {
            calculateNewNodeId(cObject);
        }
        for(CAttribute attribute:cObject.getAttributes()) {
            convert(attribute);
        }
    }

    private void calculateNewNodeId(CObject cObject) {
        if(cObject.getNodeId() == null) {
            if(cObject instanceof CComplexObject || cObject instanceof CArchetypeRoot || cObject instanceof ArchetypeSlot) {
                //TODO: generate node id, but only after every code has been converted first!
//                cObject.setNodeId(archetype.generateNextIdCode());
            } else if(cObject instanceof CPrimitiveObject){
               // cObject.setNodeId("id9999");//TODO: there's a constant somewhere for this:)
            }
        } else {
            String oldNodeId = cObject.getNodeId();
            String newNodeId = convertNodeId(oldNodeId);
            cObject.setNodeId(newNodeId);
            updateTerminology(oldNodeId, newNodeId);
        }
    }

    private void updateTerminology(String oldNodeId, String newNodeId) {
        for(String language:archetype.getTerminology().getTermDefinitions().keySet()) {
            Map<String, ArchetypeTerm> terms = archetype.getTerminology().getTermDefinitions().get(language);
            ArchetypeTerm term = terms.get(oldNodeId);
            term.setCode(newNodeId);
            terms.remove(oldNodeId);
            terms.put(newNodeId, term);
        }
    }

    public static String convertNodeId(String oldNodeId) {
        NodeIdUtil nodeIdUtil = new NodeIdUtil(oldNodeId);
        if(nodeIdUtil.getCodes().isEmpty()) {
            return oldNodeId;
        }
        nodeIdUtil.setPrefix("id"); //will automatically strip the leading zeroes due to integer-parsing
        nodeIdUtil.getCodes().set(0, nodeIdUtil.getCodes().get(0) + 1); //increment with 1, old is 0-based
        return nodeIdUtil.toString();
    }

    private void convert(CAttribute attribute) {
        for(CObject object:attribute.getChildren()) {
            convert(object);
        }
    }
}
