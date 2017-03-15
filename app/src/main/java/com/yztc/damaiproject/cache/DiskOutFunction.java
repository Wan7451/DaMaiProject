package com.yztc.damaiproject.cache;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by wanggang on 2017/3/15.
 */

public class DiskOutFunction implements Function<String,String> {
    @Override
    public String apply(@NonNull String s) throws Exception {
        //从 sd 取数据
        return DiskCache.getInstance().get(s);
    }
}
