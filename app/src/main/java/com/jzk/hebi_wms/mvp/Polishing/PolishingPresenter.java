package com.jzk.hebi_wms.mvp.Polishing;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.polishing.PolishBiographyRequestBean;
import com.jzk.hebi_wms.data.polishing.PolishResultBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * Created by JuQent on 2018-07-24.
 */

public class PolishingPresenter extends MvpBasePresenter<PolishingView> {
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    private HttpSubscriber<InjectMoldBean> polishHtpSubscriber;
    private HttpSubscriber<PolishResultBean> polishResultBeanHttpSubscriber;
    PolishingModel model;
    public PolishingPresenter(Context context) {
        super(context);
        model=new PolishingModel();
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


    public void collectionPolishAsync(PolishBiographyRequestBean polishBiographyRequestBean){
        if(null==polishResultBeanHttpSubscriber){
            polishResultBeanHttpSubscriber=new HttpSubscriber<PolishResultBean>(new OnResultCallBack() {
                @Override
                public void onSuccess(Object o) {
                    getView().collectionPolishAsync(polishBiographyRequestBean);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });

        }
        model.collectionPolishAsync(polishResultBeanHttpSubscriber,polishBiographyRequestBean);

    }
    /**
     * 获取抛光设备
     *
     */
    public void getPolish() {
        if (null == polishHtpSubscriber) {
            polishHtpSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectMoldBean>() {
                @Override
                public void onSuccess(InjectMoldBean o) {
                    getView().getPolish(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getPolish(polishHtpSubscriber);
    }
}
