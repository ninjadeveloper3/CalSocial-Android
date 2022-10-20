package com.CalSocial.data;

public class TemporaryData {
    /**
     * This class is to hold data temporarily only.
     * This class will be used to pass data from one
     * screen to another. We do not need to
     * persist the data contained in this class.
     */

    private String index;
    private String strValue;
    private Integer intValue;
    private Long lnValue;

    public TemporaryData() {
    }

    public TemporaryData(String index, String strValue, Integer intValue, Long lnValue) {
        this.index = index;
        this.strValue = strValue;
        this.intValue = intValue;
        this.lnValue = lnValue;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Long getLnValue() {
        return lnValue;
    }

    public void setLnValue(Long lnValue) {
        this.lnValue = lnValue;
    }
}
