package com.whf.android.jar.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whf.android.jar.BarColorT;
import com.whf.android.jar.R;

/**
 * General title bar
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class MinTitleView extends LinearLayout {

    private TextView text,textBack;
    private Context context;
    private AttributeSet attrs;

    public MinTitleView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public MinTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        initView();
        onTypedArray();
    }

    /**
     * init
     */
    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.hai_layout_min_title, this, true);
        text = (TextView) findViewById(R.id.id_textTitle);
        textBack = (TextView)findViewById(R.id.id_textBack);
        findViewById(R.id.id_return).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    /**
     * init
     */
    private void onTypedArray() {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MinTitleView);
        String title = a.getString(R.styleable.MinTitleView_text);
        int titleColor = a.getColor(R.styleable.MinTitleView_color, 0xff1e90ff);
        if (title != null && !"".equals(title)) {
            text.setText(title);
        }
        textBack.setBackgroundColor(titleColor);
        BarColorT.initSystemBar((Activity) getContext(), titleColor);
    }

}
