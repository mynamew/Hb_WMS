package com.jzk.hebi_wms.mvp.device;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.device.DeviceResponse;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

import java.util.List;
/** 
  * 获取设备类型的presenter
  * @author   jzk
  * create at: 2018/8/27 13:02
  */  
public class SelectDeviceTypePresenter extends MvpBasePresenter<SelectDeviceTypeView> {
    private SelectDeviceTypeModel model;
    private HttpSubscriber<List<DeviceResponse>> deviceSubscriber;
    public SelectDeviceTypePresenter(Context context) {
        super(context);
        model=new SelectDeviceTypeModel();
    }
    /**
     * 获取工序列表
     */
    public void getEquipmentTypeListasync() {
        if (null == deviceSubscriber) {
            deviceSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<DeviceResponse>>() {
                @Override
                public void onSuccess(List<DeviceResponse> o) {
                    getView().getEquipmentTypeListasync(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
            model.getEquipmentTypeListasync(deviceSubscriber);
        }
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != deviceSubscriber) {
            deviceSubscriber.unSubscribe();
            deviceSubscriber = null;
        }
    }
}
