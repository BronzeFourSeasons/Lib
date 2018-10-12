package com.whf.android.jar.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whf.android.jar.BarColorT;
import com.whf.android.jar.LogT;
import com.whf.android.jar.R;

/**
 * General title bar
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class MinTitleView extends LinearLayout {

    private TextView text, textBack;
    private Context context;
    private AttributeSet attrs;
    private View.OnClickListener onClickListener;

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
        text = getView(R.id.id_textTitle);
        textBack = getView(R.id.id_textBack);
        findViewById(R.id.id_return).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setOnClickListener(View.OnClickListener l) {
        onClickListener = l;
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

    /**
     * init
     */
    private void onTypedArray() {
        @SuppressLint("Recycle")
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MinTitleView);
        String title = a.getString(R.styleable.MinTitleView_text);
        int titleColor = a.getColor(R.styleable.MinTitleView_color, 0xff1e90ff);
        if (title != null && !"".equals(title)) {
            text.setText(title);
        }
        textBack.setBackgroundColor(titleColor);
        BarColorT.initSystemBar((Activity) getContext(), titleColor);
    }
    
    @Override
	public void setBackgroundResource(int resId) {
        textBack.setBackgroundResource(resId);
    }

    @Override
    public void setBackgroundColor(int color) {
        textBack.setBackgroundColor(color);
    }
    @Override
    public void setBackground(Drawable background) {
        textBack.setBackground(background);
    }
}
