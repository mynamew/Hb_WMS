package com.jzk.hebi_wms.mvp.update_password;


import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.set.ChangePasswordRequest;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;

import io.reactivex.Observable;

/**
 * $dsc 更改密码的model
 * author: timi
 * create at: 2017-08-24 17:16
 */

public class UpdatePassworModel extends MvpBaseModel {
    /**
     * 修改密码
     * @param request
     * @param observer
     */
    public void changePassword(final ChangePasswordRequest request, io.reactivex.Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.changePassword(request);
            }
        });
    }
}
