package com.jzk.hebi_wms.mvp.ipqc.record;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcProcessResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcRecordRequest;
import com.jzk.hebi_wms.data.ipqc.record.IpqcRecordResult;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
  * ipqc抽检外观的presenter
  * @author   jzk
  * create at: 2018/8/24 9:28
  */  
public class IpqcRecordPresenter extends MvpBasePresenter<IpqcRecordView> {
    private IpqcRecordModel model;
    private HttpSubscriber<IpqcCommonResult> getTimePerodAsyncSubscriber;
    private HttpSubscriber<IpqcProcessResult> getProcessSelectSubscriber;
    private HttpSubscriber<IpqcRecordResult> ipqcRecordResultHttpSubscriber;
    public IpqcRecordPresenter(Context context) {
        super(context);
        model=new IpqcRecordModel();
    }
    /**
     * 获取时段
     */
    public void getTimePerodAsync() {
        if (null == getTimePerodAsyncSubscriber) {
            getTimePerodAsyncSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<IpqcCommonResult>() {
                @Override
                public void onSuccess(IpqcCommonResult o) {
                    getView().getTimePerodAsync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getTimePerodAsync(getTimePerodAsyncSubscriber);
    }

    /**
     * 获取工序列表
     */
    public void getProcessSelectSubscriber() {
        if (null == getProcessSelectSubscriber) {
            getProcessSelectSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<IpqcProcessResult>() {
                @Override
                public void onSuccess(IpqcProcessResult o) {
                    getView().getProcessSelectSubscriber(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
            model.getProcessList(getProcessSelectSubscriber);
        }
    }
    /**
     * 获取抽检记录
     */
    public void getIPQCInfoAsync(IpqcRecordRequest recordRequest) {
        if (null == ipqcRecordResultHttpSubscriber) {
            ipqcRecordResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<IpqcRecordResult>() {
                @Override
                public void onSuccess(IpqcRecordResult o) {
                    getView().getIPQCInfoAsync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getIPQCInfoAsync(recordRequest,ipqcRecordResultHttpSubscriber);
    }


}
