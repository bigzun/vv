/*
 * Copyright (c) 2017.
 * www.bigzun.com
 */

package com.bigzun.sdk.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.bigzun.sdk.Constants;
import com.bigzun.sdk.receiver.AlarmReceiver;

import java.util.Calendar;

/**
 * Created by namnh40 on 9/22/2017.
 */

public class AlarmUtils {
    public static void scheduleExitApp(Context context, int minute, boolean cancle) {
        Log.e("AlarmUtils", "scheduleExitApp minute:" + minute);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        if (cancle) {
            intent.setAction(Constants.ACTION_ALARM_CANCEL);
            intent.putExtra(Constants.KEY_DATA, "Hủy bỏ hẹn giờ thoát app  .............");
        } else {
            intent.setAction(Constants.ACTION_ALARM_EXIT_APP);
            intent.putExtra(Constants.KEY_DATA, "Hẹn giờ thoát app  .............");
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + minute * DateTimeUtils.MINUTE, AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    /**
     * @param minute: minutes
     */
    public static void scheduleStopMusic(Context context, int minute, boolean cancle) {
        Log.e("AlarmUtils", "scheduleStopMusic minute:" + minute);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        if (cancle) {
            intent.setAction(Constants.ACTION_ALARM_CANCEL_STOP_MUSIC);
            intent.putExtra(Constants.KEY_DATA, "Hủy bỏ hẹn giờ tắt nhạc  .............");
        } else {
            intent.setAction(Constants.ACTION_ALARM_STOP_MUSIC);
            intent.putExtra(Constants.KEY_DATA, "Hẹn giờ tắt nhạc  .............");
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + minute * DateTimeUtils.MINUTE, AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    /**
     * @param time: millis seconds
     */
    public static void scheduleStopMusic(Context context, long time, boolean cancle) {
        Log.e("AlarmUtils", "scheduleStopMusic time:" + time);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        if (cancle) {
            intent.setAction(Constants.ACTION_ALARM_CANCEL_STOP_MUSIC);
            intent.putExtra(Constants.KEY_DATA, "Hủy bỏ hẹn giờ tắt nhạc  .............");
        } else {
            intent.setAction(Constants.ACTION_ALARM_STOP_MUSIC);
            intent.putExtra(Constants.KEY_DATA, "Hẹn giờ tắt nhạc  .............");
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public static void scheduleOpenApp(Context context, int hour, int minute) {
        Log.e("AlarmUtils", "scheduleOpenApp hour:" + hour + ", minute:" + minute);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(Constants.ACTION_ALARM_OPEN_APP);
        intent.putExtra(Constants.KEY_DATA, "Hẹn giờ mở app mới  .............");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
