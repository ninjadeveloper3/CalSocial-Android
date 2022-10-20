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

/**
 * Created by janisharali on 28/01/17.
 */

public class ContactsSwarmResponse {

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<Swarm> data;

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

    public List<Swarm> getData() {
        return data;
    }

    public void setData(List<Swarm> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactsSwarmResponse)) return false;

        ContactsSwarmResponse that = (ContactsSwarmResponse) o;

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

    public static class Swarm {

        @Expose
        @SerializedName("id")
        private Long id;

        @Expose
        @SerializedName("swarm_name")
        private String swarmName;

        @Expose
        @SerializedName("members")
        private String[] members;

        @Expose
        @SerializedName("profile_image_url")
        private String profileImageUrl;

        @Expose
        @SerializedName("is_favorite")
        private Boolean isFavorite;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSwarmName() {
            return swarmName;
        }

        public void setSwarmName(String swarmName) {
            this.swarmName = swarmName;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Swarm)) return false;

            Swarm swarm = (Swarm) o;

            if (!id.equals(swarm.id)) return false;
            if (!swarmName.equals(swarm.swarmName)) return false;
            if (!members.equals(swarm.members)) return false;
            if (!profileImageUrl.equals(swarm.profileImageUrl)) return false;
            return isFavorite.equals(swarm.isFavorite);

        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + swarmName.hashCode();
            result = 31 * result + members.hashCode();
            result = 31 * result + profileImageUrl.hashCode();
            result = 31 * result + isFavorite.hashCode();
            return result;
        }
    }
}
