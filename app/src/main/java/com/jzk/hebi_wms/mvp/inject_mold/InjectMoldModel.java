package com.jzk.hebi_wms.mvp.inject_mold;

import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.NoneClass;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.SupplyMaterialBean;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
  * 
  * @author: timi    
  * create at: 2018/7/20 10:21
  */  
public class InjectMoldModel extends MvpBaseModel{
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
    public void getInjectionMoldings(HttpSubscriber<InjectMoldBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getInjectionMoldings(Constants.DeviceType.MOLDING.toString()));
    }
    /**
     * 获取工单
     *
     * @param stationBeanHttpSubscriber
     */
    public void getMoCode(HttpSubscriber<WorkerOrderBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getMoCode(new NoneClass()));
    }
    /**
     * 获取模具
     *
     * @param stationBeanHttpSubscriber
     */
    public void getMould(HttpSubscriber<InjectMoldBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getInjectionMoldings(Constants.DeviceType.MOULD.toString()));
    }
}
