package com.yztc.damaiproject.net;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wanggang on 2017/3/13.
 */

public class NetRequest {


    private static OkHttpClient client;


    public static OkHttpClient getHttpClient() {

        if (client != null) {
            return client;
        }

        synchronized (NetRequest.class) {
            if (client != null) {
                return client;
            }

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("======", message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(new BasicParamsInterceptor())//添加公共参数
                    .build();
        }
        return client;
    }

}
