package com.CalSocial.service;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import androidx.annotation.Nullable;

import com.CalSocial.data.CalendarEventTempData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalenderSyncService extends IntentService {

    public CalenderSyncService() {
        super("ContactSyncService");
    }


    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.
    public static final String[] CALENDAR_LIST_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,
            CalendarContract.Calendars.ACCOUNT_NAME,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            CalendarContract.Calendars.ACCOUNT_TYPE,
            CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
            CalendarContract.Calendars.IS_PRIMARY
    };

    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Events.CALENDAR_ID,
            CalendarContract.Events.ORGANIZER,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.EVENT_LOCATION,
            CalendarContract.Events.DESCRIPTION,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND,
            CalendarContract.Events.EVENT_TIMEZONE,
            CalendarContract.Events.EVENT_END_TIMEZONE,
            CalendarContract.Events.DURATION,
            CalendarContract.Events.ALL_DAY,
            CalendarContract.Events.AVAILABILITY

    };


    // The indices for the projection array for the CALENDAR_LIST_PROJECTION.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_ACCOUNT_TYPE = 3;
    private static final int PROJECTION_CALENDAR_ACCESS_LEVEL = 4;
    private static final int PROJECTION_IS_PRIMARY = 5;

    // The indices for the projection array for the EVENT_PROJECTION.
    private static final int PROJECTION_EVENT_CALENDAR_ID_INDEX = 0;
    private static final int PROJECTION_EVENT_ORGANIZER_INDEX = 1;
    private static final int PROJECTION_EVENT_TITLE_INDEX = 2;
    private static final int PROJECTION_EVENT_LOCATION = 3;
    private static final int PROJECTION_EVENT_DESCRIPTION_INDEX = 4;
    private static final int PROJECTION_EVENT_DTSTART_INDEX = 5;
    private static final int PROJECTION_EVENT_DTEND_INDEX = 6;
    private static final int PROJECTION_EVENT_EVENT_TIMEZONE_INDEX = 7;
    private static final int PROJECTION_EVENT_EVENT_END_TIMEZONE_INDEX = 8;
    private static final int PROJECTION_EVENT_DURATION_INDEX = 9;
    private static final int PROJECTION_EVENT_ALL_DAY_INDEX = 10;
    private static final int PROJECTION_EVENT_AVAILABILITY_INDEX = 11;

    private ArrayList<CalendarEventTempData> calendarEventTempDataArrayList = new ArrayList<>();


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {

            Log.e("app", "in calender sync service");


            // Run query
            Cursor cur = null;
            ContentResolver cr = getContentResolver();
            Uri uri = CalendarContract.Calendars.CONTENT_URI;
            Uri eventUri = CalendarContract.Events.CONTENT_URI;
            // Submit the query and get a Cursor object back.
            cur = cr.query(uri, CALENDAR_LIST_PROJECTION, "", null, null);

            // Use the cursor to step through the returned records
            while (cur.moveToNext()) {
                long calID = cur.getLong(PROJECTION_ID_INDEX);
                String displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
                String accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
                String accountType = cur.getString(PROJECTION_ACCOUNT_TYPE);
                String calendarAccessLevel = cur.getString(PROJECTION_CALENDAR_ACCESS_LEVEL);
                String isPrimary = cur.getString(PROJECTION_IS_PRIMARY);

                // Do something with the values...
                Log.e("app", "calID: " + calID);
                Log.e("app", "displayName: " + displayName);
                Log.e("app", "accountName: " + accountName);
                Log.e("app", "accountType: " + accountType);
                Log.e("app", "calendarAccessLevel: " + calendarAccessLevel);
                Log.e("app", "isPrimary: " + isPrimary);

                Log.e("app", "*******************************************");
                String selection = "((" + CalendarContract.Events.CALENDAR_ID + " = ?))";
                String[] selectionArgs = new String[]{calID + ""};
                cur = cr.query(eventUri, EVENT_PROJECTION, null, null, null);
                while (cur.moveToNext()) {
                    Long calendarId = cur.getLong(PROJECTION_EVENT_CALENDAR_ID_INDEX);
                    String organizer = cur.getString(PROJECTION_EVENT_ORGANIZER_INDEX);
                    String eventTitle = cur.getString(PROJECTION_EVENT_TITLE_INDEX);
                    String eventLocation = cur.getString(PROJECTION_EVENT_LOCATION);
                    String eventDescription = cur.getString(PROJECTION_EVENT_DESCRIPTION_INDEX);
                    String startDate = cur.getString(PROJECTION_EVENT_DTSTART_INDEX);
                    String endDate = cur.getString(PROJECTION_EVENT_DTEND_INDEX);
                    String duration = cur.getString(PROJECTION_EVENT_DURATION_INDEX);
                    String allDay = cur.getString(PROJECTION_EVENT_ALL_DAY_INDEX);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    Date startDateD = new Date(Long.parseLong(startDate));
                    Date endDateD = new Date(Long.parseLong(endDate));
                    Date startTime = new Date(Long.parseLong(startDate));
                    Date endTime = new Date(Long.parseLong(endDate));

                    // Do something with the values...
                    Log.e("app", "calendarId: " + calendarId);
                    Log.e("app", "organizer: " + organizer);
                    Log.e("app", "eventTitle: " + eventTitle);
                    Log.e("app", "eventLocation: " + eventLocation);
                    Log.e("app", "eventDescription: " + eventDescription);
                    Log.e("app", "duration: " + duration);

                    Log.e("app", "start date: " + dateFormat.format(startDateD));
                    Log.e("app", "end date: " + dateFormat.format(endDateD));

                    Log.e("app", "start time: " + timeFormat.format(startTime));
                    Log.e("app", "end time: " + timeFormat.format(endTime));

                    Log.e("app", "allDay:" + allDay);

                    Log.e("app", "++++++++++++++++++++++++++++++++++++++++");

                    calendarEventTempDataArrayList.add(new CalendarEventTempData(eventTitle, eventLocation, dateFormat.format(startDateD), dateFormat.format(endDateD), timeFormat.format(startTime), timeFormat.format(endTime), duration));
                }

            }

            callSyncCalendarApi();


            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }

    }

    void callSyncCalendarApi() {

        for (int i = 0; i < calendarEventTempDataArrayList.size(); i++) {

        }

    }
}
