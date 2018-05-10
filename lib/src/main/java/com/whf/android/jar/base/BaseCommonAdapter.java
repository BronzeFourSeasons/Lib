package com.whf.android.jar.base;


import android.widget.BaseAdapter;

import java.util.List;

/**
 * BaseAdapter
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public abstract class BaseCommonAdapter<T> extends BaseAdapter {

    protected List<T> mData;

    /**
     * @param mList:List Date
     */
    public BaseCommonAdapter(List<T> mList) {
        super();
        this.mData = mList;
    }

    /**
     * Total number
     */
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}