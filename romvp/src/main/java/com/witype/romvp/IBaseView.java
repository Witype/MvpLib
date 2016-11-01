package com.witype.romvp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by WiType on 2016/9/13.
 * Email witype716@gmail.com
 * Desc:
 */

public interface IBaseView<T> {

    T getPresenter();

    Context getApplicationContext();

    Activity getAppActivity();

    void onNetError();

    void onError(String error);

    void showToast(String message);

    void showProgress(String message, DialogInterface.OnCancelListener listener);

    void shouldShowProgress();

    void dismissProgress();

    void shouldDismissProgress();
}
