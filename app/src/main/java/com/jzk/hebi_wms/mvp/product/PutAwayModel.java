package com.jzk.hebi_wms.mvp.product;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.product.MaterialScanPutAwayBean;
import com.jzk.hebi_wms.data.product.VertifyLocationCodeBean;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc 入库上架的model
 * author: timi
 * create at: 2017-08-28 10:56
 */

public class PutAwayModel extends MvpBaseModel {

    /**
     * 物料扫码上架的网络请求
     * @param params
     * @param observer
     */
    public void materialScanPutAawy(final Map<String,Object> params,
                                    final Observer<MaterialScanPutAwayBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<MaterialScanPutAwayBean>() {
            @Override
            public Observable<CommonResult<MaterialScanPutAwayBean>> createObservable(ApiService apiService) {
                return apiService.materialScanPutAawy(params);
            }
        });
    }
    /**
     * 验证库位码是否有效
     * @param params
     * @param observer
     */
    public void vertifyLocationCode(final Map<String,Object> params,
                                    final Observer<VertifyLocationCodeBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<VertifyLocationCodeBean>() {
            @Override
            public Observable<CommonResult<VertifyLocationCodeBean>>
            createObservable(ApiService apiService) {
                return apiService.vertifyLocationCode(params);
            }
        });
    }
    /**
     * 生成入库单
     * @param params
     * @param observer
     */
    public void createInStockOrderno(final Map<String,Object> params,
                                    final Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>>
            createObservable(ApiService apiService) {
                return apiService.createInStockOrderno(params);
            }
        });
    }

}
