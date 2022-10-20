package com.CalSocial.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.CalSocial.data.ContactTempData;
import com.CalSocial.data.db.model.Contact;
import com.CalSocial.data.db.model.ContactDao;
import com.CalSocial.data.db.model.DaoSession;
import com.CalSocial.data.network.ApiEndPoint;
import com.CalSocial.utils.AppUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.tomash.androidcontacts.contactgetter.entity.ContactData;
import com.tomash.androidcontacts.contactgetter.main.contactsGetter.ContactsGetterBuilder;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsSyncService extends IntentService {


    public ContactsSyncService() {
        super("ContactSyncService");
    }

    private ArrayList<ContactTempData> validNumbers = new ArrayList<>();

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Bundle bundle = intent.getExtras();
        String accessToken = bundle.getString("access_token");
        Boolean firstRun = bundle.getBoolean("first_run");
        if (accessToken != null && firstRun != null) {

            try {

                List<ContactData> contacts = new ContactsGetterBuilder(this)
                        .onlyWithPhones()//.allFields()
                        .buildList();

                Log.e("app", "number of contacts " + contacts.size());
                for (int i = 0; i < contacts.size(); i++) {
                    if (contacts.get(i).getPhoneList().size() > 0) {
                       /* Log.e("app", "account name: " + contacts.get(i).getAccountName());
                        Log.e("app", "composite name: " + contacts.get(i).getCompositeName());
                        Log.e("app", "num phones: " + contacts.get(i).getPhoneList().size() + "");*/

                        for (int j = 0; j < contacts.get(i).getPhoneList().size(); j++) {
                            String formattedPhone = AppUtils.getPhoneNumberUSCANFormat(contacts.get(i).getPhoneList().get(j).getMainData());
                            if (formattedPhone != null) {
                                //Log.e("app", "number: " + contacts.get(i).getPhoneList().get(j).getMainData());
                                validNumbers.add(new ContactTempData(contacts.get(i).getCompositeName(), formattedPhone));

                            }
                        }
                    }
                }

               /* for (int i = 0; i < validNumbers.size(); i++) {
                    Log.e("valid number", validNumbers.get(i).getPhoneNumber());
                }*/

                syncContactsApiCall(accessToken, firstRun);

            /*for (int i = 0; i < contacts.get(1).getPhoneList().size(); i++) {
                Log.e("app", "number: " + contacts.get(1).getPhoneList().get(i).getMainData());
            }*/
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
        }


    }

    void syncContactsApiCall(String accessToken, Boolean firstRun) {

        String CONTACTS_SYNCED = "All contacts synced successfully";
        Map<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < validNumbers.size(); i++) {
            map.put("contact[" + i + "][name]", validNumbers.get(i).getName());
            map.put("contact[" + i + "][phone_number]", validNumbers.get(i).getPhoneNumber());
            map.put("contact[" + i + "][is_CalSocial]", "0");
            map.put("contact[" + i + "][is_phone]", "1");

        }

        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SYNC_CONTACTS)
                .addBodyParameter(map)
                .addHeaders("Authorization", "Bearer " + accessToken)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            DaoSession daoSession = AppUtils.getDaoSession(getApplicationContext());

                            JSONObject responseHeader = response.getJSONObject("ResponseHeader");
                            JSONObject responseBody = response.getJSONObject("ResponseBody");
                            Log.e("app", responseBody.toString());
                            String responseMessage = responseBody.getString("ResponseMessage");
                            if (responseMessage.compareToIgnoreCase(CONTACTS_SYNCED) == 0) {
                                JSONArray data = responseBody.getJSONArray("Data");

                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject dataObject = data.getJSONObject(i);
                                    String contactId = dataObject.getString("id");
                                    String contactName = dataObject.getString("name");
                                    String contactPhoneNumber = dataObject.getString("phone_number");
                                    String isCalSocialStr = dataObject.getString("is_CalSocial");
                                    String isPhoneStr = dataObject.getString("is_phone");

                                    Boolean isCalSocial = isCalSocialStr.equals("1");
                                    Boolean isPhone = isPhoneStr.equals("1");

                                    if (firstRun) {
                                        //This contact sync service is being run for the first time.
                                        daoSession.getContactDao().insert(new Contact(Long.parseLong(contactId), contactName, contactPhoneNumber, "", isCalSocial, isPhone));
                                    } else {
                                        //Thi contact sync service has been run before, so there may be data saved in the local sqllite database
                                        QueryBuilder<Contact> contactQuery = daoSession.getContactDao().queryBuilder().where(ContactDao.Properties.Id.eq(Long.parseLong(contactId))).where(ContactDao.Properties.PhoneNumber.eq(contactPhoneNumber));
                                        if (contactQuery.count() > 0) {
                                            //update the local entry with whatever has been returned by the API
                                            daoSession.getContactDao().update(new Contact(Long.parseLong(contactId), contactName, contactPhoneNumber, "", isCalSocial, isPhone));
                                        } else {
                                            daoSession.getContactDao().insert(new Contact(Long.parseLong(contactId), contactName, contactPhoneNumber, "", isCalSocial, isPhone));
                                        }
                                    }

                                }

                            }

                            daoSession.clear();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });

    }


}
