package com.github.xiaogegechen.library.impl;

import android.view.View;
import android.widget.TextView;

import com.github.xiaogegechen.library.AttrsHandler;
import com.github.xiaogegechen.library.model.Attr;

import java.util.List;

public class TextViewHandler extends AttrsHandler {
    @Override
    public void handleAttrs(View view, List<Attr> attrList) {
        if(view instanceof TextView){
            TextView textView = (TextView) view;
            for (Attr attr : attrList) {
                // 属性名
                String attrName = attr.getAttrName();
                // 属性值，已经去掉了"@"
                int resId = getResIdFromAttr (attr);
                switch (attrName){
                    case "text":
                        if(attr.getType() == Attr.TYPE_STRING){
                            String newString = getString(resId);
                            textView.setText(newString);
                        }
                        break;

                    case "textSize":
                        if(attr.getType() == Attr.TYPE_DIMEN){
                            int newDimen = getDimensionPixelSize(resId);
                            textView.setTextSize(newDimen);
                        }
                        break;

                    case "textColor":
                        if(attr.getType() == Attr.TYPE_COLOR){
                            int newColor = getColor(resId);
                            textView.setTextColor(newColor);
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
