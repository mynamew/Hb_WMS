package com.jzk.hebi_wms.mvp.product;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.product.MaterialScanPutAwayBean;
import com.jzk.hebi_wms.data.product.VertifyLocationCodeBean;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 10:56
 */

public interface PutAwayView extends MvpBaseView {

    /**
     * 物料扫码
     *
     * @param bean
     */
    void materialScanResult(MaterialScanPutAwayBean bean);

    /**
     * 库位码是否有效
     *
     * @param bean
     */
    void vertifyLocationCode(VertifyLocationCodeBean bean);

    /**
     * 创建入库单
     */
    void createInStockOrderno();

    /**
     * 设置物料条码选中
     */
    void setMaterialEdittextSelect();

    /**
     * 设置库位码选中
     */
    void setLocationSelect();
}
