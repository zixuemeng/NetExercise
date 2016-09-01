package com.joany.news;

import com.joany.news.bean.NewsEntity;

import java.util.List;

/**
 * Created by joany on 2016/9/1.
 */
public interface NewsInterface {
    void add(List<NewsEntity> list);
    void showProgress();
    void hideProgress();
    void showLoadFail();
}
