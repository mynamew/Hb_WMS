package com.jzk.hebi_wms.mvp.home;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.VersionBean;

import java.io.File;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 15:55
 */

public interface SetFragmentView extends MvpBaseView {
    /**
     * 获取当前版本
     * @param versionBean
     */
    void getVersion(VersionBean versionBean);

    /**
     * 下载APK
     * @param o
     * @param versionBean
     * @param newVersion
     */
    void downLoadApk(File o, VersionBean versionBean, String newVersion);
}
