package com.jzk.hebi_wms.mvp.about;

import android.os.Bundle;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseNoMvpActivity;
import com.jzk.hebi_wms.utils.PackageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutActivity extends BaseNoMvpActivity {
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;

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
        tvCurrentVersion.setText(PackageUtils.getAppVersionName(this));
    }

}
