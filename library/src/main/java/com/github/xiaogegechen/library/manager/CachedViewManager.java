package com.github.xiaogegechen.library.manager;

import android.view.View;

import com.github.xiaogegechen.library.Attr;
import com.github.xiaogegechen.library.AttrsHandler;
import com.github.xiaogegechen.library.ISkinningListener;
import com.github.xiaogegechen.library.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存的支持换肤的view集合，单例
 */
public class CachedViewManager {

    private static volatile CachedViewManager sInstance;

    public static CachedViewManager getInstance() {
        if (sInstance == null) {
            synchronized (CachedViewManager.class){
                if (sInstance == null) {
                    sInstance = new CachedViewManager ();
                }
            }
        }
        return sInstance;
    }

    // 已经解析过的并且支持换肤的view和它的属性,以便换肤后更新view
    private Map<View, List<Attr>> mMap;

    private CachedViewManager(){
        mMap = new HashMap<> ();
    }

    /**
     * 拿到缓存的mMap
     * @return mMap
     */
    public Map<View, List<Attr>> getMap() {
        return mMap;
    }

    /**
     * 向mMap中添加view
     * @param view view
     * @param attrList view的属性集合
     */
    public void addView(View view, List<Attr> attrList){
        mMap.put (view, attrList);
    }

    /**
     * 更新所有缓存的view
     * @param handlerList 属性处理器集合
     * @param listener 换肤监听
     */
    public void refreshCachedView(List<AttrsHandler> handlerList, ISkinningListener listener){
        LogUtils.d ("cachedMap is -> " + mMap);
        for (View view : mMap.keySet ()) {
            List<Attr> attrList = mMap.get (view);
            for (AttrsHandler attrsHandler : handlerList) {
                attrsHandler.handleAttrs (view, attrList);
            }
        }
        LogUtils.d ("refresh done!");
        listener.onSuccess ();
    }
}
