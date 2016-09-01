package com.joany.news.presenter;

import com.joany.news.NewsInterface;
import com.joany.news.net.RxNews;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsPresenter {

    private NewsInterface newsInterface;

    public NewsPresenter(NewsInterface newsInterface) {
        this.newsInterface = newsInterface;
    }

    public void loadNews(int titleId,int pageIndex){
        RxNews.updateNews(titleId,pageIndex);
    }
}
