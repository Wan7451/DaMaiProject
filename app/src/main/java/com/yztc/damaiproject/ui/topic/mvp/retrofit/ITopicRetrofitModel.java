package com.yztc.damaiproject.ui.topic.mvp.retrofit;

import com.yztc.damaiproject.ui.topic.TopicBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by wanggang on 2017/3/16.
 */

public interface ITopicRetrofitModel {
    Observable<List<TopicBean>> getData1(HashMap<String,String> params);
    Observable<List<TopicBean>> getData2(HashMap<String,String> params);
    Observable<List<TopicBean>> getData3(HashMap<String,String> params);

}
