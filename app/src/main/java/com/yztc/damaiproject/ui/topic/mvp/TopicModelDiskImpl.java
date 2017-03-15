package com.yztc.damaiproject.ui.topic.mvp;

import com.alibaba.fastjson.JSONObject;
import com.yztc.damaiproject.cache.DiskCache;
import com.yztc.damaiproject.cache.DiskInFunction;
import com.yztc.damaiproject.cache.DiskOutFunction;
import com.yztc.damaiproject.net.NetFunction;
import com.yztc.damaiproject.ui.topic.TopicBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wanggang on 2017/3/14.
 */

public class TopicModelDiskImpl implements ITopicModel {

    @Override
    public Observable<List<TopicBean>> getDataFromNet(String path) {
        return Observable.just(path)
                .map(new DiskInFunction())//需要进行磁盘缓存
                .map(new TopicParseFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<TopicBean>> getDataFromCache(String path) {

        return Observable.just(path)
                .map(new DiskOutFunction())
                .map(new TopicParseFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public boolean isTimeOut(String path, long timeOut) {
        return DiskCache.getInstance().isTimeOut(path, timeOut);
    }


    public static class TopicParseFunction implements Function<String, List<TopicBean>> {
        @Override
        public List<TopicBean> apply(@NonNull String s) throws Exception {
            //JSON 解析
            JSONObject object = JSONObject.parseObject(s);
            String list = object.getString("l");
            return JSONObject.parseArray(list, TopicBean.class);
        }
    }

}
