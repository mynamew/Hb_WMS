package com.jzk.hebi_wms.mvp.device;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.device.DeviceResponse;
import com.jzk.hebi_wms.data.station.NoneClass;
import com.jzk.hebi_wms.http.HttpManager;

import java.util.List;

import io.reactivex.Observer;

/**
  * 选择设备类型的model；
  * @author   jzk
  * create at: 2018/8/27 12:54
  */
public class SelectDeviceTypeModel extends MvpBaseModel {
    /**
     * 获取设备列表
     * @param observer
     */
    public void getEquipmentTypeListasync( Observer<List<DeviceResponse>> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getEquipmentTypeListasync(new NoneClass()));
    }
}
