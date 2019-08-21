package com.github.xiaogegechen.library;

/**
 * 常量类
 */
public class Consts {

    // 用以标记支持换肤view的命名空间
    public static final String NAMESPACE = "http://schemas.android.com/android/skin";

    // 支持换肤view的属性，
    public static final String ATTR_ENABLE = "enable";

    // 传入的皮肤包文件不存在或者为空
    public static final String FILE_ERROR = "the skin file you passed is null or not exist! please check it!";

    // 从文件加载资源遇到异常
    public static final String EXCEPTION_ERROR = "catch exception when loading skin resources from file, please see log for more information and track stack!";

    //未初始化
    public static final String INIT_ERROR = "you must call Skinning.INSTANCE.init(Context applicationContext) first!";

    // log tag
    public static final String SKINNING_TAG = "skinning";

    public static final String SKINNING_LOG_PREFIX = "Skinning >>>> ";

    // log开关
    public static final boolean LOG = true;
}
