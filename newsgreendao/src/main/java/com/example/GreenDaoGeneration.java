package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGeneration {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1,"com.joany.greendao");
        Entity news = schema.addEntity("GreenRxNews");
        news.addIdProperty();
        news.addStringProperty("newsResponse");
        news.addStringProperty("type");
        //需手动创建java-gen目录，并且添加sourceSets
        new DaoGenerator().generateAll(schema,"../News/app/src/main/java-gen");
    }
}