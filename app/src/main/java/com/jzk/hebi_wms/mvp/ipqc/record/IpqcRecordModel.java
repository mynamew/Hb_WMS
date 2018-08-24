package com.jzk.hebi_wms.mvp.ipqc.record;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcProcessResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcRecordRequest;
import com.jzk.hebi_wms.data.ipqc.record.IpqcRecordResult;
import com.jzk.hebi_wms.data.station.NoneClass;
import com.jzk.hebi_wms.http.HttpManager;

import io.reactivex.Observer;

/**
  * ipqc抽检记录的model
  * @author   jzk
  * create at: 2018/8/24 9:26
  */
public class IpqcRecordModel extends MvpBaseModel {
    /**
     * 获取抽检时段
     *
     * @param observer
     */
    public void getTimePerodAsync(Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getTimePerodAsync(new NoneClass()));
    }
    /**
     * 获取工序列表
     * @param observer
     */
    public void getProcessList( Observer<IpqcProcessResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getProcessAsyncIpqc("IPQCProcess"));
    }
    /**
     * 获取抽检记录
     * @param observer
     */
    public void getIPQCInfoAsync(IpqcRecordRequest recordRequest,Observer<IpqcRecordResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getIPQCInfoAsync(recordRequest));
    }
}
