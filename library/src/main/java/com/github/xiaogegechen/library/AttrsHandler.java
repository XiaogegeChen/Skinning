package com.github.xiaogegechen.library;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;

import com.github.xiaogegechen.library.model.Attr;

import java.util.List;

public abstract class AttrsHandler {

    protected static Context sApplicationContext;

    public static void init(Context applicationContext){
        sApplicationContext = applicationContext;
    }

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
     * 从当前的皮肤包中拿到新的id
     * @param resId 旧的id
     * @return 新的id，0无效.有可能是0，使用者需要检查。
     */
    protected int getId(int resId){
        Resources originRes = ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        String resName = originRes.getResourceEntryName (resId);
        String attrTypeName = originRes.getResourceTypeName(resId);
        return currentRes.getIdentifier (resName, attrTypeName, currentPkgName);
    }

    /**
     * 从当前的皮肤包中拿到颜色属性值（当前皮肤包中R.class中id）
     * @param resId 原来xml文件中颜色的属性值(原R.class文件中id)
     * @return 当前皮肤包中的颜色
     */
    protected int getColor(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        int originColor = originRes.getColor (resId);
        // 颜色名称,如 bg_button
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "color", currentPkgName);
        if(newId == 0){
            // 皮肤包没有设置这个颜色
            LogUtils.d ("no color find in skin file, so use origin color!");
            return originColor;
        }
        return currentRes.getColor (newId);
    }

    /**
     * 从当前皮肤包拿到尺寸值（px）
     * @param resId 原尺寸资源id
     * @return 当前皮肤包中尺寸
     */
    protected int getDimensionPixelSize(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        int oldDimenPixelSize = originRes.getDimensionPixelSize (resId);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "dimen", currentPkgName);
        if(newId == 0){
            return oldDimenPixelSize;
        }
        return currentRes.getDimensionPixelSize (newId);
    }

    /**
     * 从当前皮肤包拿到Drawable
     * @param resId 原Drawable id
     * @return 当前皮肤包中的drawable
     */
    protected Drawable getDrawable(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        Drawable oldDrawable = originRes.getDrawable(resId);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "drawable", currentPkgName);
        if(newId == 0){
            return oldDrawable;
        }
        return currentRes.getDrawable(newId);
    }

    /**
     * 从当前皮肤包拿到字符串
     * @param resId 原资源id
     * @return 当前皮肤包中的字符串
     */
    protected String getString(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        String old = originRes.getString(resId);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "string", currentPkgName);
        if(newId == 0){
            return old;
        }
        return currentRes.getString(newId);
    }

    /**
     * 从当前皮肤包拿到数字
     * @param resId 原资源id
     * @return 当前皮肤包中的数字
     */
    protected int getInteger(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        int old = originRes.getInteger(resId);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "integer", currentPkgName);
        if(newId == 0){
            return old;
        }
        return currentRes.getInteger(newId);
    }

    /**
     * 从当前皮肤包拿到bool
     * @param resId 原资源id
     * @return 当前皮肤包中的bool
     */
    protected boolean getBoolean(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        boolean old = originRes.getBoolean(resId);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "bool", currentPkgName);
        if(newId == 0){
            return old;
        }
        return currentRes.getBoolean(newId);
    }

    /**
     * 从当前皮肤包拿到字符串数组
     * @param resId 原资源id
     * @return 当前皮肤包中的字符串数组
     */
    protected String[] getStringArray(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        String[] old = originRes.getStringArray(resId);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "array", currentPkgName);
        if(newId == 0){
            return old;
        }
        return currentRes.getStringArray(newId);
    }

    /**
     * 从当前皮肤包拿到int数组
     * @param resId 原资源id
     * @return 当前皮肤包中的int数组
     */
    protected int[] getIntArray(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        int[] old = originRes.getIntArray(resId);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "array", currentPkgName);
        if(newId == 0){
            return old;
        }
        return currentRes.getIntArray(newId);
    }

    /**
     * 从当前皮肤包拿到分数
     * @param resId 原资源id
     * @return 当前皮肤包中的分数
     */
    protected float getFraction(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        float old = originRes.getFraction(resId, 1, 1);
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "array", currentPkgName);
        if(newId == 0){
            return old;
        }
        return currentRes.getFraction(newId, 1, 1);
    }

    /**
     * 从当前皮肤包拿到浮点数
     * @param resId 原资源id
     * @return 当前皮肤包中的浮点数
     */
    protected float getFloat(int resId){
        Resources originRes= ResourcesManager.getInstance ().mOriginRes;
        Resources currentRes = ResourcesManager.getInstance ().mCurrentRes;
        String currentPkgName = ResourcesManager.getInstance ().mCurrentPkgName;
        TypedValue typedValue = new TypedValue();
        originRes.getValue(resId, typedValue, true);
        float old = typedValue.getFloat();
        String resName = originRes.getResourceEntryName (resId);
        int newId = currentRes.getIdentifier (resName, "dimen", currentPkgName);
        if(newId == 0){
            return old;
        }
        TypedValue newTypedValue = new TypedValue();
        currentRes.getValue(resId, newTypedValue, true);
        return newTypedValue.getFloat();
    }

}
