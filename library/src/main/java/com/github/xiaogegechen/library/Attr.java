package com.github.xiaogegechen.library;

/**
 * view的属性
 */
public class Attr {

    // 属性名，如：textColor, text
    private String mAttrName;

    // 属性值, 如："hello world!"
    private String mAttrValue;

    public Attr(String attrName, String attrValue) {
        mAttrName = attrName;
        mAttrValue = attrValue;
    }

    public String getAttrName() {
        return mAttrName;
    }

    public String getAttrValue() {
        return mAttrValue;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "mAttrName='" + mAttrName + '\'' +
                ", mAttrValue='" + mAttrValue + '\'' +
                '}';
    }
}
