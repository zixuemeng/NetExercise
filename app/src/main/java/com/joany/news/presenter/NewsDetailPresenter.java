package com.joany.news.presenter;

import com.joany.news.NewsDetailInterface;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsDetailPresenter{
    private NewsDetailInterface newsDetailInterface;

    public NewsDetailPresenter(NewsDetailInterface newsDetailInterface){
        this.newsDetailInterface = newsDetailInterface;
    }
    public void loadNewsDetail(String url) {
        newsDetailInterface.showProgress();
        newsDetailInterface.showNewsDetailContent(url);
    }
}
