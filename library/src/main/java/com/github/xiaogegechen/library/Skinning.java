package com.github.xiaogegechen.library;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.core.view.LayoutInflaterCompat;

import com.github.xiaogegechen.library.impl.ImageViewHandler;
import com.github.xiaogegechen.library.impl.SkinningListenerAdapter;
import com.github.xiaogegechen.library.impl.TextViewHandler;
import com.github.xiaogegechen.library.impl.ViewHandler;
import com.github.xiaogegechen.library.model.Attr;

import java.io.File;
import java.util.ArrayList;
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
     * @param context context
     * @param addDefaultAttrsHandlers 是否把默认的AttrsHandlers添加进去
     */
    public void init(Context context, boolean addDefaultAttrsHandlers){
        mApplicationContext = context;
        initResourcesManager ();
        if(addDefaultAttrsHandlers){
            initAttrsHandlerManager ();
        }
    }

    private void initAttrsHandlerManager(){
        // 默认的 AttrsHandler
        List<AttrsHandler> attrsHandlerList = new ArrayList<>();
        attrsHandlerList.add(new ViewHandler());
        attrsHandlerList.add(new TextViewHandler());
        attrsHandlerList.add(new ImageViewHandler());
        AttrsHandlerManager.getInstance ().addAttrsHandlers (attrsHandlerList);
    }

    private void initResourcesManager(){
        ResourcesManager.getInstance().init (mApplicationContext);
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

    public void addView(Activity target, View view, List<Attr> attrs){
        // 向该activity添加view
        CachedViewManager.getInstance ().addViewToTarget(target, view, attrs);
    }

    private void checkInit(){
        if (mApplicationContext == null) {
            throw new RuntimeException (Consts.INIT_ERROR);
        }
    }
}
