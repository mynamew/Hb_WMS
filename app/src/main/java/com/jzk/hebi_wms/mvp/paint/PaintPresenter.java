package com.jzk.hebi_wms.mvp.paint;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.paint.PaintRequest;
import com.jzk.hebi_wms.data.paint.PaintResult;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
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
    private HttpSubscriber<InjectMoldBean> injectMoldBeanHttpSubscriber;
    private HttpSubscriber<WorkerOrderBean> workerOrderBeanHttpSubscriber;
    private HttpSubscriber<PaintResult> paintResultHttpSubscriber;

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
            injectMoldBeanHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<InjectMoldBean>() {
                @Override
                public void onSuccess(InjectMoldBean o) {
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
    /**
     * 喷漆条码扫描
     */
    public void createOrUpdateOnWipPaint(PaintRequest request) {
        if (null == paintResultHttpSubscriber) {
            paintResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<PaintResult>() {
                @Override
                public void onSuccess(PaintResult o) {
                    getView().createOrUpdateOnWipPaint(o);
                }

                @Override
                public void onError(String errorMsg) {
                }
            });
        }
        model.createOrUpdateOnWipPaint(request,paintResultHttpSubscriber);
    }
}
