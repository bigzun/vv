package com.bigzun.sdk;
/*
 * Copyright (c) www.bigzun.com 
 * Created by admin on 1/18/2018.
 */

public interface Constants {
    String KEY_ID = "_ID";
    String KEY_TYPE = "_TYPE";
    String KEY_NAME = "_NAME";
    String KEY_DATA = "_DATA";
    String KEY_IMAGE = "_IMAGE";

    String ACTION_ALARM_STOP_MUSIC = BuildConfig.APPLICATION_ID + ".alarm.stopmusic";
    String ACTION_ALARM_EXIT_APP = BuildConfig.APPLICATION_ID + ".alarm.exitapp";
    String ACTION_ALARM_OPEN_APP = BuildConfig.APPLICATION_ID + ".alarm.openapp";
    String ACTION_ALARM_CANCEL = BuildConfig.APPLICATION_ID + ".alarm.cancel";
    String ACTION_ALARM_CANCEL_STOP_MUSIC = BuildConfig.APPLICATION_ID + ".alarm.cancel.stopmusic";

    int REQUEST_SETTING_RINGTONE_PERMISSION = 0x33;
}
