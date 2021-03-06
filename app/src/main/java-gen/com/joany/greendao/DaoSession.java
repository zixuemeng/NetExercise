package com.joany.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.joany.greendao.GreenRxNews;

import com.joany.greendao.GreenRxNewsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig greenRxNewsDaoConfig;

    private final GreenRxNewsDao greenRxNewsDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        greenRxNewsDaoConfig = daoConfigMap.get(GreenRxNewsDao.class).clone();
        greenRxNewsDaoConfig.initIdentityScope(type);

        greenRxNewsDao = new GreenRxNewsDao(greenRxNewsDaoConfig, this);

        registerDao(GreenRxNews.class, greenRxNewsDao);
    }
    
    public void clear() {
        greenRxNewsDaoConfig.getIdentityScope().clear();
    }

    public GreenRxNewsDao getGreenRxNewsDao() {
        return greenRxNewsDao;
    }

}
