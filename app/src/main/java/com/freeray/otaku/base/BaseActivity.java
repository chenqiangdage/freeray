package com.freeray.otaku.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity  extends AppCompatActivity implements BaseInterface{

    private static  final  String TAG="Base";
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
}
