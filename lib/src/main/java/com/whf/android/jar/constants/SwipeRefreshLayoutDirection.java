package com.whf.android.jar.constants;

/**
 * 上拉加载,下拉刷新
 * 是否需要
 *
 * @author Administrator
 */

public enum SwipeRefreshLayoutDirection {
    /**
     * 顶部
     */
    TOP(0),
    /**
     * 底部
     */
    BOTTOM(1),
    /**
     * 两者都
     */
    BOTH(2);

    /**
     * 顶部-0,底部-1,两者都-2;
     */
    private int mValue;

    /**
     * 顶部-0,底部-1,两者都-2;
     *
     * @param value:下标
     */
    SwipeRefreshLayoutDirection(int value) {
        this.mValue = value;
    }

    /**
     * 顶部-0,底部-1,两者都-2;
     *
     * @param value:下标
     */
    public static SwipeRefreshLayoutDirection getFromInt(int value) {
        for (SwipeRefreshLayoutDirection direction : SwipeRefreshLayoutDirection.values()) {
            if (direction.mValue == value) {
                return direction;
            }
        }
        return BOTH;
    }
}
