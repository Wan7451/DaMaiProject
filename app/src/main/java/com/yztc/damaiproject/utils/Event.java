package com.yztc.damaiproject.utils;

/**
 * Created by wanggang on 2017/3/16.
 */

public class Event {


    public static final int LIST_TOPIC_OK=1;
    public static final int LIST_TOPIC_ERROR=2;

    public Event(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    private int type;
    private Object data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
