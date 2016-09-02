package com.joany.news.presenter;

import com.joany.news.NewsInterface;
import com.joany.news.event.NewsEvent;
import com.joany.news.net.Constant;
import com.joany.news.net.RxBus;
import com.joany.news.net.RxNews;

import rx.functions.Action1;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsPresenter {

    private NewsInterface newsInterface;
    private int titleId;

    public NewsPresenter(NewsInterface newsInterface,int titleId) {
        this.newsInterface = newsInterface;
        this.titleId = titleId;
        RxBus.getInstance().toObservable().subscribe(newsEventAction);
    }

    public void loadNews(int titleId,int pageIndex){
        RxNews.updateNews(titleId,pageIndex);
    }

    private Action1<? super Object> newsEventAction = new Action1<Object>() {
        @Override
        public void call(Object o) {
            if(o instanceof NewsEvent && ((NewsEvent) o).getNewsType().equals(Constant.getID(titleId))) {
                if(((NewsEvent) o).getResult().equals(Constant.Result.SUCCESSS)){
                    if(newsInterface != null) {
                        newsInterface.hideProgress();
                        newsInterface.add(((NewsEvent) o).getNewsResponse().getNewslist());
                    }
                } else if(((NewsEvent) o).getResult().equals(Constant.Result.FAIL)) {
                    if(newsInterface != null) {
                        newsInterface.hideProgress();
                        newsInterface.showLoadFail();
                    }
                }
            }
        }
    };
}
