package com.witype.romvp.http;

import android.content.DialogInterface;

import com.witype.romvp.IBaseView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by WiType on 2016/4/25 0025.
 * tuteng
 */
public abstract class HttpSubscriber<T> extends Subscriber<T> implements DialogInterface.OnCancelListener {

    /* 是否显示错误提示，默认显示 */
    private boolean isShowToast     =   true;
    /* 是否显示等待框，默认显示 */
    private boolean isShowWait      =   true;
    /* 是否自动消失登陆框,默认自动消失 */
    private boolean isAutoDismiss   =   true;

    private IBaseView iBaseView;

    private String title = "请稍后";

    protected HttpSubscriber(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    public HttpSubscriber(IBaseView iBaseView, String title) {
        this.iBaseView = iBaseView;
        this.title = title;
    }

    /**
     *
     * @return
     */
    public HttpSubscriber<T> disableToast() {
        this.isShowToast = false;
        return this;
    }

    /**
     *
     * @return
     */
    public HttpSubscriber<T> disableWait() {
        this.isShowWait = false;
        return this;
    }

    /**
     *
     * @return
     */
    public HttpSubscriber<T> disableAutoDismiss() {
        this.isAutoDismiss = false;
        return this;
    }

    /**
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        iBaseView.dismissProgress();
        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            boolean isDeal = onApiFailure(exception);
            boolean isIntercept = onInterceptApiFailure(exception);
            if (isShowToast && !isDeal && !isIntercept) iBaseView.showToast(exception.getMessage());
        } else if (e instanceof SocketTimeoutException ||
                e instanceof ConnectException ||
                e instanceof HttpException ||
                e instanceof UnknownHostException) {
            boolean isDeal = onNetWorkError();
            if (isShowToast && !isDeal) {
                iBaseView.showToast("网络错误");
                iBaseView.onNetError();
            }
        } else {
            onError(e.getMessage());
        }
    }

    /**
     *
     */
    public void showProgressDialog() {
        if (isShowWait) iBaseView.showProgress(title,this);
        else iBaseView.shouldShowProgress();
    }

    /**
     *
     * @param dialog
     */
    @Override
    public void onCancel(DialogInterface dialog) {
        onCancel();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    private void dismissProgressDialog() {
        if (isShowWait && isAutoDismiss) iBaseView.dismissProgress();
        else iBaseView.shouldDismissProgress();
    }

    private void onCancel() {
        if (!isUnsubscribed()) {
            unsubscribe();
        }
    }

    /**
     *
     * @param t
     */
    @Override
    public void onNext(T t) {
        if (t instanceof ArrayList) {
            if (((ArrayList) t).size() > 0) onGetListSuccess(t);
            else onGetListNo();
        } else if (t instanceof List) {
            if (((List) t).size() > 0) onGetListSuccess(t);
            else onGetListNo();
        }
    }

    /**
     *
     * @param t
     */
    public void onGetListSuccess(T t) {}

    /**
     *
     */
    public void onGetListNo() {}

    /**
     *
     * @param exception
     * @return
     */
    public boolean onInterceptApiFailure(ApiException exception) {
        return false;
    }

    /**
     * 自定义错误
     * @return 对象是否自己处理了onApiFailure错误，{true} 实现类自己处理错误 {false} 由父类进行处理
     */
    public boolean onApiFailure(ApiException e) {
        return false;
    }

    public boolean onNetWorkError() {
        return false;
    }

    public void onError(String error) {
        iBaseView.onError(error);
    }

}
