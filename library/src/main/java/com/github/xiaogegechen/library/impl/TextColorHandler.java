package com.github.xiaogegechen.library.impl;

import android.view.View;
import android.widget.TextView;

import com.github.xiaogegechen.library.Attr;
import com.github.xiaogegechen.library.AttrsHandler;

import java.util.List;

/**
 * 默认的textColor属性处理器
 */
public class TextColorHandler extends AttrsHandler {

    private static final String TARGET = "textColor";

    @Override
    public void handleAttrs(View view, List<Attr> attrList) {
        if(view instanceof TextView){
            TextView textView = (TextView) view;
            for (Attr attr : attrList) {
                if(attrIsTarget (attr, TARGET)){
                    int resId = getResIdFromAttr (attr);
                    int color = getColor (resId);
                    textView.setTextColor (color);
                }
            }
        }
    }
}
