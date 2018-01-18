package com.bigzun.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;

import com.bigzun.sdk.utils.Log;

import java.util.Locale;

public class SmsReceiver extends BroadcastReceiver {
    public static final String CONFIRM_CODE_KICH_HOAT = "CONFIRM_CODE_KICH_HOAT";
    public static final String CONFIRM_CODE_MAT_KHAU = "CONFIRM_CODE_MAT_KHAU";
    public static final String SMS_EXTRA_NAME = "pdus";
    public static final String SIGN_MA_KICH_HOAT = "ma kich hoat";
    public static final String SIGN_MA_LAY_LAI_MAT_KHAU = "ma lay lai mat khau";
    public static final String SIGN_CONFIRM_CODE_REGIS_KEENG = "ma xac thuc keeng";
    public static final String CONFIRM_CODE_REGIS_KEENG = "CONFIRM_CODE_REGIS_KEENG";
    static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null)
            return;
        try {
            Bundle extras = intent.getExtras();
            String messages;
            if (extras != null) {
                // Get received SMS array
                Object[] smsExtra = (Object[]) extras.get(SMS_EXTRA_NAME);
                for (int i = 0; i < smsExtra.length; ++i) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) smsExtra[i]);
                    String address = "";
                    try {
                        address = sms.getOriginatingAddress();
                    } catch (Exception e) {
                        Log.e(TAG, e);
                    }
                    if (TextUtils.isEmpty(address)) {
                        return;
                    }
                    if (address.compareTo("KEENG") == 0) {
                        String body = sms.getDisplayMessageBody().toString();
                        if (body.toLowerCase(Locale.US).contains(SIGN_MA_KICH_HOAT)) {
                            messages = body.substring(body.length() - 6).trim();
                            Intent in = new Intent("SmsMessage.intent.MAIN");
                            in.putExtra(CONFIRM_CODE_KICH_HOAT, messages);
                            context.sendBroadcast(in);
                        } else if (body.toLowerCase(Locale.US).contains(SIGN_MA_LAY_LAI_MAT_KHAU)) {
                            messages = body.substring(body.length() - 6).trim();
                            Intent in = new Intent("SmsMessage.intent.MAIN");
                            in.putExtra(CONFIRM_CODE_MAT_KHAU, messages);
                            context.sendBroadcast(in);
                        } else if (body.toLowerCase(Locale.US).contains(SIGN_CONFIRM_CODE_REGIS_KEENG)) {
                            messages = body.substring(body.length() - 6).trim();
                            Intent in = new Intent("SmsMessage.intent.MAIN");
                            in.putExtra(CONFIRM_CODE_REGIS_KEENG, messages);
                            context.sendBroadcast(in);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e);
        }
    }
}
