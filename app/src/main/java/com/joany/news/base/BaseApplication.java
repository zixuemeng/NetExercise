package com.joany.news.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by joany on 2016/9/1.
 */
public class BaseApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
