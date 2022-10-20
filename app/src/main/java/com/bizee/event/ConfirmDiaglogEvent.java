package com.CalSocial.event;

public class ConfirmDiaglogEvent {

    public String actionTaken;
    public static final String POSITIVE_ACTION = "positive";
    public static final String NEGATIVE_ACTION = "negative";

    public ConfirmDiaglogEvent(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }
}
