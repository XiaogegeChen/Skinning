package com.github.xiaogegechen.skinning;

import android.app.Application;

import com.github.xiaogegechen.library.Skinning;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate ();
        Skinning.INSTANCE.init (getApplicationContext ());
    }
}
