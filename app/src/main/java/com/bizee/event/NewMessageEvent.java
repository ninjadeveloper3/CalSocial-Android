package com.CalSocial.event;

public class NewMessageEvent {

    public String message;
    public String source;

    public NewMessageEvent(String source, String message) {
        this.source = source;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
