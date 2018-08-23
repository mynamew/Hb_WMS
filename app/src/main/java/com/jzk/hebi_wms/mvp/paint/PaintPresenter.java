package com.jzk.hebi_wms.mvp.paint;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
  * 喷漆的Presenter
  * @author   jzk
  * create at: 2018/8/23 14:26
  */  
public class PaintPresenter extends MvpBasePresenter<PaintView> {
    PaintModel model;
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    private HttpSubscriber<EquipmentByTypeList> injectMoldBeanHttpSubscriber;
    private HttpSubscriber<WorkerOrderBean> workerOrderBeanHttpSubscriber;

    public PaintPresenter(Context context) {
        super(context);
        model=new PaintModel();
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
}
