package com.nedap.archie.serializer.adl;

import com.nedap.archie.aom.*;
import com.nedap.archie.aom.primitives.CInteger;
import com.nedap.archie.aom.primitives.CReal;
import com.nedap.archie.aom.primitives.CString;
import com.nedap.archie.aom.primitives.CTerminologyCode;
import com.nedap.archie.base.Interval;
import org.junit.Assert;
import org.junit.Test;

public class ADL14DefinitionLightweightSerialiserTest {
    private boolean debug = false;
    @Test
    public void testText() {
        CComplexObject c = new CComplexObject();
        c.setRmTypeName("DV_TEXT");
        String expected = "\n    DV_TEXT matches {*}";
        assertPrimitive(c, expected);
    }
    @Test
    public void testCount() {
        CComplexObject c = new CComplexObject();
        c.setRmTypeName("DV_COUNT");
        String facit = "\n    DV_COUNT matches {*}";
        assertPrimitive(c, facit);
    }


    @Test
    public void testQuantityWithTuple() {
        CComplexObject c = new CComplexObject();
        c.setRmTypeName("DV_QUANTITY");

        CAttribute propertyAttribut = new CAttribute();
        propertyAttribut.setRmAttributeName("property");

        CTerminologyCode propertyTerminology = new CTerminologyCode();
        propertyTerminology.addConstraint("openehr::127");
        propertyAttribut.getChildren().add(propertyTerminology);
        c.addAttribute(propertyAttribut);

        CAttribute unitsAttribute = new CAttribute();
        unitsAttribute.setRmAttributeName("units");
        CAttribute magnitudeAttribute = new CAttribute();
        magnitudeAttribute.setRmAttributeName("magnitude");
        CAttribute precisionAttribute = new CAttribute();
        precisionAttribute.setRmAttributeName("precision");


        CPrimitiveTuple celTuple = new CPrimitiveTuple();
        celTuple.addMember(createCString("Cel"));
        celTuple.addMember(createCReal(0.00, 100));
        celTuple.addMember(createCInteger(1, 1));


        CPrimitiveTuple farTuple = new CPrimitiveTuple();
        farTuple.addMember(createCString("[degF]"));
        farTuple.addMember(createCReal(30.0, 200.0));
        farTuple.addMember(createCInteger(1, 1));


        // Adding tuple
        CAttributeTuple tuple = new CAttributeTuple();
        tuple.addMember(unitsAttribute);
        tuple.addMember(magnitudeAttribute);
        tuple.addMember(precisionAttribute);

        tuple.addTuple(celTuple);
        tuple.addTuple(farTuple);

        c.addAttributeTuple(tuple);

        String facit = "\n"+
                "    value matches {\n" +
                "        C_DV_QUANTITY <\n" +
                "            property = <[openehr::127]>\n" +
                "            list = <\n" +
                "                [\"1\"] = <\n" +
                "                    units = <\"Cel\">\n" +
                "                    magnitude = <|0.0..<100.0|>\n" +
                "                    precision = <|1|>\n" +
                "                >\n" +
                "                [\"2\"] = <\n" +
                "                    units = <\"[degF]\">\n" +
                "                    magnitude = <|30.0..<200.0|>\n" +
                "                    precision = <|1|>\n" +
                "                >\n" +
                "            >\n" +
                "        >\n" +
                "    }\n";
        assertPrimitive(c, facit);
    }

    @Test
    public void testQuantity() {
        CComplexObject c = new CComplexObject();
        c.setRmTypeName("DV_QUANTITY");


        // Add properties
        CAttribute propertyAttribut = new CAttribute();
        propertyAttribut.setRmAttributeName("property");
        CTerminologyCode propertyTerminology = new CTerminologyCode();
        propertyTerminology.addConstraint("openehr::127");
        propertyAttribut.getChildren().add(propertyTerminology);
        c.addAttribute(propertyAttribut);

        // Add units
        CAttribute unitsAttribute = new CAttribute();
        unitsAttribute.setRmAttributeName("units");
        unitsAttribute.addChild(createCString("Cel"));

        c.addAttribute(unitsAttribute);

        // Add magntiute
        CAttribute magnitudeAttribute = new CAttribute();
        magnitudeAttribute.setRmAttributeName("magnitude");
        magnitudeAttribute.addChild(createCReal(0.0, 100.0));

        c.addAttribute(magnitudeAttribute);

        CAttribute precisionAttribute = new CAttribute();
        precisionAttribute.setRmAttributeName("precision");
        precisionAttribute.addChild(createCInteger(1, 1));
        c.addAttribute(precisionAttribute);

        String result = print(c);
    }

    private void assertPrimitive(CObject o, String expected) {
        Assert.assertEquals(expected, print(o));
    }

    private String print(CObject c) {
        String s = ADL14DefinitionSerializer.serialize(c);
        if(debug) {
            System.out.println("--- START  -----");
            System.out.println(s);
            System.out.println("---  END   -----");
        }
        return s;
    }


    private CReal createCReal(double lower, double upper) {
        CReal r = new CReal();
        Interval<Double> i = new Interval<>(lower, upper);
        i.setUpperUnbounded(false);
        i.setLowerUnbounded(false);
        i.setLowerIncluded(true);
        i.setUpperIncluded(true);
        r.addConstraint(i);
        return r;
    }

    private CString createCString(String constraint) {
        CString s = new CString();
        s.addConstraint(constraint);
        return s;
    }

    private CInteger createCInteger(int upper, int lower) {
        return createCInteger(new Long(upper), new Long(lower));
    }

    private CInteger createCInteger(Long upper, Long lower) {
        CInteger i = new CInteger();
        Interval<Long> l = new Interval<>(upper, lower);
        l.setUpperIncluded(true);
        l.setLowerIncluded(true);
        l.setLowerUnbounded(false);
        l.setUpperUnbounded(false);
        i.addConstraint(l);
        return i;
    }

}
