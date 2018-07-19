package com.jzk.hebi_wms.mvp.station;

import android.os.Bundle;
import android.text.TextUtils;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.utils.LogUitls;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.view.MyDialog;
/**
  * 工位选择界面
  * author: timi
  * create at: 2018/7/19 17:21
  */  
public class StationSelectActivity extends BaseActivity<StationSelectView, StationSelectPresenter> implements StationSelectView {

    StationRequest request = new StationRequest();
    String processSelectCode = "";

    @Override
    public int setLayoutId() {
        return R.layout.activity_station_select;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        processSelectCode = SpUtils.getInstance().getProcessSelectCode();
        if (TextUtils.isEmpty(processSelectCode)) {
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, "错误信息")
                    .setTextViewContent(R.id.tv_content, "请先选择工序再进行此操作！")
                    .setButtonListener(R.id.btn_cancel, null, dialog -> {
                        onBackPressed();
                    }).show();
        }
        request.setEmployeeCode(processSelectCode);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public StationSelectPresenter createPresenter() {
        return new StationSelectPresenter(this);
    }

    @Override
    public StationSelectView createView() {
        return this;
    }

    @Override
    public void getStations(StationBean o) {
        LogUitls.e("工位信息--->", o.toString());
    }
}
