package com.github.xiaogegechen.library;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.core.view.LayoutInflaterCompat;

import com.github.xiaogegechen.library.impl.SkinningListenerAdapter;
import com.github.xiaogegechen.library.impl.TextColorHandler;
import com.github.xiaogegechen.library.manager.AttrsHandlerManager;
import com.github.xiaogegechen.library.manager.CachedViewManager;
import com.github.xiaogegechen.library.manager.ResourcesManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外部调用，单例模式
 */
public enum Skinning {

    INSTANCE;

    // application 级别的context
    private Context mApplicationContext;

    private Map<Activity, MyFactory2> mActivityMyFactoryMap = new HashMap<> ();

    /**
     * 初始化
     * @param context application 级别的context
     */
    public void init(Context context){
        mApplicationContext = context;
        initAttrsHandlerManager ();
        initResourcesManager ();
    }

    private void initAttrsHandlerManager(){
        AttrsHandlerManager.getInstance ().addAttrsHandler (new TextColorHandler ());
    }

    private void initResourcesManager(){
        ResourcesManager.getInstance ().init (mApplicationContext);
    }

    /**
     * 添加属性处理器
     * @param handler 属性处理器
     */
    public void addAttrsHandler(AttrsHandler handler){
        checkInit ();
        AttrsHandlerManager.getInstance ().addAttrsHandler (handler);
    }

    /**
     * 批量添加属性处理器
     * @param handlerList 属性处理器集合
     */
    public void addAttrsHandlers(List<AttrsHandler> handlerList){
        checkInit ();
        AttrsHandlerManager.getInstance ().addAttrsHandlers (handlerList);
    }

    /**
     * 更改皮肤，更改后要通知所有已经加载过的并且支持换肤view进行更新
     * 对于没有加载的但是支持换肤的view，加载时候会自动处理，不用管
     * @param newSkinFile 皮肤包所在的文件
     * @param listener 回调监听
     */
    public void switchToNewSkin(File newSkinFile, ISkinningListener listener){
        checkInit ();
        if (listener == null) {
            listener = new SkinningListenerAdapter ();
        }
        // 加载并更换当前资源
        boolean isLoadSuccess = ResourcesManager.getInstance ().loadAndSetRes (newSkinFile, listener);
        if(isLoadSuccess){
            // 更新缓存的view
            CachedViewManager.getInstance ().refreshCachedView (AttrsHandlerManager.getInstance ().getAttrsHandlerList (), listener);
        }
    }

    /**
     * 更改皮肤，更改后要通知所有已经加载过的并且支持换肤view进行更新
     * 对于没有加载的但是支持换肤的view，加载时候会自动处理，不用管
     * @param newSkinFile 皮肤包所在的文件
     */
    public void switchToNewSkin(File newSkinFile){
        switchToNewSkin (newSkinFile, null);
    }

    public void attach(Activity activity){
        MyFactory2 factory2 = new MyFactory2 (activity);
        LayoutInflaterCompat.setFactory2 (activity.getLayoutInflater (), factory2);
        mActivityMyFactoryMap.put (activity, factory2);
    }

    public void detach(Activity activity){
        CachedViewManager.getInstance ().removeAllViewsFromTarget (activity);
        mActivityMyFactoryMap.remove (activity);
    }

    public void removeView(Activity activity, View view){
        // 从该activity种删除view
        CachedViewManager.getInstance ().removeViewFromTarget (activity, view);
    }

    private void checkInit(){
        if (mApplicationContext == null) {
            throw new RuntimeException (Consts.INIT_ERROR);
        }
    }
}
