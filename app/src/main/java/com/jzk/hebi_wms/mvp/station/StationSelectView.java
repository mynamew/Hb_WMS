package com.jzk.hebi_wms.mvp.station;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.station.StationBean;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 17:04
 */
public interface StationSelectView extends MvpBaseView {
    void getStations(StationBean o);
}
