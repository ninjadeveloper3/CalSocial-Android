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

package com.CalSocial.utils;

/**
 * Created by amitshekhar on 08/01/17.
 */

public final class AppConstants {

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "CalSocial_mvp.db";
    public static final String PREF_NAME = "CalSocial_pref";

    public static final long NULL_INDEX = -1L;

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final String TERMS_LINK = "http://www.google.com";
    public static final String POLICY_LINK = "http://www.google.com";

    public static final String OPEN_LANDING = "OPEN_LANDING";
    public static final String OPEN_SIGNUP = "OPEN_SIGN_UP";
    public static final String OPEN_LOGIN = "OPEN_LOGIN";
    public static final String PROCEED_TO_PHONE_VERIFICATION_SIGNUP = "PROCEED_TO_PHONE_VERIFICATION_SIGNUP";
    public static final String PROCEED_TO_ADDITIONAL_INFO_PHONE_CODE = "PROCEED_TO_ADDITIONAL_INFO_PHONE_CODE";

    public static final String PHONE_CODE_SMS_MESSAGE = "PHONE_CODE_SMS_MESSAGE";
    public static final String CONTACTS_SYNC = "CONTACTS_SYNC";
    public static final String CALENDER_SYNC = "CALENDER_SYNC";
    public static final String OPEN_HOME = "OPEN_HOME";
    public static final String CREATE_FIRST_EVENT = "CREATE_FIRST_EVENT";
    public static final String CONTACTS_FOUND = "CONTACTS_FOUND";

    public static final String SWIPE_PAGER = "SWIPE_PAGER";

    public static final String OPEN_CONTACT_PROFILE = "OPEN_CONTACT_PROFILE";
    public static final String OPEN_SWARM_PROFILE = "OPEN_SWARM_PROFILE";
    public static final String OPEN_EDIT_SWARM_PROFILE = "OPEN_EDIT_SWARM_PROFILE";
    public static final String OPEN_VIEW_HIVE_PROFILE = "OPEN_VIEW_HIVE_PROFILE";
    public static final String OPEN_YOUR_PROFILE = "OPEN_YOUR_PROFILE";
    public static final String OPEN_EDIT_PROFILE = "OPEN_EDIT_PROFILE";
    public static final String IGNORE_SECOND_CHECK = "IGNORE_SECOND_CHECK";
    public static final String OPEN_SETTINGS_OPTIONS = "OPEN_SETTINGS_OPTIONS";
    public static final String OPEN_SETTINGS_CalSocial_SUGGESTIONS = "OPEN_SETTINGS_CalSocial_SUGGESTIONS";
    public static final String OPEN_CREATE_SWARM_FRAGMENT = "OPEN_CREATE_SWARM_FRAGMENT";
    public static final String OPEN_CREATE_SWARM_SELECT_MEMBERS_FRAGMENT = "OPEN_CREATE_SWARM_SELECT_MEMBERS_FRAGMENT";
    public static final String OPEN_SELECT_DATE_TIME_CREATE_EVENT = "OPEN_SELECT_DATE_TIME_CREATE_EVENT";
    public static final String OPEN_CalSocial_SUGGESTIONS_CREATE_EVENT = "OPEN_CalSocial_SUGGESTIONS_CREATE_EVENT";


    public static final String CONTACT_PROFILE_FRAGMENT = "CONTACT_PROFILE_FRAGMENT";
    public static final String EDIT_PROFILE_FRAGMENT = "EDIT_PROFILE_FRAGMENT";
    public static final String EDIT_SWARM_FRAGMENT = "EDIT_SWARM_FRAGMENT";
    public static final String SWARM_PROFILE_FRAGMENT = "SWARM_PROFILE_FRAGMENT";
    public static final String VIEW_HIVE_FRAGMENT = "VIEW_HIVE_FRAGMENT";
    public static final String YOUR_PROFILE_FRAGMENT = "YOUR_PROFILE_FRAGMENT";

    public enum ProfilePageMoreSettingsSource {SWARM_PROFILE, CONTACT_PROFILE}

    public static final String SWARM_PROFILE = "SWARM_PROFILE";
    public static final String CONTACT_PROFILE = "CONTACT_PROFILE";

    public enum EventsPageMoreSettingsSource {EVENT_OWNER, EVENT_GUEST}

    public static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    public static final String EVENT_DETAIL_FRAGMENT = "EVENT_DETAIL_FRAGMENT";
    public static final String CREATE_EVENT_FRAGMENT = "CREATE_EVENT_FRAGMENT";
    public static final String ADD_GUEST_FRAGMENT = "ADD_GUEST_FRAGMENT";
    public static final String MESSAGES_ONE_TO_ONE_FRAGMENT = "MESSAGES_ONE_TO_ONE_FRAGMENT";
    public static final String MESSAGES_ONE_TO_MANY_FRAGMENT = "MESSAGES_ONE_TO_MANY_FRAGMENT";

    public static final String OPEN_EVENT_DETAIL = "OPEN_EVENT_DETAIL";
    public static final String OPEN_CREATE_EVENT = "OPEN_CREATE_EVENT";
    public static final String OPEN_ADD_GUEST_EVENT = "OPEN_ADD_GUEST_EVENT";

    public static final String GOING_EVENT = "going";
    public static final String NOT_GOING_EVENT = "not_going";
    public static final String MAYBE_GOING_EVENT = "maybe";
    public static final String NO_RESPONSE_EVENT = "no_response";

    public static final String OPEN_HOME_FRAGMENT = "OPEN_HOME_FRAGMENT";
    public static final String OPEN_SETTINGS_FRAGMENT = "OPEN_SETTINGS_FRAGMENT";
    public static final String OPEN_NOTIFICATIONS_FRAGMENT = "OPEN_NOTIFICATIONS_FRAGMENT";


    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
