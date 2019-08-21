package com.github.xiaogegechen.skinning;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.xiaogegechen.library.ISkinningListener;
import com.github.xiaogegechen.library.Skinning;

import java.io.File;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_second);
        findViewById (R.id.change).setOnClickListener (v -> {
            File cacheDir = getExternalCacheDir ();
            File skinFile = new File (cacheDir, "skin_pkg-release.skin");
            Skinning.INSTANCE.switchToNewSkin (skinFile, new ISkinningListener () {
                @Override
                public void onSuccess() {
                    Toast.makeText (SecondActivity.this, "success", Toast.LENGTH_LONG).show ();
                }

                @Override
                public void onFailure(String errorMsg) {
                    Log.d (TAG, "onFailure: " + errorMsg);
                    Toast.makeText (SecondActivity.this, "error" + errorMsg, Toast.LENGTH_LONG).show ();
                }
            });
        });
    }
}