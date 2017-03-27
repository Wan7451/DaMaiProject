package com.yztc.damaiproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yztc.damaiproject.application.App;

/**
 * Created by wanggang on 2017/3/16.
 */

public class NetUtils {

    public static boolean isNetConnect() {
        ConnectivityManager mgr = (ConnectivityManager) App.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mgr.getActiveNetworkInfo();
        return networkInfo != null;
    }

}
