package com.jzk.hebi_wms.mvp.oqc.result;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcDataRequest;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * 抽检采集的view
 *
 * @author jzk
 * create at: 2018/8/3 11:25
 */
public class CheckResultPresenter extends MvpBasePresenter<CheckResultView> {
    CheckResultModel model;
    private HttpSubscriber<CollectionIpqcDataRequest> collectionIpqcDataRequestHttpSubscriber;


    public CheckResultPresenter(Context context) {
        super(context);
        model = new CheckResultModel();
    }

    public void getCollectionIPQCDataAsync(CollectionIpqcDataRequest getCollectionIPQCDataAsyncUploadBean){
        if (null == collectionIpqcDataRequestHttpSubscriber) {
            collectionIpqcDataRequestHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<CollectionIpqcData>() {
                @Override
                public void onSuccess(CollectionIpqcData collectionIpqcData) {
                    getView().GetCollectionIPQCDataAsyncUploadBean(collectionIpqcData);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
    }

}
