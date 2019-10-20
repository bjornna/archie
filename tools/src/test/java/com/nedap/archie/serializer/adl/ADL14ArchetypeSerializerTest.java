package com.nedap.archie.serializer.adl;
import com.nedap.archie.adl14.ADL14ConversionConfiguration;
import com.nedap.archie.adl14.ADL14Parser;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.CAttribute;
import com.nedap.archie.query.AOMPathQuery;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.referencemodels.BuiltinReferenceModels;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ADL14ArchetypeSerializerTest {

    private static ADL14ConversionConfiguration conversionConfiguration ;
    private static ADL14Parser parser ;

    @BeforeClass
    public static void setupClass() {
        conversionConfiguration = new ADL14ConversionConfiguration();
        parser = new ADL14Parser(BuiltinReferenceModels.getMetaModels());
    }

    @Test
    public void serialize14() throws IOException {
        Archetype archetype  = load14("adl14/entry/evaluation/openEHR-EHR-EVALUATION.test.v0.adl");
        String serialized = ADLArchetypeSerializer.serialize(archetype);
        assertThat(serialized, containsString("concept"));
    }
    private CAttribute find(Archetype a, String path) {
        AOMPathQuery query = new AOMPathQuery(path);
        return query.find(a.getDefinition());

    }

    private Archetype load14(String resourceName) throws IOException {
        return parser.parse(ADLArchetypeSerializer.class.getClassLoader().getResourceAsStream(resourceName), conversionConfiguration);

    }
}
