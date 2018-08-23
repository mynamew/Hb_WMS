package com.jzk.hebi_wms.mvp.paint;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.paint.PaintRequest;
import com.jzk.hebi_wms.data.paint.PaintResult;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.NoneClass;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
  * 喷漆的model
  * @author   jzk
  * create at: 2018/8/23 14:24
  */  
public class PaintModel extends MvpBaseModel {
    /**
     * 获取工位
     *
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void getStations(StationRequest request, HttpSubscriber<StationBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber,
                apiService -> apiService.getStationsPaint(request));
    }

    /**
     * 获取喷漆设备
     *
     * @param stationBeanHttpSubscriber
     */
    public void getEquipmentByTypeList(HttpSubscriber<InjectMoldBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.getEquipmentByTypeListPaint("COATING"));
    }
    /**
     * 获取工单
     *
     * @param stationBeanHttpSubscriber
     */
    public void getMoCode(HttpSubscriber<WorkerOrderBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.getMoCodePaint(new NoneClass()));
    }
    /**
     * 喷漆条码扫描
     *
     * @param stationBeanHttpSubscriber
     */
    public void createOrUpdateOnWipPaint(PaintRequest request, HttpSubscriber<PaintResult> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.createOrUpdateOnWipPaint(request));
    }
}
