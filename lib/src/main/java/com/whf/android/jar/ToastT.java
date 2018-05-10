package com.whf.android.jar;

import android.content.Context;
import android.widget.Toast;

/**
 * For simplifying and unifying prompt information
 *
 * @author wang.hai.fang
 * @author qf
 * @author wang.hai.fang
 * @link For simplifying and unifying prompt information
 * @code class
 * @since 2.5.0
 */
public final class ToastT {

    /**
     * Long time cue
     *
     * @param context:Context
     * @param obj:Object
     */
    public static void makeTextLong(Context context, Object obj) {
        Toast.makeText(context, "" + obj, Toast.LENGTH_LONG).show();
    }

    /**
     * Short time hints
     *
     * @param context:Context
     * @param obj:Object
     */
    public static void makeTextShort(Context context, Object obj) {
        Toast.makeText(context, "" + obj, Toast.LENGTH_SHORT).show();
    }

}
