package com.whf.android.jar.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.whf.android.jar.R.id;
import com.whf.android.jar.R.layout;
import com.whf.android.jar.change.ChangeNoViewPager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期和时间选择器
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class DateTimePop extends PopupWindow implements CalendarView.OnDateChangeListener,
    TimePicker.OnTimeChangedListener, View.OnClickListener {

    private int TYPE = 0;
    private int resource;
    private View mMenuView,viewDate, viewTime;
    private CalendarView mCalendarView;
    private TimePicker mTimePicker;
    private TabLayout mTabLayout;
    private TextView tv_confirm;
    private ChangeNoViewPager mViewPager;
    private LayoutInflater inflater;
    private String strDate, strTime;
    private IListener iListener;
    private final static String[] titles = {"日期", "时间"};
    public final static int START_TIME = 11,END_TIME = 12,APPLY_TIME = 13,USE_TIME = 14;

    /**
     * 带参构造函数
     *
     * @param context:Context
     */
    public DateTimePop(Context context) {
        super(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        resource = id.view_main;
        mMenuView = inflater.inflate(layout.pop_date_time, null);
        viewDate = inflater.inflate(layout.item_date, null);
        viewTime = inflater.inflate(layout.item_time, null);
        tv_confirm = (TextView) mMenuView.findViewById(id.tv_confirm);
        mTabLayout = (TabLayout) mMenuView.findViewById(id.id_TabLayout);
        mViewPager = (ChangeNoViewPager) mMenuView.findViewById(id.id_ChangeNoViewPager);
        mCalendarView = (CalendarView) viewDate.findViewById(id.calendarView);
        mTimePicker = (TimePicker) viewTime.findViewById(id.timePicker);
        initAdapter();
        onSetPopup();
    }

    /**
     * 添加适配器
     */
    private void initAdapter() {
        mTabLayout.setupWithViewPager(mViewPager);
        List<View> viewList = new ArrayList<>();
        viewList.add(viewDate);
        viewList.add(viewTime);
        //实例化适配器
        AdapterViewpager viewAdapter = new AdapterViewpager(viewList);
        //设置适配器
        mViewPager.setAdapter(viewAdapter);
        mCalendarView.setOnDateChangeListener(this);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setOnTimeChangedListener(this);
        tv_confirm.setOnClickListener(this);
        initDateTime();
    }

     /**
     * 初始化时间
     */
    private void initDateTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");

        Date curDate = new Date(System.currentTimeMillis());
        strDate = formatterDate.format(curDate);
        strTime = formatterTime.format(curDate);
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
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xE0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }


    /**
     * 显示出来
     */
    public void show(View parent) {
        mViewPager.setCurrentItem(0);
        this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 日期选择
     */
    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
        //时间都是2位数( yyyy-MM-dd )
        month++;
        String sMonth = month < 10 ? ("0" + month) : "" + month;
        String sDayOfMonth = dayOfMonth < 10 ? ("0" + dayOfMonth) : "" + dayOfMonth;
        strDate = year + "-" + sMonth + "-" + sDayOfMonth;
        mViewPager.setCurrentItem(1);
    }

    /**
     * 时间选择
     */
    @Override
    public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
        //时间都是2位数( HH:mm )
        String sHourOfDay = hourOfDay < 10 ? ("0" + hourOfDay) : "" + hourOfDay;
        String sMinute = minute < 10 ? ("0" + minute) : "" + minute;
        strTime = sHourOfDay + ":" + sMinute;
    }

    /**
     * 设置确认的点击事件
     */
    public void setOnDateTimeListener(OnDateTimeListener onDateTimeListener) {
        this.iListener = onDateTimeListener;
    }

    /**
     * 选择标识
     *
     * @param strType:1
     */
    public void setStrType(int strType) {
        this.TYPE = strType;
    }


    @Override
    public void onClick(View view) {
        iListener.onDateTime(TYPE, strDate, strTime);
        this.dismiss();
    }

    /**
     * 适配器
     */
    public class AdapterViewpager extends PagerAdapter {
        private List<View> mViewList;

        private AdapterViewpager(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position % titles.length];
        }
    }

    /**
     * 接口
     */
    public interface OnDateTimeListener {
        void onDateTime(int TYPE, String strDate, String strTime);
    }

}

