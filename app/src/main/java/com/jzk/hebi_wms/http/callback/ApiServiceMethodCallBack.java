package com.jzk.hebi_wms.http.callback;


import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;

import io.reactivex.Observable;

/**
 * api setvice 的回调
 * author: timi
 * create at: 2017-08-15 10:56
 */
public interface ApiServiceMethodCallBack<T> {
    Observable<CommonResult<T>> createObservable(ApiService apiService);
}
