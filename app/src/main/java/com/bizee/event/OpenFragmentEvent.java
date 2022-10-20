package com.CalSocial.event;

public class OpenFragmentEvent {

    public String sourceFragment;
    public String targetFragment;

    public OpenFragmentEvent(String sourceFragment, String targetFragment) {
        this.sourceFragment = sourceFragment;
        this.targetFragment = targetFragment;
    }

    public String getSourceFragment() {
        return sourceFragment;
    }

    public void setSourceFragment(String sourceFragment) {
        this.sourceFragment = sourceFragment;
    }

    public String getTargetFragment() {
        return targetFragment;
    }

    public void setTargetFragment(String targetFragment) {
        this.targetFragment = targetFragment;
    }
}
