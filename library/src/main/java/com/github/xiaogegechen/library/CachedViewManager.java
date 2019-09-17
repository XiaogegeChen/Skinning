package com.github.xiaogegechen.library;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.github.xiaogegechen.library.model.Attr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于管理缓存的支持换肤的view集合，存储结构为：
 * Map<Activity, Map<View, List<Attr>>> activity为组，Map<View, List<Attr>>是改组的所有缓存的view及其属性
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

    /**
     * 向特定的分组添加view
     * @param target activity,组
     * @param view 要添加的view
     * @param attrs 要添加view的属性
     */
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

    /**
     * 从特定分组种删除特定的view
     * @param target activity,组
     * @param view 要删除的view
     */
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

    /**
     * 删除特定的分组
     * @param target activity,组
     */
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
