package com.joany.news.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsEntity implements Parcelable {
    private String time;
    private String title;
    private String description;
    private String pictureUrl;
    private String url;

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String gettime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getUrl() {
        return url;
    }


    protected NewsEntity(Parcel source) {
        this.time = source.readString();
        this.title = source.readString();
        this.description = source.readString();
        this.pictureUrl = source.readString();
        this.url = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.time);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.pictureUrl);
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
        return "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picureUrl='" + pictureUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
