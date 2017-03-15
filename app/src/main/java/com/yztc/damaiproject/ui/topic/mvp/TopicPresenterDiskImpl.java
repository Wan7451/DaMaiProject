package com.yztc.damaiproject.ui.topic.mvp;

import com.yztc.damaiproject.cache.CacheObserver;
import com.yztc.damaiproject.net.NetConfig;
import com.yztc.damaiproject.net.NetObserver;
import com.yztc.damaiproject.net.RequestParams;
import com.yztc.damaiproject.ui.topic.TopicBean;

import java.util.List;

/**
 * Created by wanggang on 2017/3/14.
 */

public class TopicPresenterDiskImpl implements ITopicPresenter {

    private ITopicModel model;
    private ITopicView view;

    public TopicPresenterDiskImpl(ITopicView view) {
        this.view = view;
        model = new TopicModelDiskImpl();
    }

    int page;

    @Override
    public void loadData() {
        page = 1;
        String url = new RequestParams.Builder()
                .baseUrl(NetConfig.BASE_URL)
                .path("ProjLst.aspx")
                .params("cc", "0")
                .params("ps", "20")
                .params("mc", "0")
                .params("ot", "0")
                .params("v", "0")
                .params("p", page + "")
                .params("cityId", "852")
                .build()
                .generateUrl();

        //1 从本地取数据
        model.getDataFromCache(url).subscribe(new CacheObserver<List<TopicBean>>() {
            @Override
            public void onNext(List<TopicBean> data) {
                view.fillData(data, true);
            }
        });


        //2 判断是否超时  60s
        if (!model.isTimeOut(url, 60 * 1000)) {
            view.hideLoading();
            return;
        }

        //3 网球请求
        view.showLoading();
        model.getDataFromNet(url)
                .subscribe(new NetObserver<List<TopicBean>>(view.getContext()) {
                    @Override
                    public void onNext(List<TopicBean> data) {
                        super.onNext(data);
                        view.hideLoading();
                        view.fillData(data, true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.hideLoading();
                        view.showError(e.getMessage());
                    }
                });
    }

    @Override
    public void addData() {
        page++;


        String url = new RequestParams.Builder()
                .baseUrl(NetConfig.BASE_URL)
                .path("ProjLst.aspx")
                .params("cc", "0")
                .params("ps", "20")
                .params("mc", "0")
                .params("ot", "0")
                .params("v", "0")
                .params("p", page + "")
                .params("cityId", "852")
                .build()
                .generateUrl();

        //1 从本地取数据
        model.getDataFromCache(url)
                .subscribe(new CacheObserver<List<TopicBean>>() {
                    @Override
                    public void onNext(List<TopicBean> data) {
                        view.fillData(data, false);
                    }
                });

        //2 判断是否超时
        if (!model.isTimeOut(url, 60 * 1000)) {
            view.hideLoading();
            return;
        }

        //3 网球请求
        view.showLoading();
        model.getDataFromNet(url)
                .subscribe(new NetObserver<List<TopicBean>>(view.getContext()) {
                    @Override
                    public void onNext(List<TopicBean> data) {
                        super.onNext(data);
                        view.hideLoading();
                        view.fillData(data, false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.hideLoading();
                        view.showError(e.getMessage());
                    }
                });
    }
}
