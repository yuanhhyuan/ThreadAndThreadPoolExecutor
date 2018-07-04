package com.hy.base.androidbase.handler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hy.AndroidBase.R;


/**
1. UI线程新起线程执行耗时操作
 2. UI线程通过handler通知UI线程更新UI
 */
public class HandlerActivity extends Activity implements View.OnClickListener {
    private final static String TAG = "070_HandlerActivity";

    Button btnStart;
    Button btnStop;
    Button btnClose;

    Context mContext = null;

    private static final int START = 0;
    private static final int STOP = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handleractivity);

        initView();  //初始化view
        initListener();  //初始化多个监听事件

        test();
    }

    private void initView() {
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnClose = findViewById(R.id.btn_close);
    }

    private void initListener() {
        mContext = this;
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //方式一：实现Runnable接口（用匿名类方式实现Runnable接口）
            case R.id.btn_start:
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i(TAG, "btn_start thread id = " +
                                Thread.currentThread().getId());

                        Message msg = new Message();
                        msg.what = START;
                        handler.sendMessage(msg);
                    }
                }).start();
                break;
            //方式二：继承Thread类
            case R.id.btn_stop:
                StopThread stopThread = new StopThread("stopthread");
                stopThread.start();
                break;
            //方式三：实现Runnable接口（定义类实现Runnable接口）
            case R.id.btn_close:
                CloseRunnable closeRunnable = new CloseRunnable();
                Thread closeThread = new Thread(closeRunnable, "closeThread");
                closeThread.start();
                break;
        }
    }

    private class StopThread extends Thread {
        StopThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            //执行耗时操作
            Log.i(TAG, "btn_stop thread id = " +
                    Thread.currentThread().getId());

            Message msg = new Message();
            msg.what = STOP;
            handler.sendMessage(msg);
        }
    }

    private class CloseRunnable implements Runnable {
        @Override
        public void run() {
            //执行耗时操作
            Log.i(TAG, "btn_close thread id = " +
                    Thread.currentThread().getId());
            finish();
        }
    }

    //非UI线程更新UI方式一：创建handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case START:
                    Toast.makeText(HandlerActivity.this, "start！", 0).show();
                    break;

                case STOP:
                    Toast.makeText(HandlerActivity.this, "stop！", 0).show();
                    break;

                default:
                    break;
            }
        }
    };

    //非UI线程更新UI方式二：runOnUiThread
    private void test() {
        //创建一个线程
        new Thread(new Runnable() {

            @Override
            public void run() {

                //延迟两秒
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HandlerActivity.this, "hah", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).start();
    }

}
