package com.joany.news.net;

import com.joany.news.bean.NewsResponse;

import retrofit2.http.Query;
import rx.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by joany on 2016/9/1.
 */
public interface RxNewsService {
    @GET("/{type}/other")
    Observable<NewsResponse> loadNews(@Path("type") String type,
                                      @Query("key") String key,
                                      @Query("num") String num,
                                      @Query("page") String page);
}
