package com.jzk.hebi_wms.mvp.oqc.result;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcDataRequest;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

import io.reactivex.Observable;

/**
 * 抽检采集的model
 *
 * @author jzk
 *         create at: 2018/8/3 11:24
 */
public class CheckResultModel extends MvpBaseModel {

    public void GetCollectionIPQCDataAsync(CollectionIpqcDataRequest collectionIpqcDataRequest, HttpSubscriber<CollectionIpqcData> collectionIpqcDataHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(collectionIpqcDataHttpSubscriber, new ApiServiceMethodCallBack<CollectionIpqcData>() {
            @Override
            public Observable<CommonResult<CollectionIpqcData>> createObservable(ApiService apiService) {
                return apiService.getCollectionIPQCDataAsync(collectionIpqcDataRequest);
            }
        });

    }


}
