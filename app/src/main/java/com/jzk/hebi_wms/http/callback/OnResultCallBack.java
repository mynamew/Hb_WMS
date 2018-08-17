package com.jzk.hebi_wms.http.callback;

/**
 * 网络请求的返回
 * @author: jzk
 * create at: 2017-08-15 11:26     
 */
public interface OnResultCallBack<T> {
    /**
     * 成功的返回
     * @param t
     */
    void onSuccess(T t);

    /**
     * 失败的返回
     * @param errorMsg
     */
    void onError(String errorMsg);
}
