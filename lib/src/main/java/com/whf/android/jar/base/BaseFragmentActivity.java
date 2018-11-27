package com.whf.android.jar.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;


/**
 * Fragment+Activity的
 * 基础类
 *
 * @author haifeng.wang
 * @ 汪海峰 .
 * @since 2.5.0
 */
public abstract class BaseFragmentActivity extends AppCompatActivity {

    protected ArrayList<Fragment> myFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewId());
        myFragments = myFragments();
        initAllMembersView(savedInstanceState);
        initViewPager();
    }

    /**
     * ViewPager + RadioGroup
     * 实现点击切换和滑动切换的初始化
     */
    protected void initViewPager() {
        FragPageAdapter mAdapter = new FragPageAdapter(getSupportFragmentManager(),
                myViewPager(), myFragments, myRadioButton());
        myViewPager().setAdapter(mAdapter);
        myRadioGroup().setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton mRButton = findViewById(checkedId);
                int i = 0;
                for (RadioButton mRB : myRadioButton()) {
                    if (mRB == mRButton) {
                        myViewPager().setCurrentItem(i);
                    }
                    i++;
                }
            }
        });
    }


    /**
     * 让Fragment可以获得返回值
     *
     * @param requestCode 返回值标识
     * @param resultCode  返回状态
     * @param data        返回值
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : myFragments) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    /**
     * 滑动适配器
     */
    public class FragPageAdapter extends FragmentPagerAdapter
            implements ViewPager.OnPageChangeListener {

        private ViewPager mPager = null;
        private ArrayList<Fragment> mFragments = null;
        private ArrayList<RadioButton> mRadioButton = null;

        private FragPageAdapter(FragmentManager fm, ViewPager pager,
                                ArrayList<Fragment> frags, ArrayList<RadioButton> mRadioButton) {
            super(fm);
            this.mPager = pager;
            this.mFragments = frags;
            this.mRadioButton = mRadioButton;
            this.mPager.addOnPageChangeListener(this);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        /**
         * 当滑动状态改变时调用
         * state=0的时候表示什么都没做，就是停在那
         * state=1的时候表示正在滑动
         * state==2的时候表示滑动完毕了
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            //当滑动状态改变时调用
        }

        /**
         * 当前页面被滑动时调用
         * position:当前页面
         * positionOffset:当前页面偏移的百分比
         * positionOffsetPixels:当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //当前页面被滑动时调用
        }

        @Override
        public void onPageSelected(int position) {
            //当新的页面被选中时调用
            mRadioButton.get(position).setChecked(true);
        }
    }

    /**
     * 加载布局文件
     *
     * @return 布局文件 如:R.layout.activity_main
     */
    protected abstract int getContentViewId();


    /**
     * 获得下面的选择控件
     *
     * @return RadioGroup控件 如：(RadioGroup) findViewById(R.id.radioGroup1)
     */
    protected abstract RadioGroup myRadioGroup();

    /**
     * 获得中间可滑动控件
     *
     * @return ViewPager控件 如：(ViewPager) findViewById(R.id.id_ViewPager)
     */
    protected abstract ViewPager myViewPager();


    /**
     * 获得RadioButton集合
     *
     * @return ArrayList
     */
    protected abstract ArrayList<RadioButton> myRadioButton();

    /**
     * Fragment集合
     *
     * @return ArrayList<Fragment>
     */
    protected abstract ArrayList<Fragment> myFragments();

    /**
     * 里面是初始化
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void initAllMembersView(Bundle savedInstanceState);

}
