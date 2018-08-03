package com.jzk.hebi_wms.mvp.oqc.check;

import android.os.Bundle;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
/** 
  * 抽检采集的界面
  * @author   jzk
  * create at: 2018/8/3 11:04
  */  
public class OqcCheckActivity extends BaseActivity<OqcCheckView, OqcCheckPresenter> implements OqcCheckView {

    @Override
    public int setLayoutId() {
        return R.layout.activity_oqc_check;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("抽检采集");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public OqcCheckPresenter createPresenter() {
        return new OqcCheckPresenter(this);
    }

    @Override
    public OqcCheckView createView() {
        return this;
    }
}
