package com.freeray.otaku.model;

import com.freeray.otaku.entity.dto.DemoDto;
import com.freeray.otaku.net.BaseNetService;
import com.freeray.otaku.net.MainFactory;

import java.util.List;

import io.reactivex.Observable;

public class DemoModel {

    public static final BaseNetService baseNetService = MainFactory.getBaseNetServiceInstance();


    public void demo(){
        Observable<List<DemoDto>> Observable = baseNetService.getChapters();
        List<DemoDto> data = Observable.blockingFirst();
        System.console().printf("ok");
    }
}
