package com.hy.base.androidbase.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
java thread; java timer
 */
public class AboutThread {

    public static String TAG = "060_AboutThread";

    //log打印开关
    private final boolean DEBUG = true;

    /**
     *  *****************java thread******************
     */


    //方式一：实现Runnable接口（用匿名类方式实现Runnable接口）
    public void thread1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
            // TODO: 2017/11/13

            }
        }).start();
    }

    //方式二：
    public void thread2() {

        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {

                    if (true) {
                        return;
                    }

                    // TODO: 2017/10/19

                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    //方式三：
    public void thread3() {

        CloseRunnable closeRunnable = new CloseRunnable();
        Thread closeThread = new Thread(closeRunnable, "closeThread");
        closeThread.start();
    }
    private class CloseRunnable implements Runnable { //配合方式三
        @Override
        public void run() {
            //todo执行耗时操作

        }
    }

    //方式四：继承Thread类
    public void thread4() {
        StopThread stopThread = new StopThread("stopthread");
        stopThread.start();
    }
    private class StopThread extends Thread{  //配合方式四
        StopThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            // TODO: 2017/11/13
        }
    }

    long id = Thread.currentThread().getId();

    /**
     *  *****************java timer******************
     */

    //定时器一：用thread实现
    public void timer1() {

        Runnable runnable = new Runnable() {
            public void run() {

                while (true) {

                    if (true) {
                        return;
                    }

                    // TODO: 2017/10/19


                    try {
                        Thread.sleep(100L);   //设置定时器的间隔时间，单位是毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    //定时器二：用timer实现
    public void timer2() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO: 2017/11/13

            }
        }, 100, 100);
    }
}

