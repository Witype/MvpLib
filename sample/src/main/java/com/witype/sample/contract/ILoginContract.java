package com.witype.sample.contract;

import com.witype.romvp.IBaseView;

/**
 * Created by WiType on 2016/9/27.
 * Email witype716@gmail.com
 * Desc:
 */

public interface ILoginContract {

    interface ILoginPresenter {
        void doLogin();
    }

    interface ILoginView extends IBaseView {

        void onLoginSuccess();
    }
}
