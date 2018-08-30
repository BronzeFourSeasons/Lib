package com.whf.android.jar;

import android.util.Log;


/**
 * @author wang.hai.fang
 * @link (rewrite)android.util.Log
 * @code class
 * @since (Original position) android.util.Log
 */
public final class LogT {

    private final static String TAG = "TAG";

    /**
     * log
     *
     * @param obj:Print string
     */
    public static void i(String obj) {
        obj += "";
        Log.i(TAG, obj);
    }

    /**
     * log
     *
     * @param obj:Print string
     */
    public static void d(String obj) {
        obj += "";
        Log.d(TAG, obj);
    }

    /**
     * log
     *
     * @param obj:Print string
     */
    public static void w(String obj) {
        obj += "";
        Log.w(TAG, obj);
    }

    /**
     * log
     *
     * @param obj:Print string
     */
    public static void e(String obj) {
        obj += "";
        Log.e(TAG, obj);
    }

    /**
     * log
     *
     * @param s:is      ok
     * @param obj:Print string
     */
    public static void is(boolean s, String obj) {
        if (s) {
            i(obj + "-OK-");
        } else {
            e(obj + "-NO-");
        }
    }
}
