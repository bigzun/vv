package com.bigzun.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.bigzun.sdk.R;
import com.bigzun.sdk.dialog.DialogNotice;

/**
 * Dialog Utils Created by neo on 2/15/2016.
 */
public class DialogUtils {

    /**
     * Show alert message with message id
     */
    public static void showErrorAlert(final Activity activity, String title, String message, String positiveMessage, final DialogInterface.OnClickListener onClickListener) {

        if (activity == null || activity.isFinishing()) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.dialog_confirm_notice);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton(positiveMessage, onClickListener);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();

    }

    /**
     * Show dialog when network error
     *
     * @param activity Activity logParams dialog
     */
    public static void showNetworkErrorDialog(final Activity activity) {

        // Check context valid
        if (!isValidContext(activity)) {
            Log.e("DialogUtils", "not valid");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.dialog_confirm_notice);
        builder.setMessage(activity.getString(R.string.msg_network_error));
        builder.setTitle(activity.getString(R.string.title_network_lost));
        builder.setPositiveButton(activity.getString(R.string.title_setting),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!activity.isFinishing()) {
                            activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    }
                });

        builder.setNegativeButton(activity.getString(R.string.button_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();

    }

    /**
     * Check validation of context
     */
    public static boolean isValidContext(Context context) {
        // Context null
        if (context == null) {
            Log.e("DialogUtils", "isValidContext--context == null");
            return false;
        }

        // Activity is finishing
        if (context instanceof Activity
                && ((Activity) context).isFinishing()) {
            Log.e("DialogUtils", "isValidContext--Activity is finishing");
            return false;
        }

        // Otherwise, context is valid to show dialog
        return true;
    }

    public static void showLoginAgainDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.dialog_confirm_notice);
        builder.setMessage(activity.getString(R.string.msg_login_session_expried));
        builder.setTitle(activity.getString(R.string.title_error));
        builder.setPositiveButton(activity.getString(R.string.button_login_again),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.setNegativeButton(activity.getString(R.string.button_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();

    }

    public static void showNotice(final Activity activity, final String title
            , final DialogNoticeListener listener, boolean oneButton) {
        DialogNotice dialog = new DialogNotice(activity) {
            @Override
            public String setMessage() {
                return title;
            }

            @Override
            public void setActionYes() {
                if (listener != null)
                    listener.clickActionYes();
                dismiss();
            }

            @Override
            public void setActionNo() {
                if (listener != null)
                    listener.clickActionNo();
                dismiss();
            }

        };
        dialog.setCanceledOnTouchOutside(oneButton);
        dialog.setCancelable(oneButton);
        if (oneButton) {
            dialog.setOneButton();
        }
        dialog.show();
    }

    public static void showNoticeViewedMovie(final Activity activity, final String title
            , final DialogNoticeListener listener, String nameButtonYes, String nameButtonNo) {
        DialogNotice dialog = new DialogNotice(activity) {
            @Override
            public String setMessage() {
                return title;
            }

            @Override
            public void setActionYes() {
                if (listener != null)
                    listener.clickActionYes();
                dismiss();
            }

            @Override
            public void setActionNo() {
                if (listener != null)
                    listener.clickActionNo();
                dismiss();
            }

        };
        dialog.setNameButtonNo(nameButtonNo);
        dialog.setNameButtonYes(nameButtonYes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    public interface DialogNoticeListener {
        void clickActionYes();

        void clickActionNo();
    }

}
