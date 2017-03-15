package com.yztc.damaiproject.cache;

import com.yztc.damaiproject.net.NetFunction;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by wanggang on 2017/3/15.
 */

public class DiskInFunction implements Function<String, String> {
    @Override
    public String apply(@NonNull String s) throws Exception {

        String json = new NetFunction().apply(s);

        DiskCache.getInstance().set(s, json);
        //从 sd 取数据
        return json;
    }
}
