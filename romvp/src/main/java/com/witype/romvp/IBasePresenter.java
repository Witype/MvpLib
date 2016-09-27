package com.witype.romvp;

import android.content.Context;

/**
 * Created by WiType on 2016/9/13.
 * Email witype716@gmail.com
 * Desc:
 */

public interface IBasePresenter<T> {

    void onCreate();

    void onDestroy();

    boolean isDestroy();

    T  getView();

    Context getContext();
}
