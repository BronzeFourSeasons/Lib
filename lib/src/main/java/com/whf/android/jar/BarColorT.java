package com.whf.android.jar;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Setting up the title bar
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class BarColorT {

    /**
     * 上下文对象
     */
    private static Activity activity;

    /**
     * 当'系统标题栏'下是纯色时
     * 改变系统标题栏颜色
     *
     * @param context:
     * @param color    color xml文件下的颜色
     */
    public static void initSystemBar(Activity context, @ColorInt int color) {
        activity = context;
        setWindowStatusBarColor(color);

    }

    /**
     * 当'系统标题栏'下是图片时
     * 改变系统标题栏颜色
     *
     * @param context:
     */
    public static void initImageBar(Activity context) {
        activity = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    /**
     * 设置系统标题栏的透明度
     *
     * @param on:
     */
    @SuppressLint("ResourceAsColor")
    @TargetApi(19)
    private static void setTranslucentStatus(boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            /* a|=b的意思就是把a和b按位或然后赋值给a
             * 按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b*/
            winParams.flags |= bits;
        } else {
            /* &是位运算里面，与运算
             * a&=b相当于 a = a&b  ~非运算符*/
            winParams.flags &= ~bits;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            win.setStatusBarColor(android.R.color.transparent);
        }
        win.setAttributes(winParams);
    }

    /**
     * Setting up the title bar
     *
     * @param colorResId:color
     */
    private static void setWindowStatusBarColor(@ColorInt int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colorResId);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                activity.getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
