package com.whf.android.jar.net;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import io.reactivex.Observable;

/**
 * 带进度 下载  拦截器
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */

public interface ProgressDownloadService {

    /**
     * @param url:完整的地址
     * @return ResponseBody
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
