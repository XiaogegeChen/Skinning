package com.github.xiaogegechen.library;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.github.xiaogegechen.library.model.Attr;

import java.util.ArrayList;
import java.util.List;

/**
 * LayoutInflater.Factory2实现类，接管view从xml到
 * view实例的解析工作
 */
public class MyFactory2 implements LayoutInflater.Factory2 {

    private Activity mActivity;

    public MyFactory2(Activity activity) {
        mActivity = activity;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        // 交给framework处理
        return null;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // 是否支持换肤
        boolean enable = attrs.getAttributeBooleanValue (Consts.NAMESPACE, Consts.ATTR_ENABLE, false);
        // 不支持就返回null,交给framework处理
        if(!enable){
            LogUtils.d ("a view whose name is:" + name + " does not support skinning!");
            return null;
        }
        LogUtils.d ("a view whose name is:" + name + " support skinning!");
        // 创建这个view对象
        View view = ViewParser.createViewFromTag (context, name, attrs);
        // 缓存view
        addViewToCachedMap (view, attrs);
        // 使用当前资源包处理view属性
        handleViewAttrs (view, attrs);
        return view;
    }

    // 缓存view
    private void addViewToCachedMap(View view, AttributeSet attrs){
        if (view == null) {
            return;
        }
        List<Attr> attrList = convertAttributeSet2AttrList (attrs);
        // 缓存view
        CachedViewManager.getInstance ().addViewToTarget (mActivity, view, attrList);
    }

    // 把attrs转化为List<Attr>
    private static List<Attr> convertAttributeSet2AttrList(AttributeSet attrs){
        // 遍历attrs拿到属性
        List<Attr> attrList = new ArrayList<> ();
        int count = attrs.getAttributeCount ();
        for (int i = 0; i < count; i++) {
            // 属性名
            String attrName = attrs.getAttributeName (i);
            // 属性值
            String attrValue = attrs.getAttributeValue (i);
            Attr attr = new Attr (attrName, attrValue);
            attrList.add (attr);
        }
        return attrList;
    }

    // 使用当前资源包处理view属性
    private static void handleViewAttrs(View view, AttributeSet attrs){
        List<Attr> attrList = convertAttributeSet2AttrList (attrs);
        List<AttrsHandler> attrsHandlerList = AttrsHandlerManager.getInstance ().getAttrsHandlerList ();
        for (AttrsHandler attrsHandler : attrsHandlerList) {
            attrsHandler.handleAttrs (view, attrList);
        }
    }

}
