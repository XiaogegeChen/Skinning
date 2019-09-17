package com.github.xiaogegechen.library;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 资源管理类
 */
public class ResourcesManager {

    private static volatile ResourcesManager sInstance;

    public static ResourcesManager getInstance(){
        if(sInstance == null){
            synchronized (ResourcesManager.class){
                if (sInstance == null) {
                    sInstance = new ResourcesManager();
                }
            }
        }
        return sInstance;
    }

    // 原始资源
    Resources mOriginRes;
    // 当前皮肤包资源
    Resources mCurrentRes;
    // 当前皮肤包包名
    String mCurrentPkgName;

    private Context mApplicationContext;

    private ResourcesManager(){
    }

    public void init(Context applicationContext){
        mApplicationContext = applicationContext;
        mOriginRes = mApplicationContext.getResources ();
        mCurrentRes = mOriginRes;
    }

    /**
     * 从文件中加载资源并设置为当前的资源
     * @param skinFile 皮肤包文件
     * @return 加载成功返回true,失败返回false
     */
    public boolean loadAndSetRes(File skinFile, ISkinningListener listener){
        Resources res = loadResFromFile (skinFile, listener);
        if (res != null) {
            mCurrentRes = res;
            return true;
        }

        return false;
    }

    // 从文件中加载资源
    private Resources loadResFromFile(File skinFile, ISkinningListener listener){
        if(skinFile == null || !skinFile.exists ()){
            LogUtils.d (Consts.FILE_ERROR);
            listener.onFailure (Consts.FILE_ERROR);
            return null;
        }
        try {
            PackageManager pm = mApplicationContext.getPackageManager ();
            PackageInfo pi = pm.getPackageArchiveInfo (skinFile.getAbsolutePath (), PackageManager.GET_ACTIVITIES);
            mCurrentPkgName = pi.packageName;
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, skinFile.getAbsolutePath ());
            return new Resources (assetManager, mOriginRes.getDisplayMetrics (), mOriginRes.getConfiguration ());
        } catch (Exception e) {
            e.printStackTrace ();
            LogUtils.d (Consts.EXCEPTION_ERROR);
            listener.onFailure (Consts.EXCEPTION_ERROR);
        }
        return null;
    }
}
