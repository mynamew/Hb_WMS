package com.jzk.hebi_wms.mvp.deviceinfo;


import com.jzk.hebi_wms.base.view.iml.MvpBaseView;

/**
 * author: timi
 * create at: 2017-08-24 16:34
 */
public interface DeviceInfoView extends MvpBaseView {
    void  setPDACode();

    void getPDACode(String o);
}
