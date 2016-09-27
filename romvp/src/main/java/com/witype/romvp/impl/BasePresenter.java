package com.witype.romvp.impl;

import android.content.Context;

import com.witype.romvp.IBasePresenter;
import com.witype.romvp.IBaseView;
import com.witype.romvp.http.HttpSubscriber;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by WiType on 2016/9/13.
 * Email witype716@gmail.com
 * Desc:
 */

public class BasePresenter<T> implements IBasePresenter<T> {

    private Retrofit retrofit;

    private T t;

    private boolean isDestroy = true;

    public BasePresenter(T t) {
        this.t = t;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public void onCreate() {
        isDestroy = false;
    }

    @Override
    public void onDestroy() {
        t = null;
        isDestroy = true;
    }

    @Override
    public boolean isDestroy() {
        return isDestroy;
    }

    @Override
    public T getView() {
        return t;
    }

    @Override
    public Context getContext() {
        if (t instanceof IBaseView)
            return ((IBaseView) t).getApplicationContext();
        return null;
    }

    public <H> HttpSubscriber<H> toSubscribe(Observable<H> observable, final HttpSubscriber<H> httpSubscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        httpSubscriber.showProgressDialog();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);
        return httpSubscriber;
    }
}
