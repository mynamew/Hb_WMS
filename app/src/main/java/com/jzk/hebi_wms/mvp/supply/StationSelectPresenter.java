package com.jzk.hebi_wms.mvp.supply;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.station.AddMaterialBean;
import com.jzk.hebi_wms.data.station.AddMaterialRequest;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.SupplyMaterialBean;
import com.jzk.hebi_wms.data.station.ValIsInjectSameBatchRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 17:05
 */
public class StationSelectPresenter extends MvpBasePresenter<StationSelectView> {
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    private HttpSubscriber<EquipmentByTypeList> injectMoldBeanHttpSubscriber;
    private HttpSubscriber<SupplyMaterialBean> supplyMaterialBeanHttpSubscriber;
    private HttpSubscriber<WorkerOrderBean> workerOrderBeanHttpSubscriber;
    private HttpSubscriber<Object> valIsInjectSameBatchSubscriber;
    private HttpSubscriber<AddMaterialBean> addMaterialBeanHttpSubscriber;
    StationSelectModel model;

    public StationSelectPresenter(Context context) {
        super(context);
        model = new StationSelectModel();
    }

    /**
     * 获取工位
     *
     * @param request
     */
    public void getStations(StationRequest request) {
        if (null == stationBeanHttpSubscriber) {
            stationBeanHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<StationBean>() {
                @Override
                public void onSuccess(StationBean o) {
                    getView().getStations(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getStations(request, stationBeanHttpSubscriber);
    }

    /**
     * 获取注塑机
     */
    public void getInjectionMoldings() {
        if (null == injectMoldBeanHttpSubscriber) {
            injectMoldBeanHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<EquipmentByTypeList>() {
                @Override
                public void onSuccess(EquipmentByTypeList o) {
                    getView().getInjectionMoldings(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getEquipmentByTypeList(injectMoldBeanHttpSubscriber);
    }

    /**
     * 获取供料机
     */
    public void getSuppliyEqps() {
        if (null == supplyMaterialBeanHttpSubscriber) {
            supplyMaterialBeanHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<SupplyMaterialBean>() {
                @Override
                public void onSuccess(SupplyMaterialBean o) {
                    getView().getSuppliyEqps(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getSuppliyEqps(supplyMaterialBeanHttpSubscriber);
    }

    /**
     * 获取工单
     */
    public void getMoCode() {
        if (null == workerOrderBeanHttpSubscriber) {
            workerOrderBeanHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<WorkerOrderBean>() {
                @Override
                public void onSuccess(WorkerOrderBean o) {
                    getView().getMoCode(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getMoCode(workerOrderBeanHttpSubscriber);
    }

    /**
     * 校验
     */
    public void valIsInjectSameBatch(ValIsInjectSameBatchRequest request) {
        if (null == valIsInjectSameBatchSubscriber) {
            valIsInjectSameBatchSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().valIsInjectSameBatch(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().setBarcodeSelected();
                }
            });
        }
        model.valIsInjectSameBatch(request, valIsInjectSameBatchSubscriber);
    }

    /**
     * 单号提交
     */
    public void createOrUpdateOnWipMaterial(AddMaterialRequest request) {
        if (null == addMaterialBeanHttpSubscriber) {
            addMaterialBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<AddMaterialBean>() {
                @Override
                public void onSuccess(AddMaterialBean o) {
                    getView().createOrUpdateOnWipMaterial(o);
                    getView().setBarcodeSelected();

                }

                @Override
                public void onError(String errorMsg) {
                    getView().setBarcodeSelected();
                }
            });
        }
        model.createOrUpdateOnWipMaterial(request, addMaterialBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != supplyMaterialBeanHttpSubscriber) {
            supplyMaterialBeanHttpSubscriber.unSubscribe();
            supplyMaterialBeanHttpSubscriber = null;
        }
        if (null != stationBeanHttpSubscriber) {
            stationBeanHttpSubscriber.unSubscribe();
            stationBeanHttpSubscriber = null;
        }
        if (null != injectMoldBeanHttpSubscriber) {
            injectMoldBeanHttpSubscriber.unSubscribe();
            injectMoldBeanHttpSubscriber = null;
        }
        if (null != addMaterialBeanHttpSubscriber) {
            addMaterialBeanHttpSubscriber.unSubscribe();
            addMaterialBeanHttpSubscriber = null;
        }
        if (null != workerOrderBeanHttpSubscriber) {
            workerOrderBeanHttpSubscriber.unSubscribe();
            workerOrderBeanHttpSubscriber = null;
        }
        if (null != valIsInjectSameBatchSubscriber) {
            valIsInjectSameBatchSubscriber.unSubscribe();
            valIsInjectSameBatchSubscriber = null;
        }
        if (null != addMaterialBeanHttpSubscriber) {
            addMaterialBeanHttpSubscriber.unSubscribe();
            addMaterialBeanHttpSubscriber = null;
        }
    }
}
