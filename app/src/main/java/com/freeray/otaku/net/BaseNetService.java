package com.freeray.otaku.net;

import com.freeray.otaku.entity.dto.DemoDto;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseNetService {
    @GET("getChapterList/{id}/{chapter}")
    Observable<DemoDto> getChapters(@Path("id") String id, @Path("chapter") String chapter);

}
