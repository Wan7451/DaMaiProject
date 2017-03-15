package com.yztc.damaiproject.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.yztc.damaiproject.application.App;

/**
 * Created by wanggang on 2017/3/14.
 */

public class PackageUtils {

    public static int getVersionCode(){
        PackageManager pm = App.getContext().getPackageManager();//context为当前Activity上下文
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(App.getContext().getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
