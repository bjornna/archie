package com.nedap.archie.adl14.aom14;

import com.nedap.archie.base.terminology.TerminologyCode;

import java.util.Map;

public class CDVQuantity {

    private TerminologyCode property;

    private Map<String, CDVQuantityItem> list;
    //TODO: assumed value, which is a CDVQuantityItem without the intervals. or actual dv_quantity, but not reachable here

    private CDVQuantityAssumedValue assumedValue;

    public TerminologyCode getProperty() {
        return property;
    }

    public void setProperty(TerminologyCode property) {
        this.property = property;
    }

    public Map<String, CDVQuantityItem> getList() {
        return list;
    }

    public void setList(Map<String, CDVQuantityItem> list) {
        this.list = list;
    }

    public CDVQuantityAssumedValue getAssumedValue() {
        return assumedValue;
    }

    public void setAssumedValue(CDVQuantityAssumedValue assumedValue) {
        this.assumedValue = assumedValue;
    }
}
