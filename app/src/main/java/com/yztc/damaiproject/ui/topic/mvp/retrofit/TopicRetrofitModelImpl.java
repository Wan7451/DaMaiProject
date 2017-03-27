package com.yztc.damaiproject.ui.topic.mvp.retrofit;

import com.alibaba.fastjson.JSONObject;
import com.yztc.damaiproject.net.IApi;
import com.yztc.damaiproject.net.NetConfig;
import com.yztc.damaiproject.net.NetRequest;
import com.yztc.damaiproject.ui.topic.TopicBean;
import com.yztc.damaiproject.ui.topic.TopicListBean;
import com.yztc.damaiproject.utils.Event;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wanggang on 2017/3/16.
 */

public class TopicRetrofitModelImpl implements ITopicRetrofitModel {

    private final IApi iApi;

    public TopicRetrofitModelImpl() {
        this.iApi = NetRequest
                .getRetrofit()
                .create(IApi.class);
    }


    //回调方式
    public interface OnNetResponse {
        void onResponse(Object data);

        void onFail(String error);
    }

    public void getData(HashMap<String, String> params, final OnNetResponse callback) {
        Call<ResponseBody> call = iApi.listTopic1(params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    TopicListBean listBean = JSONObject.parseObject(json, TopicListBean.class);

                    //或者 使用 EventBus 把结果回调
                    if (callback != null) {
                        callback.onResponse(listBean.getL());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback != null) {
                    callback.onFail(t.getMessage());
                }
            }
        });
    }


    public void getDataEventBus(HashMap<String, String> params) {
        Call<ResponseBody> call = iApi.listTopic1(params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    TopicListBean listBean = JSONObject.parseObject(json, TopicListBean.class);
                    EventBus.getDefault().post(new Event(Event.LIST_TOPIC_OK, listBean));
                } catch (IOException e) {
                    e.printStackTrace();
                    EventBus.getDefault().post(new Event(Event.LIST_TOPIC_ERROR, e.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                EventBus.getDefault().post(new Event(Event.LIST_TOPIC_ERROR, t.getMessage()));
            }
        });
    }

    @Override
    public Observable<List<TopicBean>> getData1(HashMap<String, String> params) {
//        call.execute();  同步
//        call.enqueue(new Callback<ResponseBody>() );  异步  ui

        return Observable.just(params)
                .map(new Function<HashMap<String, String>, List<TopicBean>>() {
                    @Override
                    public List<TopicBean> apply(@NonNull HashMap<String, String> params) throws Exception {
                        Call<ResponseBody> call = iApi.listTopic1(params);
                        Response<ResponseBody> response = call.execute();
                        String json = response.body().string();
//                        TopicListBean listBean = JSONObject.parseObject(json, TopicListBean.class);
//                        List<TopicBean> topicBeanList = listBean.getL();
                        return JSONObject.parseObject(json, TopicListBean.class).getL();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<TopicBean>> getData2(HashMap<String, String> params) {

        return Observable.just(params)
                .map(new Function<HashMap<String, String>, List<TopicBean>>() {
                    @Override
                    public List<TopicBean> apply(@NonNull HashMap<String, String> params) throws Exception {
//                        Call<String> call = iApi.listTopic2(params);
//                        try {
//                            Response<String> response = call.execute();
//                            String json = response.body();
//                            return JSONObject.parseObject(json, TopicListBean.class).getL();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        Call<TopicListBean> call = iApi.listTopic3(params);
                        Response<TopicListBean> response = call.execute();
                        return response.body().getL();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<List<TopicBean>> getData3(HashMap<String, String> params) {
        return iApi.listTopic4(params)
                .map(new Function<TopicListBean, List<TopicBean>>() {
                    @Override
                    public List<TopicBean> apply(@NonNull TopicListBean list) throws Exception {
                        return list.getL();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
