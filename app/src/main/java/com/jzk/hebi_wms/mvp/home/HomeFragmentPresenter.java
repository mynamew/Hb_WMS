package com.jzk.hebi_wms.mvp.home;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.VersionBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;


/**
 * $dsc
 * author: timi
 * create at: 2018-07-05 08:33
 */
public class HomeFragmentPresenter extends MvpBasePresenter<HomeFragmentView> {
    HomeFragmentModel model;
    private HttpSubscriber<VersionBean> versionBeanHttpSubscriber;
    public HomeFragmentPresenter(Context context) {
        super(context);
        model=new HomeFragmentModel();
    }
    /**
     * 获取app 版本
     *
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
        model.getVersion( versionBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != versionBeanHttpSubscriber) {
            versionBeanHttpSubscriber.unSubscribe();
            versionBeanHttpSubscriber = null;
        }
    }
}
