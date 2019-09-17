package com.github.xiaogegechen.library.model;

import androidx.annotation.AnyRes;
import androidx.annotation.IntDef;

import com.github.xiaogegechen.library.Utils;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * view的属性
 */
public class Attr {

    public static final int TYPE_INVALID = 100;
    public static final int TYPE_DIMEN = 101;
    public static final int TYPE_COLOR = 102;
    public static final int TYPE_DRAWABLE = 103;
    public static final int TYPE_MIPMAP = 104;
    public static final int TYPE_STRING = 105;

    @Retention(SOURCE)
    @IntDef(value = {
            TYPE_INVALID,
            TYPE_DIMEN,
            TYPE_COLOR,
            TYPE_DRAWABLE,
            TYPE_MIPMAP,
            TYPE_STRING
    })
    public @interface AttrType{}

    // 属性名，如：textColor, text
    private String mAttrName;

    // 属性值, 如："@12354821"
    private String mAttrValue;

    // 引用资源类型，如：string, color, drawable
    private int mType;

    /**
     * 构造器
     * @param attrName 属性名
     * @param attrValue 属性值
     */
    public Attr(String attrName, String attrValue) {
        this(attrName, attrValue, Utils.getAttrType(attrValue));
    }

    /**
     * 构造器
     * @param attrName 属性名
     * @param resId 资源id
     */
    public Attr(String attrName, @AnyRes int resId){
        this(attrName, "@" + resId);
    }

    /**
     * 构造器
     * @param attrName 属性名
     * @param attrValue 属性值
     * @param type 引用的资源类型
     */
    public Attr(String attrName, String attrValue, @AttrType int type) {
        mAttrName = attrName;
        mAttrValue = attrValue;
        mType = type;
    }

    public String getAttrName() {
        return mAttrName;
    }

    public String getAttrValue() {
        return mAttrValue;
    }

    public int getType() {
        return mType;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "mAttrName='" + mAttrName + '\'' +
                ", mAttrValue='" + mAttrValue + '\'' +
                ", mType=" + mType +
                '}';
    }
}
