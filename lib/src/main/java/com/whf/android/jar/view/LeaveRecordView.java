package com.whf.android.jar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whf.android.jar.R.drawable;
import com.whf.android.jar.R.id;
import com.whf.android.jar.R.layout;
import com.whf.android.jar.R.styleable;
import com.whf.android.jar.base.BaseCommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 休假记录类控件
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class LeaveRecordView extends ListView implements AdapterView.OnItemClickListener {

    //标题数组
    private String[] Vacation = {"item 0", "item 1", "item 2"};
    // 上下文
    private Context context;
    //适配器
    private LeaveRecordAdapter adapter;
    //设置item颜色
    private int itemColor;
    //设置item间距的颜色
    private Drawable divider;
    //设置item间距
    private int dividerHeight;
    //设置点击事件
    private OnClickListener listener;

    public LeaveRecordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // 获得控件属性
        TypedArray typed = context.obtainStyledAttributes(attrs, styleable.LeaveRecordView);//TypedArray是一个数组容器
        itemColor = (int) typed.getColor(styleable.LeaveRecordView_itemColor, 0x00000000);
        divider = typed.getDrawable(styleable.LeaveRecordView_itemDivider);
        dividerHeight = typed.getDimensionPixelSize(styleable.LeaveRecordView_itemDividerHeight,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
        //初始化控件及属性
        initView();
    }

    /**
     * 设置点击事件
     */
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        listener.oClick(i, Vacation[i]);
    }

    /**
     * 初始化控件属性
     */
    private void initView() {
        List<ViewInfo> ls = new ArrayList<>();
        for (String v : Vacation) {
            ViewInfo info = new ViewInfo(drawable.shape_ash,v, "按半天请假");
            ls.add(info);
        }
        adapter = new LeaveRecordAdapter(ls);
        this.setAdapter(adapter);
        if (divider != null) {
            this.setDivider(divider);
        }
        if (dividerHeight != 0) {
            this.setDividerHeight(dividerHeight);
        }
        this.setOnItemClickListener(this);
    }

    /**
     * 设置数据
     */
    public void setLeaveRecordAdapter( List<ViewInfo> ls){
        adapter = new LeaveRecordAdapter(ls);
        this.setAdapter(adapter);
    }

    /**
     * 自定义控件的适配器
     */
    private class LeaveRecordAdapter extends BaseCommonAdapter<ViewInfo> {

        List<ViewInfo> mList;

        private LeaveRecordAdapter(List<ViewInfo> mList) {
            super(mList);
            this.mList = mList;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(layout.item_leave_record, null);
                holder.layout = (RelativeLayout) view.findViewById(id.id_item_leave_record);
                holder.imageView = (ImageView) view.findViewById(id.id_imageView);
                holder.textTitle = (TextView) view.findViewById(id.id_textView);
                holder.textContent = (TextView) view.findViewById(id.id_textView_content);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            if (itemColor != 0x00000000) { //颜色不为透明,则设置背景名称.
                holder.layout.setBackgroundColor(itemColor);
            }
            holder.imageView.setImageResource(mList.get(position).getResId());
            holder.textTitle.setText(mList.get(position).getStrTitle());
            holder.textContent.setText(mList.get(position).getStrContent());
            return view;
        }
    }

    /**
     * 点击接口
     */
    public interface OnClickListener {
        void oClick(int i, String text);
    }

    /**
     * 数据实体类
     */
    
    public static class ViewInfo {
        private int resId = drawable.shape_ash;
        private String strTitle = "", strContent = "";

        public ViewInfo() {
            
        }
        
        public ViewInfo(int resId,String strTitle, String strContent) {
            this.resId = resId;
            this.strTitle = strTitle;
            this.strContent = strContent;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public String getStrTitle() {
            return strTitle;
        }

        public void setStrTitle(String strTitle) {
            this.strTitle = strTitle;
        }

        public String getStrContent() {
            return strContent;
        }

        public void setStrContent(String strContent) {
            this.strContent = strContent;
        }
    }
    /**
     * 控件实体类
     */
    private class ViewHolder {
        RelativeLayout layout;
        ImageView imageView;
        TextView textTitle, textContent;
    }

}
