package com.jzk.hebi_wms.mvp.process;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.process.ProcessSelectBean;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 14:37
 */
public interface ProcessSelectView extends MvpBaseView {
    void getProcessSelectSubscriber(List<ProcessSelectBean> data);
}
