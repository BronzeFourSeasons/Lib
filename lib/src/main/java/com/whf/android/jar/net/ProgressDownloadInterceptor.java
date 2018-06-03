package com.whf.android.jar.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * 带进度 下载  拦截器
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class ProgressDownloadInterceptor implements Interceptor {

    private OnProgressListener downloadListener;

    public ProgressDownloadInterceptor(OnProgressListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(new ProgressResponseBody(response.body(), downloadListener)).build();
    }
}
