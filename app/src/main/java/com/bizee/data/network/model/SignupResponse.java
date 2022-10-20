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

import org.json.JSONObject;


public class SignupResponse {

    @Expose
    @SerializedName("ResponseHeader")
    private JSONObject responseHeader;

    @Expose
    @SerializedName("ResponseBody")
    private JSONObject responseBody;

    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("biography")
    private String biography;

    @Expose
    @SerializedName("phone")
    private String phone;

    @Expose
    @SerializedName("token")
    private String token;

    public JSONObject getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(JSONObject responseHeader) {
        this.responseHeader = responseHeader;
    }

    public JSONObject getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(JSONObject responseBody) {
        this.responseBody = responseBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignupResponse)) return false;

        SignupResponse that = (SignupResponse) o;

        if (!responseHeader.equals(that.responseHeader)) return false;
        if (!responseBody.equals(that.responseBody)) return false;
        if (!id.equals(that.id)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!address.equals(that.address)) return false;
        if (!biography.equals(that.biography)) return false;
        if (!phone.equals(that.phone)) return false;
        return token.equals(that.token);


    }

    @Override
    public int hashCode() {
        int result = responseHeader.hashCode();
        result = 31 * result + responseBody.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + biography.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + token.hashCode();

        return result;
    }


}

