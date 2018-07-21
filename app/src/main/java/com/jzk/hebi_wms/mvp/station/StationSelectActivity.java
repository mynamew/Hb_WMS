package com.jzk.hebi_wms.mvp.station;

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
import com.jzk.hebi_wms.data.station.AddMaterialBean;
import com.jzk.hebi_wms.data.station.AddMaterialRequest;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.SupplyMaterialBean;
import com.jzk.hebi_wms.data.station.ValIsInjectSameBatchRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.utils.ToastUtils;
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
    @BindView(R.id.spinner_station)
    MaterialSpinner spinnerStation;
    @BindView(R.id.tv_ponum)
    TextView tvPonum;
    @BindView(R.id.spinner_worker_order)
    MaterialSpinner spinnerWorkerOrder;
    @BindView(R.id.spinner_inject_machine)
    MaterialSpinner spinnerInjectMachine;
    @BindView(R.id.spinner_add_material)
    MaterialSpinner spinnerAddMaterial;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_add_material_order)
    EditText etAddMaterialOrder;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.btn_commit)
    TextView btnCommit;
    @BindView(R.id.tv_work_line_code)
    TextView tvWorkLineCode;
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
    private List<InjectMoldBean.EqpmentsBean> mInjectMolds = new ArrayList<>();
    /********供料机***********************************************************************************************/
    /**
     * 供料机数据源
     *
     * @return
     */
    private List<SupplyMaterialBean.EqpmentsBean> mSupplyMaterials = new ArrayList<>();

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

    }

    @Override
    public void initData() {
        StationRequest request = new StationRequest();
        processSelectCode = SpUtils.getInstance().getProcessSelectCode();
        processSelectCode = "OP101";
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
    }

    @Override
    public void getInjectionMoldings(InjectMoldBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            spinnerInjectMachine.setText("暂无注塑机信息");
        } else {
            List<InjectMoldBean.EqpmentsBean> stations = o.getEqpments();
            mInjectMolds.clear();
            mInjectMolds.addAll(stations);
            List<String> mStrs = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                mStrs.add(stations.get(i).getDisplayText());
            }
            //设置数据源
            spinnerInjectMachine.setItems(mStrs);
            spinnerInjectMachine.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> view.setText(item));
        }
    }

    @Override
    public void getSuppliyEqps(SupplyMaterialBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            spinnerAddMaterial.setText("暂无供料机信息");
        } else {
            List<SupplyMaterialBean.EqpmentsBean> stations = o.getEqpments();
            mSupplyMaterials.clear();
            mSupplyMaterials.addAll(stations);
            List<String> mStrs = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                mStrs.add(stations.get(i).getDisplayText());
            }
            //设置数据源
            spinnerAddMaterial.setItems(mStrs);
            spinnerAddMaterial.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> view.setText(item));

        }
    }

    @Override
    public void getMoCode(WorkerOrderBean o) {
        if (null == o.getMos() || o.getMos().isEmpty()) {
            spinnerWorkerOrder.setText("暂无工单信息");
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
    }

    @Override
    public void valIsInjectSameBatch(Object o) {
        if (o instanceof Boolean) {
            if ((Boolean) o) {
                ToastUtils.showShort("上料条码校验成功,可以进行提交！");
            } else {
                new MyDialog(this, R.layout.dialog_logout)
                        .setTextViewContent(R.id.tv_title, "扫码提示")
                        .setTextViewContent(R.id.tv_content, "当前上料条码与单号不一致，是否强制提交？")
                        .setButtonListener(R.id.btn_commit, null, dialog -> btnCommit.performLongClick())
                        .setButtonListener(R.id.btn_cancel, null, Dialog::dismiss)
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

    @OnClick({R.id.iv_scan, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, (requestCode, result) -> {
                    etAddMaterialOrder.setText(result);
                    judgeSameBatch(result);
                });
                break;
            case R.id.btn_commit:
                String barcode = etAddMaterialOrder.getText().toString().trim();
                if (TextUtils.isEmpty(barcode)) {
                    ToastUtils.showShort("请扫描上料单号！");
                    return;
                }
                AddMaterialRequest request = new AddMaterialRequest();
                request.setBarCode(barcode);
                request.setProcessCode(processSelectCode);
                request.setEmployeeCode("");
                request.setEmployeeName("");
                request.setInjectionMoldingEqpCode(mInjectMolds.get(spinnerInjectMachine.getSelectedIndex()).getValue());
                request.setMoCode(mMoCodes.get(spinnerWorkerOrder.getSelectedIndex()).getMoCode());
                request.setItemCode(mMoCodes.get(spinnerWorkerOrder.getSelectedIndex()).getItemCode());
                request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                request.setProductionLineCode(mStations.get(spinnerStation.getSelectedIndex()).getProductionLineCode());
                request.setSuppliyEqpCode(mSupplyMaterials.get(spinnerAddMaterial.getSelectedIndex()).getValue());
                //发起请求
                showProgressDialog();
                getPresenter().createOrUpdateOnWipMaterial(request);
                break;
            default:
                break;
        }
    }

    /**
     * 判断是否单号是否一致
     */
    private void judgeSameBatch(String barcode) {
        ValIsInjectSameBatchRequest request = new ValIsInjectSameBatchRequest();
        request.setBarcode(barcode);

        request.setInjectionMoldingEqpCode(mInjectMolds.get(spinnerInjectMachine.getSelectedIndex()).getValue());
//        /**
//         *为了测试，正式请用上面语句
//         */
//        request.setInjectionMoldingEqpCode("MOLDING-02");
        request.setSuppliyEqpCode(mSupplyMaterials.get(spinnerAddMaterial.getSelectedIndex()).getValue());
        showProgressDialog();
        getPresenter().valIsInjectSameBatch(request);
    }
}
