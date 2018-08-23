package com.jzk.hebi_wms.mvp.paint;

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
import com.jzk.hebi_wms.base.ScanQRCodeResultListener;
import com.jzk.hebi_wms.data.paint.PaintRequest;
import com.jzk.hebi_wms.data.paint.PaintResult;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
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
  * 喷漆上料界面
  * @author   jzk
  * create at: 2018/8/23 14:41
  */  
public class PaintActivity extends BaseActivity<PaintView, PaintPresenter> implements PaintView {
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
    @BindView(R.id.tv_diluent_tip)
    TextView tvDiluentTip;
    @BindView(R.id.et_diluent)
    EditText etDiluent;
    @BindView(R.id.iv_diluent_scan)
    ImageView ivDiluentScan;
    @BindView(R.id.tv_diluent_barcode_tip)
    TextView tvDiluentBarcodeTip;
    @BindView(R.id.et_diluent_barcode_order)
    EditText etDiluentBarcodeOrder;
    @BindView(R.id.iv_scan_diluent_code)
    ImageView ivScanDiluentCode;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
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
    /**
     * 工序代码
     */
    String processSelectCode = "";
    @Override
    public int setLayoutId() {
        return R.layout.activity_paint;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_paint);
    }

    @Override
    public void initView() {
        /**
         * 稀释剂
         */
        setEdittextListener(etDiluent, Constants.REQUEST_SCAN_CODE_DILUENT, R.string.input_diluent_barcode, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                setEdittextSelected(etDiluentBarcodeOrder);
            }
        });
        /**
         * 喷漆
         */
        setEdittextListener(etDiluentBarcodeOrder, Constants.REQUEST_SCAN_CODE_PAINT, R.string.input_paint_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
            }
        });
        /**
         * 注塑机
         */
        dvInjectMachine.setEdittextListener(new DeviceView.EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }

            @Override
            public void hideInputSoftware() {
                InputMethodUtils.hide(PaintActivity.this);
            }
        });
        /**
         * 喷漆设备
         */
        dvInjectMachine.setEdittextListener(new DeviceView.EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }

            @Override
            public void hideInputSoftware() {
                InputMethodUtils.hide(PaintActivity.this);
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
        if (!getString(R.string.process_paint).equals(processSelectCode)) {
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, R.string.error_title)
                    .setTextViewContent(R.id.tv_content,getString(R.string.tip_no_paint))
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
        //喷漆
        getPresenter().getInjectionMoldings();
        //工单
        getPresenter().getMoCode();
    }

    @Override
    public PaintPresenter createPresenter() {
        return new PaintPresenter(this);
    }

    @Override
    public PaintView createView() {
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
    public void getInjectionMoldings(InjectMoldBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            dvInjectMachine.setSpinnerText(R.string.tip_no_inject_machine_info);
        } else {
            List<InjectMoldBean.EqpmentsBean> stations = o.getEqpments();
            mInjectMolds.clear();
            mInjectMolds.addAll(stations);
            //设置数据源
            dvInjectMachine.initDeviceData(mInjectMolds, new DeviceView.DeviceListener() {
                @Override
                public void deviceSelect(int position) {
                }
            });
            dvInjectMachine.setEdittextContent(mInjectMolds.get(0).getValue());
            dvInjectMachine.setSpinnerEdittextSelect();
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
    public void createOrUpdateOnWipPaint(PaintResult o) {
        ToastUtils.showShort(o.getResultMessages().get(0).getMessageText());
        /**
         * 设置选中
         */
        etDiluentBarcodeOrder.setText("");
        setEdittextSelected(etDiluentBarcodeOrder);
    }

    /**
     * 判断是否隐藏加载框
     */
    public void dismissProgressDialog() {
        if (!mStations.isEmpty() && !mInjectMolds.isEmpty() && !mMoCodes.isEmpty()) {
            dismisProgressDialog();
        }
    }
    @OnClick({R.id.iv_diluent_scan, R.id.iv_scan_diluent_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_diluent_scan:
                scan(Constants.REQUEST_SCAN_CODE_DILUENT, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etDiluent.setText(result);
                        setEdittextSelected(etDiluentBarcodeOrder);
                    }
                });
                break;
            case R.id.iv_scan_diluent_code:
                scan(Constants.REQUEST_SCAN_CODE_PAINT, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        requestPaint(result);
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 喷漆条码扫描请求
     * @param result
     */
    private void requestPaint(String result) {
        PaintRequest request=new PaintRequest();
        request.setBarCode(result);
        request.setThinnerCard(etDiluent.getText().toString().trim());
        request.setInjectionMoldingEqpCode(mInjectMolds.get(dvInjectMachine.getSpinnerSelectIndex()).getValue());
        request.setItemCode(mMoCodes.get(spinnerStation.getSelectedIndex()).getItemCode());
        request.setMoCode(mMoCodes.get(spinnerStation.getSelectedIndex()).getMoCode());
        request.setProcessCode(processSelectCode);
        request.setProductionLineCode(mStations.get(spinnerWorkerOrder.getSelectedIndex()).getProductionLineCode());
        request.setStationCode(mStations.get(spinnerWorkerOrder.getSelectedIndex()).getStationCode());
        showProgressDialog();
        getPresenter().createOrUpdateOnWipPaint(request);
    }
}
