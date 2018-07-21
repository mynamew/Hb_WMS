package com.jzk.hebi_wms.mvp.inject_mold;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * @author: timi
 * create at: 2018/7/20 10:18
 */
public class InjectMoldPresenter extends MvpBasePresenter<InjectMoldView> {

    private final InjectMoldModel model;
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    private HttpSubscriber<InjectMoldBean> injectMoldBeanHttpSubscriber;
    private HttpSubscriber<InjectMoldBean> mouldHttpSubscriber;
    private HttpSubscriber<WorkerOrderBean> workerOrderBeanHttpSubscriber;

    public InjectMoldPresenter(Context context) {
        super(context);
        model = new InjectMoldModel();
    }


    /**
     * 获取工位
     *
     * @param request
     */
    public void getStations(StationRequest request) {
        if (null == stationBeanHttpSubscriber) {
            stationBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<StationBean>() {
                @Override
                public void onSuccess(StationBean o) {
                    getView().getStations(o);
                }

                @Override
                public void onError(String errorMsg) {

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
            injectMoldBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectMoldBean>() {
                @Override
                public void onSuccess(InjectMoldBean o) {
                    getView().getInjectionMoldings(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getInjectionMoldings(injectMoldBeanHttpSubscriber);
    }

    /**
     * 获取模具
     */
    public void getMould() {
        if (null == mouldHttpSubscriber) {
            mouldHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectMoldBean>() {
                @Override
                public void onSuccess(InjectMoldBean o) {
                    getView().getMould(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getMould(mouldHttpSubscriber);
    }

    /**
     * 获取工单
     */
    public void getMoCode() {
        if (null == workerOrderBeanHttpSubscriber) {
            workerOrderBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<WorkerOrderBean>() {
                @Override
                public void onSuccess(WorkerOrderBean o) {
                    getView().getMoCode(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getMoCode(workerOrderBeanHttpSubscriber);
    }
}
