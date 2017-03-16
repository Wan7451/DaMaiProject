package com.yztc.damaiproject.ui.topic.mvp;

import com.yztc.damaiproject.db.SQLManager;
import com.yztc.damaiproject.net.NetFunction;
import com.yztc.damaiproject.ui.topic.TopicBean;
import com.yztc.damaiproject.ui.topic.TopicBeanDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wanggang on 2017/3/14.
 */

public class TopicModelDBImpl implements ITopicModel {

    @Override
    public Observable<List<TopicBean>> getDataFromNet(String path) {
        return Observable.just(path)
                .map(new TopicInFunction())//插入数据库
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    //从数据库读
    @Override
    public Observable<List<TopicBean>> getDataFromCache(String path) {
        return Observable.just(path)
                .map(new TopicOutFunction()) //取数据
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public boolean isTimeOut(String path, long timeOut) {
        TopicBeanDao topicDao = SQLManager.getInstance().getTopicDao();

        List<TopicBean> list = topicDao.queryBuilder()
                .where(TopicBeanDao.Properties.Key.eq(path))
                .limit(1)
                .list();

        if(list==null || list.size()==0){
            return true;//没有缓存数据
        }

        Long time = list.get(0).getTime();
        return System.currentTimeMillis()-time>timeOut;
    }



    public static class TopicOutFunction implements Function<String, List<TopicBean>> {
        @Override
        public List<TopicBean> apply(@NonNull String s) throws Exception {
            TopicBeanDao topicDao = SQLManager.getInstance().getTopicDao();
            return topicDao.queryBuilder()
                    .where(TopicBeanDao.Properties.Key.eq(s))
                    .list();
        }
    }

    public static class TopicInFunction implements Function<String, List<TopicBean>> {
        @Override
        public List<TopicBean> apply(@NonNull String s) throws Exception {

            //网络请求
            String json = new NetFunction().apply(s);

            //JSON 解析
            List<TopicBean> topicList = new TopicModelDiskImpl
                    .TopicParseFunction().apply(json);

            for (TopicBean bean : topicList) {
                bean.setKey(s);
                bean.setTime(System.currentTimeMillis());//插入数据库的时间
            }
            TopicBeanDao topicDao = SQLManager.getInstance().getTopicDao();
            List<TopicBean> old = topicDao.queryBuilder()
                    .where(TopicBeanDao.Properties.Key.eq(s))
                    .list();
            topicDao.deleteInTx(old);


            topicDao.insertOrReplaceInTx(topicList);//插入数据库
            return topicList;
        }
    }

}
