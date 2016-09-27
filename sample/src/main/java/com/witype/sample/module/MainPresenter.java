package com.witype.sample.module;

import android.os.Handler;

import com.witype.romvp.impl.BasePresenter;
import com.witype.sample.presenter.ILoginPresenter;


/**
 * Created by WiType on 2016/9/27.
 * Email witype716@gmail.com
 * Desc:
 */

public class MainPresenter extends BasePresenter<ILoginPresenter.ILoginView> implements ILoginPresenter {

    public MainPresenter(ILoginPresenter.ILoginView iLoginView) {
        super(iLoginView);
    }

    @Override
    public void doLogin() {
        //TODO Login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getView().onLoginSuccess();
            }
        }, 1000);
    }
}
