package com.hy.base.androidbase.thread;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hy.AndroidBase.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 线程池 ThreadPoolExecutor
 */
public class ThreadPoolExecutorActivity extends Activity{

    Button newFixed;
    Button newCached;
    Button newSingle;
    Button newSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadpool);

        initView();  //初始化view
        initListener();  //初始化多个监听事件
    }

    private void initView(){
        newFixed = (Button) findViewById(R.id.newFixed);
        newCached = (Button) findViewById(R.id.newCached);
        newSingle = (Button) findViewById(R.id.newSingle);
        newSchedule = (Button) findViewById(R.id.newSchedule);
    }

    private void initListener(){
        newFixed.setOnClickListener(new MyListener());
        newCached.setOnClickListener(new MyListener());
        newSingle.setOnClickListener(new MyListener());
        newSchedule.setOnClickListener(new MyListener());
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {
                case R.id.newFixed:
                    newFixed();
                    break;
                case R.id.newCached:
                    newCached();
                    break;
                case R.id.newSingle:
                    newSingle();
                    break;
                case R.id.newSchedule:
                    newSchedule();
                    break;
                default:
                    break;
            }
        }
    }

    private void newFixed(){
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new WorkerThread("thread-" + 1));
    }

    private void newCached(){
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WorkerThread("thread-" + 2));
    }

    private void newSingle(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new WorkerThread("thread-"+ 3));
    }

    private void newSchedule(){
        Executors.newScheduledThreadPool (5).scheduleAtFixedRate(new WorkerThread("thread-"+ 4), 1000, 2000, TimeUnit.MILLISECONDS);
    }

    // 模拟耗时任务
    public class WorkerThread implements Runnable {
        private String threadName;
        public WorkerThread(String threadName) {
            this.threadName = threadName;
        }
        @Override
        public synchronized void run() {

            int i = 0;
            boolean flag = true;
            try {
                while (flag) {
                    Thread.sleep(1000);
                    i++;
                    Log.e("060", "WorkerThread " + threadName + "  " + i);
                    if (i >2) flag = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public String getThreadName() {
            return threadName;
        }
    }

}
