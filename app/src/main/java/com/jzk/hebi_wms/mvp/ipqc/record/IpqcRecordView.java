package com.jzk.hebi_wms.mvp.ipqc.record;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.device.DeviceResponse;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcProcessResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcRecordResult;

import java.util.List;

/**
  * ipac抽检记录的view
  * @author   jzk
  * create at: 2018/8/24 9:28
  */  
public interface IpqcRecordView extends MvpBaseView {
    /**
     * 获取时段
     * @param o
     */
    void getTimePerodAsync(IpqcCommonResult o);

    /**
     * 获取工序
     * @param data
     */
    void getProcessSelectSubscriber(IpqcProcessResult data);

    /**
     * 获取抽检记录
     * @param o
     */
    void getIPQCInfoAsync(IpqcRecordResult o);
    /**
     * 获取到的设备
     * @param o
     */
    void getEquipmentTypeListasync(List<DeviceResponse> o);
    /**
     * 获取设备列表
     * @param o
     */
    void getEqCodeAsync(IpqcCommonResult o);
}
