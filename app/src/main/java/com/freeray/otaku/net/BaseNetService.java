package com.freeray.otaku.net;

import com.freeray.otaku.entity.dto.DemoDto;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseNetService {
    @GET("/news/oschina-news")
    Observable<List<DemoDto>> getChapters();

}
