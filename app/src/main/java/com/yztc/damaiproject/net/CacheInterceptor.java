package com.yztc.damaiproject.net;

import com.yztc.damaiproject.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanggang on 2017/3/16.
 * get 请求有效
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetUtils.isNetConnect()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        //1  get 请求
        //2  头部字段
        Response response = chain.proceed(request);
        if (NetUtils.isNetConnect()) {
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control", "public, max-age=300")
                    .build();
        } else {
            int maxTime = 24 * 60 * 60;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control", "only-if-cached, max-stale=" + maxTime)
                    .build();
        }
    }
}
