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

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();


    Single<UsersResponse> getFavoritesApiCall();

    Single<ContactsFoundResponse> getFoundcontactsCall();

    Single<EventsHomeResponse> getEventsHomeApiCall();

    Single<EventsHomeResponse> getProfileEventsApiCall();

    Single<UsersResponse> getSwarmMembersApiCall();

    Single<EventAttendeesResponse> getEventAttendeesApiCall();

    Single<CommentsResponse> getCommentsApiCall();

    Single<NotificationsResponse> getNotificationsApiCall();

    Single<ContactsHiveResponse> getContactsHiveApiCall();

    Single<ContactsSwarmResponse> getContactsSwarmApiCall();

    Single<MessagesResponse> getMessagesApiCall();

    Single<TimeSuggestionsResponse> getTimeSuggestionsApiCall();

    Single<SignupResponse> doServerSignupRequestApiCall(SignupRequest.ServerSignupRequest request);

    Single<NewMessageSelectContactsResponse> getNewMessagesSelectContactsApiCall();

}
