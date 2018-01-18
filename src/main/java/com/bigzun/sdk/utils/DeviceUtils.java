package com.bigzun.sdk.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.bigzun.sdk.BuildConfig;
import com.bigzun.sdk.Constants;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by namnh40 on 7/12/2017.
 */

public class DeviceUtils {
    static final String TAG = "DeviceUtils";

    public static void setRingtone(Activity activity, String filePath) {
        if (activity == null || TextUtils.isEmpty(filePath))
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(activity)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                activity.startActivityForResult(intent, Constants.REQUEST_SETTING_RINGTONE_PERMISSION);
                return;
            }
        } else if (PermissionUtils.declinedPermission(activity, Manifest.permission.WRITE_SETTINGS)) {
            PermissionUtils.requestPermission(activity, Manifest.permission.WRITE_SETTINGS, Constants.REQUEST_SETTING_RINGTONE_PERMISSION);
            return;
        }
        try {
            Log.i("DeviceUtils", "localUrl: " + filePath);
            File file = new File(filePath);
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
            values.put(MediaStore.MediaColumns.TITLE, file.getName());
            values.put(MediaStore.MediaColumns.SIZE, 215454);
            values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/ogg");
            values.put(MediaStore.Audio.Media.DURATION, 230);
            values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
            values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
            values.put(MediaStore.Audio.Media.IS_ALARM, false);
            values.put(MediaStore.Audio.Media.IS_MUSIC, false);

            Log.i("DeviceUtils", "setRingtone file:" + file.getAbsolutePath());

            Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
            Uri newUri = activity.getContentResolver().insert(uri, values);
            RingtoneManager.setActualDefaultRingtoneUri(
                    activity,
                    RingtoneManager.TYPE_RINGTONE,
                    newUri
            );

        } catch (SecurityException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
    }

    public static Point getDeviceSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static Point getDeviceSizePortrait(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int x = Math.min(size.x, size.y);
        int y = Math.max(size.x, size.y);
        return new Point(x, y);
    }

    public static int getDpi(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (metrics.density * 160f);
    }

    public static int dpToPx(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = Math.round(dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    /**
     * Convert pixels to dpi
     */
    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static boolean isLandscape(Activity activity) {
        return activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isActivityAutoRotate(Activity activity) {
        return activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_UNDEFINED;
    }

    /**
     * Force set the orientation of activity
     *
     * @param activity    target activity
     * @param orientation 1 of those values Configuration.ORIENTATION_LANDSCAPE
     *                    or Configuration.ORIENTATION_PORTRAIT or
     *                    Configuration.ORIENTATION_UNDEFINED
     */
    public static void forceRotateScreen(Activity activity, int orientation) {
        switch (orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case Configuration.ORIENTATION_UNDEFINED:
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                break;
        }
    }

    public static boolean isDeviceLockRotate(Context context) {
        final int rotationState = Settings.System.getInt(
                context.getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0);

        return rotationState == 0;
    }

    public static void openAppInStore(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    //TODO kiem tra xem 1 ung dung da duoc cai dat hay chua
    public static boolean checkAppInstalled(Context context, String packageId) {
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo(packageId, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenHeight(Context c) {
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenWidth(Context c) {
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getOrientationInDegree(Activity activity) {
        int degrees = 0;
        try {
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            switch (rotation) {
                case Surface.ROTATION_0:
                    degrees = 0;
                    break;
                case Surface.ROTATION_90:
                    degrees = 90;
                    break;
                case Surface.ROTATION_180:
                    degrees = 180;
                    break;
                case Surface.ROTATION_270:
                    degrees = 270;
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, e);
        }
        return degrees;
    }

    private boolean checkNormalEmulator() {
        StringBuffer deviceInfo = new StringBuffer("");
        deviceInfo.append(
                "Build.MODEL = " + Build.MODEL
                        + "; Build.BRAND = " + Build.BRAND
                        + "; Build.FINGERPRINT = " + Build.FINGERPRINT
                        + "; Build.TIME = " + Build.TIME
                        + "; Build.DEVICE = " + Build.DEVICE
                        + "; Build.MANUFACTURER = " + Build.MANUFACTURER
                        + "; Build.PRODUCT = " + Build.PRODUCT
                        + "; Build.TAGS = " + Build.TAGS
                        + "; Build.TYPE = " + Build.TYPE
                        + "; Build.UNKNOWN = " + Build.UNKNOWN
                        + "; Build.USER = " + Build.USER
                        + "; Build.BOARD = " + Build.BOARD
                        + "; Build.BOOTLOADER = " + Build.BOOTLOADER
                        + "; Build.DISPLAY = " + Build.DISPLAY
                        + "; Build.HARDWARE = " + Build.HARDWARE
                        + "; Build.HOST = " + Build.HOST
                        + "; Build.ID = " + Build.ID
                        + "; Build.SDK_INT = " + Build.VERSION.SDK_INT
                        + "; Build.SDK = " + Build.VERSION.SDK
                        + "; Build.VERSION.BASE_OS = " + Build.VERSION.BASE_OS
                        + "; Build.VERSION.CODENAME = " + Build.VERSION.CODENAME
                        + "; Build.VERSION.INCREMENTAL = " + Build.VERSION.INCREMENTAL
                        + "; Build.VERSION.RELEASE = " + Build.VERSION.RELEASE
                        + "; Build.VERSION.SECURITY_PATCH = " + Build.VERSION.SECURITY_PATCH
                        + "; Build.VERSION.PREVIEW_SDK_INT = " + Build.VERSION.PREVIEW_SDK_INT

        );

        Log.e(TAG, "checkNormalEmulator deviceInfo: " + deviceInfo.toString());

        boolean result = //
                Build.FINGERPRINT.startsWith("generic")//
                        || Build.FINGERPRINT.startsWith("unknown")//
                        || Build.MODEL.contains("google_sdk")//
                        || Build.MODEL.contains("Emulator")//
                        || Build.MODEL.contains("Android SDK built for x86")
                        || Build.MANUFACTURER.contains("Genymotion");
        if (result)
            return true;
        result |= Build.BRAND.startsWith("generic")
                && Build.DEVICE.startsWith("generic");
        if (result)
            return true;
        result |= "google_sdk".equals(Build.PRODUCT);
        return result;
    }

    private void showHashKey(Context context) {
        try {
            PackageInfo info;
            String packagename = BuildConfig.APPLICATION_ID;
            info = context.getPackageManager().getPackageInfo(packagename, PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String mHashKey = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e(TAG, "HashKey:" + mHashKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
    }
}
