package com.jzk.hebi_wms.mvp.process;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.process.ProcessSelectBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 14:37
 */
public class ProcessSelectPresenter extends MvpBasePresenter<ProcessSelectView> {
    private HttpSubscriber<List<ProcessSelectBean>> getProcessSelectSubscriber;
    ProcessSelectModel model;

    public ProcessSelectPresenter(Context context) {
        super(context);
        model = new ProcessSelectModel();
    }

    /**
     * 获取工序列表
     */
    public void getProcessSelectSubscriber() {
        if (null == getProcessSelectSubscriber) {
            getProcessSelectSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<ProcessSelectBean>>() {
                @Override
                public void onSuccess(List<ProcessSelectBean> o) {
                  getView().getProcessSelectSubscriber(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
            model.getProcessList(getProcessSelectSubscriber);
        }
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != getProcessSelectSubscriber) {
            getProcessSelectSubscriber.unSubscribe();
            getProcessSelectSubscriber = null;
        }
    }
}
