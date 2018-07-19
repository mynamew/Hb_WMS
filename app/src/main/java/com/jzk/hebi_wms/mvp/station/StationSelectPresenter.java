package com.jzk.hebi_wms.mvp.station;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 17:05
 */
public class StationSelectPresenter extends MvpBasePresenter<StationSelectView> {
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    StationSelectModel model;
    public StationSelectPresenter(Context context) {
        super(context);
        model=new StationSelectModel();
    }

    public void getStations(StationRequest request){
        if(null==stationBeanHttpSubscriber){
            stationBeanHttpSubscriber=new HttpSubscriber<>(new OnResultCallBack<StationBean>() {
                @Override
                public void onSuccess(StationBean o) {
                       getView().getStations(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getStations(request,stationBeanHttpSubscriber);
    }
}
