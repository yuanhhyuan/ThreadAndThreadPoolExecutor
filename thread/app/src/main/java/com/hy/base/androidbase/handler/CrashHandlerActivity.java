package com.hy.base.androidbase.handler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hy.AndroidBase.R;


/**
 CrashHandler类
 */
public class CrashHandlerActivity extends Activity implements View.OnClickListener {
    private final static String TAG = "070_CrashHandlerActivity";
    Button mcrashhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crashhandler);

        initView();  //初始化view
        initListener();  //初始化多个监听事件
    }

    private void initView() {
        mcrashhandler = findViewById(R.id.mcrashhandler);
    }

    private void initListener() {
        mcrashhandler.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mcrashhandler:
                f();
                break;
        }
    }

    private void f(){
        throw  new RuntimeException("抛出一个异常");
    }

}
