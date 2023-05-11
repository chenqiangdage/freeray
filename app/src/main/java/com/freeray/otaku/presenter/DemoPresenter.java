package com.freeray.otaku.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.freeray.otaku.model.DemoModel;
import com.freeray.otaku.view.IDemoVIew;

public class DemoPresenter  extends BasePresenter<IDemoVIew>{
    private Context context;
    private DemoModel mModel;

    public DemoPresenter(Activity context, IDemoVIew view, Intent intent){
        super(context, view);
        this.context = context;
        this.mModel = new DemoModel(context);
    }


    public String demoApi(){
       return mModel.demo();
    }
}
