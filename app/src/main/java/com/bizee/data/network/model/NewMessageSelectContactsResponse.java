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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by janisharali on 28/01/17.
 */

public class NewMessageSelectContactsResponse {

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<User> data;

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

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewMessageSelectContactsResponse)) return false;

        NewMessageSelectContactsResponse that = (NewMessageSelectContactsResponse) o;

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

    public static class User {

        @Expose
        @SerializedName("id")
        private Long id;

        @Expose
        @SerializedName("name")
        private String name;

        @Expose
        @SerializedName("members")
        private String[] members;

        @Expose
        @SerializedName("profile_image_url")
        private String profileImageUrl;

        @Expose
        @SerializedName("is_favorite")
        private Boolean isFavorite;

        @Expose
        @SerializedName("type")
        private String type;

        public User(Long id, String name, String[] members, String profileImageUrl, Boolean isFavorite, String type) {

            this.id = id;
            this.name = name;
            this.members = members;
            this.profileImageUrl = profileImageUrl;
            this.isFavorite = isFavorite;
            this.type = type;
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getMembers() {
            return members;
        }

        public void setMembers(String[] members) {
            this.members = members;
        }

        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public void setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
        }

        public Boolean getFavorite() {
            return isFavorite;
        }

        public void setFavorite(Boolean favorite) {
            isFavorite = favorite;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id.equals(user.id) &&
                    name.equals(user.name) &&
                    Arrays.equals(members, user.members) &&
                    profileImageUrl.equals(user.profileImageUrl) &&
                    isFavorite.equals(user.isFavorite) &&
                    type.equals(user.type);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(id, name, profileImageUrl, isFavorite, type);
            result = 31 * result + Arrays.hashCode(members);
            return result;
        }
    }
}
