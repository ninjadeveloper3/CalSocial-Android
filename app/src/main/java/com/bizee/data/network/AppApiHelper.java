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

package com.CalSocial.data.network;

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
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }


    @Override
    public Single<UsersResponse> getFavoritesApiCall() {

        Single<UsersResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_FAVORITES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(UsersResponse.class);


        return object;
    }

    @Override
    public Single<ContactsFoundResponse> getFoundcontactsCall() {
        Single<ContactsFoundResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CONTACTS_FOUND)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(ContactsFoundResponse.class);


        return object;
    }

    @Override
    public Single<EventsHomeResponse> getEventsHomeApiCall() {
        Single<EventsHomeResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EVENTS_HOME)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(EventsHomeResponse.class);


        return object;
    }

    @Override
    public Single<EventsHomeResponse> getProfileEventsApiCall() {
        Single<EventsHomeResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EVENTS_PROFILE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(EventsHomeResponse.class);


        return object;
    }

    @Override
    public Single<UsersResponse> getSwarmMembersApiCall() {
        Single<UsersResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_SWARM_MEMBERS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(UsersResponse.class);


        return object;
    }

    @Override
    public Single<EventAttendeesResponse> getEventAttendeesApiCall() {

        Single<EventAttendeesResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EVENT_ATTENDEES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(EventAttendeesResponse.class);


        return object;
    }

    @Override
    public Single<CommentsResponse> getCommentsApiCall() {
        Single<CommentsResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EVENT_COMMENTS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CommentsResponse.class);


        return object;
    }

    @Override
    public Single<NotificationsResponse> getNotificationsApiCall() {
        Single<NotificationsResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_NOTIFICATIONS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(NotificationsResponse.class);

        return object;
    }

    @Override
    public Single<ContactsHiveResponse> getContactsHiveApiCall() {
        Single<ContactsHiveResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CONTACTS_HIVE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(ContactsHiveResponse.class);

        return object;
    }

    @Override
    public Single<ContactsSwarmResponse> getContactsSwarmApiCall() {
        Single<ContactsSwarmResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CONTACTS_SWARM)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(ContactsSwarmResponse.class);

        return object;
    }

    @Override
    public Single<MessagesResponse> getMessagesApiCall() {
        Single<MessagesResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CONTACTS_MESSAGES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(MessagesResponse.class);

        return object;
    }

    @Override
    public Single<TimeSuggestionsResponse> getTimeSuggestionsApiCall() {
        Single<TimeSuggestionsResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CalSocial_TIME_SUGGESTIONS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(TimeSuggestionsResponse.class);

        return object;
    }

    @Override
    public Single<SignupResponse> doServerSignupRequestApiCall(SignupRequest.ServerSignupRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SIGN_UP)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build().getObjectSingle(SignupResponse.class);
    }

    @Override
    public Single<NewMessageSelectContactsResponse> getNewMessagesSelectContactsApiCall() {
        Single<NewMessageSelectContactsResponse> object = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_NEW_MESSAGES_ADD_MEMBERS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(NewMessageSelectContactsResponse.class);

        return object;
    }


}

