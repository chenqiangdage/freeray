package com.freeray.otaku.model;

import android.app.Activity;

import com.freeray.otaku.entity.database.DaoHelper;
import com.freeray.otaku.entity.dto.DemoDto;
import com.freeray.otaku.net.BaseNetService;
import com.freeray.otaku.net.MainFactory;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import io.reactivex.Observable;

public class DemoModel {

    public static final BaseNetService baseNetService = MainFactory.getBaseNetServiceInstance();

    private RxAppCompatActivity rxAppCompatActivity;
    private DaoHelper mHelper;
    public DemoModel(Activity context){
        rxAppCompatActivity = (RxAppCompatActivity) context;
        //mHelper = new DaoHelper(context);
    }
    public String demo(){
        Observable<List<DemoDto>> Observable = baseNetService.getChapters();
        List<DemoDto> data = Observable.blockingFirst();
        System.console().printf("ok");
        Gson gson = new Gson();
        return  gson.toJson(data);
    }
}
