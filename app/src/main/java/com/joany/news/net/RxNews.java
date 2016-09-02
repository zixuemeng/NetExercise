package com.joany.news.net;

import com.joany.news.bean.NewsResponse;
import com.joany.news.event.NewsEvent;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by joany on 2016/9/1.
 */
public class RxNews {

    public static final String KEY = "09fc0a2397154952297ea4b7e6b2646a";

    public static Subscription updateNews(int titleId, int pageIndex){
        final String id = Constant.getID(titleId);
        return NewsUtils.getInstance().getRxNewsService().loadNews(id,KEY,Constant.PAZE_SIZE+"",pageIndex+"")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Action1<NewsResponse>() {
                    @Override
                    public void call(NewsResponse newsResponse) {
                        setCacheNews(newsResponse, id);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsResponse>() {
                    @Override
                    public void call(NewsResponse newsResponse) {
                        NewsEvent newsEvent = new NewsEvent(newsResponse,Constant.GetNewsWay.UPDATE,id);
                        if(newsEvent == null) {
                            newsEvent.setResult(Constant.Result.FAIL);
                        } else {
                            newsEvent.setResult(Constant.Result.SUCCESSS);
                        }
                        RxBus.getInstance().post(newsEvent);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        NewsEvent newsEvent = new NewsEvent(new NewsResponse(),Constant.GetNewsWay.UPDATE,id);
                        newsEvent.setResult(Constant.Result.FAIL);
                        RxBus.getInstance().post(newsEvent);
                    }
                });
    }

    public static void setCacheNews(NewsResponse newsResponse,String id) {
        //TODO:
    }

    public static NewsResponse getCacheNews(String id){
        //TODO:
        return null;
    }
}
