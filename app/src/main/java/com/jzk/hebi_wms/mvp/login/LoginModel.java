package com.jzk.hebi_wms.mvp.login;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.LoginBean;
import com.jzk.hebi_wms.data.LoginRequest;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class LoginModel extends MvpBaseModel {
    /**
     * 登录的方法
     * author: timi
     * create at: 2017/8/15 14:26
     */
    public void login(final LoginRequest bean, Observer<LoginBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, apiService -> apiService.login(bean));
    }
}
