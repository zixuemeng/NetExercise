package com.joany.news.net;

/**
 * Created by joany on 2016/9/1.
 */
public class Constant {
    public static final String BASEURL = "http://api.huceo.com";
    public static final int PAZE_SIZE = 10;
    public static final String NEWSDETAIL = "newsdetal";
    public static final String NEWSTYPE_KEJI = "keji";
    public static final String NEWSTYPE_TIYU = "tiyu";
    public static final String NEWSTYPE_GUOJI = "world";
    public static final String NEWSTYPE_SHEHUI = "social";

    public enum GetNewsWay {
        INIT, UPDATA, LOADMORE;
    }

    public enum Result {
        SUCCESSS, FAIL, NORMAL;
    }
}
