package com.jzk.hebi_wms.mvp.station;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

import io.reactivex.Observable;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 17:05
 */
public class StationSelectModel extends MvpBaseModel {
    /**
     * 获取工位
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void getStations(StationRequest request, HttpSubscriber<StationBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getStations(request));
    }
}
