package com.github.xiaogegechen.library.impl;

import android.view.View;
import android.widget.TextView;

import com.github.xiaogegechen.library.model.Attr;
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
                // 找到textColor属性
                if(attrIsTarget (attr, TARGET)){
                    // 引用的资源id
                    int resId = getResIdFromAttr (attr);
                    // 新颜色
                    int color = getColor (resId);
                    // 设置
                    textView.setTextColor (color);
                }
            }
        }
    }
}
