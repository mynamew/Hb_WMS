package com.jzk.hebi_wms.mvp.ipqc;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

public class CheckAppearancePresenter extends MvpBasePresenter<CheckAppearanceView> {
    private CheckAppearanceModel model;

    private HttpSubscriber<IpqcCommonResult> getLotInfoAsyncHttpSubscriber;
    private HttpSubscriber<IpqcCommonResult> createNewLotNoAsyncSubscriber;
    private HttpSubscriber<IpqcCommonResult> getIQPCNameAsyncSubscriber;
    private HttpSubscriber<IpqcCommonResult> getTimePerodAsyncSubscriber;
    private HttpSubscriber<IpqcCommonResult> getProcessAsyncSubscriber;
    private HttpSubscriber<IpqcCommonResult> checkRCardInfoAsyncSubscriber;

    public CheckAppearancePresenter(Context context) {
        super(context);
        model = new CheckAppearanceModel();
    }

    /**
     * 获取批号信息
     * @param lotNo
     */
    public void getLotInfoAsync(String lotNo) {
        if (null == getLotInfoAsyncHttpSubscriber) {
            getLotInfoAsyncHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<IpqcCommonResult>() {
                @Override
                public void onSuccess(IpqcCommonResult o) {
                    getView().getLotInfoAsync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getLotInfoAsync(lotNo, getLotInfoAsyncHttpSubscriber);
    }
}
