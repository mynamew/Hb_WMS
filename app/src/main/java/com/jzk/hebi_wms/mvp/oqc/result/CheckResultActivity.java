package com.jzk.hebi_wms.mvp.oqc.result;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.view.MyDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置抽检采集结果
 *
 * @author jzk
 * create at: 2018/8/3 11:22
 */
public class CheckResultActivity extends BaseActivity<CheckResultView, CheckResultPresenter> implements CheckResultView {


    @BindView(R.id.tv_product_code)
    TextView tvProductCode;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_product_standard)
    TextView tvProductStandard;
    @BindView(R.id.tv_quality_type)
    TextView tvQualityType;
    @BindView(R.id.spinner_quality_type)
    MaterialSpinner spinnerQualityType;
    @BindView(R.id.btn_quality)
    Button btnQuality;
    @BindView(R.id.et_bad_code)
    EditText etBadCode;
    @BindView(R.id.ll_input_bad_code)
    LinearLayout llInputBadCode;
    @BindView(R.id.tv_bad_group_tip)
    TextView tvBadGroupTip;
    @BindView(R.id.spinner_bad_groups)
    MaterialSpinner spinnerBadGroups;
    @BindView(R.id.ll_bad_group)
    LinearLayout llBadGroup;
    @BindView(R.id.rlv_bac_code)
    RecyclerView rlvBacCode;
    @BindView(R.id.btn_save)
    TextView btnSave;

    @Override
    public int setLayoutId() {
        return R.layout.activity_check_result;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("产品检验");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public CheckResultPresenter createPresenter() {
        return new CheckResultPresenter(this);
    }

    @Override
    public CheckResultView createView() {
        return this;
    }

    @OnClick({R.id.btn_quality, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_quality:
                MyDialog myDialog = new MyDialog(this, R.layout.dialog_quality);
                myDialog.show();
                break;
            case R.id.btn_save:
                break;
            default:
                break;
        }
    }
}
