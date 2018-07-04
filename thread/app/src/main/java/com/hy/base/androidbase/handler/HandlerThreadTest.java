package com.hy.base.androidbase.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 HandlerThread、回调接口、
 */
public class HandlerThreadTest {
    private static final String TAG = "070_FingerThread";

    private boolean isUpdateInfo = true;
    private static final int MSG_MATCH = 0x110;
    private static boolean stop = false;

    private HandlerThread mMatchThread;
    private Handler mMatchgHandler;
    private IHandlerThreadCallback callback;


    public HandlerThreadTest() {

    }
    public HandlerThreadTest(IHandlerThreadCallback callback) {
        this.callback = callback;
    }

    public void startMatch() {
        long threadid = Thread.currentThread().getId();
        Log.v(TAG, "startMatch thread id is : " + threadid);

        stop = false;
        match();
    }
    private void match() {
        mMatchThread = new HandlerThread("check-message-coming");
        mMatchThread.start();
        mMatchgHandler = new Handler(mMatchThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {

                long threadid = Thread.currentThread().getId();
                Log.v(TAG, "startMatch handleMessage thread id is : " + threadid);

                if (stop) {
                    return;
                }

                if (null == callback) {
                    Log.v(TAG, " callback is : " + callback);
                    return;
                }

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isUpdateInfo) {
                    mMatchgHandler.sendEmptyMessageDelayed(MSG_MATCH, 1000);
                    Log.v(TAG, "sendEmptyMessageDelayed ");

                    isUpdateInfo = false;

                    int i = 1;
                    callback.onMeassure(i,"onMeassure");

                }

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!isUpdateInfo) {
                    mMatchgHandler.sendEmptyMessageDelayed(MSG_MATCH, 2000);
                    Log.v(TAG, "sendEmptyMessageDelayed ");

                    int j = 2;
                    callback.onEnd(j,"onEnd");
                }

            }
        };

        mMatchgHandler.sendEmptyMessageDelayed(MSG_MATCH, 0);
    }


    public void stopMatch() {
        long threadid = Thread.currentThread().getId();
        Log.v(TAG, "stopMatch thread id is : " + threadid);

        stop = true;

        mMatchgHandler.removeMessages(MSG_MATCH);
    }



}
