package com.whf.android.jar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Dialog
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class DialogT {

    public static DialogT init() {
        return new DialogT();
    }

    public void showNormalDialog(@NonNull Context context,
                                 @DrawableRes int iconId,
                                 @NonNull String setTitle,
                                 @NonNull String setMessage,
                                 @NonNull String setPositive,
                                 DialogInterface.OnClickListener onPositive) {
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        normalDialog.setIcon(iconId);
        normalDialog.setTitle(setTitle);
        normalDialog.setMessage(setMessage);
        if(onPositive!= null) {
            normalDialog.setPositiveButton(setPositive, onPositive);
        }
        normalDialog.show();
    }

    public void showNormalDialog(@NonNull Context context,
                                 @DrawableRes int iconId,
                                 @NonNull String setTitle,
                                 @NonNull String setMessage,
                                 @NonNull String setPositive,
                                 @NonNull String setNegative,
                                 DialogInterface.OnClickListener onPositive,
                                 DialogInterface.OnClickListener onNegative) {
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        if (iconId != 0) {
            normalDialog.setIcon(iconId);
        }
        normalDialog.setTitle(setTitle);
        normalDialog.setMessage(setMessage);
        if(onPositive!= null) {
            normalDialog.setPositiveButton(setPositive, onPositive);
        }
        if(onNegative!= null) {
            normalDialog.setNegativeButton(setNegative, onNegative);
        }
        normalDialog.show();
    }

    /**
     * Waiting  Dialog
     *
     * @param context:Context
     * @param setTitle:Title
     * @param setMessage:Message
     */
    public void showWaitingDialog(@NonNull Context context,
                                  @NonNull String setTitle,
                                  @NonNull String setMessage) {
        ProgressDialog waitingDialog = new ProgressDialog(context);
        waitingDialog.setTitle(setTitle);
        waitingDialog.setMessage(setMessage);
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

}