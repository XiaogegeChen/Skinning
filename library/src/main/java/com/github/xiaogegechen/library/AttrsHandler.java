package com.github.xiaogegechen.library;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.github.xiaogegechen.library.manager.ResourcesManager;

import java.util.List;

public abstract class AttrsHandler {

    private static final String TAG = Consts.SKINNING_TAG;

    /**
     * 处理view的属性值，从而更新view，这个方法需要你从attrList中拿到你需要处理的
     * 属性attr，然后根据这个属性去当前的皮肤包中拿到同名属性值，并设置给view
     * @param view 待处理的view
     * @param attrList 这个view的属性集合
     */
    public abstract void handleAttrs(View view, List<Attr> attrList);

    /**
     * 返回一个属性值的id(R.class中id)，如果不是引用自资源，就返回0
     * @param attr 资源
     * @return 引用自资源返回资源id,否则返回0
     */
    protected int getResIdFromAttr(@NonNull Attr attr){
        if(!isAttrFromResources (attr)){
            return 0;
        }
        return Integer.parseInt (attr.getAttrValue ().substring (1));
    }

    /**
     * 通过属性值是否以"@"开头，判断一个attr是否引用自资源
     * @param attr 属性
     * @return 如果引用自资源，返回true，否则返回false
     */
    protected boolean isAttrFromResources(@NonNull Attr attr){
        String attrValue = attr.getAttrValue ();
        return attrValue != null && attrValue.startsWith ("@");
    }

    /**
     * 判断属性是不是目标属性
     * @param attr 属性
     * @param target 目标属性名
     * @return 如果是返回true,否则false
     */
    protected boolean attrIsTarget(@NonNull Attr attr,@NonNull String target){
        return target.equals (attr.getAttrName ());
    }

    /**
     * 从当前的皮肤包中拿到颜色属性值（当前皮肤包中R.class中id）
     * @param resId 原来xml文件中颜色的属性值(原R.class文件中id)
     * @return 当前皮肤包中R.class中id
     */
    protected int getColor(int resId){
        Resources originRes= ResourcesManager.getInstance ().getOriginRes ();
        Resources currentRes = ResourcesManager.getInstance ().getCurrentRes ();
        String currentPkgName = ResourcesManager.getInstance ().getCurrentPkgName ();
        int originColor = originRes.getColor (resId);
        // 颜色名称,如 bg_button
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "color", currentPkgName);
        if(newId == 0){
            // 皮肤包没有设置这个颜色
            Log.d (TAG, "no color find in skin file, so use origin color!");
            return originColor;
        }
        return currentRes.getColor (newId);
    }
}
