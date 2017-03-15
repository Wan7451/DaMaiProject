package com.yztc.damaiproject.db;

import com.yztc.damaiproject.application.App;
import com.yztc.damaiproject.ui.topic.DaoMaster;
import com.yztc.damaiproject.ui.topic.DaoSession;
import com.yztc.damaiproject.ui.topic.TopicBeanDao;

/**
 * Created by wanggang on 2017/3/15.
 */

public class SQLManager {

    private static SQLManager instance;
    private final DaoSession daoSession;

    public static SQLManager getInstance() {
        if (instance == null) {
            synchronized (SQLManager.class) {
                if (instance == null)
                    instance = new SQLManager();
            }
        }
        return instance;
    }

    private SQLManager(){
        DaoMaster.DevOpenHelper openHelper =
                new DaoMaster.DevOpenHelper(App.getContext(), "data.db");

        DaoMaster master=new DaoMaster(openHelper.getWritableDb());
        daoSession = master.newSession();
    }


    public TopicBeanDao getTopicDao(){
        return daoSession.getTopicBeanDao();
    }
}
