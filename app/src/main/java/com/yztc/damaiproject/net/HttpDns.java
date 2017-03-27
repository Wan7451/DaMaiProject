package com.yztc.damaiproject.net;

import android.text.TextUtils;
import android.util.Log;

import com.yztc.damaiproject.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wanggang on 2017/3/16.
 */

public class HttpDns implements Dns {

    private static OkHttpClient httpDnsclient;

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        //防御代码
        if (hostname == null) throw new UnknownHostException("hostname == null");
        //dnspod提供的dns服务
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http")
                .host("119.29.29.29")
                .addPathSegment("d")
                .addQueryParameter("dn", hostname)
                .build();
        Request dnsRequest = new Request.Builder().url(httpUrl).get().build();
        try {
            String s = getHTTPDnsClient().newCall(dnsRequest).execute().body().string();
            //避免服务器挂了却无法查询DNS
            if (TextUtils.isEmpty(s)) {
                return Dns.SYSTEM.lookup(hostname);
            }
            return Arrays.asList(InetAddress.getAllByName(s));
        } catch (IOException e) {
            return Dns.SYSTEM.lookup(hostname);
        }
    }

    static public synchronized OkHttpClient getHTTPDnsClient() {
        if (httpDnsclient == null) {
            File dnsCacheFloder = FileUtils.getDnsCacheFloder();
            httpDnsclient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Response originalResponse = chain.proceed(chain.request());
                            return originalResponse.newBuilder()
                                    //在返回header中加入缓存消息
                                    //下次将不再发送请求
                                    .header("Cache-Control", "max-age=600").build();
                        }
                    })
                    //5MB的文件缓存
                    .cache(new Cache(dnsCacheFloder, 5 * 1024 * 1024))
                    .build();
        }
        return httpDnsclient;
    }

}
