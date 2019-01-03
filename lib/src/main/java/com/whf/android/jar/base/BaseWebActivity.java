package com.whf.android.jar.base;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.whf.android.jar.LogT;

/**
 * 网页de父类
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class BaseWebActivity extends AppCompatActivity {

    private final static String MIME_TYPE = "text/html";
    private final static String ENCODING = "utf-8";
    private WebSettings webSettings;
    protected WebView webView;


    @SuppressLint("SetJavaScriptEnabled")
    public void loadWeb(String url) {
        if (webView == null) {
            return;
        }
        webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        LogT.i(url);
        /* 此方法可以在webView中打开链接而不会跳转到外部浏览器 */
        webView.setWebViewClient(new AppWebViewClients());
        webView.setBackgroundColor(0); // 设置背景色
        webView.loadDataWithBaseURL(null, url, MIME_TYPE, ENCODING, null);
        /*支持弹窗，也就是支持html网页弹框*/
        webView.setWebChromeClient(new AppWebChromeClient());
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onResume() {
        super.onResume();
        if (webSettings != null) {
            webSettings.setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (webSettings != null) {
            webSettings.setJavaScriptEnabled(false);
        }
    }

    /**
     * 重载onKeyDown的函数，
     * 使其在页面内回退,而不是直接退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView != null && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class AppWebViewClients extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }
    }

    private class AppWebChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

    }

}
