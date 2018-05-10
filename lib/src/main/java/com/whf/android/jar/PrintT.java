package com.whf.android.jar;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.v4.print.PrintHelper;
import android.text.TextUtils;
import android.webkit.WebView;

/**
 * Print related settings page
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class PrintT {

    /**
     * print pictures
     *
     * @param act:Context
     * @param fileName:Startup code
     * @param id:content       code
     */
    public static void printBitmap(Context act, String fileName, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(act.getResources(), id);
        printBitmap(act, fileName, bitmap);
    }

    /**
     * print pictures
     *
     * @param act:Context
     * @param fileName:Startup code
     * @param bitmap:content
     */
    public static void printBitmap(Context act, String fileName, Bitmap bitmap) {
        if (PrintHelper.systemSupportsPrint()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                PrintHelper photoPrinter = new PrintHelper(act);
                photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
                photoPrinter.printBitmap(fileName, bitmap);
            } else {
                LogT.e("请更新安卓系统到4.4及其以上");
                ToastT.makeTextShort(act, "请更新安卓系统到4.4及其以上");
            }
        } else {
            LogT.e("系统不支持打印");
            ToastT.makeTextShort(act, "系统不支持打印");
        }
    }

    /**
     * Print web page
     *
     * @param act:Context
     * @param fileName:Startup code
     * @param webView:content
     */
    public static void printHtml(Context act, String fileName, WebView webView) {
        if (PrintHelper.systemSupportsPrint()) {
            PrintDocumentAdapter adapter = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                adapter = webView.createPrintDocumentAdapter();
                PrintManager printManager = (PrintManager) act.getSystemService(Context.PRINT_SERVICE);
                printManager.print(fileName, adapter, null);
            } else {
                LogT.e("请更新安卓系统到4.4及其以上");
                ToastT.makeTextShort(act, "请更新安卓系统到4.4及其以上");
            }
        } else {
            LogT.e("系统不支持打印");
            ToastT.makeTextShort(act, "系统不支持打印");
        }
    }

    /**
     * @param act
     * @param packageName
     * @return
     */
    private boolean hasGoogleCloudPrint(Activity act, String packageName) {
        PackageManager pm = act.getPackageManager();
        try {
            if (TextUtils.isEmpty(packageName)) {
                packageName = "com.google.Android.apps.cloudprint";
            }
            pm.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;

        }
    }

}
