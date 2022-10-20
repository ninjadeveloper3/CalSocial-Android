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


import com.CalSocial.BuildConfig;

/**
 * Created by amitshekhar on 01/02/17.
 */

public final class ApiEndPoint {


    public static final String ENDPOINT_FAVORITES = BuildConfig.BASE_URL
            + "/5d919ebe3100000f0010c8f1";

    public static final String ENDPOINT_CONTACTS_FOUND = BuildConfig.BASE_URL
            + "/5d7b9408350000627b3cacbc";

    public static final String ENDPOINT_EVENTS_HOME = BuildConfig.BASE_URL
            + "/5d8351743400003322f4a3a3";

    public static final String ENDPOINT_EVENTS_PROFILE = BuildConfig.BASE_URL
            + "/5d8351933400007b00f4a3a6";

    public static final String ENDPOINT_SWARM_MEMBERS = BuildConfig.BASE_URL
            + "/5d9c7944310000dda42fc5f2";

    public static final String ENDPOINT_EVENT_ATTENDEES = BuildConfig.BASE_URL
            + "/5d84b80a3000007a4c22daec";

    public static final String ENDPOINT_EVENT_COMMENTS = BuildConfig.BASE_URL
            + "/5d85c1803200001d3e07b2b6";

    public static final String ENDPOINT_NOTIFICATIONS = BuildConfig.BASE_URL
            + "/5d8868e8330000b611d7d885";

    public static final String ENDPOINT_CONTACTS_HIVE = BuildConfig.BASE_URL
            + "/5d89036f330000dbebd7dcff";

    public static final String ENDPOINT_CONTACTS_SWARM = BuildConfig.BASE_URL
            + "/5d89043b3300001a02d7dd04";

    public static final String ENDPOINT_CONTACTS_MESSAGES = BuildConfig.BASE_URL
            + "/5d89eeb73000006500b9a559";

    public static final String ENDPOINT_NEW_MESSAGES_ADD_MEMBERS = BuildConfig.BASE_URL
            + "/5d9dac88320000500032981a";

    public static final String ENDPOINT_CalSocial_TIME_SUGGESTIONS = BuildConfig.BASE_URL
            + "/5d8a98fc3500006200d46a05";

    public static final String ENDPOINT_MESSAGE_CHAT = BuildConfig.BASE_URL
            + "/5d8a98fc3500006200d46a05";


    public static final String ENDPOINT_SIGN_UP = BuildConfig.BASE_URL_LOCAL
            + "/signup";

    public static final String ENDPOINT_VERIFY_PHONE_CODE_AFTER_SIGN_UP = BuildConfig.BASE_URL_LOCAL
            + "/activate_account";

    public static final String ENDPOINT_SIGN_IN = BuildConfig.BASE_URL_LOCAL
            + "/send_login_verification";

    public static final String ENDPOINT_VERIFY_PHONE_CODE_AFTER_SIGN_IN = BuildConfig.BASE_URL_LOCAL
            + "/verify_login_code";

    public static final String ENDPOINT_ADDITIONAL_INFO = BuildConfig.BASE_URL_LOCAL
            + "/complete_profile";

    public static final String ENDPOINT_SYNC_CONTACTS = BuildConfig.BASE_URL_LOCAL
            + "/sync_user_contacts";


    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
