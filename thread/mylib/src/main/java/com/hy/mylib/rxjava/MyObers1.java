package com.hy.mylib.rxjava;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**   
 * 操作符的使用 
 */
public class MyObers1 {

    //在 RxJava 的默认规则中，事件的发出和消费都是在同一个线程的

    /**
     *create
     */
    //方式一
    public void startSubscribe1() {  //订阅
        myObservable1.subscribe(mySubscriber1);
    }

    Observable<String> myObservable1 = Observable.create( //被观察者
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    // TODO:
                    //......
                    sub.onNext("Hello, world!");  // 写回调，传给观察者
                    sub.onCompleted();   //订阅事件全部结束后的回调，传给观察者
                }
            }
    );

    Subscriber<String> mySubscriber1 = new Subscriber<String>() { //观察者
        @Override
        public void onNext(String s) {
            Log.v("010", s + "");
            System.out.println(s);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };


    //方式二
    public void startSubscribe2() {
        myObservable1.subscribe(onNextAction, onErrorAction);
    }

    Observable<String> myObservable2 = Observable.just("Hello, world!");  //被观察者

    //观察者
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.v("010", s + ": 1");
            System.out.println(s);
        }
    };
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable s) {

        }
    };
    Action0 onCompleteAction = new Action0() {
        @Override
        public void call() {

        }
    };

    //方式三
    public void startSubscribe22() {
        Observable.just("Hello, world!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }


    /**
     * just。参数类型String
     */
    public void justTest() {
        //        Observable<String> observable = Observable.just("hello");
        Observable<String> observable = Observable.just("hello", "my", "nice", "world");
        Subscription subscription = observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("123", "onNext : ");
            }
        });
    }

    /**
     * from。参数类型Integer
     */
    public void fromTest() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);

        Observable<Integer> observable = Observable.from(lists);
        Subscription subscription = observable.subscribe(new Observer<Integer>() {

            public void onCompleted() {
                Log.i("123", "onCompleted: ");
            }

            public void onError(Throwable e) {
                Log.i("123", "onError: ");
            }

            public void onNext(Integer integer) {
                Log.i("123", "onNext : " + integer);
            }
        });
    }

    /**
     * map。
     */
    public void mapTest() {
        Observable.just("Hello, world!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -Dan";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

        Observable.just("Hello")
                .map(new Func1<String, Integer>() {  //Func1<输入, 输出>()
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer s) {
                        System.out.println(s + "");
                    }
                });
    }

    /**
     * flatMap。Observable.flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable
     */
    public void flatMapTest() {

    }

    /**
     * filter 过滤,把不符合条件的过滤掉,留下符合条件的
     */


    /**
     * take 指定最多输出的数量
     */



}
