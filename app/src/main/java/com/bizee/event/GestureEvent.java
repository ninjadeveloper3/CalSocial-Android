package com.CalSocial.event;

import android.view.MotionEvent;

public class GestureEvent {

    public String gesture;
    public MotionEvent e1;
    public MotionEvent e2;
    public float distanceX;
    public float distanceY;

    public GestureEvent(String gesture, MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        this.gesture = gesture;
        this.e1 = e1;
        this.e2 = e2;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public MotionEvent getE1() {
        return e1;
    }

    public void setE1(MotionEvent e1) {
        this.e1 = e1;
    }

    public MotionEvent getE2() {
        return e2;
    }

    public void setE2(MotionEvent e2) {
        this.e2 = e2;
    }

    public float getDistanceX() {
        return distanceX;
    }

    public void setDistanceX(float distanceX) {
        this.distanceX = distanceX;
    }

    public float getDistanceY() {
        return distanceY;
    }

    public void setDistanceY(float distanceY) {
        this.distanceY = distanceY;
    }
}
