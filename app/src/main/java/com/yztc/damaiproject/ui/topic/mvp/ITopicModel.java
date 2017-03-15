package com.yztc.damaiproject.ui.topic.mvp;

import com.yztc.damaiproject.ui.topic.TopicBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by wanggang on 2017/3/14.
 *
 * MVP
 * m  加载数据
 *    net,disk,sql,sp
 *
 */
public interface ITopicModel {
    Observable<List<TopicBean>> getDataFromNet(String path);
    Observable<List<TopicBean>> getDataFromCache(String path);
    boolean isTimeOut(String path,long timeOut);
}
