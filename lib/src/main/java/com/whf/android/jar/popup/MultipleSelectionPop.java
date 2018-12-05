package com.whf.android.jar.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whf.android.jar.R.id;
import com.whf.android.jar.R.layout;
import com.whf.android.jar.LogT;
import com.whf.android.jar.base.BaseCommonAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多选框
 *
 * @author Administrator
 * @date 2018/12/4
 */

public class MultipleSelectionPop extends PopupWindow implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Context context;
    private View mMenuView;
    private TextView textViewConfirm;
    private LayoutInflater inflater;
    private ListView mWheelView;
    private MultipleSelectionAdapter adapter;
    private OnReturnListener onReturnListener;

    /**
     * 带参构造函数
     *
     * @param context:Context
     */
    public MultipleSelectionPop(Context context) {
        super(context);
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mMenuView = inflater.inflate(layout.pop_multiple_selection, null);
        textViewConfirm = (TextView) mMenuView.findViewById(id.tv_confirm);
        mWheelView = (ListView) mMenuView.findViewById(id.id_WheelView);
        mWheelView.setOnItemClickListener(this);
        initAdapter();
        onSetPopup();
    }
    /**
     * 添加适配器
     */
    private void initAdapter() {
        textViewConfirm.setOnClickListener(this);
    }


    public void setItems(List<String> list) {
        adapter = new MultipleSelectionAdapter(context, list);
        mWheelView.setAdapter(adapter);
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
     * 设置回传接口
     *
     * @param onReturnListener:接口
     */
    public void setOnReturnListener(OnReturnListener onReturnListener) {
        this.onReturnListener = onReturnListener;
    }

    /**
     * 显示出来
     */
    public void show(View parent) {
        this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        if (adapter != null) {
            if (onReturnListener != null) {
                onReturnListener.onReturn(adapter.getText());
            }
            LogT.i(adapter.getText().toString());
        }
        dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        boolean isChecked = adapter.status().get(position);
        adapter.status().put(position,!isChecked);
        adapter.notifyDataSetChanged();

    }


    /**
     * 多选列表
     *
     * @author Administrator
     * @date 2018/12/4
     */

    public class MultipleSelectionAdapter extends BaseCommonAdapter<String> {

        private ViewHolder holder;
        private Map<Integer, Boolean> map;

        public MultipleSelectionAdapter(Context context, List<String> mList) {
            super(context, mList);
            loadMap();
        }

        @SuppressLint("UseSparseArrays")
        private void loadMap() {
            map = new HashMap<>(2);
            for (int i = 0; i < mData.size(); i++) {
                map.put(i, false);
            }
        }

        public Map<Integer, Boolean> status() {
            return map;
        }

        private void setMap(int integer, boolean bool) {
            map.put(integer, bool);
        }

        public List<String> getText() {
            List<String> mList = new ArrayList<>();
            for (int i = 0; i < mData.size(); i++) {
                if (map.get(i)) {
                    mList.add(mData.get(i));
                }
            }
            return mList;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(layout.hai_item_check, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(mData.get(position));
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    setMap(position, isChecked);
                }

            });

            holder.checkBox.setChecked(map.get(position));
            return convertView;
        }


    }

    private class ViewHolder {
        private CheckBox checkBox;
        private TextView textView;

        private ViewHolder(View view) {
            checkBox = view.findViewById(id.hai_check);
            textView = view.findViewById(id.hai_check_text);
        }
    }

    public interface OnReturnListener {

        /**
         * 回传选中的数据
         *
         * @param list:数据
         */
        void onReturn(List<String> list);
    }
}
