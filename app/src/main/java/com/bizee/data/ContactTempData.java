package com.CalSocial.data;

public class ContactTempData {
    /**
     * This class is to hold data temporarily only.
     * This class will be used to pass data from one
     * screen to another. We do not need to
     * persist the data contained in this class.
     */

    private String name;
    private String phoneNumber;

    public ContactTempData() {
    }

    public ContactTempData(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
