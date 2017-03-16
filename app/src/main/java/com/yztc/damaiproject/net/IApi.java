package com.yztc.damaiproject.net;

import com.yztc.damaiproject.ui.topic.TopicListBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by wanggang on 2017/3/16.
 */

public interface IApi {

//    @FormUrlEncoded
//    @POST("ProjLst.aspx")
//    Call<ResponseBody> listTopic(@Field("key") String v);

    @GET("ProjLst.aspx")
    Call<ResponseBody> listTopic1(@QueryMap HashMap<String,String> map);

    //ProjLst.aspx?
    //params    source=10101&cc=0&ps=20&mc=1&ot=0&v=0&appType=1&osType=2&p=1&version=50609&channel_from=xiaomi_market&cityId=852

    @GET("ProjLst.aspx")
    Call<String> listTopic2(@QueryMap HashMap<String,String> map);

    @GET("ProjLst.aspx")
    Call<TopicListBean> listTopic3(@QueryMap HashMap<String,String> map);

    @GET("ProjLst.aspx")
    Observable<TopicListBean> listTopic4(@QueryMap HashMap<String,String> map);

}
