package com.jzk.hebi_wms.mvp.ipqc;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.utils.LogUitls;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 外观抽检的界面
 *
 * @author jzk
 * create at: 2018/8/3 16:23
 */
public class CheckAppearanceActivity extends BaseActivity<CheckAppearanceView, CheckAppearancePresenter> implements CheckAppearanceView {


    @BindView(R.id.tv_bottom_product_serial_no_tip)
    TextView tvBottomProductSerialNoTip;
    @BindView(R.id.et_bottom_product_serial_no)
    EditText etBottomProductSerialNo;
    @BindView(R.id.iv_bottom_product_serial_no_scan)
    ImageView ivBottomProductSerialNoScan;
    @BindView(R.id.btn_batch_pass)
    Button btnBatchPass;
    @BindView(R.id.btn_batch_return)
    Button btnBatchReturn;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_batch_no)
    EditText etBatchNo;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_refresh)
    Button tvRefresh;
    @BindView(R.id.spinner_quality_type)
    MaterialSpinner spinnerQualityType;
    @BindView(R.id.tv_quality_des)
    TextView tvQualityDes;
    @BindView(R.id.spinner_process)
    MaterialSpinner spinnerProcess;
    @BindView(R.id.tv_process_name)
    TextView tvProcessName;
    @BindView(R.id.spinner_time_frame)
    MaterialSpinner spinnerTimeFrame;
    @BindView(R.id.tv_time_frame_name)
    TextView tvTimeFrameName;
    @BindView(R.id.spinner_project_date)
    MaterialSpinner spinnerProjectDate;
    @BindView(R.id.tv_time_actual_sample)
    TextView tvTimeActualSample;
    @BindView(R.id.tv_pass_sample)
    TextView tvPassSample;
    @BindView(R.id.tv_unpass_sample)
    TextView tvUnpassSample;
    @BindView(R.id.rlv_product)
    RecyclerView rlvProduct;
    /***日期选择***********************************************************************************/
    private List<String> canSelectDate = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_check_appearance;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("外观抽检");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        getCurrentAndLastDate();
    }

    @Override
    public CheckAppearancePresenter createPresenter() {
        return new CheckAppearancePresenter(this);
    }

    @Override
    public CheckAppearanceView createView() {
        return this;
    }


    @OnClick({R.id.iv_scan, R.id.iv_bottom_product_serial_no_scan, R.id.btn_batch_pass, R.id.btn_batch_return, R.id.tv_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /**
             * 批号的扫描
             */
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BATCH_NO, (requestCode, result) -> {
                    etBatchNo.setText(result);
                    showProgressDialog();
                    getPresenter().getLotInfoAsync(result);
                });
                break;
            case R.id.iv_bottom_product_serial_no_scan:
                break;
            case R.id.btn_batch_pass:
                break;
            case R.id.btn_batch_return:
                break;
            case R.id.tv_refresh:
                break;
            default:
                break;
        }
    }

    /**
     * 获取当前和前一天时间供用户选择
     */
    private void getCurrentAndLastDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String currentDate = year + "-" + month + "-" + day;
        LogUitls.e("当前时间--->", currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        int yearLast = calendar.get(Calendar.YEAR);
        int monthLast = calendar.get(Calendar.MONTH);
        int dayLast = calendar.get(Calendar.DAY_OF_MONTH);
        String lastDate = yearLast + "-" + monthLast + "-" + dayLast;
        LogUitls.e("前一天时间--->", lastDate);
        canSelectDate.add(currentDate);
        canSelectDate.add(lastDate);
        spinnerProjectDate.setItems(canSelectDate);
    }

    @Override
    public void getLotInfoAsync(IpqcCommonResult o) {

    }
}
