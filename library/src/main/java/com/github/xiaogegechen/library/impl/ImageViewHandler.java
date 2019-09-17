package com.github.xiaogegechen.library.impl;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.github.xiaogegechen.library.AttrsHandler;
import com.github.xiaogegechen.library.model.Attr;

import java.util.List;

public class ImageViewHandler extends AttrsHandler {
    @Override
    public void handleAttrs(View view, List<Attr> attrList) {
        if(view instanceof ImageView){
            ImageView imageView = (ImageView) view;
            for (Attr attr : attrList) {
                // 属性名
                String attrName = attr.getAttrName();
                // 属性值，已经去掉了"@"
                int resId = getResIdFromAttr (attr);
                switch (attrName){
                    case "src":
                        // 颜色
                        if(attr.getType() == Attr.TYPE_COLOR){
                            int newColor = getColor (resId);
                            imageView.setImageDrawable(new ColorDrawable(newColor));
                        }else if(attr.getType() == Attr.TYPE_DRAWABLE || attr.getType() == Attr.TYPE_MIPMAP){
                            // drawable or mipmap
                            Drawable newDrawable = getDrawable(resId);
                            imageView.setImageDrawable(newDrawable);
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
