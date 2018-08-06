package com.jzk.hebi_wms.mvp.ipqc.result;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcDataRequest;
import com.jzk.hebi_wms.data.ipqc.SaveCheckResultRequest;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * 抽检采集的view
 *
 * @author jzk
 * create at: 2018/8/3 11:25
 */
public class CheckResultPresenter extends MvpBasePresenter<CheckResultView> {
    private CheckResultModel model;
    private HttpSubscriber<CollectionIpqcData> getCollectionIPQCDataAsyncHttpSubscriber;
    private HttpSubscriber<Object> saveCheckResultHttpSubscriber;
    /**
     * 根据不良代码组获取不良代码
     */
    private HttpSubscriber<InjectPassBean> errorCodeHttpSubscriber;
    public CheckResultPresenter(Context context) {
        super(context);
        model = new CheckResultModel();
    }

    /**
     * 获取采集数据
     *
     * @param request
     */
    public void getCollectionIPQCDataAsync(CollectionIpqcDataRequest request) {
        if (null == getCollectionIPQCDataAsyncHttpSubscriber) {
            getCollectionIPQCDataAsyncHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<CollectionIpqcData>() {
                @Override
                public void onSuccess(CollectionIpqcData o) {
                   getView().getCollectionIPQCDataAsync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getCollectionIPQCDataAsync(request, getCollectionIPQCDataAsyncHttpSubscriber);
    }

    /**
     * 保存采集数据
     *
     * @param request
     */
    public void saveCheckResult(SaveCheckResultRequest request) {
        if (null == getCollectionIPQCDataAsyncHttpSubscriber) {
            getCollectionIPQCDataAsyncHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<CollectionIpqcData>() {
                @Override
                public void onSuccess(CollectionIpqcData o) {
                   getView().saveCheckResult(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.saveCheckResult(request, saveCheckResultHttpSubscriber);
    }
    /**
     * 获取不良代码！
     */
    public void getErrorInfoByGroupCode(String errorGroupId) {
        if (null == errorCodeHttpSubscriber) {
            errorCodeHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectPassBean>() {
                @Override
                public void onSuccess(InjectPassBean o) {
                    getView().dismisProgressDialog();
                    getView().getErrorInfoByGroupCode(o.getErrorCodes());
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getErrorInfoByGroupCodeAsync(errorCodeHttpSubscriber, errorGroupId);
    }
}
