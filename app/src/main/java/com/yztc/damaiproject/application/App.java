package com.yztc.damaiproject.application;

import android.app.Application;
import android.content.Context;

import com.yztc.damaiproject.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wanggang on 2017/3/14.
 */

public class App extends Application {

    private static Context context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        //加快EventBus 执行速度
        EventBus.builder().addIndex(new MyEventBusIndex())
                .installDefaultEventBus();
    }
}
