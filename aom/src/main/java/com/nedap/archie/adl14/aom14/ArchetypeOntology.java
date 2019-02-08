package com.nedap.archie.adl14.aom14;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.aom.terminology.ValueSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArchetypeOntology {

    private List<String> terminologiesAvailable;
    //@XmlElement(name="term_definitions")
    private Map<String, TermCodeList> termDefinitions = new ConcurrentHashMap<>();
    private TermCodeList constraintDefinitions;
    private Map<String, TermBindingsList> termBindings = new ConcurrentHashMap<>();


    public List<String> getTerminologiesAvailable() {
        return terminologiesAvailable;
    }

    public void setTerminologiesAvailable(List<String> terminologiesAvailable) {
        this.terminologiesAvailable = terminologiesAvailable;
    }

    public Map<String, TermCodeList> getTermDefinitions() {
        return termDefinitions;
    }

    public void setTermDefinitions(Map<String, TermCodeList> termDefinitions) {
        this.termDefinitions = termDefinitions;
    }

    public TermCodeList getConstraintDefinitions() {
        return constraintDefinitions;
    }

    public void setConstraintDefinitions(TermCodeList constraintDefinitions) {
        this.constraintDefinitions = constraintDefinitions;
    }

    public Map<String, TermBindingsList> getTermBindings() {
        return termBindings;
    }

    public void setTermBindings(Map<String, TermBindingsList> termBindings) {
        this.termBindings = termBindings;
    }
}
