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
    /**
     * 生成批号信息
     */
    public void createNewLotNoAsync() {
        if (null == createNewLotNoAsyncSubscriber) {
            createNewLotNoAsyncSubscriber = new HttpSubscriber<>(new OnResultCallBack<IpqcCommonResult>() {
                @Override
                public void onSuccess(IpqcCommonResult o) {
                    getView().createNewLotNoAsync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.createNewLotNoAsync(createNewLotNoAsyncSubscriber);
    }
    /**
     * 获取质检名称
     */
    public void getIQPCNameAsync() {
        if (null == getIQPCNameAsyncSubscriber) {
            getIQPCNameAsyncSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<IpqcCommonResult>() {
                @Override
                public void onSuccess(IpqcCommonResult o) {
                    getView().getIQPCNameAsync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getIQPCNameAsync(getIQPCNameAsyncSubscriber);
    }
    /**
     * 获取时段
     */
    public void getTimePerodAsync() {
        if (null == getTimePerodAsyncSubscriber) {
            getTimePerodAsyncSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<IpqcCommonResult>() {
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
     * 获取工序
     */
    public void getProcessAsync() {
        if (null == getProcessAsyncSubscriber) {
            getProcessAsyncSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<IpqcCommonResult>() {
                @Override
                public void onSuccess(IpqcCommonResult o) {
                    getView().getProcessAsync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getProcessAsync(getProcessAsyncSubscriber);
    }
}
