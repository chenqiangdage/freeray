package com.freeray.otaku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.freeray.otaku.R;
import com.freeray.otaku.base.BaseActivity;
import com.freeray.otaku.presenter.DemoPresenter;
import com.freeray.otaku.view.IDemoVIew;

public class LoginActivity extends BaseActivity<DemoPresenter> implements View.OnClickListener,IDemoVIew {

    public String TAG = "abv";

    //github test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_login:
                System.console().printf(mPresenter.demoApi());
            break;
            default:;
        }
    }

    //github repo test
    @Override
    public void  logInfo(){
        Log.d(TAG, "onCreate: "+TAG);
    }

    @Override
    protected void initPresenter(Intent intent) {
        mPresenter = new DemoPresenter(this,this,intent);
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void ShowToast(String t) {

    }
}