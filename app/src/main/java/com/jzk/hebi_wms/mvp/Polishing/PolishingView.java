package com.jzk.hebi_wms.mvp.Polishing;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.polishing.PolishBiographyRequestBean;
import com.jzk.hebi_wms.data.polishing.PolishResultBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;

/**
 * Created by JuQent on 2018-07-24.
 */

public interface PolishingView extends MvpBaseView {
    /**
     * 获取工位
     * @param o
     */
    void getStations(StationBean o);


    /**
     * 获取抛光设备
     * @param o
     */
    void getPolish(InjectMoldBean o);

    /**
     * 抛光
     * @param resultBean
     */
    void collectionPolishAsync(PolishResultBean resultBean);
    /**
     * 设置产品序列号选中
     */
    void setProductCodeSelect();
}
