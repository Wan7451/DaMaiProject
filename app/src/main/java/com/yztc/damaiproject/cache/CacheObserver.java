package com.yztc.damaiproject.cache;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wanggang on 2017/3/15.
 */

public abstract class CacheObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onError(Throwable e) {

    }
    @Override
    public void onComplete() {

    }
}
