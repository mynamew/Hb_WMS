package com.jzk.hebi_wms.mvp.cnc;

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
import com.jzk.hebi_wms.data.cnc.CncBean;
import com.jzk.hebi_wms.data.cnc.CncRequest;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.utils.InputMethodUtils;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.hebi_wms.view.DeviceView;
import com.jzk.hebi_wms.view.MyDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CNC1的界面
 *
 * @author: timi
 * create at: 2018/7/24 13:26
 */
public class CNC1Activity extends BaseActivity<CNC1View, CNC1Presenter> implements CNC1View {


    @BindView(R.id.tv_process_code)
    TextView tvProcessCode;
    @BindView(R.id.spinner_station)
    MaterialSpinner spinnerStation;
    @BindView(R.id.tv_work_line_code)
    TextView tvWorkLineCode;
    @BindView(R.id.dv_cnc)
    DeviceView dvCnc;
    @BindView(R.id.tv_cnc_tongs_tip)
    TextView tvCncTongsTip;
    @BindView(R.id.et_add_cnc_tongs)
    EditText etAddCncTongs;
    @BindView(R.id.iv_cnc_scan)
    ImageView ivCncScan;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_add_material_order)
    EditText etAddMaterialOrder;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_mo_code)
    TextView tvMoCode;
    @BindView(R.id.tv_product_code)
    TextView tvProductCode;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_product_model)
    TextView tvProductModel;
    @BindView(R.id.ll_product_info)
    LinearLayout llProductInfo;
    /**
     * 工序
     */
    private String processCode = "";
    /**CNC设备******************************************************************************************/
    /**
     * CNC设备
     */
    private List<InjectMoldBean.EqpmentsBean> cncDevices = new ArrayList<>();
    /**
     * 工位的数据源
     */
    private List<StationBean.StationsBean> mStations = new ArrayList<>();
    /**
     * 是否是CNC1
     */
    private boolean isCnc1;

    @Override
    public int setLayoutId() {
        return R.layout.activity_cnc1;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_cnc);
        isCnc1=getIntent().getBooleanExtra("cnc",false);
    }

    @Override
    public void initView() {
        /**
         * 设置CNC夹具的输入框事件
         */
        setEdittextListener(etAddCncTongs, Constants.REQUEST_SCAN_CODE_CNC_TONGS, R.string.input_cnc_tongs, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                setEdittextSelected(etAddMaterialOrder);
            }
        });
        /**
         * 设置产品序列号的时间
         */
        setEdittextListener(etAddMaterialOrder, Constants.REQUEST_SCAN_CODE_BARCODE, R.string.input_product_code, R.string.input_no_low_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 请求
                 */
                requestCommitCnc();
            }
        });
        dvCnc.setEdittextListener(new DeviceView.EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
            }

            @Override
            public void hideInputSoftware() {
                InputMethodUtils.hide(CNC1Activity.this);
            }
        });
    }

    @Override
    public void initData() {
        /**
         * 获取CNC设备
         */
        getPresenter().getCNCTongs(isCnc1);
        /**
         * 获取SP的工序
         */
        processCode = SpUtils.getInstance().getProcessSelectCode();
        /**
         * 设置工序
         */
        tvProcessCode.setText(processCode);
        StationRequest request = new StationRequest();
        if (TextUtils.isEmpty(processCode)) {
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, getString(R.string.error_title))
                    .setTextViewContent(R.id.tv_content, getString(R.string.tip_please_select_process))
                    .setButtonListener(R.id.btn_cancel, null, dialog -> {
                        onBackPressed();
                    }).setImageViewListener(R.id.iv_close, dialog -> onBackPressed())
                    .setCantCancelByBackPress().setCancelByOutside(false).show();
            return;
        }
        /**
         * 判断工序是否正确
         */
        if(!getString(isCnc1?R.string.process_cnc1:R.string.process_cnc2).equals(processCode)){
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, getString(R.string.error_title))
                    .setTextViewContent(R.id.tv_content, getString(isCnc1?R.string.tip_no_cnc_process1:R.string.tip_no_cnc_process2))
                    .setButtonListener(R.id.btn_cancel, null, dialog -> {
                        onBackPressed();
                    }).setImageViewListener(R.id.iv_close, dialog -> onBackPressed())
                    .setCantCancelByBackPress().setCancelByOutside(false).show();
            return;
        }
        request.setEmployeeCode(SpUtils.getInstance().getUserName());
        request.setEqpTypeCode("");
        request.setProcessCode(processCode);
        showProgressDialog();
        getPresenter().getStations(request);

    }

    @Override
    public CNC1Presenter createPresenter() {
        return new CNC1Presenter(this);
    }

    @Override
    public CNC1View createView() {
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
        /**
         * 是否隐藏加载框
         */
        dismissProgressDialog();
    }

    @Override
    public void getCNCTongs(EquipmentByTypeList o) {
        if (null == o.getEquipmentList() || o.getEquipmentList().isEmpty()) {
            dvCnc.setSpinnerText(R.string.tip_no_cnc_info);
        } else {
            List<InjectMoldBean.EqpmentsBean> equipmentList = o.getEquipmentList();
            cncDevices.clear();
            cncDevices.addAll(equipmentList);
            //设置数据源
            dvCnc.initDeviceData(cncDevices, new DeviceView.DeviceListener() {
                @Override
                public void deviceSelect(int position) {
                    etAddCncTongs.setText(equipmentList.get(position).getRelatedEquipment());
                }
            });
            dvCnc.setEdittextContent(cncDevices.get(0).getValue());
            dvCnc.setSpinnerEdittextSelect();
        }
        /**
         * 设置初始值
         */
        etAddCncTongs.setText(cncDevices.get(0).getRelatedEquipment());
        /**
         * 是否隐藏加载框
         */
        dismissProgressDialog();
    }

    @Override
    public void cncCommit(CncBean o) {
        ToastUtils.showShort(R.string.commit_success);
        llProductInfo.setVisibility(View.VISIBLE);
        tvMoCode.setText(o.getMoCode());
        tvProductCode.setText(o.getItemCode());
        tvProductName.setText(o.getItemName());
        tvProductModel.setText(o.getItemStandard());

    }

    @Override
    public void setProductCodeSelect() {
        etAddMaterialOrder.setText("");
        setEdittextSelected(etAddMaterialOrder);
    }


    @OnClick({R.id.iv_cnc_scan, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cnc_scan:
                /**
                 * cnc夹具扫描
                 */
                scan(Constants.REQUEST_SCAN_CODE_CNC_TONGS, (requestCode, result) -> {
                    etAddCncTongs.setText(result);
                    setEdittextSelected(etAddMaterialOrder);
                });
                break;
            case R.id.iv_scan:
                /**
                 * 产品序列号扫描
                 */
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, (requestCode, result) -> {
                    etAddMaterialOrder.setText(result);
                    requestCommitCnc();
                });
                break;
            default:
        }
    }

    /**
     * 提交产品序列号
     */
    private void requestCommitCnc() {
        /**
         * cnc夹具
         */
        String cncTongs = etAddCncTongs.getText().toString().trim();
        if (TextUtils.isEmpty(cncTongs)) {
            ToastUtils.showShort(R.string.input_cnc_tongs);
            return;
        }
        String rCard = etAddMaterialOrder.getText().toString().trim();
        if (TextUtils.isEmpty(rCard)) {
            ToastUtils.showShort(R.string.input_product_code);
            return;
        }
        showProgressDialog();
        CncRequest request = new CncRequest();
        //设置code和name
        request.setEmployeeCode(SpUtils.getInstance().getUserName());
        request.setEmployeeName(SpUtils.getInstance().getNickName());
        /**
         * 设置工序
         * 工位Code
         */
        request.setProcessCode(processCode);
        request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
        /**
         * 设置夹具
         */
        request.setcNCFixture(cncTongs);
        request.setcNCEqpCode(cncDevices.get(dvCnc.getSpinnerSelectIndex()).getValue());
        /**
         * 设置产品序列号
         */
        request.setrCard(rCard);
        getPresenter().cncCommit(request);
    }

    /**
     * 判断是否隐藏加载框
     */
    public void dismissProgressDialog() {
        if (!mStations.isEmpty() && !cncDevices.isEmpty()) {
            dismisProgressDialog();
        }
    }
}
