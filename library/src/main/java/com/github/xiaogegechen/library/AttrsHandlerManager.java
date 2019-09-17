package com.github.xiaogegechen.library;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * AttrsHandler管理类，单例
 */
public class AttrsHandlerManager {

    private static volatile AttrsHandlerManager sInstance;

    public static AttrsHandlerManager getInstance() {
        if (sInstance == null) {
            synchronized (AttrsHandlerManager.class){
                if (sInstance == null) {
                    sInstance = new AttrsHandlerManager ();
                }
            }
        }
        return sInstance;
    }

    private AttrsHandlerManager(){
        mAttrsHandlerList = new ArrayList<> ();
    }

    // 属性处理器集合
    private List<AttrsHandler> mAttrsHandlerList;

    /**
     * 批量添加属性处理器
     * @param attrsHandlerList 集合
     */
    public void addAttrsHandlers(@NonNull List<AttrsHandler> attrsHandlerList){
        mAttrsHandlerList.addAll (attrsHandlerList);
    }

    /**
     * 添加属性处理器
     * @param handler 单个处理器
     */
    public void addAttrsHandler(@NonNull AttrsHandler handler){
        if(!mAttrsHandlerList.contains (handler)){
            mAttrsHandlerList.add (handler);
        }
    }

    /**
     * 获取当前属性处理器集合
     * @return 当前属性处理器集合
     */
    public List<AttrsHandler> getAttrsHandlerList(){
        return mAttrsHandlerList;
    }

}
