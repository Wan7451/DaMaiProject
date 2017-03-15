package com.yztc.damaiproject.ui.topic.mvp;

import android.content.Context;

import com.yztc.damaiproject.ui.topic.TopicBean;

import java.util.List;

/**
 * Created by wanggang on 2017/3/14.
 * <p>
 * MPV
 * v  view 控制ui界面的展示
 * 加载中的效果
 * 加载失败的效果
 * 加载完成的效果
 * 显示错误提示
 * 显示操作提示.....
 */

public interface ITopicView {
    void fillData(List<TopicBean> data, boolean isPull);

    void showError(String error);

    void showLoading();

    void hideLoading();

    Context getContext();
}
