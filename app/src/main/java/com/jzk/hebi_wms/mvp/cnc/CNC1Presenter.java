package com.jzk.hebi_wms.mvp.cnc;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.cnc.CncBean;
import com.jzk.hebi_wms.data.cnc.CncRequest;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

public class CNC1Presenter extends MvpBasePresenter<CNC1View> {
    private CNC1Model model;
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    private HttpSubscriber<EquipmentByTypeList> cncDeviceHttpSubscriber;
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
            stationBeanHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<StationBean>() {
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
     * 获取CNC设备
     * @param isCnc1
     */
    public void getCNCTongs(boolean isCnc1) {
        if (null == cncDeviceHttpSubscriber) {
            cncDeviceHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<EquipmentByTypeList>() {
                @Override
                public void onSuccess(EquipmentByTypeList o) {
                    getView().getCNCTongs(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getCNCTongs(isCnc1,cncDeviceHttpSubscriber);
    }

    /**
     * 提交CNC
     */
    public void cncCommit(CncRequest request) {
        if (null == cncBeanHttpSubscriber) {
            cncBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<CncBean>() {
                @Override
                public void onSuccess(CncBean o) {
                    getView().setProductCodeSelect();
                    getView().cncCommit(o);

                }

                @Override
                public void onError(String errorMsg) {
                    getView().setProductCodeSelect();
                }
            });
        }
        model.cncCommit(cncBeanHttpSubscriber, request);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != stationBeanHttpSubscriber) {
            stationBeanHttpSubscriber.unSubscribe();
            stationBeanHttpSubscriber = null;
        }
        if (null != cncDeviceHttpSubscriber) {
            cncDeviceHttpSubscriber.unSubscribe();
            cncDeviceHttpSubscriber = null;
        }
        if (null != cncBeanHttpSubscriber) {
            cncBeanHttpSubscriber.unSubscribe();
            cncBeanHttpSubscriber = null;
        }
    }
}
