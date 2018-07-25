package com.jzk.hebi_wms.mvp.Polishing;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.data.polishing.PolishBiographyRequestBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.hebi_wms.view.MyDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 抛光
 *
 * @author: timi
 * create at: 2018/7/25 8:46
 */
public class PolishingActivity extends BaseActivity<PolishingView, PolishingPresenter> implements PolishingView {
    String processSelectCode = "";
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.spinner_station)
    MaterialSpinner spinnerStation;
    @BindView(R.id.tv_work_line_code)
    TextView tvWorkLineCode;
    @BindView(R.id.spinner_Polishing_equipment)
    MaterialSpinner spinnerPolishingEquipment;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_add_material_order)
    EditText etAddMaterialOrder;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_worksheet_code)
    TextView tvWorksheetCode;
    @BindView(R.id.tv_Product_code)
    TextView tvProductCode;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_Product_specification_model)
    TextView tvProductSpecificationModel;
    @BindView(R.id.tv_process_code)
    TextView tvProcessCode;
    @BindView(R.id.ll_product_info)
    LinearLayout llProductInfo;

    /**
     * 工位数据
     */
    /**
     * 工位的数据源
     */
    private List<StationBean.StationsBean> mStations = new ArrayList<>();


    /********工单***********************************************************************************************/
    /**
     * CNC设备
     */
    private List<InjectMoldBean.EqpmentsBean> cncDevices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_polishing;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        setEdittextListener(etAddMaterialOrder, Constants.REQUEST_SCAN_CODE_BARCODE, R.string.input_product_code, R.string.input_no_low_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                scanUp();
            }
        });
    }

    @Override
    public void initData() {
        StationRequest request = new StationRequest();
        processSelectCode = SpUtils.getInstance().getProcessSelectCode();
        if (TextUtils.isEmpty(processSelectCode)) {
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, "错误信息")
                    .setTextViewContent(R.id.tv_content, "请先选择工序再进行此操作！")
                    .setButtonListener(R.id.btn_cancel, null, dialog -> {
                        onBackPressed();
                    }).setImageViewListener(R.id.iv_close, dialog -> onBackPressed())
                    .setCantCancelByBackPress().setCancelByOutside(false).show();
            return;
        }
        request.setEmployeeCode("");
        request.setEqpTypeCode("");
        request.setProcessCode(processSelectCode);
        showProgressDialog();
        getPresenter().getStations(request);
        getPresenter().getPolish();
    }

    @Override
    public PolishingPresenter createPresenter() {
        return new PolishingPresenter(this);
    }

    @Override
    public PolishingView createView() {
        return this;
    }

    @Override
    public void getStations(StationBean o) {
        if (null == o.getStations() || o.getStations().isEmpty()) {
            spinnerStation.setText(R.string.no_worker_order_info);
        } else {
            List<StationBean.StationsBean> stations = o.getStations();
            mStations.clear();
            mStations.addAll(stations);
            List<String> mStrs = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                mStrs.add(stations.get(i).getStationName());
            }
            //默认产线代码
            tvWorkLineCode.setText(mStations.get(0).getProductionLineCode());
            //设置数据源
            spinnerStation.setItems(mStrs);
            spinnerStation.setOnItemSelectedListener
                    ((MaterialSpinner.OnItemSelectedListener<String>)
                            (view, position, id, item) -> {
                                //注塑机
                                view.setText(item);
                                //产线代码
                                tvWorkLineCode.setText(stations.get(position).getProductionLineCode());
                            });
        }
    }

    @Override
    public void getPolish(InjectMoldBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            spinnerPolishingEquipment.setText("暂无抛光机信息");
        } else {
            List<InjectMoldBean.EqpmentsBean> stations = o.getEqpments();
            cncDevices.clear();
            cncDevices.addAll(o.getEqpments());
            List<String> mStrs = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                mStrs.add(stations.get(i).getDisplayText());
            }
            //设置数据源
            spinnerPolishingEquipment.setItems(mStrs);
            spinnerPolishingEquipment.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> view.setText(item));
        }
    }


    @Override
    public void collectionPolishAsync(PolishBiographyRequestBean polishBiographyRequestBean) {
        ToastUtils.showShort(R.string.commit_success);
        setEdittextSelected(etAddMaterialOrder);
        llProductInfo.setVisibility(View.VISIBLE);
        tvWorksheetCode.setText(polishBiographyRequestBean.getMoCode());
        tvProductCode.setText(polishBiographyRequestBean.getItemCode());
        tvProductName.setText(polishBiographyRequestBean.getItemName());
        tvProductSpecificationModel.setText(polishBiographyRequestBean.getItemStandard());
    }

    @OnClick({R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_CNC_TONGS, (requestCode, result) -> {
                    etAddMaterialOrder.setText(result);
                    setEdittextSelected(etAddMaterialOrder);
                    scanUp();
                });
                break;
            default:
        }
    }

    private void scanUp() {
        String rCard = etAddMaterialOrder.getText().toString().trim();
        if (TextUtils.isEmpty(rCard)) {
            ToastUtils.showShort(R.string.input_product_code);
            return;
        }
        showProgressDialog();
        PolishBiographyRequestBean request = new PolishBiographyRequestBean();
        //设置code和name
        request.setEmployeeCode(SpUtils.getInstance().getUserName());
        request.setEmployeeName(SpUtils.getInstance().getNickName());
        //设置抛光机Code
        request.setPolishEqpCode(cncDevices.get(spinnerPolishingEquipment.getSelectedIndex()).getValue());
        /**
         * 设置工序
         * 工位Code
         */
        request.setProcessCode(processSelectCode);
        request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
        /**
         * 操作员
         */
        request.setEmployeeCode(SpUtils.getInstance().getUserName());
        request.setEmployeeName(SpUtils.getInstance().getNickName());
        /**
         * 设置产品序列号
         */
        request.setRCard(rCard);
        getPresenter().collectionPolishAsync(request);
    }
}