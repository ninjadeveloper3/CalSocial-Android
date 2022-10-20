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

package com.CalSocial.data.prefs;

import com.CalSocial.data.DataManager;

/**
 * Created by janisharali on 27/01/17.
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentFirstName();

    void setCurrentFirstName(String firstName);

    String getCurrentLastName();

    void setCurrentLastName(String lastName);

    String getCurrentAddress();

    void setCurrentAddress(String address);

    void setCurrentBiography(String biography);

    String getCurrentBiography();

    void setCurrentPhone(String phone);

    String getCurrentPhone();

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentActivationCode();

    void setCurrentActivationCode(String activationCode);

    void setCurrentActiveStatus(Boolean status);

    Boolean getCurrentActiveStatus();


}
