package com.joany.news.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsEntity implements Parcelable {
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicUrl(String pictureUrl) {
        this.picUrl = picUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCtime() {
        return ctime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUrl() {
        return url;
    }


    protected NewsEntity(Parcel source) {
        this.ctime = source.readString();
        this.title = source.readString();
        this.description = source.readString();
        this.picUrl = source.readString();
        this.url = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.ctime);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.picUrl);
        dest.writeString(this.url);
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel source) {
            return new NewsEntity(source);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    @Override
    public String toString() {
        return "time='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picureUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
