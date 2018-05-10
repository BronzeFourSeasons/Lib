package com.whf.android.jar;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Open KeyBord and Close KeyBord
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class KeyBoardT {

    /**
     * Open KeyBord
     *
     * @param mEditText:View
     * @param mContext:Context
     */
    public static void openKeyBord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
        mEditText.setCursorVisible(true);
    }

    /**
     * Close KeyBoard
     *
     * @param mEditText:View
     * @param mContext:Context
     */
    public static void closeKeyBord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        mEditText.setCursorVisible(false);
    }

}
