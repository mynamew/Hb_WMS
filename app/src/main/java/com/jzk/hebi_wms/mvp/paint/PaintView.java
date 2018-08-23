package com.jzk.hebi_wms.mvp.paint;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.paint.PaintResult;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;

/**
  * 喷漆的View
  * @author   jzk
  * create at: 2018/8/23 14:25
  */  
public interface PaintView extends MvpBaseView {
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

    void createOrUpdateOnWipPaint(PaintResult o);
}
