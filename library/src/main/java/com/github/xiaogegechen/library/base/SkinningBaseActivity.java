package com.github.xiaogegechen.library.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.xiaogegechen.library.Skinning;

/**
 * AppCompatActivity基类，使用者可以直接继承SkinningBaseActivity
 * 如果项目中已经存在baseActivity,需要在onCreate()方法中调用
 * LayoutInflaterCompat.setFactory2 (getLayoutInflater (), MyFactory2.getInstance ());
 * 注意要在方法开始就调用
 */
public class SkinningBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 替换Factory2
        Skinning.INSTANCE.attach (this);
        super.onCreate (savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        Skinning.INSTANCE.detach (this);
        super.onDestroy ();
    }
}
