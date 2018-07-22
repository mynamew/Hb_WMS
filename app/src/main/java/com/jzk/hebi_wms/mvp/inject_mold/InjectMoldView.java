package com.jzk.hebi_wms.mvp.inject_mold;

import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;

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
    void getInjectionMoldings(InjectMoldBean o);
    /**
     * 获取工单
     * @param o
     */
    void getMoCode(WorkerOrderBean o);

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
}
