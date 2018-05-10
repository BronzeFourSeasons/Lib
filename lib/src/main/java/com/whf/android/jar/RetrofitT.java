package com.whf.android.jar;

import android.support.annotation.NonNull;

import com.whf.android.jar.tool.InterceptorT;
import com.whf.android.jar.tool.Observer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class description：Encapsulates an abstract base class for retrofit integrated 0kHttp3
 *
 * @author wang.hai.fang
 * @since 2.5.0
 */
public abstract class RetrofitT {


    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * Getting Retrofit objects
     *
     * @return Retrofit
     */
    protected static Retrofit getRetrofit(@NonNull String baseUrl) {
        if (null == mRetrofit) {
            if (null == mOkHttpClient) {
                mOkHttpClient = getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }


    /**
     * Insert observer
     *
     * @param observable:Observable
     * @param observer:Observer
     * @param <T>                   :  T
     */
    protected static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread()) //Zi Xian cheng visits the network
                .observeOn(AndroidSchedulers.mainThread()) //Callback to main thread
                .subscribe(observer);
    }

    /**
     * Building OkHttpClient objects
     */
    @NonNull
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new InterceptorT())   //Add interceptor
                .connectTimeout(10, TimeUnit.SECONDS)        //Set connection timeout
                .readTimeout(30, TimeUnit.SECONDS)           //Set read timeout
                .writeTimeout(30, TimeUnit.SECONDS)          //Set write timeout
                .build();
    }

}