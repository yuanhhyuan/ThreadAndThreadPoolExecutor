package com.hy.base.androidbase.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hy.AndroidBase.R;


/**
1. 调用HandlerThreadTest的方法，起结果通过起回调接口获得，所以重写起回调接口
 */
public class HandlerThreadActivity extends Activity implements IHandlerThreadCallback {
    private final String TAG = "080_";

    HandlerThreadTest fd = new HandlerThreadTest(this);

    TextView mTvServiceInfo;
    Button start;
    Button stop;

    MyHandler myHandler; //管理UI线程的handler

    public int aa;
    public String mcontextCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthread);

        initView();

        initListener();

    }

    private void initView(){
        mTvServiceInfo = findViewById(R.id.id_textview);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
    }

    private void initListener(){
        start.setOnClickListener(new MyListener());
        stop.setOnClickListener(new MyListener());

        myHandler = new MyHandler();

    }
    private class MyListener implements View.OnClickListener {
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {
                case R.id.start:
                    fd.startMatch();
                    break;
                case R.id.stop:
                    fd.stopMatch();
                    break;
                default:
                    break;
            }
        }
    }

    class MyHandler extends Handler {
        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }

        // 子类必须重写此方法，接受数据
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mTvServiceInfo.setText(msg.what + "    " + mcontextCallback );
                case 2:
                    mTvServiceInfo.setText(msg.what + "    " + mcontextCallback);
                default:
                    return;
            }
        }
    }

    /**
     * 重写回调接口
     */
    public void onMeassure(int i,String contextCallback) {
        Log.v(TAG, "onMeassure callback ");
        aa = i;
        mcontextCallback = contextCallback;
        myHandler.sendEmptyMessage(aa);
    }

    public void onEnd(int j,String contextCallback) {
        Log.v(TAG, "onEnd callback ");
        aa = j;
        mcontextCallback = contextCallback;
        myHandler.sendEmptyMessage(aa);
    }


}
