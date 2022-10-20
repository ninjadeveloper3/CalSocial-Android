package com.CalSocial.data;

public class CalendarEventTempData {
    /**
     * This class is to hold data temporarily only.
     * This class will be used to pass data from one
     * screen to another. We do not need to
     * persist the data contained in this class.
     */

    private String title;
    private String location;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String duration;

    public CalendarEventTempData() {
    }

    public CalendarEventTempData(String title, String location, String startDate, String endDate, String startTime, String endTime, String duration) {
        this.title = title;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
