package com.github.xiaogegechen.skinning;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import com.github.xiaogegechen.library.MyFactory2;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        LayoutInflaterCompat.setFactory2 (getLayoutInflater (), new MyFactory2 ());
        super.onCreate (savedInstanceState);
    }
}
