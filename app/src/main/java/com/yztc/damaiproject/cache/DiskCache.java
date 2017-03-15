package com.yztc.damaiproject.cache;

import com.jakewharton.disklrucache.DiskLruCache;
import com.yztc.damaiproject.utils.FileUtils;
import com.yztc.damaiproject.utils.MD5Utils;
import com.yztc.damaiproject.utils.PackageUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by wanggang on 2017/3/14.
 */

public class DiskCache {

    private static DiskCache instance;
    private DiskLruCache diskLruCache;

    public static DiskCache getInstance() {
        if (instance != null)
            return instance;
        synchronized (DiskCache.class) {
            if (instance != null)
                return instance;
            instance = new DiskCache();
        }
        return instance;
    }

    private DiskCache() {
        File cacheFloder = FileUtils.getJsonCacheFloder();
        int versionCode = PackageUtils.getVersionCode();
        //DiskLruCache  1 key  url
        //                v     json      0
        //                v     timeout   1
        int maxSize = 30 * 1024 * 1024; //30m
        try {
            this.diskLruCache = DiskLruCache
                    .open(cacheFloder, versionCode, 2, maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 取缓存
     *
     * @param k
     * @return
     */
    public String get(String k) {
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(getCacheKey(k));
            if (snapshot == null)
                return "";
            String json = snapshot.getString(0);
            snapshot.close();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "";
    }

    /**
     * 是否超时
     *
     * @param k
     * @param timeOut
     * @return
     */
    public boolean isTimeOut(String k, long timeOut) {
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(getCacheKey(k));
            if (snapshot == null)
                return true;
            String time = snapshot.getString(1);
            snapshot.close();
            long timeMills = Long.parseLong(time);
            return System.currentTimeMillis() - timeMills > timeOut;//是否超时
        } catch (IOException e) {

        }
        return true;
    }

    /**
     * 存储
     *
     * @param k
     * @param v
     */
    public void set(String k, String v) {
        try {
            DiskLruCache.Editor edit = diskLruCache.edit(getCacheKey(k));
            edit.set(0, v);
            edit.set(1, System.currentTimeMillis() + "");//存时间
            edit.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getCacheKey(String k) {
        //key  url   /   不能作为文件名字      http://www.ww.www.jpg
        //md5  16进制的字符串                 f4067fac      ffff3453450
        return MD5Utils.hashKeyForDisk(k);
    }

}
