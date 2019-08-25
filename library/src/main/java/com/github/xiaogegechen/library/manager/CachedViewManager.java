package com.github.xiaogegechen.library.manager;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.github.xiaogegechen.library.model.Attr;
import com.github.xiaogegechen.library.AttrsHandler;
import com.github.xiaogegechen.library.ISkinningListener;
import com.github.xiaogegechen.library.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于管理缓存的支持换肤的view集合，
 * 1. 当页面加载时，需要将该页面支持换肤的view都进行缓存，以便在换肤后全部更新
 * 2. 当页面销毁时，则需要将该页面上所有的支持换肤的view都从缓存里移除，避免内存泄露
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

    private Map<Activity, Map<View, List<Attr>>> mMap;

    private CachedViewManager(){
        mMap = new HashMap<> ();
    }

    public void addViewToTarget(Activity target, View view, List<Attr> attrs){
        if(!mMap.containsKey (target)){
            mMap.put (target, new HashMap<> ());
        }
        Map<View, List<Attr>> viewListMap = mMap.get (target);
        if (viewListMap != null) {
            //添加进去
            if(!viewListMap.containsKey (view)){
                viewListMap.put (view, attrs);
            }
            LogUtils.d ("addViewToTarget success, the view is: " + view + ", target is: " + target);
        }
    }

    public void removeViewFromTarget(Activity target, View view){
        if(!mMap.containsKey (target)){
            return;
        }
        Map<View, List<Attr>> viewListMap = mMap.get (target);
        if(viewListMap != null){
            if(view instanceof ViewGroup){
                int count = ((ViewGroup) view).getChildCount ();
                for (int i = 0; i < count; i++) {
                    View child = ((ViewGroup) view).getChildAt (i);
                    viewListMap.remove (child);
                    LogUtils.d ("removeViewFromTarget success, the view is: " + view + ", target is: " + target);
                }
            }else{
                viewListMap.remove (view);
                LogUtils.d ("removeViewFromTarget success, the view is: " + view + ", target is: " + target);
            }
        }
    }

    public void removeAllViewsFromTarget(Activity target){
        if(!mMap.containsKey (target)){
            return;
        }
        mMap.remove (target);
        LogUtils.d ("removeAllViewsFromTarget success, the target is: "  + target);
    }

    /**
     * 更新所有缓存的view
     * @param handlerList 属性处理器集合
     * @param listener 换肤监听
     */
    public void refreshCachedView(List<AttrsHandler> handlerList, ISkinningListener listener){
        LogUtils.d ("cachedMap is -> " + mMap);
        for (Activity activity : mMap.keySet ()) {
            Map<View, List<Attr>> viewListMap = mMap.get (activity);
            if (viewListMap != null) {
                for (View view : viewListMap.keySet ()) {
                    List<Attr> attrs = viewListMap.get (view);
                    for (AttrsHandler handler : handlerList) {
                        handler.handleAttrs (view, attrs);
                    }
                }
            }
        }
        LogUtils.d ("refresh done!");
        listener.onSuccess ();
    }
}
