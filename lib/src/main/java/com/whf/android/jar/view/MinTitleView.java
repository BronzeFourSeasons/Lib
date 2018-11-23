package com.whf.android.jar.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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

    private TextView text, textBack, textViewMore;
    private ImageView imageViewMore;
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
        imageViewMore = getView(R.id.image_more);
        textViewMore = getView(R.id.tv_more);

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

    @Override
    public void setOnClickListener(View.OnClickListener l) {
        onClickListener = l;
    }

    public void setOnClickMoreListener(View.OnClickListener l) {
        findViewById(R.id.id_more).setOnClickListener(l);
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
        String titleBack = a.getString(R.styleable.MinTitleView_textBack);
        String title = a.getString(R.styleable.MinTitleView_android_text);
        int titleColor = a.getColor(R.styleable.MinTitleView_color, 0xFF1E90FF);
        String textMore = a.getString(R.styleable.MinTitleView_textMore);
        int resMore = a.getResourceId(R.styleable.MinTitleView_moreImage, 0xff1e90ff);
        int is = a.getInteger(R.styleable.MinTitleView_isMoreDisplay, -3);
        if (title != null && !"".equals(title)) {
            setText(title);
        }
        if (titleBack != null && !"".equals(titleBack)) {
            setTextBack(titleBack);
        }
        text.setBackgroundColor(titleColor);
        BarColorT.initSystemBar((Activity) getContext(), titleColor);

        if (textMore != null && !"".equals(textMore)) {
            textViewMore.setText(textMore);
        }
        if (resMore != 0) {
            imageViewMore.setImageResource(resMore);
        }
        switch (is) {
            case -1:
                imageViewMore.setVisibility(VISIBLE);
                break;
            case -2:
                textViewMore.setVisibility(VISIBLE);
                break;
            default:
                imageViewMore.setVisibility(GONE);
                textViewMore.setVisibility(GONE);
                break;
        }
    }

    /**
     * 设置标题
     */
    public void setText(String title) {
        text.setText(title);
    }

    /**
     * 设置返回展示
     */
    public void setTextBack(String titleBack) {
        textBack.setText(titleBack);
    }

    /**
     * 设置标题颜色
     */
    public void setTextColor(int color) {
        textBack.setTextColor(color);
    }

    /**
     * 设置返回颜色
     */
    public void setTextBackColor(int color) {
        text.setTextColor(color);
    }

    /**
     * 设置背景颜色
     */
    @Override
    public void setBackgroundResource(int resId) {
        textBack.setBackgroundResource(resId);
    }

    /**
     * 设置背景颜色
     */
    @Override
    public void setBackgroundColor(int color) {
        textBack.setBackgroundColor(color);
    }

    /**
     * 设置背景颜色
     */
    @Override
    public void setBackground(Drawable background) {
        textBack.setBackground(background);
    }
}
