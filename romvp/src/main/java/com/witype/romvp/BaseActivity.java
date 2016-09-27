package com.witype.romvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.witype.romvp.impl.BasePresenter;

/**
 * Created by WiType on 2016/9/27.
 * Email witype716@gmail.com
 * Desc:
 */

public abstract class BaseActivity<T> extends Activity implements IBaseView {

    private T presenter;

    private long lastStartActivityTime;

    @SuppressWarnings("all")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (T) initPresenter();
        ((IBasePresenter)presenter).onCreate();
    }

    public IBasePresenter initPresenter() {
        return new BasePresenter<>(this);
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
}
