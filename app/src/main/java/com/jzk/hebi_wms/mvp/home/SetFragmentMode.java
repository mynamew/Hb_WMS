package com.jzk.hebi_wms.mvp.home;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.VersionBean;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 15:56
 */

public class SetFragmentMode extends MvpBaseModel {
    /**
     * 获取app 版本
     *

     * @param observer
     */
    public void getVersion( Observer<VersionBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<VersionBean>() {
            @Override
            public Observable<CommonResult<VersionBean>> createObservable(ApiService apiService) {
                return apiService.getAppVersion(1,"WPDA");
            }
        });
    }
}
