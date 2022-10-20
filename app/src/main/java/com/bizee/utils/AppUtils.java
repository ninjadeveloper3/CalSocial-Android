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

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.CalSocial.R;
import com.CalSocial.data.db.model.DaoMaster;
import com.CalSocial.data.db.model.DaoSession;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * Created by janisharali on 24/05/17.
 */

public final class AppUtils {


    public static String sourceScreenFragment = ""; //This variable will contain the name of the fragment from where the user left
    public static String destinationScreenFragment = ""; //This variable will contain the name of the fragment where the user ended up

    private AppUtils() {
        // This class is not publicly instantiable
    }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }

    public static void updateSourceDestination(String source, String destination) {

        String tempDestination = source;
        source = destination;
        destination = tempDestination;

        AppUtils.sourceScreenFragment = destination;
        AppUtils.destinationScreenFragment = source;

        Log.e("CalSocial source", AppUtils.sourceScreenFragment);
        Log.e("CalSocial destination", AppUtils.destinationScreenFragment);

    }

    public static String getPhoneNumberUSCANFormat(String phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            phone = phone.replaceAll("-", "").replaceAll(" ", "");
            Phonenumber.PhoneNumber usNumberProto = phoneUtil.parse(phone, "US");
            Phonenumber.PhoneNumber canNumberProto = phoneUtil.parse(phone, "CA");
            if (phoneUtil.isValidNumberForRegion(usNumberProto, "US")) {
                return phoneUtil.format(usNumberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL).replaceAll("-", "").replaceAll(" ", "");
            } else {
                if (phoneUtil.isValidNumberForRegion(canNumberProto, "CA")) {
                    return phoneUtil.format(canNumberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL).replaceAll("-", "").replaceAll(" ", "");
                } else {
                    return null;
                }
            }

        } catch (NumberParseException e) {
            return null;
        }
    }

    public static DaoSession getDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, AppConstants.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        return daoSession;
    }

}
