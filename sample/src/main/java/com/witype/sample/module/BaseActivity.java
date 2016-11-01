package com.witype.sample.module;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.witype.romvp.IBasePresenter;
import com.witype.romvp.IBaseView;
import com.witype.romvp.impl.BasePresenter;

/**
 * Created by WiType on 2016/9/27.
 * Email witype716@gmail.com
 * Desc:
 */

public abstract class BaseActivity<T extends IBasePresenter> extends Activity implements IBaseView {

    private T presenter;

    private long lastStartActivityTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        presenter.onCreate();
    }

    @SuppressWarnings("All")
    public T initPresenter() {
        presenter = (T) new BasePresenter<>(this);
        return presenter;
    }

    @Override
    public T getPresenter() {
        return presenter;
    }

    @Override
    public Activity getAppActivity() {
        return this;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (System.currentTimeMillis() - lastStartActivityTime > 1000) {
            lastStartActivityTime = System.currentTimeMillis();
            super.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((IBasePresenter)presenter).onDestroy();
        presenter = null;
    }

    @Override
    public void onNetError() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void showProgress(String message, DialogInterface.OnCancelListener listener) {

    }

    @Override
    public void shouldShowProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void shouldDismissProgress() {

    }
}
