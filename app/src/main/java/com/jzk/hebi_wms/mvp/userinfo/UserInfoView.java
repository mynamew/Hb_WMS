package com.jzk.hebi_wms.mvp.userinfo;


import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.UserInfoBean;

/**
 * author: timi
 * create at: 2017-08-21 09:18
 */
public interface UserInfoView extends MvpBaseView {

    /**
     * 从sp 获取用户信息
     *
     * @param bean
     */
    public void setSpUserInfo(UserInfoBean bean);
}
