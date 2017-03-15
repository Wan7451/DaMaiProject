package com.yztc.damaiproject.net;

import android.app.ProgressDialog;
import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wanggang on 2017/3/13.
 */

public class NetObserver<T> implements Observer<T> {

    private Context context;
    private ProgressDialog progressDialog;

    public NetObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
    }

    @Override
    public void onNext(T data) {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onError(Throwable e) {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onComplete() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}
