package com.jzk.hebi_wms.mvp.ipqc;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
/** 
  * 抽检外观的view
  * @author   jzk
  * create at: 2018/8/3 16:24
  */  
public interface CheckAppearanceView extends MvpBaseView {
    /**
     * 获取批号信息
     * @param o
     */
    void getLotInfoAsync(IpqcCommonResult o);

    /**
     * 生成批号信息
     * @param o
     */
    void createNewLotNoAsync(IpqcCommonResult o);

    /**
     * 获取质检名称信息
      * @param o
     */
    void getIQPCNameAsync(IpqcCommonResult o);

    /**
     * 获取时段
     * @param o
     */
    void getTimePerodAsync(IpqcCommonResult o);

    /**
     * 获取工序
     * @param o
     */
    void getProcessAsync(IpqcCommonResult o);
}
