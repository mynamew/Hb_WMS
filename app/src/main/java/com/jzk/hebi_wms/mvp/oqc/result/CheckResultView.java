package com.jzk.hebi_wms.mvp.oqc.result;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;

/**
 * 抽检采集结果的view
 *
 * @author jzk
 *         create at: 2018/8/3 13:52
 */
public interface CheckResultView extends MvpBaseView {

    void GetCollectionIPQCDataAsyncUploadBean(CollectionIpqcData collectionIpqcData);
}
