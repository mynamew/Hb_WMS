  package com.jzk.hebi_wms.mvp.inject_mold;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;

import java.util.List;

/** 
  * 
  * @author: timi    
  * create at: 2018/7/20 10:18
  */  
public interface InjectMoldView extends MvpBaseView {
    /**
     * 获取工位
     * @param o
     */
    void getStations(StationBean o);

    /**
     * 获取注塑机
     * @param o
     */
    void getInjectionMoldings(EquipmentByTypeList o);
    /**
     * 获取模具
     * @param o
     */
    void getMould(InjectMoldBean o);

    /**
     * 校验
     * @param o
     */
    void checkRCardInfoAsync(InjectPassBean o);

    /**
     * 校验失败
     */
    void checkRCardInfoAsyncFalse();

    /**
     * 获取不良代码组
     * @param errorGroups
     */
    void errorGroupHttpSubscriber(List<InjectPassBean.ErrorGroupsBean> errorGroups);

    /**
     * 根据不良组Code获取不良代码
     * @param errorCodes
     */
    void getErrorInfoByGroupCode(List<InjectPassBean.ErrorCodesBean> errorCodes);

    /**
     * 提交注塑过站
     * @param o
     */
    void collectionMoldingAsync(InjectPassBean o);
    /**
     * 设置条码选中
     */
    void setBarcodeSelected();

    /**
     * 根据输入找不良代码
     * @param errorInfo
     */
    void getErrorInfoByErrorCodeAsync(InjectPassBean.ErrorInfo errorInfo);
}
