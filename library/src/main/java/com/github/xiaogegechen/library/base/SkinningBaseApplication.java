package com.github.xiaogegechen.library.base;

import android.app.Application;

import com.github.xiaogegechen.library.Skinning;

/**
 * Application基类，如果使用者有需要的话直接继承SkinningBaseApplication即可
 * 如果已经定义了Application基类，则需要在onCreate()中调用
 * Skinning.INSTANCE.init (getApplicationContext ());
 */
public class SkinningBaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate ();
        Skinning.INSTANCE.init (getApplicationContext (), true);
    }
}
