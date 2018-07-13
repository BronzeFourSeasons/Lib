package com.whf.android.jar.change;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Viewpager sliding function is prohibited
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class ChangeNoViewPager extends ViewPager {

    /**
     * The default is static sliding
     */
    private boolean noScroll = true;

    public ChangeNoViewPager(Context context) {
        super(context);
    }

    public ChangeNoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Setting requires static sliding
     *
     * @param noScroll:true
     */
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return !noScroll && super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return !noScroll && super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


}
