package com.jzk.hebi_wms.mvp.userinfo;


import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.UserInfoBean;
import com.jzk.hebi_wms.utils.SpUtils;

/**
 * author: timi
 * create at: 2017-08-21 09:20
 */
public class UserInfoModel extends MvpBaseModel {
    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoBean getSpUserInfo() {
        UserInfoBean bean = new UserInfoBean();
        //获取各个存储过的信息
        bean.userName = SpUtils.getInstance().getString(Constants.USER_NAME);
        bean.userSex = SpUtils.getInstance().getString(Constants.USER_SEX);
        bean.userTel= SpUtils.getInstance().getString(Constants.USER_TEL);
        bean.userDepart = SpUtils.getInstance().getString(Constants.USER_DEPART);
        bean.userFrom = SpUtils.getInstance().getString(Constants.USER_FROM);
        bean.userRoot = SpUtils.getInstance().getString(Constants.USER_ROOT);
        bean.userNum = SpUtils.getInstance().getString(Constants.USER_NUM);
        return bean;
    }
}
