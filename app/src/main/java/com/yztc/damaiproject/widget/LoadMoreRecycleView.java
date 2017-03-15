package com.yztc.damaiproject.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wanggang on 2017/3/14.
 */

public class LoadMoreRecycleView extends RecyclerView {
    public LoadMoreRecycleView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){

        addOnScrollListener(new OnScrollListener() {
            int lastPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //是否需要加载更多
                    if(getAdapter()==null)
                        return;

                    if (lastPosition == getAdapter().getItemCount() - 1) {
                        //上拉加载，判断是否是加载中的状态，
                        //如果正在加载，则不会再去加载
                        if (!isRefreshing && l!=null) {
                            isRefreshing=true;
                            l.loadMore();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取最有一个可见的Item
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (manager instanceof LinearLayoutManager) {
                    int lastVisibleItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
                    if (lastVisibleItemPosition != -1) {
                        lastPosition = lastVisibleItemPosition;
                    }
                }
            }
        });
    }


    //判断是否是加载的状态
    private boolean isRefreshing;

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    private OnLoadMoreListener l;

    //加载更多的监听器
    public void setOnLoadMoreListener(OnLoadMoreListener l) {
        this.l = l;
    }

    public interface OnLoadMoreListener{
        void loadMore();
    }
}
