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

import android.content.Context;
import android.content.SharedPreferences;

import com.CalSocial.data.DataManager;
import com.CalSocial.di.ApplicationContext;
import com.CalSocial.di.PreferenceInfo;
import com.CalSocial.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_FIRST_NAME = "PREF_KEY_CURRENT_FIRST_NAME";
    private static final String PREF_KEY_CURRENT_LAST_NAME = "PREF_KEY_CURRENT_LAST_NAME";
    private static final String PREF_KEY_CURRENT_ADDRESS = "PREF_KEY_CURRENT_ADDRESS";
    private static final String PREF_KEY_CURRENT_BIOGRAPHY = "PREF_KEY_CURRENT_BIOGRAPHY";
    private static final String PREF_KEY_CURRENT_PHONE = "PREF_KEY_CURRENT_PHONE";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_ACTIVATION_CODE = "PREF_KEY_ACTIVATION_CODE";
    private static final String PREF_KEY_ACTIVE_STATUS = "PREF_KEY_ACTIVE_STATUS";
    private static final String PREF_KEY_CURRENT_ONBOARDING_STEP = "PREF_KEY_CURRENT_ONBOARDING_STEP";


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public String getCurrentFirstName() {
        return mPrefs.getString(PREF_KEY_CURRENT_FIRST_NAME, null);
    }

    @Override
    public void setCurrentFirstName(String firstName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_FIRST_NAME, firstName).apply();
    }

    @Override
    public String getCurrentLastName() {
        return mPrefs.getString(PREF_KEY_CURRENT_LAST_NAME, null);
    }

    @Override
    public void setCurrentLastName(String lastName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_LAST_NAME, lastName).apply();
    }

    @Override
    public String getCurrentAddress() {
        return mPrefs.getString(PREF_KEY_CURRENT_ADDRESS, null);
    }

    @Override
    public void setCurrentAddress(String address) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_ADDRESS, address).apply();
    }

    @Override
    public void setCurrentBiography(String biography) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_BIOGRAPHY, biography).apply();

    }

    @Override
    public String getCurrentBiography() {
        return mPrefs.getString(PREF_KEY_CURRENT_BIOGRAPHY, null);

    }

    @Override
    public void setCurrentPhone(String phone) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_PHONE, phone).apply();

    }

    @Override
    public String getCurrentPhone() {
        return mPrefs.getString(PREF_KEY_CURRENT_PHONE, null);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getCurrentActivationCode() {
        return mPrefs.getString(PREF_KEY_ACTIVATION_CODE, null);
    }

    @Override
    public void setCurrentActivationCode(String activationCode) {
        mPrefs.edit().putString(PREF_KEY_ACTIVATION_CODE, activationCode).apply();

    }

    @Override
    public void setCurrentActiveStatus(Boolean status) {
        mPrefs.edit().putBoolean(PREF_KEY_ACTIVE_STATUS, status).apply();
    }

    @Override
    public Boolean getCurrentActiveStatus() {
        return mPrefs.getBoolean(PREF_KEY_ACTIVE_STATUS, false);
    }

}
