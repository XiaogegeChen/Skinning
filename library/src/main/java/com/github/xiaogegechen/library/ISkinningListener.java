package com.github.xiaogegechen.library;

/**
 * 换肤监听回调
 */
public interface ISkinningListener {

    /**
     * 成功
     */
    void onSuccess();

    /**
     * 失败
     * @param errorMsg 失败原因
     */
    void onFailure(String errorMsg);

}
