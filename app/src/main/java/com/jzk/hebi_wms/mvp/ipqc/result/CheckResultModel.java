package com.jzk.hebi_wms.mvp.ipqc.result;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcDataRequest;
import com.jzk.hebi_wms.data.ipqc.SaveCheckResultRequest;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * 抽检采集的model
 *
 * @author jzk
 * create at: 2018/8/3 11:24
 */
public class CheckResultModel extends MvpBaseModel {
    /**
     * 获取采集数据
     *
     * @param collectionIpqcDataRequest
     * @param observer
     */
    public void getCollectionIPQCDataAsync(CollectionIpqcDataRequest collectionIpqcDataRequest, Observer<CollectionIpqcData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<CollectionIpqcData>() {
            @Override
            public Observable<CommonResult<CollectionIpqcData>> createObservable(ApiService apiService) {
                return apiService.getCollectionIPQCDataAsync(collectionIpqcDataRequest);
            }
        });
    }
    /**
     * 获取不良代码
     *
     * @param stationBeanHttpSubscriber
     */
    public void getErrorInfoByGroupCodeAsyncByQuality
    (HttpSubscriber<CollectionIpqcData> stationBeanHttpSubscriber, String request) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getErrorInfoByGroupCodeAsyncByQuality(request));
    }
    /**
     * 保存采集数据
     *
     * @param request
     * @param observer
     */
    public void saveCheckResult(SaveCheckResultRequest request, Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.createIPQCTemporaryDatasAsync(request);
            }
        });
    }
}
