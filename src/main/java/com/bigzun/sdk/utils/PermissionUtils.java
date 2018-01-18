package com.bigzun.sdk.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.bigzun.sdk.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    static final String TAG = "PermissionUtils";

    public static boolean allowedPermission(Context context, String permission) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            }
        } catch (SecurityException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
        return true;
    }

    public static boolean declinedPermission(Context context, String permission) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED;
            }
        } catch (SecurityException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
        return false;
    }

    public static void requestPermission(Activity activity, String permission, int requestCode) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                activity.requestPermissions(new String[]{permission}, requestCode);
        } catch (SecurityException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
    }

    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                activity.requestPermissions(permissions, requestCode);
        } catch (SecurityException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
    }

    public static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void requestOverlayDrawPermission(Activity act, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
        act.startActivityForResult(intent, requestCode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isSystemAlertGranted(Context context) {
        try {
            return Settings.canDrawOverlays(context);
        } catch (NoSuchMethodError e) {
            Log.e(TAG, e);
        } catch (SecurityException e) {
            Log.e(TAG, e);
        } catch (Exception e) {
            Log.e(TAG, e);
        }
        return true;
    }

    public static List<String> declinedPermissions(Activity activity, String[] permissions) {
        List<String> permissionsNeededs = new ArrayList<>();
        for (String permission : permissions) {
            if (declinedPermission(activity, permission)) {
                permissionsNeededs.add(permission);
            }
        }
        return permissionsNeededs;
    }

    public static boolean allowedPermissionStorage(Activity activity, int requestCode) {
        if (declinedPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE, requestCode);
            return false;
        }
        return true;
    }

}