package com.jzk.hebi_wms.mvp.home;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.VersionBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

import java.io.File;


/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 15:57
 */

public class SetFragmentPresenter extends MvpBasePresenter<SetFragmentView> {
    private SetFragmentMode setFragmentMode;
    private HttpSubscriber<VersionBean> versionBeanHttpSubscriber;
    private HttpSubscriber<File> downloadHttpSubscriber;

    public SetFragmentPresenter(Context context) {
        super(context);
        setFragmentMode = new SetFragmentMode();
    }

    /**
     * 获取app 版本
     */
    public void getVersion() {
        if (null == versionBeanHttpSubscriber) {
            versionBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<VersionBean>() {
                @Override
                public void onSuccess(VersionBean versionBean) {
                    getView().getVersion(versionBean);
                }

                @Override
                public void onError(String errorMsg) {
                }
            });
        }
        setFragmentMode.getVersion(versionBeanHttpSubscriber);
    }

    /**
     * 下载APK
     *
     * @param url
     * @param versionBean
     * @param newVersion
     */
    public void downLoad(String url, VersionBean versionBean, String newVersion) {
        if (null == downloadHttpSubscriber) {
            downloadHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<File>() {
                @Override
                public void onSuccess(File o) {
                    getView().downLoadApk(o, versionBean, newVersion);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        setFragmentMode.downLoadApk(url, downloadHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != versionBeanHttpSubscriber) {
            versionBeanHttpSubscriber.unSubscribe();
            versionBeanHttpSubscriber = null;
        }
        if (null != downloadHttpSubscriber) {
            downloadHttpSubscriber.unSubscribe();
            downloadHttpSubscriber = null;
        }
    }
}
