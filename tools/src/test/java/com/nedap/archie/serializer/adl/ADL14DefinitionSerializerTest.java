package com.nedap.archie.serializer.adl;

import com.nedap.archie.adl14.ADL14ConversionConfiguration;
import com.nedap.archie.adl14.ADL14Parser;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.CObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.referencemodels.BuiltinReferenceModels;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;

public class ADL14DefinitionSerializerTest {
    private static Archetype archetypePrimitives;
    @BeforeClass
    public static void setupClass() throws IOException {
        archetypePrimitives = load14("adl14/entry/evaluation/openEHR-EHR-EVALUATION.test.v0.adl");
    }

    private  static Archetype load14(String resourceName) throws IOException {
        ADL14ConversionConfiguration conversionConfiguration = new ADL14ConversionConfiguration();
        ADL14Parser parser = new ADL14Parser(BuiltinReferenceModels.getMetaModels());
        return parser.parse(ADLArchetypeSerializer.class.getClassLoader().getResourceAsStream(resourceName), conversionConfiguration);

    }
    @Test
    public void testIsLoaded(){
        Assert.assertNotNull(archetypePrimitives);
    }
    private void assertPrimitive(String attributeName, String expected) {
        CObject cons = archetypePrimitives.getDefinition().getAttribute(attributeName).getChildren().get(0);

        String actual = serializeConstraint(cons);

        assertThat(actual, equalTo(expected));
    }

    private String serializeConstraint(CObject cons) {
        ADL14DefinitionSerializer serializer = new ADL14DefinitionSerializer(new ADLStringBuilder());
        serializer.appendCObject(cons);
        return serializer.getBuilder().toString();
    }


}
