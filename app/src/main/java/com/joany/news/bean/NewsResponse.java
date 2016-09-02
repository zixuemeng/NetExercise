package com.joany.news.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsResponse implements Parcelable {
    private int code;
    private String msg;
    private List<NewsEntity> newslist;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setNewslist(List<NewsEntity> newsList) {
        this.newslist = newsList;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<NewsEntity> getNewslist() {
        return newslist;
    }

    public NewsResponse(){
    }

    protected NewsResponse(Parcel source) {
        this.code = source.readInt();
        this.msg = source.readString();
        this.newslist = new ArrayList<>();
        source.readList(this.newslist,List.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeList(this.newslist);
    }

    public static final Creator<NewsResponse> CREATOR = new Creator<NewsResponse>() {
        @Override
        public NewsResponse createFromParcel(Parcel source) {
            return new NewsResponse(source);
        }

        @Override
        public NewsResponse[] newArray(int size) {
            return new NewsResponse[size];
        }
    };

    @Override
    public String toString() {
        return "NewsResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", newsListSize=" + newslist.size() +
                "}";
    }
}
