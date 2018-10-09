package com.whf.android.jar.act;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.whf.android.jar.LogT;
import com.whf.android.jar.R;

/**
 * 提示输入弹框
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */

public class DiaInputActivity extends Activity {

    public final static int INT_NO = 1521;
    public final static int INT_YES = 1530;
    public final static int INT_INTENT = 1550;
    public final static String KEY = "key";
    public final static String TITLE = "title";
    public final static String MESSAGE = "message";
    public final static String BUTTON_1 = "BUTTON_1";
    public final static String BUTTON_2 = "BUTTON_2";
    public final static String OPINION = "Opinion";

    private TextView titleTV;
    private EditText messageTv;
    private Button no, yes;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hai_layout_dialog_input);
        context = this;
        initView();
    }

    private void initView() {
        titleTV = getView(R.id.title);
        messageTv = getView(R.id.message);
        no = getView(R.id.cancel);
        yes = getView(R.id.confirm);
        initDate();
    }

    /**
     * 获得传传参
     */
    private void initDate() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE);
        if (!TextUtils.isEmpty(title)) {
            titleTV.setText(title);
        }
        String message = intent.getStringExtra(MESSAGE);
        if (!TextUtils.isEmpty(message)) {
            messageTv.setHint(message);
        }
        String btnNo = intent.getStringExtra(BUTTON_1);
        if (!TextUtils.isEmpty(btnNo)) {
            no.setText(btnNo);
        }
        String btnYes = intent.getStringExtra(BUTTON_2);
        if (!TextUtils.isEmpty(btnYes)) {
            yes.setText(btnYes);
        }
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaInputActivity.this.onClick(INT_NO);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaInputActivity.this.onClick(INT_YES);
            }
        });
    }

    /**
     * 通用findViewById,减少重复的类型转换
     */
    @SuppressWarnings("unchecked")
    public final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            LogT.e(ex.getMessage());
            throw ex;
        }
    }

    public void onClick(int var2) {
        String message = messageTv.getText().toString().trim();
        Intent intent = getIntent();
        Bundle build = intent.getExtras();
        if (build == null) {
            build = new Bundle();
        }
        build.putInt(KEY, var2);
        build.putString(OPINION, message);
        intent.putExtras(build);
        setResult(INT_INTENT, intent);
        finish();
    }

}
