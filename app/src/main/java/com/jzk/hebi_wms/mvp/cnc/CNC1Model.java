package com.jzk.hebi_wms.mvp.cnc;

import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.cnc.CncBean;
import com.jzk.hebi_wms.data.cnc.CncRequest;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

public class CNC1Model extends MvpBaseModel {
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
     * 获取CNC设备
     *
     * @param isCnc1
     * @param stationBeanHttpSubscriber
     */
    public void getCNCTongs(boolean isCnc1, HttpSubscriber<EquipmentByTypeList> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.getEquipmentByTypeList(isCnc1?Constants.DeviceType.CNC1.toString():Constants.DeviceType.CNC2.toString()));
    }

    /**
     * cnc提交
     * @param cncHttpSubscriber
     * @param request
     */
    public void cncCommit(HttpSubscriber<CncBean> cncHttpSubscriber, CncRequest request) {
        HttpManager.getInstance().HttpManagerRequest(cncHttpSubscriber, apiService ->
                apiService.collectionCNCAsync(request));
    }
}
