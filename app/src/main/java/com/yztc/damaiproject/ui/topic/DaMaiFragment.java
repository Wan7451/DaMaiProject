package com.yztc.damaiproject.ui.topic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yztc.damaiproject.R;
import com.yztc.damaiproject.ui.topic.mvp.ITopicPresenter;
import com.yztc.damaiproject.ui.topic.mvp.ITopicView;
import com.yztc.damaiproject.ui.topic.mvp.TopicPresenterDBImpl;
import com.yztc.damaiproject.ui.topic.mvp.TopicPresenterDiskImpl;
import com.yztc.damaiproject.ui.topic.mvp.retrofit.TopicRetrofitPresenter;
import com.yztc.damaiproject.widget.LoadMoreRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DaMaiFragment extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener ,ITopicView, LoadMoreRecycleView.OnLoadMoreListener {


    @BindView(R.id.topic_recycle)
    LoadMoreRecycleView topicRecycle;
    @BindView(R.id.topic_swipe)
    SwipeRefreshLayout topicSwipe;
    private DaMaiAdapter adapter;
    private ArrayList<TopicBean> data = new ArrayList<>();
    private ITopicPresenter topicPresenter;


    public DaMaiFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_da_mai, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topicSwipe.setColorSchemeColors(
                Color.CYAN,
                Color.BLUE,
                Color.GREEN,
                Color.RED);
        topicSwipe.setOnRefreshListener(this);

        LinearLayoutManager mgr = new LinearLayoutManager(getContext());
        topicRecycle.setLayoutManager(mgr);

        DividerItemDecoration decoration =
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        topicRecycle.addItemDecoration(decoration);

        adapter = new DaMaiAdapter(getContext(), data);
        topicRecycle.setAdapter(adapter);

        topicRecycle.setOnLoadMoreListener(this);


        topicPresenter = new TopicRetrofitPresenter(this);

        topicPresenter.loadData();
    }

    @Override
    public void onRefresh() {
        topicPresenter.loadData();
    }
    @Override
    public void loadMore() {
        topicPresenter.addData();
    }


    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        topicSwipe.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        topicSwipe.setRefreshing(false);

    }
    @Override
    public void fillData(List<TopicBean> data, boolean isPull) {
        //下拉刷新。清除之前的数据
        if (isPull) {
            this.data.clear();
        }else {
            topicRecycle.setRefreshing(false);
        }
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
        topicSwipe.setRefreshing(false);
    }



}
