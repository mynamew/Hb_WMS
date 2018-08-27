package com.jzk.hebi_wms.mvp.device;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.device.DeviceResponse;

import java.util.List;
/** 
  * 选择设备类型的view
  * @author   jzk
  * create at: 2018/8/27 12:53
  */  
public interface SelectDeviceTypeView extends MvpBaseView{
    /**
     * 获取到的设备
     * @param o
     */
    void getEquipmentTypeListasync(List<DeviceResponse> o);
}
