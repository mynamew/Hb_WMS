package com.jzk.hebi_wms.mvp.process;

import com.jzk.hebi_wms.base.model.impl.MvpBaseModel;
import com.jzk.hebi_wms.data.process.ProcessSelectBean;
import com.jzk.hebi_wms.data.set.ChangePasswordRequest;
import com.jzk.hebi_wms.data.station.NoneClass;
import com.jzk.hebi_wms.http.HttpManager;
import com.jzk.hebi_wms.http.api.ApiService;
import com.jzk.hebi_wms.http.api.CommonResult;
import com.jzk.hebi_wms.http.callback.ApiServiceMethodCallBack;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 14:37
 */
public class ProcessSelectModel extends MvpBaseModel {
    /**
     * 获取工序列表
     * @param observer
     */
    public void getProcessList( Observer<List<ProcessSelectBean>> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getProcessList(new NoneClass()));
    }
}
