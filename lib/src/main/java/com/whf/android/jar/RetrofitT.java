package com.whf.android.jar;

import android.support.annotation.NonNull;

import com.whf.android.jar.net.BaseUrlInterceptor;
import com.whf.android.jar.net.OnProgressListener;
import com.whf.android.jar.net.ProgressDownloadInterceptor;
import com.whf.android.jar.tool.InterceptorT;
import com.whf.android.jar.tool.Observer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
    private static final int DEFAULT_TIMEOUT = 15;

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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }
    /**
     * Getting Retrofit objects
     *
     * @return Retrofit
     */
    protected static Retrofit getBaseRetrofit(@NonNull String baseUrl) {
        mOkHttpClient = getOkHttpClient(baseUrl);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        return mRetrofit;
    }

    /**
     * Getting Retrofit objects
     *
     * @return Retrofit
     */
    protected static Retrofit getRetrofit(@NonNull String baseUrl, OnProgressListener listener) {
        if (null == mRetrofit) {
            /**
             * 进度 下载  拦截器
             */
            ProgressDownloadInterceptor mInterceptor = new ProgressDownloadInterceptor(listener);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(mInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
    private static OkHttpClient getOkHttpClient2() {
        return new OkHttpClient.Builder()
                .addInterceptor(new InterceptorT())   //Add interceptor
                .connectTimeout(10, TimeUnit.SECONDS)        //Set connection timeout
                .readTimeout(30, TimeUnit.SECONDS)           //Set read timeout
                .writeTimeout(30, TimeUnit.SECONDS)          //Set write timeout
                .build();
    }
    
    @NonNull
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient
                .Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                        return chain.proceed(builder.build());
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                //Set read timeout
                .readTimeout(300, TimeUnit.SECONDS)
                //Set write timeout
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();
    }

    @NonNull
    private static OkHttpClient getOkHttpClient(String baseUrl) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(new BaseUrlInterceptor(baseUrl))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                        return chain.proceed(builder.build());
                    }
                })
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
    }
}
