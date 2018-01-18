/*
 * Copyright (c) 2017.
 * www.bigzun.com
 */

package com.bigzun.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by mc.kim on 8/3/2016.
 */
public class TimeManager {
    public static final long GMT_7 = 7 * 60 * 60 * 1000;
    private static TimeManager ourInstance = new TimeManager();
    private final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private final String PATTERNS = "EEE MMM dd HH:mm:ss zzz yyyy";
    private long serverTime = 0;
    private long timeOffset = 0;

    private TimeManager() {
    }

    public static TimeManager getInstance() {
        return ourInstance;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public long getServerCurrentTimeMillis() {
        long system = System.currentTimeMillis();
        calculateTimeOffset();
        long tmpTime = timeOffset + serverTime;
        return system - timeOffset;
    }

    public void calculateTimeOffset() {
        Calendar calInitial = Calendar.getInstance();
        calInitial.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        long offsetInitial = calInitial.getTimeInMillis();
    }

    public int getMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String formattedDate = format.format(serverTime);
        return Integer.valueOf(formattedDate);
    }

    public long getTodayStartTimeMills() {
        long today = -1;
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        if (timeZone.getRawOffset() == 0) timeZone = TimeZone.getTimeZone("GMT+07");
        Calendar currentCalendar = Calendar.getInstance(timeZone);
        currentCalendar.setTimeInMillis(TimeManager.getInstance().getServerCurrentTimeMillis());
//        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
//        currentCalendar.set(Calendar.MINUTE, 0);
//        currentCalendar.set(Calendar.SECOND, 0);
//        currentCalendar.set(Calendar.MILLISECOND, 0);
        setTimeToBeginningOfDay(currentCalendar);
        today = currentCalendar.getTimeInMillis();

        Log.i("TimeManager", "getTodayStartTime()-today : " + today);
        return today;
    }

}
