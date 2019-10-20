package com.nedap.archie.serializer.adl;

import com.nedap.archie.adl14.ADL14ConversionConfiguration;
import com.nedap.archie.adl14.ADL14Parser;
import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.adlparser.antlr.AdlParser;
import com.nedap.archie.aom.*;
import com.nedap.archie.query.AOMPathQuery;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.referencemodels.BuiltinReferenceModels;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ADL14DefinitionSerializerTest {
    private static Archetype archetypePrimitives;
    private static ADL14ConversionConfiguration conversionConfiguration ;
    private static ADL14Parser parser ;

    @BeforeClass
    public static void setupClass() throws IOException {
        conversionConfiguration = new ADL14ConversionConfiguration();
        parser = new ADL14Parser(BuiltinReferenceModels.getMetaModels());
        archetypePrimitives = load14("adl14/entry/evaluation/openEHR-EHR-EVALUATION.test.v0.adl");
    }

    private static Archetype load14(String resourceName) throws IOException {

        return parser.parse(ADLArchetypeSerializer.class.getClassLoader().getResourceAsStream(resourceName), conversionConfiguration);

    }

    @Test
    public void testLoadTemperature() throws IOException {
        Archetype a = load14("adl14/entry/observation/openEHR-EHR-OBSERVATION.body_temperature.v2.adl");
        CAttribute c = find(a, "/data/events[at0003]/data[at0001]/items[at0063]/value");

        String serialized = serializeConstraint(c.getChildren().get(0));
        System.out.println(serialized);
        Assert.assertNotNull(serialized);

    }


    @Test
    public void testLoadBloodPressure() throws IOException {
        ADLParser n = new ADLParser();

        Archetype t =  n.parse(ADLArchetypeSerializer.class.getClassLoader().getResourceAsStream("ckm-mirror/local/archetypes/entry/observation/openEHR-EHR-OBSERVATION.body_temperature.v2.0.0.adls"));
        System.out.println(t);
    }

    @Test
    public void testIsLoaded() {
        String path = "/data/items[at0002]/value";
        //String facit = "DV_TEXT matches {*}";
        String facit = "\n" +
                "    DV_TEXT ";
        assertPrimitive(path, facit);

    }

    @Test
    public void testBoolean() {
        String path = "/data/items[at0003]/value";
        String facit = "\n"+
                "    DV_BOOLEAN matches {\n" +
                "        value matches {True, False}\n" +
                "    }";
        assertPrimitive(path, facit);

    }



    @Test
    public void testQuantity(){
        String path = "/data/items[at0005]/items[at0006]/value";
        String facit ="\n" +
                "    DV_QUANTITY matches {\n" +
                "        property matches {[[openehr::119]]}\n" +
                "        units matches {\"gm/100ml\"}\n" +
                "    }";
        assertPrimitive(path,facit);
    }

    @Test
    public void testElementWithQuantity(){
        String facit = "ELEMENT[at0006] occurrences matches {0..1} matches {\t-- Quantity data element\n" +
                "    value matches {\n" +
                "        C_DV_QUANTITY <\n" +
                "            property = <[openehr::119]>\n" +
                "            list = <\n" +
                "                [\"1\"] = <\n" +
                "                    units = <\"gm/100ml\">\n" +
                "                >\n" +
                "            >\n" +
                "        >\n" +
                "    }\n" +
                "}";



    }

    private CAttribute find(Archetype a,String path) {
        AOMPathQuery query = new AOMPathQuery(path);
        return query.find(a.getDefinition());

    }
    private CAttribute find(String path) {
        AOMPathQuery query = new AOMPathQuery(path);
        return query.find(archetypePrimitives.getDefinition());

    }


    private void assertPrimitive(String path, String expected) {
        CAttribute cons = (CAttribute) find(path);
        CObject c = cons.getChildren().get(0);
        String actual = serializeConstraint(c);
        assertThat(actual, equalTo(expected));
        //assertThat(strip(actual), equalTo(strip(expected)));
    }


    private String serializeConstraint(CObject cons) {
        ADL14DefinitionSerializer serializer = new ADL14DefinitionSerializer(new ADLStringBuilder());
        serializer.appendCObject(cons);
        return serializer.getBuilder().toString();
    }
    private String strip(String a){
        return a.trim().replaceAll("\\s+", " ").replaceAll("\\n+", "").trim();
    }

}
