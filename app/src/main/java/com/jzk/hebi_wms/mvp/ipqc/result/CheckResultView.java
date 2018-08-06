package com.jzk.hebi_wms.mvp.ipqc.result;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;

import java.util.List;

/**
  * 抽检采集结果的view
  * @author   jzk
  * create at: 2018/8/3 13:52
  */  
public interface CheckResultView extends MvpBaseView {
    /**
     * 获取采集数据
     * @param o
     */
    void getCollectionIPQCDataAsync(CollectionIpqcData o);

    /**
     * 保存采集数据
     * @param o
     */
    void saveCheckResult(CollectionIpqcData o);

    /**
     *
     * @param errorCodes
     */
    void getErrorInfoByGroupCode(List<InjectPassBean.ErrorCodesBean> errorCodes);
}
