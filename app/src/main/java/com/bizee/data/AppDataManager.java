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

package com.CalSocial.data;


import android.content.Context;

import com.CalSocial.data.db.DbHelper;
import com.CalSocial.data.db.model.Contact;
import com.CalSocial.data.db.model.ContactHasEvent;
import com.CalSocial.data.db.model.Event;
import com.CalSocial.data.db.model.Swarm;
import com.CalSocial.data.db.model.SwarmHasContact;
import com.CalSocial.data.db.model.User;
import com.CalSocial.data.network.ApiHeader;
import com.CalSocial.data.network.ApiHelper;
import com.CalSocial.data.network.model.CommentsResponse;
import com.CalSocial.data.network.model.ContactsFoundResponse;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.data.network.model.ContactsSwarmResponse;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.data.network.model.NewMessageSelectContactsResponse;
import com.CalSocial.data.network.model.NotificationsResponse;
import com.CalSocial.data.network.model.SignupRequest;
import com.CalSocial.data.network.model.SignupResponse;
import com.CalSocial.data.network.model.TimeSuggestionsResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.data.prefs.PreferencesHelper;
import com.CalSocial.di.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    private final ArrayList<TemporaryData> temporaryDataList = new ArrayList<>();


    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public String getCurrentActivationCode() {
        return mPreferencesHelper.getCurrentActivationCode();
    }

    @Override
    public void setCurrentActivationCode(String activationCode) {
        mPreferencesHelper.setCurrentActivationCode(activationCode);

    }

    @Override
    public void setCurrentActiveStatus(Boolean status) {
        mPreferencesHelper.setCurrentActiveStatus(status);
    }

    @Override
    public Boolean getCurrentActiveStatus() {
        return mPreferencesHelper.getCurrentActiveStatus();
    }


    @Override
    public Observable<Long> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }


    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentFirstName() {
        return mPreferencesHelper.getCurrentFirstName();
    }

    @Override
    public void setCurrentFirstName(String firstName) {
        mPreferencesHelper.setCurrentFirstName(firstName);
    }

    @Override
    public String getCurrentLastName() {
        return mPreferencesHelper.getCurrentLastName();
    }

    @Override
    public void setCurrentLastName(String lastName) {
        mPreferencesHelper.setCurrentLastName(lastName);
    }

    @Override
    public String getCurrentAddress() {
        return mPreferencesHelper.getCurrentAddress();
    }

    @Override
    public void setCurrentAddress(String address) {
        mPreferencesHelper.setCurrentAddress(address);
    }

    @Override
    public void setCurrentBiography(String biography) {
        mPreferencesHelper.setCurrentBiography(biography);
    }

    @Override
    public String getCurrentBiography() {
        return mPreferencesHelper.getCurrentBiography();
    }

    @Override
    public void setCurrentPhone(String phone) {
        mPreferencesHelper.setCurrentPhone(phone);
    }

    @Override
    public String getCurrentPhone() {
        return mPreferencesHelper.getCurrentBiography();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }


    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String firstName,
            String lastName,
            String address,
            String biography,
            String phone,
            String profilePicPath,
            String activationCode,
            Boolean isActivated) {

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentFirstName(firstName);
        setCurrentLastName(lastName);
        setCurrentAddress(address);
        setCurrentBiography(biography);
        setCurrentPhone(phone);
        setCurrentUserProfilePicUrl(profilePicPath);

        updateApiHeader(userId, accessToken);
    }

    @Override
    public void updateCommonData(TemporaryData temporaryData) {
        boolean foundItem = false;
        for (int i = 0; i < temporaryDataList.size(); i++) {
            //I only want one copy of the index in the list, hence overwrite an existing data item.
            if (temporaryDataList.get(i).getIndex().compareToIgnoreCase(temporaryData.getIndex()) == 0) {
                temporaryDataList.get(i).setIntValue(temporaryData.getIntValue());
                temporaryDataList.get(i).setStrValue(temporaryData.getStrValue());
                temporaryDataList.get(i).setLnValue(temporaryData.getLnValue());
                foundItem = true;
                break;
            }
        }

        if (!foundItem) {
            temporaryDataList.add(new TemporaryData(temporaryData.getIndex(), temporaryData.getStrValue(), temporaryData.getIntValue(), temporaryData.getLnValue()));
        }

    }

    @Override
    public TemporaryData getCommonData(String index) {
        for (int i = 0; i < temporaryDataList.size(); i++) {
            //I only want one copy of the index in the list, hence overwrite an existing data item.
            if (temporaryDataList.get(i).getIndex().compareToIgnoreCase(index) == 0) {
                return temporaryDataList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }


    @Override
    public Observable<Long> insertContact(Contact contact) {
        return mDbHelper.insertContact(contact);
    }

    @Override
    public Observable<List<Contact>> getAllContacts() {
        return mDbHelper.getAllContacts();
    }

    @Override
    public Observable<Long> insertEvent(Event event) {
        return null;
    }

    @Override
    public Observable<Long> insertSwarm(Swarm swarm) {
        return null;
    }

    @Override
    public Observable<Long> insertContactHasEvent(ContactHasEvent contactHasEvent) {
        return null;
    }

    @Override
    public Observable<Long> insertSwarmHasContact(SwarmHasContact swarmHasContact) {
        return null;
    }

    @Override
    public Single<UsersResponse> getFavoritesApiCall() {
        return mApiHelper.getFavoritesApiCall();
    }

    @Override
    public Single<ContactsFoundResponse> getFoundcontactsCall() {
        return mApiHelper.getFoundcontactsCall();
    }

    @Override
    public Single<EventsHomeResponse> getEventsHomeApiCall() {
        return mApiHelper.getEventsHomeApiCall();
    }

    @Override
    public Single<EventsHomeResponse> getProfileEventsApiCall() {
        return mApiHelper.getProfileEventsApiCall();

    }

    @Override
    public Single<UsersResponse> getSwarmMembersApiCall() {
        return mApiHelper.getSwarmMembersApiCall();
    }

    @Override
    public Single<EventAttendeesResponse> getEventAttendeesApiCall() {
        return mApiHelper.getEventAttendeesApiCall();
    }

    @Override
    public Single<CommentsResponse> getCommentsApiCall() {
        return mApiHelper.getCommentsApiCall();
    }

    @Override
    public Single<NotificationsResponse> getNotificationsApiCall() {
        return mApiHelper.getNotificationsApiCall();
    }

    @Override
    public Single<ContactsHiveResponse> getContactsHiveApiCall() {
        return mApiHelper.getContactsHiveApiCall();
    }

    @Override
    public Single<ContactsSwarmResponse> getContactsSwarmApiCall() {
        return mApiHelper.getContactsSwarmApiCall();
    }

    @Override
    public Single<MessagesResponse> getMessagesApiCall() {
        return mApiHelper.getMessagesApiCall();
    }

    @Override
    public Single<TimeSuggestionsResponse> getTimeSuggestionsApiCall() {
        return mApiHelper.getTimeSuggestionsApiCall();
    }

    @Override
    public Single<SignupResponse> doServerSignupRequestApiCall(SignupRequest.ServerSignupRequest request) {
        return mApiHelper.doServerSignupRequestApiCall(request);
    }

    @Override
    public Single<NewMessageSelectContactsResponse> getNewMessagesSelectContactsApiCall() {
        return mApiHelper.getNewMessagesSelectContactsApiCall();
    }

}
