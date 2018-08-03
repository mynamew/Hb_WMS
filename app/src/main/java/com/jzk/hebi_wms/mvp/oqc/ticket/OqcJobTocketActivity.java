package com.jzk.hebi_wms.mvp.oqc.ticket;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作业单维护
 *
 * @author jzk
 * create at: 2018/8/3 10:07
 */
public class OqcJobTocketActivity extends BaseActivity<OqcJobTocketView, OqcJobTocketPresenter> implements OqcJobTocketView {


    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_batch_no)
    EditText etBatchNo;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_new_batch)
    Button tvNewBatch;
    @BindView(R.id.tv_product_serial_no_tip)
    TextView tvProductSerialNoTip;
    @BindView(R.id.et_product_serial_no)
    EditText etProductSerialNo;
    @BindView(R.id.iv_product_serial_no_scan)
    ImageView ivProductSerialNoScan;
    @BindView(R.id.tv_box_num_tip)
    TextView tvBoxNumTip;
    @BindView(R.id.et_box_num)
    EditText etBoxNum;
    @BindView(R.id.iv_box_num_scan)
    ImageView ivBoxNumScan;
    @BindView(R.id.btn_get_batch)
    Button btnGetBatch;
    @BindView(R.id.tv_product_code)
    TextView tvProductCode;
    @BindView(R.id.tv_mo_code)
    TextView tvMoCode;
    @BindView(R.id.tv_quality_status)
    TextView tvQualityStatus;
    @BindView(R.id.rlv_box_detail)
    RecyclerView rlvBoxDetail;
    @BindView(R.id.tv_box_no_tip)
    TextView tvBoxNoTip;
    @BindView(R.id.et_box_no)
    EditText etBoxNo;
    @BindView(R.id.iv_box_no_scan)
    ImageView ivBoxNoScan;
    @BindView(R.id.btn_send_quality)
    Button btnSendQuality;

    @Override
    public int setLayoutId() {
        return R.layout.activity_oqcjob_tocket;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("送检作业");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public OqcJobTocketPresenter createPresenter() {
        return new OqcJobTocketPresenter(this);
    }

    @Override
    public OqcJobTocketView createView() {
        return this;
    }
    @OnClick({R.id.iv_scan, R.id.tv_new_batch, R.id.iv_product_serial_no_scan, R.id.iv_box_num_scan, R.id.btn_get_batch, R.id.iv_box_no_scan, R.id.btn_send_quality})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BATCH_NO, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etBatchNo.setText(result);
                        // TODO: 2018/8/3 通过批次号 获取箱号信息
                    }
                });
                break;
            case R.id.tv_new_batch:
                // TODO: 2018/8/3 新批次的请求
                break;
            case R.id.iv_product_serial_no_scan:
                scan(Constants.REQUEST_SCAN_CODE_PRODUCT_SERIAL_NO, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etBatchNo.setText(result);
                        // TODO: 2018/8/3 通过产品序列号  获取箱号信息
                    }
                });
                break;
            case R.id.iv_box_num_scan:
                scan(Constants.REQUEST_SCAN_CODE_BOX_NO, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etBatchNo.setText(result);
                        // TODO: 2018/8/3 通过箱号  获取箱号信息
                    }
                });
                break;
            case R.id.btn_get_batch:
                break;
            case R.id.iv_box_no_scan:
                scan(Constants.REQUEST_SCAN_CODE_BOX_NO, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etBatchNo.setText(result);
                        // TODO: 2018/8/3 通过送检箱号  获取箱号信息
                    }
                });
                break;
            case R.id.btn_send_quality:
                // TODO: 2018/8/3 送检的请求 
                break;
            default:
                break;
        }
    }
}
