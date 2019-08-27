package com.github.xiaogegechen.library.impl;

import android.view.View;
import android.widget.TextView;

import com.github.xiaogegechen.library.AttrsHandler;
import com.github.xiaogegechen.library.model.Attr;

import java.util.List;

/**
 * 默认的textSize处理器
 */
public class TextSizeHandler extends AttrsHandler {

    private static final String TARGET = "textSize";

    @Override
    public void handleAttrs(View view, List<Attr> attrList) {
        if(view instanceof TextView){
            TextView textView = (TextView) view;
            for (Attr attr : attrList) {
                // 找到textSize属性
                if(attrIsTarget (attr, TARGET)){
                    //引用的资源id
                    int id = getResIdFromAttr (attr);
                    // 新字体大小
                    int pixelSize = getDimensionPixelSize (id);
                    // 设置
                    textView.setTextSize (pixelSize);
                }
            }
        }
    }
}
