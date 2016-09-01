package com.joany.news.net;

import rx.Subscription;

/**
 * Created by joany on 2016/9/1.
 */
public class RxNews {

    public static Subscription updateNews(int titleId, int pageIndex){
        //TODO:
        return null;
    }

    private String getID(int type) {
        String id;
        switch (type) {
            case 0:
                id = Constant.NEWSTYPE_KEJI;
                break;
            case 1:
                id = Constant.NEWSTYPE_GUOJI;
                break;
            case 2:
                id = Constant.NEWSTYPE_SHEHUI;
                break;
            case 3:
                id = Constant.NEWSTYPE_TIYU;
                break;
            default:
                id = Constant.NEWSTYPE_KEJI;
                break;
        }
        return id;
    }
}
