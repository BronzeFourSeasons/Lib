package com.whf.android.jar.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * BaseExpandableListAdapter
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public abstract class BaseExpandableAdapter<T, Q> extends BaseExpandableListAdapter {

    protected List<T> mGroup;
    protected List<Q> mChild;
    protected Context mContext;
    private LinkedHashMap<Integer, List<Q>> mChildHashMap;

    /**
     * @param context:上下文对象
     * @param mList:List    Date
     */
    public BaseExpandableAdapter(Context context, List<T> mList, LinkedHashMap<Integer, List<Q>> childHashMap) {
        super();
        this.mContext = context;
        this.mGroup = mList;
        this.mChildHashMap = childHashMap;
    }

    /**
     * @param mList:List Date
     * @param childHashMap:List Date
     */
    public BaseExpandableAdapter(List<T> mList, LinkedHashMap<Integer, List<Q>> childHashMap) {
        super();
        this.mGroup = mList;
        this.mChildHashMap = childHashMap;
    }

    /**
     * 一级列表个数
     */
    @Override
    public int getGroupCount() {
        return mGroup == null ? 0 : mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildHashMap.get(groupPosition) == null ? 0 : mChildHashMap.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildHashMap.get(groupPosition) == null ? null : mChildHashMap.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        mChild = mChildHashMap.get(groupPosition);
        return getChildView(childPosition, isLastChild, convertView, parent);
    }

    /**
     * 重写子布局
     *
     * @param childPosition:子布局ID
     * @param isLastChild:是否
     * @param convertView:view
     * @param parent:ViewGroup
     * @return convertView
     */
    public abstract View getChildView(int childPosition, boolean isLastChild, View convertView, ViewGroup parent);

}
