package com.github.xiaogegechen.skinning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.xiaogegechen.library.base.SkinningBaseActivity;

public class MainActivity extends SkinningBaseActivity {

    private static final String TAG = "MainActivity";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        mTextView = findViewById (R.id.text_view_1);
        findViewById (R.id.button).setOnClickListener (v -> {
            Intent intent = new Intent (MainActivity.this, SecondActivity.class);
            startActivity (intent);
        });
        getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment, new TestFragment ()).commit ();
        float dimension = getResources ().getDimension (R.dimen.text_size);
        int dimensionPixelSize = getResources ().getDimensionPixelSize (R.dimen.text_size);
        float textSize = mTextView.getTextSize ();
        Log.d (TAG, "dimension -> " + dimension);
        Log.d (TAG, "dimensionPixelSize -> " + dimensionPixelSize);
        Log.d (TAG, "textSize -> " + textSize);
    }
}
