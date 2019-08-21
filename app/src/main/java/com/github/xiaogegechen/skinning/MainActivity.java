package com.github.xiaogegechen.skinning;

import android.content.Intent;
import android.os.Bundle;

import com.github.xiaogegechen.library.base.SkinningBaseActivity;

public class MainActivity extends SkinningBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        findViewById (R.id.button).setOnClickListener (v -> {
            Intent intent = new Intent (MainActivity.this, SecondActivity.class);
            startActivity (intent);
        });
        getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment, new TestFragment ()).commit ();
    }
}
