package com.jzk.hebi_wms.mvp.home;


import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.VersionBean;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-05 08:33
 */
public class HomeFragmentModel extends MvpBaseModel {
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
    /**
     * 下载APK
     *
     * @param url
     * @param observer
     */
    public void downLoadApk(final String url, Observer<File> observer) {
        HttpManager.getInstance().downLoadAPkRequest(observer, url);
    }
}
