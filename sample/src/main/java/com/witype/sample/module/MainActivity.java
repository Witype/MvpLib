package com.witype.sample.module;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.witype.romvp.BaseActivity;
import com.witype.romvp.IBasePresenter;
import com.witype.sample.R;
import com.witype.sample.presenter.ILoginPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements ILoginPresenter.ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().doLogin();
            }
        });
    }

    @Override
    public IBasePresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onLoginSuccess() {
        showToast("登录成功");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
