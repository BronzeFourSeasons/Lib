package com.whf.android.jar.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whf.android.jar.R.id;
import com.whf.android.jar.R.layout;
import com.whf.android.jar.view.WheelView;

import java.util.ArrayList;

/**
 * 单列选择器
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class HolidayChoicePop extends PopupWindow implements View.OnClickListener {

    private int resource;
    private View mMenuView;
    private TextView textViewConfirm;
    private LayoutInflater inflater;
    private WheelView mWheelView;

    /**
     * 带参构造函数
     *
     * @param context:Context
     */
    public HolidayChoicePop(Context context) {
        super(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        resource = id.view_main;
        mMenuView = inflater.inflate(layout.pop_holiday_choice, null);
        textViewConfirm = (TextView) mMenuView.findViewById(id.tv_confirm);
        mWheelView = (WheelView) mMenuView.findViewById(id.id_WheelView);
        initAdapter();
        onSetPopup();
    }

    /**
     * 添加适配器
     */
    private void initAdapter() {
        textViewConfirm.setOnClickListener(this);
    }

    public void setOnWheelViewListener(WheelView.OnSelectListener onWheelViewListener){
        mWheelView.setOnSelectListener(onWheelViewListener);
    }

    /*
     * 性别设置
     */
    public void setSex(){
        ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        setItems(list);
    }
    
    /**
     * 数据设置
     */
    public void setItems(ArrayList<String> list) {
        mWheelView.setData(list);
    }


    /**
     * 窗体设置
     */
    private void onSetPopup() {
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80FFFFFF);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }


    /**
     * 显示出来
     */
    public void show(View parent) {
        this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
