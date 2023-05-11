package com.freeray.otaku.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.freeray.otaku.presenter.BasePresenter;

public abstract class BaseActivity<P extends BasePresenter>  extends AppCompatActivity implements BaseInterface{

    private static  final  String TAG="Base";
    //未指定类型的Presenter
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        logInfo();
        super.onCreate(savedInstanceState);
    }


    /**
     * 需要所 有子类实现的方法
     * 记录进入当前活动页的基本信息
     */
    public abstract void  logInfo();
    protected abstract void initPresenter(Intent intent);
    //设置布局
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();
    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }
}
