package com.github.xiaogegechen.skinning;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.xiaogegechen.library.Skinning;
import com.github.xiaogegechen.library.base.SkinningBaseActivity;
import com.github.xiaogegechen.library.model.Attr;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SkinningBaseActivity {

    private static final List<String> LIST;

    static {
        LIST = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            LIST.add(i + " hello");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        // 测试listView
//        ListView listView = findViewById(R.id.list_view);
//        listView.setAdapter(new MyAdapter(LIST));

        // 测试动态添加的view
        List<Attr> attrs = new ArrayList<>();

        Button button = new Button(getApplicationContext());
        button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText(getText(R.string.main_text_0_text));
        attrs.add(new Attr("text", R.string.main_text_0_text));

        Skinning.INSTANCE.addView(this, button, attrs);
        LinearLayout root = findViewById(R.id.root);
        root.addView(button);
    }
}
