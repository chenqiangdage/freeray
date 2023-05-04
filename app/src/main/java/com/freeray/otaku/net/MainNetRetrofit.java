package com.freeray.otaku.net;

import android.os.FileUtils;

import com.freeray.otaku.base.commonconst.Url;
import com.freeray.otaku.util.LogUtil;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;


public class MainNetRetrofit {
    public BaseNetService baseNetService;

    public static final String DemoUrl = Url.DemoUrl;

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    MainNetRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        okhttp3.Response orginalResponse = chain.proceed(chain.request());
                        return orginalResponse.newBuilder()
                                .body(new ProgressResponseBody(orginalResponse.body(), new ProgressListener() {
                                    @Override
                                    public void onProgress(long progress, long total, boolean done) {
                                        LogUtil.d( "onProgress: " + "total ---->" + total + "done ---->" + progress );
                                    }
                                }))
                                .build();
                    }
                })
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        /*OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);*/

        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(DemoUrl)
                .build();

        baseNetService = retrofit.create(BaseNetService.class);
    }

    public BaseNetService getService(){
        return baseNetService;
    }

}
