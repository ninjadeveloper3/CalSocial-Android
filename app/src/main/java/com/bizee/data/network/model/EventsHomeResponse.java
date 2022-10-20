/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.CalSocial.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsHomeResponse {

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<Event> data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Event> getData() {
        return data;
    }

    public void setData(List<Event> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventsHomeResponse)) return false;

        EventsHomeResponse that = (EventsHomeResponse) o;

        if (!statusCode.equals(that.statusCode)) return false;
        if (!message.equals(that.message)) return false;
        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        int result = statusCode.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    public static class Event {

        @Expose
        @SerializedName("id")
        private Long id;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("date_of_event")
        private String dateOfEvent;

        @Expose
        @SerializedName("start_time")
        private String startTime;

        @Expose
        @SerializedName("end_time")
        private String endTime;

        @Expose
        @SerializedName("going_status")
        private String goingStatus;

        @Expose
        @SerializedName("event_type")
        private String eventType;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDateOfEvent() {
            return dateOfEvent;
        }

        public void setDateOfEvent(String dateOfEvent) {
            this.dateOfEvent = dateOfEvent;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getGoingStatus() {
            return goingStatus;
        }

        public void setGoingStatus(String goingStatus) {
            this.goingStatus = goingStatus;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EventsHomeResponse.Event)) return false;

            EventsHomeResponse.Event user = (EventsHomeResponse.Event) o;

            if (!id.equals(user.id)) return false;
            if (!title.equals(user.title)) return false;
            if (!dateOfEvent.equals(user.dateOfEvent)) return false;
            if (!startTime.equals(user.startTime)) return false;
            if (!endTime.equals(user.endTime)) return false;
            if (!goingStatus.equals(user.goingStatus)) return false;
            return eventType.equals(user.eventType);

        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + dateOfEvent.hashCode();
            result = 31 * result + startTime.hashCode();
            result = 31 * result + endTime.hashCode();
            result = 31 * result + goingStatus.hashCode();
            result = 31 * result + eventType.hashCode();

            return result;
        }
    }
}
