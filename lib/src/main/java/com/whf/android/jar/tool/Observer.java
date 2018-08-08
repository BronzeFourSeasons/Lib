package com.whf.android.jar.tool;

import com.whf.android.jar.base.BaseApplication;

import java.net.ConnectException;
import java.net.SocketException;
import retrofit2.HttpException;

/**
 * exception handling
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public abstract class Observer<T> implements io.reactivex.Observer<T> {

    public abstract void onError(String error);

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            if (code == 404 || code == 500) {
                onError("服务器错误，请稍后重试");
            }
        } else if (e instanceof ConnectException) {
            onError(NetT.isConnected(BaseApplication.getContext()) ? "连接不到服务器，请稍后重试" : "连接不到服务器，请检查网路");
        } else if (e instanceof SocketException) {
            onError("连接服务器超时，请稍后重试");
        } else {
            onError("未知错误:" + e.getMessage());
        }
    }

}
