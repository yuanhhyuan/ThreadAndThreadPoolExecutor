package com.hy.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hy.AndroidBase.R;

import com.hy.base.androidbase.handler.CrashHandlerActivity;
import com.hy.base.androidbase.handler.HandlerActivity;
import com.hy.base.androidbase.handler.HandlerThreadActivity;
import com.hy.base.androidbase.thread.AsyncTaskActivity;
import com.hy.base.androidbase.thread.IntentServiceActivity;
import com.hy.base.androidbase.thread.ThreadActivity;
import com.hy.base.androidbase.thread.ThreadPoolExecutorActivity;


public class AndroidbaseActivity extends Activity {
    Button mHandlerThreadActivity;
    Button mHandlerActivity;
    Button mCrashHandlerActivity;

    Button mthread;
    Button masynctask;
    Button mintentservice;
    Button mthreadpool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidbase);


        initView();  //初始化view
        initListener();  //初始化多个监听事件
    }

    private void initView(){

        mHandlerThreadActivity = (Button) findViewById(R.id.mHandlerThreadActivity);
        mHandlerActivity = (Button) findViewById(R.id.mHandlerActivity);
        mCrashHandlerActivity = (Button) findViewById(R.id.mCrashHandler);

        mthread = (Button) findViewById(R.id.mthread);
        masynctask = (Button) findViewById(R.id.masynctask);
        mintentservice = (Button) findViewById(R.id.mintentservice);
        mthreadpool  = (Button) findViewById(R.id.mthreadpool);


    }

    private void initListener(){

        mHandlerThreadActivity.setOnClickListener(new MyListener());
        mHandlerActivity.setOnClickListener(new MyListener());
        mCrashHandlerActivity.setOnClickListener(new MyListener());

        mthread.setOnClickListener(new MyListener());
        masynctask.setOnClickListener(new MyListener());
        mintentservice.setOnClickListener(new MyListener());
        mthreadpool.setOnClickListener(new MyListener());

    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {

                case R.id.mHandlerActivity:
                    newHandlerActivity();
                    break;
                case R.id.mCrashHandler:
                    newCrashHandlerActivity();
                    break;
                case R.id.mHandlerThreadActivity:
                    newHandlerThreadActivity();
                    break;
                case R.id.mthread:
                    newThreadActivity();
                    break;
                case R.id.masynctask:
                    newAsyncTaskActivity();
                    break;
                case R.id.mintentservice:
                    newIntentServiceActivity();
                    break;
                case R.id.mthreadpool:
                    newThreadPoolExecutorActivity();
                    break;
                default:
                    break;
            }
        }
    }


    private void newHandlerActivity(){
        Intent i = new Intent(AndroidbaseActivity.this,HandlerActivity.class);
        startActivity(i);
    }
    private void newHandlerThreadActivity(){
        Intent i = new Intent(AndroidbaseActivity.this,HandlerThreadActivity.class);
        startActivity(i);
    }
    private void newCrashHandlerActivity(){
        Intent i = new Intent(AndroidbaseActivity.this,CrashHandlerActivity.class);
        startActivity(i);
    }
    private void newThreadActivity(){
        Intent i = new Intent(AndroidbaseActivity.this,ThreadActivity.class);
        startActivity(i);
    }
    private void newAsyncTaskActivity(){
        Intent i = new Intent(AndroidbaseActivity.this,AsyncTaskActivity.class);
        startActivity(i);
    }
    private void newIntentServiceActivity(){
        Intent i = new Intent(AndroidbaseActivity.this,IntentServiceActivity.class);
        startActivity(i);
    }
    private void newThreadPoolExecutorActivity(){
        Intent i = new Intent(AndroidbaseActivity.this,ThreadPoolExecutorActivity.class);
        startActivity(i);
    }

}
