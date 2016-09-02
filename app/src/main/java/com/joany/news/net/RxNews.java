package com.joany.news.net;

import com.google.gson.Gson;
import com.joany.greendao.DBHelper;
import com.joany.greendao.GreenRxNews;
import com.joany.greendao.GreenRxNewsDao;
import com.joany.news.base.BaseApplication;
import com.joany.news.bean.NewsResponse;
import com.joany.news.event.NewsEvent;

import java.util.List;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.Query;
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
        GreenRxNewsDao greenRxNewsDao = DBHelper.getInstance(BaseApplication.getContext())
                .getDaoSession().getGreenRxNewsDao();
        DeleteQuery deleteQuery = greenRxNewsDao.queryBuilder()
                .where(GreenRxNewsDao.Properties.Type.eq(id)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
        String news = new Gson().toJson(newsResponse);
        GreenRxNews greenRxNews = new GreenRxNews(null,news,id);
        greenRxNewsDao.insert(greenRxNews);
    }

    public static NewsResponse getCacheNews(String id){
        NewsResponse newsResponse = null;
        GreenRxNewsDao greenRxNewsDao = DBHelper.getInstance(BaseApplication.getContext())
                .getDaoSession().getGreenRxNewsDao();
        Query query = greenRxNewsDao.queryBuilder()
                .where(GreenRxNewsDao.Properties.Type.eq(id)).build();
        List<GreenRxNews> greenRxNewses = query.list();
        if(greenRxNewses != null && greenRxNewses.size() > 0) {
            newsResponse = new Gson().fromJson(greenRxNewses.get(0).getNewsResponse(),NewsResponse.class);
        }
        return newsResponse;
    }
}
