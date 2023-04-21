package com.freeray.otaku.net;

import com.freeray.otaku.model.dto.DemoDto;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface BaseNetService {
    @GET("getChapterList/{id}/{chapter}")
    Observable<DemoDto> getChapters(@Path("id") String id, @Path("chapter") String chapter);

}
