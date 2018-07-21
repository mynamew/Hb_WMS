package com.jzk.hebi_wms.mvp.inject_mold;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;
import com.jzk.hebi_wms.utils.LogUitls;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.hebi_wms.view.MyDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注塑过站
 *
 * @author: timi
 * create at: 2018/7/20 9:21
 */
public class InjectMoldActivity extends BaseActivity<InjectMoldView, InjectMoldPresenter> implements InjectMoldView {


    @BindView(R.id.spinner_station)
    MaterialSpinner spinnerStation;
    @BindView(R.id.tv_ponum)
    TextView tvPonum;
    @BindView(R.id.spinner_worker_order)
    MaterialSpinner spinnerWorkerOrder;
    @BindView(R.id.spinner_inject_machine)
    MaterialSpinner spinnerInjectMachine;
    @BindView(R.id.spinner_mold)
    MaterialSpinner spinnerMold;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_add_material_order)
    EditText etAddMaterialOrder;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_product_code)
    TextView tvProductCode;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_product_model)
    TextView tvProductModel;
    @BindView(R.id.tv_product_batch)
    TextView tvProductBatch;
    @BindView(R.id.rd_good)
    RadioButton rdGood;
    @BindView(R.id.rd_bad)
    RadioButton rdBad;
    @BindView(R.id.et_bad_code)
    EditText etBadCode;
    @BindView(R.id.btn_commit)
    TextView btnCommit;
    @BindView(R.id.tv_work_line_code)
    TextView tvWorkLineCode;

    /********工位***********************************************************************************************/
    /**
     * 工位的数据源
     */
    private List<StationBean.StationsBean> mStations = new ArrayList<>();
    /**
     * 用于选择框的数据源
     */
    private List<String> mStationsStr = new ArrayList<>();
    /**
     * 工位在链表中的位置 默认是第一个位置
     */
    private int mStationPosition = 0;

    /********工单***********************************************************************************************/
    /**
     * 工单数据源
     *
     * @return
     */
    private List<WorkerOrderBean.MosBean> mMoCodes = new ArrayList<>();
    private List<String> mMoCodeStrs = new ArrayList<>();
    private int mMoCodePosition = 0;
    /********注塑机***********************************************************************************************/
    /**
     * 注塑机数据源
     *
     * @return
     */
    private List<InjectMoldBean.EqpmentsBean> mInjectMolds = new ArrayList<>();
    /********模具***********************************************************************************************/
    /**
     * 模具数据源
     *
     * @return
     */
    private List<InjectMoldBean.EqpmentsBean> mMoulds = new ArrayList<>();
    /**
     * 工序Code
     */
    private String processSelectCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_inject_mold;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
     setActivityTitle(R.string.title_inject_mold_pass);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        StationRequest request = new StationRequest();
        processSelectCode = SpUtils.getInstance().getProcessSelectCode();
        // TODO: 2018/7/21 正式的时候要删除下面这句话，工序由前面的工序选择界面设置
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
        //模具
        getPresenter().getMould();
    }

    @Override
    public InjectMoldPresenter createPresenter() {
        return new InjectMoldPresenter(this);
    }

    @Override
    public InjectMoldView createView() {
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
            mStationsStr.clear();
            for (int i = 0; i < stations.size(); i++) {
                mStationsStr.add(stations.get(i).getStationName());
            }
            //默认产线代码
            tvWorkLineCode.setText(mStations.get(mStationPosition).getProductionLineCode());
            //设置数据源
            spinnerStation.setItems(mStationsStr);
            spinnerStation.setOnItemSelectedListener((view, position, id, item) -> {
                //工位
                spinnerStation.setText(mStationsStr.get(position));
                //产线代码
                tvWorkLineCode.setText(mStations.get(position).getProductionLineCode());
                //保存位置
                mStationPosition = position;
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
            List<String> mstrs = new ArrayList<>();
            mstrs.clear();
            for (int i = 0; i < stations.size(); i++) {
                mstrs.add(stations.get(i).getDisplayText());
            }
            //设置数据源
            spinnerInjectMachine.setItems(mstrs);
            spinnerInjectMachine.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> view.setText(item));

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
            mMoCodeStrs.clear();
            for (int i = 0; i < stations.size(); i++) {
                mMoCodeStrs.add(stations.get(i).getMoCode());
            }
            //默认产品代码
            tvPonum.setText(stations.get(mMoCodePosition).getItemCode());
            //设置数据源
            spinnerWorkerOrder.setItems(mMoCodeStrs);
            spinnerWorkerOrder.setOnItemSelectedListener((view, position, id, item) -> {
                //注塑机
                spinnerWorkerOrder.setText(mMoCodeStrs.get(position));
                //产品代码
                tvPonum.setText(stations.get(position).getItemCode());
                //保存位置
                mMoCodePosition = position;
            });
        }
    }

    @Override
    public void getMould(InjectMoldBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            spinnerMold.setText("暂无模具信息");
        } else {

            List<InjectMoldBean.EqpmentsBean> stations = o.getEqpments();
            mMoulds.clear();
            mMoulds.addAll(stations);
            List<String> mstrs = new ArrayList<>();
            mstrs.clear();
            for (int i = 0; i < stations.size(); i++) {
                mstrs.add(stations.get(i).getDisplayText());
            }
            //设置数据源
            spinnerMold.setItems(mstrs);
            spinnerMold.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> view.setText(item));
        }
    }

    @OnClick({R.id.iv_scan, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, (requestCode, result) -> {
                    etAddMaterialOrder.setText(result);
                    // TODO: 2018/7/20  校验
                });
                break;
            case R.id.btn_commit:
                LogUitls.e("当前选择的位置--->", spinnerStation.getSelectedIndex());
                String barcode = etAddMaterialOrder.getText().toString().trim();
                if (TextUtils.isEmpty(barcode)) {
                    ToastUtils.showShort("请扫描上料单号！");
                    return;
                }

                break;
            default:
                break;
        }
    }
}
