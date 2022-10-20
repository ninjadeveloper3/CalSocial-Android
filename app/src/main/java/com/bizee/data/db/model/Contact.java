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

package com.CalSocial.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity(nameInDb = "contact")
public class Contact {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String phoneNumber;

    private String profilePicture;

    private boolean isCalSocialUser;

    private boolean isPhoneContact;

    @Generated(hash = 2093277833)
    public Contact(Long id, String name, String phoneNumber, String profilePicture,
            boolean isCalSocialUser, boolean isPhoneContact) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.isCalSocialUser = isCalSocialUser;
        this.isPhoneContact = isPhoneContact;
    }

    @Generated(hash = 672515148)
    public Contact() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean getIsCalSocialUser() {
        return this.isCalSocialUser;
    }

    public void setIsCalSocialUser(boolean isCalSocialUser) {
        this.isCalSocialUser = isCalSocialUser;
    }

    public boolean getIsPhoneContact() {
        return this.isPhoneContact;
    }

    public void setIsPhoneContact(boolean isPhoneContact) {
        this.isPhoneContact = isPhoneContact;
    }


}