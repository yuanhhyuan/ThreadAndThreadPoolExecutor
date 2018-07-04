package com.hy.base.androidbase.thread;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hy.AndroidBase.R;

import java.util.concurrent.locks.ReentrantLock;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadActivity extends Activity {
    String TAG = "060_ThreadActivity";

    @BindView(R.id.btnstart)
    Button btnstart;
    @BindView(R.id.btnstop)
    Button btnstop;
    @BindView(R.id.btnmstart)
    Button btnmstart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        //绑定初始化ButterKnife
        ButterKnife.bind(this);
    }


    Thread1 t1 = new Thread1("线程一");
    Thread1 t2 = new Thread1("线程二");
    Thread1 t3 = new Thread1("线程三");

    @OnClick({R.id.btnstart, R.id.btnstop,R.id.btnmstart})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnstart:
                isRunning = true;
                t1.start();
                break;
            case R.id.btnstop:
                Toast.makeText(ThreadActivity.this,"stop被点击了",Toast.LENGTH_SHORT).show();
                isRunning = false;
                break;
            case R.id.btnmstart:
                isRunning = true;
                t1.start();
                t2.start();
                t3.start();
                break;
            default:
                // TODO: 2017/12/11
                break;
        }

    }

    private boolean isRunning = false;
    /**
     * 继承Thread方式
     */
    private class Thread1 extends Thread {
        Thread1(String name) {
            super(name);
        }

        @Override
        public void run() {
            //执行耗时操作
            while (isRunning) {
                count();
                try{
                sleep(500);}catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

//    int count = 1000;
    /** 1：使用特殊域变量(volatile)实现线程同步 *
     *
     * 　a.volatile关键字为域变量的访问提供了一种免锁机制，
     　　b.使用volatile修饰域相当于告诉虚拟机该域可能会被其他线程更新，
     　　c.因此每次使用该域就要重新计算，而不是使用寄存器中的值
     　　d.volatile不会提供任何原子操作，它也不能用来修饰final类型的变量
     */
    private volatile int count = 1000;
    private void count() {
        if (count > 0) {
            Log.e(TAG, Thread.currentThread().getName() + "--->" + count--);
        } else {
            isRunning = false;
        }
    }

    /**2：同步函数*/
    private synchronized void count1() {
        if (count > 0) {
            Log.e(TAG, Thread.currentThread().getName() + "--->" + count--);
        } else {
            isRunning = false;
        }
    }

    /** 3：同步代码块*/
    private void count2() {
        synchronized (this) {
            if (count > 0) {
                Log.e(TAG, Thread.currentThread().getName() + "--->" + count--);
            } else {
                isRunning = false;
            }
        }
    }

    /** 4：使用重入锁实现线程同步

     ReentrantLock() : 创建一个ReentrantLock实例

     lock() : 获得锁

     unlock() : 释放锁 */
    ReentrantLock lock = new ReentrantLock();
    private void count4() {
        lock.lock();
        if (count > 0) {
            Log.e(TAG, Thread.currentThread().getName() + "--->" + count--);
        } else {
            isRunning = false;
        }
        lock.unlock();
    }

}
