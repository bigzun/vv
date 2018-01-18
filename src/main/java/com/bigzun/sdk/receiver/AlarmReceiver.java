/*
 * Copyright (c) 2017.
 * www.bigzun.com
 */

package com.bigzun.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.bigzun.sdk.BuildConfig;
import com.bigzun.sdk.Constants;
import com.bigzun.sdk.utils.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by namnh40 on 9/22/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            if (BuildConfig.DEBUG) {
                String message = intent.getStringExtra(Constants.KEY_DATA);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                Log.e("AlarmReceiver", action + ": " + date.getHours() + "h " + date.getMinutes() + "m " + date.getSeconds() + "s message: " + message
                        + "\n-----------------------------------------");
            }
            if (!TextUtils.isEmpty(action)) {
                switch (action) {
                    case Constants.ACTION_ALARM_STOP_MUSIC:
                    case Constants.ACTION_ALARM_EXIT_APP:
//                        EventHelper.pushAlarmReceiver(action);
//                        EventHelper.pushScheduleCancelStopMusic();
                        break;
                    case Constants.ACTION_ALARM_OPEN_APP:
//                        Intent openIntent = new Intent(context, SplashActivity.class);
//                        openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
                        break;
                    case Constants.ACTION_ALARM_CANCEL:
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            Log.e("AlarmReceiver", e);
        }
    }
}

