package com.freeray.otaku.activity;

import android.os.Bundle;
import android.util.Log;

import com.freeray.otaku.R;
import com.freeray.otaku.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    public String TAG = "abv";

    //github test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //github repo test
    @Override
    public void  logInfo(){
        Log.d(TAG, "onCreate: "+TAG);
    }
}