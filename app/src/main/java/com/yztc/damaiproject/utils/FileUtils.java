package com.yztc.damaiproject.utils;

import android.os.Environment;

import com.yztc.damaiproject.application.App;

import java.io.File;

/**
 * Created by wanggang on 2017/3/14.
 */

public class FileUtils {


    private static File getCahceRootFiloder() {
        if (Environment.isExternalStorageEmulated()) {
            //sdcard/.android/data/包名/cahce
            return App.getContext().getExternalCacheDir();
        } else {
            //data/data/包名/cahce
            return App.getContext().getCacheDir();
        }
    }

    public static File getJsonCacheFloder(){
        File rootFiloder = getCahceRootFiloder();
        File cache=new File(rootFiloder,"json");
        if(!cache.exists()){
            cache.mkdirs();
        }
        return cache;
    }

    public static File getDnsCacheFloder(){
        File rootFiloder = getCahceRootFiloder();
        File cache=new File(rootFiloder,"dns");
        if(!cache.exists()){
            cache.mkdirs();
        }
        return cache;
    }

    public static File getNetCacheFloder(){
        File rootFiloder = getCahceRootFiloder();
        File cache=new File(rootFiloder,"net");
        if(!cache.exists()){
            cache.mkdirs();
        }
        return cache;
    }

}
