package com.joany.news.net;

/**
 * Created by joany on 2016/9/1.
 */
public class Constant {
    public static final String BASEURL = "http://api.huceo.com";
    public static final int PAZE_SIZE = 10;
    public static final String NEWSDETAIL = "newsdetail";
    public static final String NEWSTYPE_KEJI = "keji";
    public static final String NEWSTYPE_TIYU = "tiyu";
    public static final String NEWSTYPE_GUOJI = "world";
    public static final String NEWSTYPE_SHEHUI = "social";

    public enum GetNewsWay {
        INIT, UPDATE, LOADMORE;
    }

    public enum Result {
        SUCCESSS, FAIL, NORMAL;
    }

    public static String getID(int type) {
        String id;
        switch (type) {
            case 0:
                id = Constant.NEWSTYPE_KEJI;
                break;
            case 1:
                id = Constant.NEWSTYPE_SHEHUI;
                break;
            case 2:
                id = Constant.NEWSTYPE_TIYU;
                break;
            case 3:
                id = Constant.NEWSTYPE_GUOJI;
                break;
            default:
                id = Constant.NEWSTYPE_KEJI;
                break;
        }
        return id;
    }
}
