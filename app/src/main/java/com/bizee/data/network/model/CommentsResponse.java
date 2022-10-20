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


public class CommentsResponse {

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<Comment> data;

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

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentsResponse)) return false;

        CommentsResponse that = (CommentsResponse) o;

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

    public static class Comment {

        @Expose
        @SerializedName("id")
        private Long id;

        @Expose
        @SerializedName("event_id")
        private Long eventId;

        @Expose
        @SerializedName("user_id")
        private Long userId;

        @Expose
        @SerializedName("message")
        private String message;

        @Expose
        @SerializedName("date_of_message")
        private String dateOfMessage;

        @Expose
        @SerializedName("time_of_message")
        private String timeOfMessage;

        @Expose
        @SerializedName("message_type")
        private String messageType;

        @Expose
        @SerializedName("picture_image_url")
        private String pictureImageUrl;

        @Expose
        @SerializedName("profile_image_url")
        private String profileImageUrl;

        @Expose
        @SerializedName("commenter_name")
        private String commenterName;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getEventId() {
            return eventId;
        }

        public void setEventId(Long eventId) {
            this.eventId = eventId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDateOfMessage() {
            return dateOfMessage;
        }

        public void setDateOfMessage(String dateOfMessage) {
            this.dateOfMessage = dateOfMessage;
        }

        public String getTimeOfMessage() {
            return timeOfMessage;
        }

        public void setTimeOfMessage(String timeOfMessage) {
            this.timeOfMessage = timeOfMessage;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getPictureImageUrl() {
            return pictureImageUrl;
        }

        public void setPictureImageUrl(String pictureImageUrl) {
            this.pictureImageUrl = pictureImageUrl;
        }

        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public void setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
        }

        public String getCommenterName() {
            return commenterName;
        }

        public void setCommenterName(String commenterName) {
            this.commenterName = commenterName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CommentsResponse.Comment)) return false;

            CommentsResponse.Comment comment = (CommentsResponse.Comment) o;

            if (!id.equals(comment.id)) return false;
            if (!eventId.equals(comment.eventId)) return false;
            if (!userId.equals(comment.userId)) return false;
            if (!message.equals(comment.message)) return false;
            if (!dateOfMessage.equals(comment.dateOfMessage)) return false;
            if (!timeOfMessage.equals(comment.timeOfMessage)) return false;
            if (!messageType.equals(comment.messageType)) return false;
            if (!pictureImageUrl.equals(comment.pictureImageUrl)) return false;
            if (!profileImageUrl.equals(comment.profileImageUrl)) return false;
            return commenterName.equals(comment.commenterName);

        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + eventId.hashCode();
            result = 31 * result + userId.hashCode();
            result = 31 * result + message.hashCode();
            result = 31 * result + dateOfMessage.hashCode();
            result = 31 * result + timeOfMessage.hashCode();
            result = 31 * result + messageType.hashCode();
            result = 31 * result + profileImageUrl.hashCode();
            result = 31 * result + pictureImageUrl.hashCode();
            result = 31 * result + commenterName.hashCode();

            return result;
        }
    }
}
