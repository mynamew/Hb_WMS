package com.jzk.hebi_wms.mvp.cnc;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.cnc.CncBean;
import com.jzk.hebi_wms.data.cnc.CncRequest;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

public class CNC1Presenter extends MvpBasePresenter<CNC1View> {
    private CNC1Model model;
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    private HttpSubscriber<InjectMoldBean> cncDeviceHttpSubscriber;
    private HttpSubscriber<CncBean> cncBeanHttpSubscriber;

    public CNC1Presenter(Context context) {
        super(context);
        model = new CNC1Model();
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
     * 获取CNC设备
     */
    public void getCNCTongs() {
        if (null == cncDeviceHttpSubscriber) {
            cncDeviceHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectMoldBean>() {
                @Override
                public void onSuccess(InjectMoldBean o) {
                    getView().getCNCTongs(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getCNCTongs(cncDeviceHttpSubscriber);
    }

    /**
     * 提交CNC
     */
    public void cncCommit(CncRequest request) {
        if (null == cncBeanHttpSubscriber) {
            cncBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<CncBean>() {
                @Override
                public void onSuccess(CncBean o) {
                    getView().cncCommit(o);
                    getView().setProductCodeSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    getView().setProductCodeSelect();
                }
            });
        }
        model.cncCommit(cncBeanHttpSubscriber, request);
    }
}
