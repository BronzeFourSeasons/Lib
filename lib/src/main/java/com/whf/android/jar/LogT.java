package com.whf.android.jar;

import android.util.Log;

import com.whf.android.jar.constants.IConstants;


/**
 * @author wang.hai.fang
 * @link (rewrite)android.util.Log
 * @code class
 * @since (Original position) android.util.Log
 */
public final class LogT {


    /**
     * log
     *
     * @param obj:Print string
     */
    public static void i(String obj) {
        if (obj != null) {
            System.out.print(IConstants.LOG_TAG + obj);
        } else {
            System.out.print(IConstants.LOG_TAG + "");
        }
    }

    /**
     * log
     *
     * @param obj:Print string
     */
    public static void d(String obj) {
        if (obj != null) {
            System.out.print(IConstants.LOG_TAG + obj);
        } else {
            System.out.print(IConstants.LOG_TAG + "");
        }
    }

    /**
     * log
     *
     * @param obj:Print string
     */
    public static void w(String obj) {
        if (obj != null) {
            Log.w(IConstants.LOG_TAG, "NO:" + obj);
        } else {
            Log.w(IConstants.LOG_TAG, "");
        }
    }

    /**
     * log
     *
     * @param obj:Print string
     */
    public static void e(String obj) {
        if (obj != null) {
            Log.e(IConstants.LOG_TAG, "NO:" + obj);
        } else {
            Log.e(IConstants.LOG_TAG, "");
        }
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
