package com.freeray.otaku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.freeray.otaku.R;
import com.freeray.otaku.base.BaseActivity;

public class MainActivity extends BaseActivity {


    public String TAG = "abcd";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
    @Override
    public void  logInfo(){
        Log.d(TAG, "onCreate: "+TAG);
    }

    @Override
    protected void initPresenter(Intent intent) {

    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}