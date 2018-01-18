package com.bigzun.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bigzun.sdk.utils.Log;

/**
 * Created by namnh40 on 12/21/2016.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("NetworkChangeReceiver onReceive ------------------------------------");
//        App application = App.getInstance();
//        application.setMobileConnected(NetworkUtils.isConnected(context));
//        application.setConnected(NetworkUtils.isMobileConnected(context));

    }
}
