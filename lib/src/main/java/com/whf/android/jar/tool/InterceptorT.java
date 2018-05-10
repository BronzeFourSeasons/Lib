package com.whf.android.jar.tool;

import android.content.DialogInterface;

import com.whf.android.jar.DialogT;
import com.whf.android.jar.base.BaseApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Class name：InterceptorT
 * Class description：An interceptor that encapsulates a network state of OkHttp3
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class InterceptorT implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetT.isConnected(BaseApplication.getContext())) {
            DialogT.init().showNormalDialog(BaseApplication.getContext(), 0, "设置", "连接超时，请检查网络设置。", "设置", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    NetT.open4GSetting(BaseApplication.getContext());
                }
            }, null);
            return null;
        }
        return chain.proceed(request);
    }
}