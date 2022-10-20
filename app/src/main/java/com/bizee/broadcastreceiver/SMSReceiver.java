package com.CalSocial.broadcastreceiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.CalSocial.event.NewMessageEvent;
import com.CalSocial.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;

public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG =
            SMSReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM = (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.M);
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    msgs[i] =
                            SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                // Build the message to show.
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :" + msgs[i].getMessageBody() + "\n";

                // Log and display the SMS message.
                Log.e(TAG, "onReceive: " + strMessage);
                //TODO: parse the message here based on the template that will be provided.
                //The message field will contain the extracted code.
                //TODO: Remove this assignment.
                strMessage="123456";
                EventBus.getDefault().post(new NewMessageEvent(AppConstants.PHONE_CODE_SMS_MESSAGE, strMessage));


            }
        }
    }
}
