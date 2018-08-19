package com.jzk.hebi_wms.mvp.supply;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.station.AddMaterialBean;
import com.jzk.hebi_wms.data.station.AddMaterialRequest;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.SupplyMaterialBean;
import com.jzk.hebi_wms.data.station.ValIsInjectSameBatchRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
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
 * 工位选择界面
 * author: timi
 * create at: 2018/7/19 17:21
 *
 * @author jzk
 */
public class StationSelectActivity extends BaseActivity<StationSelectView, StationSelectPresenter> implements StationSelectView {
    String processSelectCode = "";
    @BindView(R.id.tv_process_code)
    TextView tvProcessCode;
    @BindView(R.id.spinner_station)
    MaterialSpinner spinnerStation;
    @BindView(R.id.tv_work_line_code)
    TextView tvWorkLineCode;
    @BindView(R.id.spinner_worker_order)
    MaterialSpinner spinnerWorkerOrder;
    @BindView(R.id.tv_ponum)
    TextView tvPonum;
    @BindView(R.id.dv_inject_machine)
    DeviceView dvInjectMachine;
    @BindView(R.id.dv_supply_material)
    DeviceView dvSupplyMaterial;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_add_material_order)
    EditText etAddMaterialOrder;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.btn_commit)
    TextView btnCommit;

    /**
     * 工位数据
     */
    /**
     * 工位的数据源
     */
    private List<StationBean.StationsBean> mStations = new ArrayList<>();


    /********工单***********************************************************************************************/
    /**
     * 工单数据源
     *
     * @return
     */
    private List<WorkerOrderBean.MosBean> mMoCodes = new ArrayList<>();
    /********注塑机***********************************************************************************************/
    /**
     * 注塑机数据源
     *
     * @return
     */
    private List<EquipmentByTypeList.EquipmentListBean> mInjectMolds = new ArrayList<>();
    /********供料机***********************************************************************************************/
    /**
     * 供料机数据源
     *
     * @return
     */
    private List<InjectMoldBean.EqpmentsBean> mSupplyMaterials = new ArrayList<>();
    private List<InjectMoldBean.EqpmentsBean> mOldSupplyMaterials = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_station_select;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_add_material);
    }

    @Override
    public void initView() {
        /**
         * 产品序列号
         */
        setEdittextListener(etAddMaterialOrder, Constants.REQUEST_SCAN_CODE_BARCODE, R.string.input_product_code, R.string.input_no_low_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                judgeSameBatch(result);
            }
        });
        dvInjectMachine.setEdittextListener(new DeviceView.EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }

            @Override
            public void hideInputSoftware() {
                InputMethodUtils.hide(StationSelectActivity.this);
            }
        });
        dvSupplyMaterial.setEdittextListener(new DeviceView.EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }

            @Override
            public void hideInputSoftware() {
                InputMethodUtils.hide(StationSelectActivity.this);
            }
        });
    }

    @Override
    public void initData() {
        StationRequest request = new StationRequest();
        processSelectCode = SpUtils.getInstance().getProcessSelectCode();
        /**
         * 设置工序代码
         */
        tvProcessCode.setText(processSelectCode);
        if (TextUtils.isEmpty(processSelectCode)) {
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, R.string.error_title)
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
        if (!getString(R.string.process_inject).equals(processSelectCode)) {
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, R.string.error_title)
                    .setTextViewContent(R.id.tv_content, getString(R.string.tip_no_inject_process))
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
        //注塑机
        getPresenter().getInjectionMoldings();
        //工单
        getPresenter().getMoCode();
        //供料机
        getPresenter().getSuppliyEqps();
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
        //隐藏加载框
        dismissProgressDialog();
    }

    @Override
    public void getInjectionMoldings(EquipmentByTypeList o) {
        if (null == o.getEquipmentList() || o.getEquipmentList().isEmpty()) {
            dvInjectMachine.setSpinnerText(R.string.tip_no_inject_machine_info);
        } else {
            List<EquipmentByTypeList.EquipmentListBean> stations = o.getEquipmentList();
            mInjectMolds.clear();
            mInjectMolds.addAll(stations);
            //设置数据源
            dvInjectMachine.initDeviceDataSupply(mInjectMolds, new DeviceView.DeviceListener() {
                @Override
                public void deviceSelect(int position) {
                   dealWithInjectAndSupply(position);
                }
            });
        }
        if(!mSupplyMaterials.isEmpty()&&!mInjectMolds.isEmpty()){
            dealWithInjectAndSupply(0);
        }
        //隐藏加载框
        dismissProgressDialog();
    }

    /**
     * 处理 注塑机和供料机的数据
     * @param position
     */
    private void dealWithInjectAndSupply(int position) {
        /**
         * 清空链表
         */
        mSupplyMaterials.clear();
        if (TextUtils.isEmpty(mInjectMolds.get(position).getRelatedEquipment())) {
            mSupplyMaterials.addAll(mOldSupplyMaterials);
            /**
             * 初始化数据
             */
            dvSupplyMaterial.setEdittextContent(mSupplyMaterials.get(0).getValue());
            dvSupplyMaterial.setSpinnerSelectIndex(0);
            //设置数据源
            dvSupplyMaterial.initDeviceData(mSupplyMaterials);
            return;
        }

        /**
         * 处理点击事件，获取供料机的列表
         */
        String[] split = mInjectMolds.get(position).getRelatedEquipment().trim().split("\\|");
        for (int i = 0; i < mOldSupplyMaterials.size(); i++) {
            String value = mOldSupplyMaterials.get(i).getValue();
            boolean isContainSupply=false;
            for (int j = 0; j <split.length; j++) {
                if(value.equals(split[j])){
                    isContainSupply=true;
                }
            }
            if(isContainSupply){
                mSupplyMaterials.add(mOldSupplyMaterials.get(i));
            }
        }
        /**
         * 初始化数据
         */
        dvSupplyMaterial.setEdittextContent(mSupplyMaterials.get(0).getValue());
        dvSupplyMaterial.setSpinnerSelectIndex(0);
        //设置数据源
        dvSupplyMaterial.initDeviceData(mSupplyMaterials);
    }

    @Override
    public void getSuppliyEqps(SupplyMaterialBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            dvSupplyMaterial.setSpinnerText(R.string.tip_no_supply_material_machine_info);
        } else {
            List<InjectMoldBean.EqpmentsBean> stations = o.getEqpments();
            mSupplyMaterials.clear();
            mSupplyMaterials.addAll(stations);
            mOldSupplyMaterials.clear();
            mOldSupplyMaterials.addAll(stations);
            //设置数据源
            dvSupplyMaterial.initDeviceData(mSupplyMaterials);
        }
        if(!mSupplyMaterials.isEmpty()&&!mInjectMolds.isEmpty()){
            dealWithInjectAndSupply(0);
        }
        //隐藏加载框
        dismissProgressDialog();
    }

    @Override
    public void getMoCode(WorkerOrderBean o) {
        if (null == o.getMos() || o.getMos().isEmpty()) {
            spinnerWorkerOrder.setText(R.string.tip_no_mocode_info);
        } else {
            List<WorkerOrderBean.MosBean> stations = o.getMos();
            mMoCodes.clear();
            mMoCodes.addAll(stations);
            List<String> mStrs = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                mStrs.add(stations.get(i).getMoCode());
            }
            //默认产品代码
            tvPonum.setText(stations.get(0).getItemCode());
            //设置数据源
            spinnerWorkerOrder.setItems(mStrs);
            spinnerWorkerOrder.setOnItemSelectedListener
                    ((MaterialSpinner.OnItemSelectedListener<String>)
                            (view, position, id, item) -> {
                                //注塑机
                                view.setText(item);
                                //产品代码
                                tvPonum.setText(stations.get(position).getItemCode());
                            });
        }
        //隐藏加载框
        dismissProgressDialog();
    }

    @Override
    public void valIsInjectSameBatch(Object o) {
        if (o instanceof Boolean) {
            if ((Boolean) o) {
                ToastUtils.showShort(R.string.tip_add_material_barcode_check_success);
                btnCommit.performClick();
            } else {
                new MyDialog(this, R.layout.dialog_logout)
                        .setTextViewContent(R.id.tv_title, getString(R.string.tip_scan))
                        .setTextViewContent(R.id.tv_content, getString(R.string.tip_add_material_barcode_no_same_order))
                        .setButtonListener(R.id.tv_logout_confirm, null, dialog ->
                        {
                            dialog.dismiss();
                            btnCommit.performClick();
                        })
                        .setButtonListener(R.id.tv_logout_cancel, null, Dialog::dismiss)
                        .setImageViewListener(R.id.iv_close, Dialog::dismiss)
                        .show();
            }
        }
    }

    @Override
    public void createOrUpdateOnWipMaterial(AddMaterialBean o) {
        //提示
        ToastUtils.showShort(o.getResultMessages().get(0).getMessageText());
        //设置选中
        setEdittextSelected(etAddMaterialOrder);
    }

    @Override
    public void setBarcodeSelected() {
        etAddMaterialOrder.setText("");
        setEdittextSelected(etAddMaterialOrder);
    }

    @OnClick({R.id.iv_scan, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /**
             * 物料条码
             */
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, (requestCode, result) -> {
                    etAddMaterialOrder.setText(result);
                    judgeSameBatch(result);
                });
                break;
            case R.id.btn_commit:
                String barcode = etAddMaterialOrder.getText().toString().trim();
                if (TextUtils.isEmpty(barcode)) {
                    ToastUtils.showShort(R.string.input_material_barcode);
                    return;
                }
                /**
                 * 上料提交
                 */
                addMateriaCommitlRequest(barcode);
                break;
            default:
                break;
        }
    }

    /**
     * 上料提交
     *
     * @param barcode
     */
    private void addMateriaCommitlRequest(String barcode) {
        AddMaterialRequest request = new AddMaterialRequest();
        request.setBarCode(barcode);
        request.setProcessCode(processSelectCode);
        request.setEmployeeCode("");
        request.setEmployeeName("");
        request.setInjectionMoldingEqpCode(mInjectMolds.get(dvInjectMachine.getSpinnerSelectIndex()).getValue());
        request.setMoCode(mMoCodes.get(spinnerWorkerOrder.getSelectedIndex()).getMoCode());
        request.setItemCode(mMoCodes.get(spinnerWorkerOrder.getSelectedIndex()).getItemCode());
        request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
        request.setProductionLineCode(mStations.get(spinnerStation.getSelectedIndex()).getProductionLineCode());
        request.setSuppliyEqpCode(mSupplyMaterials.get(dvSupplyMaterial.getSpinnerSelectIndex()).getValue());
        //发起请求
        showProgressDialog();
        getPresenter().createOrUpdateOnWipMaterial(request);
    }

    /**
     * 判断是否单号是否一致
     */
    private void judgeSameBatch(String barcode) {
        ValIsInjectSameBatchRequest request = new ValIsInjectSameBatchRequest();
        request.setBarcode(barcode);
        request.setInjectionMoldingEqpCode(mInjectMolds.get(dvInjectMachine.getSpinnerSelectIndex()).getValue());
        request.setSuppliyEqpCode(mSupplyMaterials.get(dvSupplyMaterial.getSpinnerSelectIndex()).getValue());
        showProgressDialog();
        getPresenter().valIsInjectSameBatch(request);
    }

    /**
     * 判断是否隐藏加载框
     */
    public void dismissProgressDialog() {
        if (!mStations.isEmpty() && !mInjectMolds.isEmpty() && !mSupplyMaterials.isEmpty() && !mMoCodes.isEmpty()) {
            dismisProgressDialog();
        }
    }

}
