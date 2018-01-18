package com.bigzun.sdk.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Network utilities Created by neo on 3/23/2016.
 */
@SuppressLint("MissingPermission")
public class NetworkUtils {
    static final String TAG = "NetworkUtils";

    public static boolean checkNetwork(final Activity activity) {
        boolean isCon = isConnected(activity);
        if (!isCon) {
            DialogUtils.showNetworkErrorDialog(activity);
        }
        return isCon;
    }

    public static boolean isConnected(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isMobileConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
            // connected to the mobile provider's data plan
            return true;
        }
        // not connected to the internet
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
            // connected to the mobile provider's data plan
            return true;
        }
        // not connected to the internet
        return false;
    }

    public static String getNetworkInfo(Context context) {
        String networkInfo = "unknown";
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                int type = activeNetwork.getType();
                String typeName = activeNetwork.getTypeName();
                int subType = activeNetwork.getSubtype();
                String subtypeName = activeNetwork.getSubtypeName();
                String extraInfo = activeNetwork.getExtraInfo();
                String reason = activeNetwork.getReason();
                if (type == ConnectivityManager.TYPE_WIFI) {
                    networkInfo = "Wifi";
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    switch (subType) {
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                            networkInfo = "NETWORK_TYPE_1xRTT";
                            break;
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                            networkInfo = "NETWORK_TYPE_CDMA";
                            break;
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                            networkInfo = "NETWORK_TYPE_EDGE";
                            break;
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                            networkInfo = "NETWORK_TYPE_EVDO_0";
                            break;
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                            networkInfo = "NETWORK_TYPE_EVDO_A";
                            break;
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                            networkInfo = "NETWORK_TYPE_GPRS - 2G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                            networkInfo = "NETWORK_TYPE_HSDPA - 3.5G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                            networkInfo = "NETWORK_TYPE_HSPA - 3.5G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                            networkInfo = "NETWORK_TYPE_HSUPA - 3.5G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                            networkInfo = "NETWORK_TYPE_UMTS - 3G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                            networkInfo = "NETWORK_TYPE_EHRPD";
                            break;
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                            networkInfo = "NETWORK_TYPE_EVDO_B";
                            break;
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            networkInfo = "NETWORK_TYPE_HSPAP";
                            break;
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            networkInfo = "NETWORK_TYPE_IDEN";
                            break;
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            networkInfo = "NETWORK_TYPE_LTE";
                            break;
                        case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                            networkInfo = "Unknown";
                            break;
                        default:
                            break;
                    }
                }
                Log.i(TAG, "type: " + type + " | typeName: " + typeName
                        + " | subType: " + subType + " | subtypeName: " + subtypeName
                        + " | extraInfo: " + extraInfo + " | reason: " + reason
                        + " | networkInfo: " + networkInfo
                        + "\n======================================== getNetworkInfo"
                );
            }
        }

        return networkInfo;
    }

    public static boolean isConnectionFast(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                int type = activeNetwork.getType();
                String typeName = activeNetwork.getTypeName();
                int subType = activeNetwork.getSubtype();
                String subtypeName = activeNetwork.getSubtypeName();
                String extraInfo = activeNetwork.getExtraInfo();
                String reason = activeNetwork.getReason();
                Log.i(TAG, "type: " + type + " | typeName: " + typeName
                        + " | subType: " + subType + " | subtypeName: " + subtypeName
                        + " | extraInfo: " + extraInfo + " | reason: " + reason
                        + "\n======================================== isConnectionFast"
                );
                if (type == ConnectivityManager.TYPE_WIFI) {
                    return true;
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    switch (subType) {
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                            return false; // ~ 50-100 kbps
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                            return false; // ~ 14-64 kbps
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                            return false; // ~ 50-100 kbps
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                            return true; // ~ 400-1000 kbps
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                            return true; // ~ 600-1400 kbps
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                            return false; // 100 kbps
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                            return true; // ~ 2-14 Mbps
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                            return true; // ~ 700-1700 kbps
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                            return true; // ~ 1-23 Mbps
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                            return true; // ~ 400-7000 kbps
                        case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                            return true; // ~ 1-2 Mbps
                        case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                            return true; // ~ 5 Mbps
                        case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                            return true; // ~ 10-20 Mbps
                        case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                            return false; // ~25 kbps
                        case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                            return true; // ~ 10+ Mbps
                        // Unknown
                        case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                            return false;
                        default:
                            return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isReachable(Context context) {
        // First, check we have any sort of connectivity
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connMgr.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            // Some sort of connection is open, check if server is reachable
            try {
                URL url = new URL("http://www.google.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("User-Agent", "Android Application");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(2 * 1000); // Thirty seconds timeout in
                // milliseconds
                urlc.connect();
                if (urlc.getResponseCode() == 200) { // Good response
                    return true;
                } else { // Anything else is unwanted
                    return false;
                }
            } catch (Exception e) {
                Log.e(TAG, e);
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkInternetConenction(Context context) {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        try {
            if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED || connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING
                    || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                return true;
            } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED || connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, e);
        }
        return false;
    }

    public static String getIPAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress() && addr instanceof Inet4Address) {
                        return addr.getHostAddress().toUpperCase();
                    } else if (!addr.isLoopbackAddress() && addr instanceof Inet6Address) {
                        return addr.getHostAddress().toUpperCase();
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e);
        }
        return "";
    }
}
