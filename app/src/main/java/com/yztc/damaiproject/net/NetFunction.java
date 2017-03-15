package com.yztc.damaiproject.net;

import com.yztc.damaiproject.cache.DiskCache;
import com.yztc.damaiproject.net.NetRequest;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanggang on 2017/3/13.
 */

public class NetFunction implements Function<String, String> {

    @Override
    public String apply(@NonNull String s) throws Exception {
        //网络请求
        OkHttpClient client = NetRequest.getHttpClient();
        Request request = new Request.Builder()
                .url(s)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
