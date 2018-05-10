package com.whf.android.jar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator .
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class IntentT {

    /**
     * set
     *
     * @param index:class
     */
    public static Intent onIntent(Context context, Class index) {
        return onIntent(context, index, null);
    }

    /**
     * set
     *
     * @param index:class
     */
    public static Intent onIntent(Context context, Class index, Bundle extras) {
        Intent intent = new Intent();
        intent.setClass(context, index);
        if (extras != null) {
            intent.putExtras(extras);
        }
        return intent;
    }
}
