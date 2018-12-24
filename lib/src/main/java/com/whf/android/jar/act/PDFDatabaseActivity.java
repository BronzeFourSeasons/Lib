package com.whf.android.jar.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.whf.android.jar.LogT;
import com.whf.android.jar.R;
import com.whf.android.jar.ToastT;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * dpf预览页
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class PDFDatabaseActivity extends AppCompatActivity implements DownloadFile.Listener {

    /**
     * PDF网页传值标识
     */
    public final static String PDF_URL = "PDF_URL";

    private RelativeLayout pdf_root;
    private ProgressBar pb_bar;
    private RemotePDFViewPager remotePDFViewPager;
    private String mUrl = "file:///android_asset/AdobeXML.pdf";
    private PDFPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hai_layout_pdf);
        initView();
    }


    protected void initView() {
        pdf_root = (RelativeLayout) findViewById(R.id.remote_pdf_root);
        pb_bar = (ProgressBar) findViewById(R.id.pb_bar);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            mUrl = b.getString(PDF_URL);
        }
        LogT.i(mUrl);
        setDownloadListener();
    }

    /*设置监听*/
    protected void setDownloadListener() {
        remotePDFViewPager = new RemotePDFViewPager(this, mUrl, this);
        remotePDFViewPager.setId(R.id.pdfViewPager);
    }

    /*加载成功调用*/
    @Override
    public void onSuccess(String url, String destinationPath) {
        pb_bar.setVisibility(View.GONE);
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        updateLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.close();
        }
    }

    /*更新视图*/
    private void updateLayout() {
        pdf_root.removeAllViewsInLayout();
        pdf_root.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    /*加载失败调用*/
    @Override
    public void onFailure(Exception e) {
        if (pb_bar != null) {
            pb_bar.setVisibility(View.GONE);
        }
        LogT.e("异常>>" + e.getMessage());
        ToastT.makeTextShort(this, "文件未找到,可能已删除");
    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}
