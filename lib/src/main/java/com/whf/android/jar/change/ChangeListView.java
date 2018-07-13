package com.whf.android.jar.change;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Incomplete display for nested nesting
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class ChangeListView extends ListView {

    /**
     * @param context:Context
     */
    public ChangeListView(Context context) {
        super(context);
    }

    /**
     * @param context:Context
     * @param attrs:AttributeSet
     */
    public ChangeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
