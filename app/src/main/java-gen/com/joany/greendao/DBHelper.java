package com.joany.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Config;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by joany on 2016/9/2.
 */
public class DBHelper {
    private static DBHelper instance;
    private final String DB_NAME = "news";
    private SQLiteDatabase db;
    private DaoSession daoSession;

    public static DBHelper getInstance(Context context) {
        if(instance == null) {
            synchronized (DBHelper.class) {
                if(instance == null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    private DBHelper(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        db = devOpenHelper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        //开启QueryBuilder身上的SQL和参数的log,在任何build方法调用的时候打印出SQL命令和传入的值
        if(Config.DEBUG){
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
