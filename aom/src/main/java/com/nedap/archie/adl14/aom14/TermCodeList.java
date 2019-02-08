package com.nedap.archie.adl14.aom14;

import com.nedap.archie.aom.terminology.ArchetypeTerm;
import com.nedap.archie.base.terminology.TerminologyCode;

import java.util.List;
import java.util.Map;

public class TermCodeList {

    private Map<String, ArchetypeTerm> items;

    public Map<String, ArchetypeTerm> getItems() {
        return items;
    }

    public void setItems(Map<String, ArchetypeTerm> items) {
        this.items = items;
    }
}
