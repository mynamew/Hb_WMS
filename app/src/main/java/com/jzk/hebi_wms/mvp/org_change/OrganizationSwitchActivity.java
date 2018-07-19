package com.jzk.hebi_wms.mvp.org_change;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.data.LoginBean;
import com.jzk.hebi_wms.data.set.ChangeOrgRequest;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 组织切换
 * author: timi
 * create at: 2018/4/16 10:20
 */
public class OrganizationSwitchActivity extends BaseActivity<OrganizationSwitchView, OrganizationSwitchPresenter> implements OrganizationSwitchView {


    @BindView(R.id.tv_current_org)
    TextView tvCurrentOrg;
    @BindView(R.id.spinner_org)
    MaterialSpinner spinnerOrg;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    List<String> mOrgs = new ArrayList<>();
    ChangeOrgRequest request = new ChangeOrgRequest();

    @Override
    public int setLayoutId() {
        return R.layout.activity_organization_switch;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.switch_org_title);
    }

    @Override 
    public void initView() {

    }

    @Override
    public void initData() {
        LoginBean loginBean = new Gson().fromJson(SpUtils.getInstance().getString(Constants.USER_INFO), LoginBean.class);
        //当前组织
        final LoginBean.CurrentOrgUnitBean currentOrgUnit = loginBean.getCurrentOrgUnit();
        tvCurrentOrg.setText(currentOrgUnit.getDisplayName());
        //组织列表
        final List<LoginBean.OrgUnitsBean> orgUnits = loginBean.getOrgUnits();
        if (null == orgUnits || orgUnits.isEmpty()) {
            spinnerOrg.setText("当前用户暂无组织数据");
            return;
        }
        for (int i = 0; i < orgUnits.size(); i++) {
            mOrgs.add(orgUnits.get(i).getDisplayName().replace("/", "").trim());
        }
        spinnerOrg.setItems(mOrgs);
        spinnerOrg.setOnItemSelectedListener((view, position, id, item) -> request.setOrgId(orgUnits.get(position).getId()));
    }

    @Override
    public OrganizationSwitchPresenter createPresenter() {
        return new OrganizationSwitchPresenter(this);
    }

    @Override
    public OrganizationSwitchView createView() {
        return this;
    }


    @Override
    public void changeOrg(LoginBean o) {
        //存储
        SpUtils.getInstance().putString(Constants.USER_INFO, new Gson().toJson(o));
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        request.setDeviceType(8);
    }
}
