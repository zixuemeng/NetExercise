package com.joany.news.event;

import com.joany.news.bean.NewsResponse;
import com.joany.news.net.Constant;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsEvent {
    private Constant.Result result;
    private NewsResponse newsResponse;
    private Constant.GetNewsWay getNewsWay;
    private String newsType;

    public NewsEvent(NewsResponse newsResponse,Constant.GetNewsWay getNewsWay,String newsType) {
        this.newsResponse = newsResponse;
        this.getNewsWay = getNewsWay;
        this.newsType = newsType;
    }

    public Constant.Result getResult() {
        return result;
    }
    public void setResult(Constant.Result result) {
        this.result = result;
    }

    public NewsResponse getNewsResponse(){
        return newsResponse;
    }

    public Constant.GetNewsWay getGetNewsWay(){
        return getNewsWay;
    }

    public String getNewsType(){
        return newsType;
    }
}
