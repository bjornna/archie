package com.nedap.archie.serializer.adl;

import com.nedap.archie.aom.*;
import com.nedap.archie.aom.primitives.*;
import com.nedap.archie.serializer.adl.constraints.*;
import com.nedap.archie.serializer.adl14.constraints.ADL14CComplexObjectSerializer;

import java.util.HashMap;
import java.util.Map;

public class ADL14DefinitionSerializer extends ADLDefinitionSerializer {
    protected final ADLStringBuilder builder;

    private final Map<Class, ConstraintSerializer> constraintSerializers;
    public ADL14DefinitionSerializer(ADLStringBuilder builder) {
        super(builder);
        this.builder = builder;

        constraintSerializers = new HashMap<>();
        constraintSerializers.put(ArchetypeSlot.class, new ArchetypeSlotSerializer(this));
        constraintSerializers.put(CArchetypeRoot.class, new CArchetypeRootSerializer(this));
        constraintSerializers.put(CBoolean.class, new CBooleanSerializer(this));
        constraintSerializers.put(CComplexObject.class, new ADL14CComplexObjectSerializer(this));
        constraintSerializers.put(CComplexObjectProxy.class, new CComplexObjectProxySerializer(this));
        constraintSerializers.put(CDate.class, new CDateSerializer(this));
        constraintSerializers.put(CDateTime.class, new CDateTimeSerializer(this));
        constraintSerializers.put(CDuration.class, new CDurationSerializer(this));
        constraintSerializers.put(CInteger.class, new CIntegerSerializer(this));
        constraintSerializers.put(CReal.class, new CRealSerializer(this));
        constraintSerializers.put(CString.class, new CStringSerializer(this));
        constraintSerializers.put(CTerminologyCode.class, new CTerminologyCodeSerializer(this));
        constraintSerializers.put(CTime.class, new CTimeSerializer(this));
    }

    public static String serialize(CObject cons) {
        final ADLStringBuilder builder = new ADLStringBuilder();
        ADL14DefinitionSerializer serializer = new ADL14DefinitionSerializer(builder);
        serializer.appendCObject(cons);
        return builder.toString();
    }

    public void appendCObject(CObject cobj) {
        ConstraintSerializer<CObject> serializer = getSerializer(cobj);
        if (serializer != null) {
            serializer.serialize(cobj);
        } else {
            throw new AssertionError("Unsupported constraint: " + cobj.getClass().getName());
        }
    }
    private ConstraintSerializer<CObject> getSerializer(CObject cobj) {
        return constraintSerializers.get(cobj.getClass());
    }

}
