package com.jzk.hebi_wms.mvp.inject_mold;

import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.inject.CheckRCardInfoRquest;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.inject.InjectMouldCommitRequest;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * @author: timi
 * create at: 2018/7/20 10:21
 */
public class InjectMoldModel extends MvpBaseModel {
    /**
     * 获取工位
     *
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void getStations(StationRequest request, HttpSubscriber<StationBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getStations(request));
    }

    /**
     * 获取注塑机
     *
     * @param stationBeanHttpSubscriber
     */
    public void getEquipmentByTypeList(HttpSubscriber<EquipmentByTypeList> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.getEquipmentByTypeList(Constants.DeviceType.MOLDING.toString()));
    }

    /**
     * 获取模具
     *
     * @param stationBeanHttpSubscriber
     */
    public void getMould(HttpSubscriber<InjectMoldBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber,
                apiService -> apiService.getInjectionMoldings(Constants.DeviceType.MOULD.toString()));
    }
    /**
     * 校验
     *
     * @param stationBeanHttpSubscriber
     */
    public void checkRCardInfoAsync
    (HttpSubscriber<InjectPassBean> stationBeanHttpSubscriber, CheckRCardInfoRquest request) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.checkRCardInfoAsync(request));
    }

    /**
     * 获取不良代码组
     *
     * @param stationBeanHttpSubscriber
     */
    public void getErrorInfosAsync(HttpSubscriber<InjectPassBean> stationBeanHttpSubscriber,
                                   int request) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getErrorInfosAsync(request));
    }

    /**
     * 获取不良代码
     *
     * @param stationBeanHttpSubscriber
     */
    public void getErrorInfoByGroupCodeAsync
    (HttpSubscriber<InjectPassBean> stationBeanHttpSubscriber, String request) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getErrorInfoByGroupCodeAsync(request));
    }

    /**
     * 获取不良代码输入
     *
     * @param stationBeanHttpSubscriber
     * @param categoryId                产品别Id
     * @param errorCode                 输入的不良代码
     */
    public void getErrorInfoByErrorCodeAsync
    (HttpSubscriber<InjectPassBean> stationBeanHttpSubscriber, int categoryId, String
            errorCode) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getErrorInfoByErrorCodeAsync(categoryId, errorCode));
    }

    /**
     * 注塑过站提交
     *
     * @param stationBeanHttpSubscriber
     */
    public void collectionMoldingAsync
    (HttpSubscriber<InjectPassBean> stationBeanHttpSubscriber, InjectMouldCommitRequest
            request) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.collectionMoldingAsync(request));
    }
}
