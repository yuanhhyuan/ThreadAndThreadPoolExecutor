/**
 * Copyright 2017 Sun Jian
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hy.app.config;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.hy.app.utils.MyActivityManager;
import com.hy.base.androidbase.handler.CrashHandler;

/**

 */
public class MyApplication extends Application {
    String tag = "060_MyApp";

    private static MyApplication sInstance;

    private MyActivityManager activityManager = null; // activity管理类

    //单例模式
    public static synchronized MyApplication getInstance() {
        return sInstance;
    }
    /**
     * 提供全局获取 Context
     */
    private static Context context;
    public static Context getContext() {
        return context;
    }

    /**
     * 重写Application生命周期
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(tag,"MyApplication onCreate");

        context = getApplicationContext();

        initCrashHandler();
        initGlobalManager();
        initGlobalService();

    }
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.e(tag, "onTerminate");
        super.onTerminate();
    }
    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.e(tag, "onLowMemory");
        super.onLowMemory();
    }
    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.e(tag, "onTrimMemory");
        super.onTrimMemory(level);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e(tag, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }



    public MyActivityManager getActivityManager() {
        return activityManager;
    }

    //
    public void initCrashHandler() {
        CrashHandler crashHandler=CrashHandler.getInstance();
        crashHandler.init(this);
    }

    public void initGlobalManager() {
        // 获得activity管理类实例
        activityManager = MyActivityManager.getInstance();
    }

    public void initGlobalService() {
        //启动应用级的service
//        startService(new Intent(getApplicationContext(), MyService.class));
//        startService(new Intent(getApplicationContext(), MyForegroundService.class));
    }

}


/**
 应用场景：
 1. Application是全局的单例模式。在其各个生命周期可以进行对应的控制。
 2. 应用级全局变量：在Android中，可以通过继承Application类来实现应用程序级的全局变量，这种全局变量方法相对静态类更有保障，直到应用的所有Activity全部被destory掉之后才会被释放掉。
 3. 多个组件之间数据共享
 4. 管理全部的activity
 5. 启动应用级的service；启动应用级的广播监听
 */ 