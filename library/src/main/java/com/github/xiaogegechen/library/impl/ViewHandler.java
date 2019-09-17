package com.github.xiaogegechen.library.impl;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.github.xiaogegechen.library.AttrsHandler;
import com.github.xiaogegechen.library.model.Attr;

import java.util.List;

public class ViewHandler extends AttrsHandler {
    @Override
    public void handleAttrs(View view, List<Attr> attrList) {
        for (Attr attr : attrList) {
            // 属性名
            String attrName = attr.getAttrName();
            // 属性值，已经去掉了"@"
            int resId = getResIdFromAttr (attr);
            switch (attrName){
                case "background":
                    // 颜色
                    if(attr.getType() == Attr.TYPE_COLOR){
                        int newColor = getColor (resId);
                        view.setBackgroundColor(newColor);
                    }else if(attr.getType() == Attr.TYPE_DRAWABLE || attr.getType() == Attr.TYPE_MIPMAP){
                        // drawable or mipmap
                        Drawable newDrawable = getDrawable(resId);
                        view.setBackground(newDrawable);
                    }
                    break;

                case "padding":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(newDimen, newDimen, newDimen, newDimen);
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
