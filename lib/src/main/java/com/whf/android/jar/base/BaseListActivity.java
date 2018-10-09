package com.whf.android.jar.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.whf.android.jar.LogT;
import com.whf.android.jar.R;
import com.whf.android.jar.view.MinTitleView;

import java.util.List;
import java.util.Map;

/**
 * 列表父类
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class BaseListActivity extends Activity {

    protected Context context;
    protected TextView tvTitle, tvMore;
    protected ListView mList;
    protected ProgressBar pbBar;
    protected ImageView serverAccess;
    protected MinTitleView returnTitle;
    protected SwipeRefreshLayout mSwipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hai_layout_list);
        context = this;
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tvTitle = getView(R.id.hai_text_title);
        tvMore = getView(R.id.hai_text_more);
        mList = getView(R.id.hai_list);
        pbBar = getView(R.id.hai_pb_bar);
        serverAccess = getView(R.id.hai_server_access);
        returnTitle = getView(R.id.hai_MinTitleView);
        mSwipe = getView(R.id.hai_main_srl);
        mSwipe.setColorSchemeResources(
                android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

    }

    /**
     * 通用findViewById,减少重复的类型转换
     */
    @SuppressWarnings("unchecked")
    public final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            LogT.e("Could not cast View to concrete class.");
            throw ex;
        }
    }

    protected void onAdapterSimple(List<? extends Map<String, ?>> data) {
        if (data != null && data.size() > 0) {
            SimpleAdapter saImageItems = new SimpleAdapter(
                    context,
                    data,
                    android.R.layout.simple_list_item_2,
                    new String[]{"itemText", "itemText2"},
                    new int[]{android.R.id.text1, android.R.id.text2});
            mList.setAdapter(saImageItems);
        }
    }

    protected void onAdapterArray(List<String> data) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                data);
        mList.setAdapter(adapter);
    }

}
