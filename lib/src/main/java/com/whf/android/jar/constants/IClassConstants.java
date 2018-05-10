package com.whf.android.jar.constants;

import android.content.Context;
import android.view.View;

/**
 * Jump target
 *
 * @author haifeng.wang
 * @since 2.5.0
 */
public interface IClassConstants extends View.OnClickListener {

    void startClass(Context context,Class c);
}
