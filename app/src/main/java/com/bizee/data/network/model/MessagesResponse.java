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


public class MessagesResponse {

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<Message> data;

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

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessagesResponse)) return false;

        MessagesResponse that = (MessagesResponse) o;

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

    public static class Message {

        @Expose
        @SerializedName("id")
        private Long id;

        @Expose
        @SerializedName("participants")
        private String[] participants;

        @Expose
        @SerializedName("profile_image_url")
        private String profileImageUrl;

        @Expose
        @SerializedName("message_status")
        private String messageStatus;

        @Expose
        @SerializedName("time")
        private String time;

        @Expose
        @SerializedName("excerpt")
        private String excerpt;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String[] getParticipants() {
            return participants;
        }

        public void setParticipants(String[] participants) {
            this.participants = participants;
        }

        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public void setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
        }

        public String getMessageStatus() {
            return messageStatus;
        }

        public void setMessageStatus(String messageStatus) {
            this.messageStatus = messageStatus;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Message)) return false;

            Message message = (Message) o;

            if (!id.equals(message.id)) return false;
            if (!participants.equals(message.participants)) return false;
            if (!profileImageUrl.equals(message.profileImageUrl)) return false;
            if (!messageStatus.equals(message.messageStatus)) return false;
            if (!time.equals(message.time)) return false;
            return excerpt.equals(message.excerpt);

        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + participants.hashCode();
            result = 31 * result + participants.hashCode();
            result = 31 * result + profileImageUrl.hashCode();
            result = 31 * result + messageStatus.hashCode();
            result = 31 * result + time.hashCode();
            result = 31 * result + excerpt.hashCode();

            return result;
        }
    }
}
