package com.jzk.hebi_wms.mvp.supply;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.station.AddMaterialBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.SupplyMaterialBean;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 17:04
 */
public interface StationSelectView extends MvpBaseView {
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
     * 获取供料机
     * @param o
     */
    void getSuppliyEqps(SupplyMaterialBean o);

    /**
     * 获取工单
     * @param o
     */
    void getMoCode(WorkerOrderBean o);

    /**
     * 校验
     * @param o
     */
    void valIsInjectSameBatch(Object o);

    /**
     * 上料单号提交
     * @param o
     */
    void createOrUpdateOnWipMaterial(AddMaterialBean o);
    /**
     * 设置条码选中
     */
    void setBarcodeSelected();
}
