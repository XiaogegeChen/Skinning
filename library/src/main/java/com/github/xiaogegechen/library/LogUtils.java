package com.github.xiaogegechen.library;

import android.util.Log;

/**
 * Log工具类
 */
public class LogUtils {

    public static void i(String msg){
        if(Consts.LOG){
            Log.i (Consts.SKINNING_TAG, Consts.SKINNING_LOG_PREFIX + msg);
        }
    }

    public static void d(String msg){
        if(Consts.LOG){
            Log.d (Consts.SKINNING_TAG, Consts.SKINNING_LOG_PREFIX + msg);
        }
    }

    public static void e(String msg){
        if(Consts.LOG){
            Log.e (Consts.SKINNING_TAG, Consts.SKINNING_LOG_PREFIX + msg);
        }
    }
}
