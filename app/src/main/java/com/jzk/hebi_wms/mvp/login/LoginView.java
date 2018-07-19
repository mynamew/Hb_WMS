package com.jzk.hebi_wms.mvp.login;


import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.LoginBean;

/**
 * 登录
 */

public interface LoginView extends MvpBaseView {
    void getLoginResult(LoginBean bean);
}
