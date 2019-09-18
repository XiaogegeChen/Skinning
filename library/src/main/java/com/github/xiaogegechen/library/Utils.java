package com.github.xiaogegechen.library;

import androidx.annotation.AnyRes;

import com.github.xiaogegechen.library.model.Attr;

public class Utils {

    /**
     * 通过资源id拿到资源类型
     * @param resId 资源id，比如R.string.app_name,R.color.colorAccent
     * @return 资源类型，{@link com.github.xiaogegechen.library.model.Attr.AttrType}类型
     */
    public static @Attr.AttrType int getAttrType(@AnyRes int resId){
        int attrType = Attr.TYPE_INVALID;
        String attrTypeName = ResourcesManager.getInstance().mOriginRes.getResourceTypeName(resId);
        switch (attrTypeName){
            case "dimen":
                attrType = Attr.TYPE_DIMEN;
                break;
            case "color":
                attrType = Attr.TYPE_COLOR;
                break;
            case "drawable":
                attrType = Attr.TYPE_DRAWABLE;
                break;
            case "mipmap":
                attrType = Attr.TYPE_MIPMAP;
                break;
            case "string":
                attrType = Attr.TYPE_STRING;
                break;
            case "bool":
                attrType = Attr.TYPE_BOOL;
                break;
            case "integer":
                attrType = Attr.TYPE_INTEGER;
                break;
            case "anim":
                attrType = Attr.TYPE_ANIM;
                break;
            case "style":
                attrType = Attr.TYPE_STYLE;
                break;
            case "fraction":
                attrType = Attr.TYPE_FRACTION;
                break;
            case "menu":
                attrType = Attr.TYPE_MENU;
                break;
            case "raw":
                attrType = Attr.TYPE_RAW;
                break;
            case "interpolator":
                attrType = Attr.TYPE_INTERPOLATOR;
                break;
            default:
                break;
        }
        return attrType;
    }

    /**
     * 通过资源id拿到资源类型
     * @param attrValue 直接从xml中解析到的attrValue，以@开头
     * @return 资源类型，{@link com.github.xiaogegechen.library.model.Attr.AttrType}类型
     */
    public static @Attr.AttrType int getAttrType(String attrValue){
        if(attrValue.startsWith("@")){
            int resId = Integer.parseInt(attrValue.substring(1));
            return getAttrType(resId);
        }
        return Attr.TYPE_INVALID;
    }

}
