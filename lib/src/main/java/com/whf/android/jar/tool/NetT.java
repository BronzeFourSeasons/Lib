package com.whf.android.jar.tool;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Network related tool classes
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class NetT {

    /**
     * Exception acquisition
     */
    private NetT() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Determine whether the network is connected
     *
     * @param context:Context
     * @return Connection
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determine whether it is a WiFi connection
     *
     * @param context:Context
     * @return Is the WiFi connection?
     */
    public static boolean isWifi(Context context) {
        if (isConnected(context)) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            return null != cm && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * Open the network settings interface
     *
     * @param context:Context
     */
    public static void openNetSetting(Context context) {
        context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
    }

    /**
     * Open the WiFi settings interface
     *
     * @param context:Context
     */
    public static void openWifiSetting(Context context) {
        context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    /**
     * Open the 4G settings interface
     *
     * @param context:Context
     */
    public static void open4GSetting(Context context) {
        context.startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
    }

}
