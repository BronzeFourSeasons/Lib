package com.whf.android.jar.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.whf.android.jar.base.BaseApplication;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Local storage tools class
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class BaseUrlInterceptor implements Interceptor {

    private String mBaseUrl;

    public BaseUrlInterceptor() {
    }

    public BaseUrlInterceptor(@NonNull String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers(RestService.BASE_URL);
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader(RestService.BASE_URL);
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            if (RestService.OTHER.equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(BaseApplication.getBaseUrl());
            } else if (RestService.BASE_V.equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(mBaseUrl);
            }
            if (newBaseUrl != null) {
                //重建新的HttpUrl，修改需要修改的url部分
                HttpUrl newFullUrl = oldHttpUrl
                        .newBuilder()
                        //更换网络协议
                        .scheme(newBaseUrl.scheme())
                        //更换主机名
                        .host(newBaseUrl.host())
                        //更换端口
                        .port(newBaseUrl.port())
                        .build();
                //重建这个request，通过builder.url(newFullUrl).build()；
                // 然后返回一个response至此结束修改
                Log.i("Url", "intercept: " + newFullUrl.toString());
                return chain.proceed(builder.url(newFullUrl).build());
            }
        }
        return chain.proceed(request);
    }
}
