package com.yztc.damaiproject.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanggang on 2017/3/14.
 */

public class BasicParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //加上公共参数
        HttpUrl url = request.url();

        url = url.newBuilder()
                .addQueryParameter("source", NetConfig.source)
                .addQueryParameter("appType", NetConfig.appType)
                .addQueryParameter("osType", NetConfig.osType)
                .addQueryParameter("version", NetConfig.version)
                .addQueryParameter("channel_from", NetConfig.channel_from)
                .build();

        request=request.newBuilder()
                .url(url)
                .build();

        return chain.proceed(request);
    }
}
