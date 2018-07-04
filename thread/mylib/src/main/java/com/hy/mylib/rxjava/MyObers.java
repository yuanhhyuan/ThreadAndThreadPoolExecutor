package com.hy.mylib.rxjava;

import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MyObers {
    private String tag = "010_MyObers";

    /**
     * 线程控制Scheduler
     */
    public void test1() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {

                        Log.d(tag, "number:" + number + "; threadid is :" + Thread.currentThread().getId());
                    }
                });
    }


}
