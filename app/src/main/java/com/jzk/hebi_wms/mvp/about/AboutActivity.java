package com.jzk.hebi_wms.mvp.about;

import android.os.Bundle;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseNoMvpActivity;


public class AboutActivity extends BaseNoMvpActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("关于");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
