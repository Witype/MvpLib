package com.witype.sample.module;

import android.os.Handler;

import com.witype.romvp.impl.BasePresenter;
import com.witype.sample.contract.ILoginContract;


/**
 * Created by WiType on 2016/9/27.
 * Email witype716@gmail.com
 * Desc:
 */

public class MainPresenter extends BasePresenter<ILoginContract.ILoginView> implements ILoginContract.ILoginPresenter {

    public MainPresenter(ILoginContract.ILoginView iLoginView) {
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
