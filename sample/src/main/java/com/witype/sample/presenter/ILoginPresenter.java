package com.witype.sample.presenter;

import com.witype.romvp.IBaseView;

/**
 * Created by WiType on 2016/9/27.
 * Email witype716@gmail.com
 * Desc:
 */

public interface ILoginPresenter {

    void doLogin();

    interface ILoginView  extends IBaseView {

        void onLoginSuccess();
    }
}
