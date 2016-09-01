package com.joany.news.net;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by joany on 2016/9/1.
 */
public class RxBus {
    private static volatile RxBus instance;

    public RxBus(){
    }

    public static RxBus getInstance(){
        if(instance == null) {
            synchronized (RxBus.class) {
                if(instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    private final Subject<Object,Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void post(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObservable(){
        return _bus;
    }
}
