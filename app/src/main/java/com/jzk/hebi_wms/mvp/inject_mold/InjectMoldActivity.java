package com.jzk.hebi_wms.mvp.inject_mold;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.adapter.BaseRecyclerAdapter;
import com.jzk.hebi_wms.base.adapter.RecyclerViewHolder;
import com.jzk.hebi_wms.base.divider.DividerItemDecoration;
import com.jzk.hebi_wms.data.inject.CheckRCardInfoRquest;
import com.jzk.hebi_wms.data.inject.InjectMouldCommitRequest;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
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
    @BindView(R.id.et_remark)
    EditText etRemark;
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
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.spinner_bad_groups)
    MaterialSpinner spinnerBadGroups;
    @BindView(R.id.rlv_bac_code)
    RecyclerView rlvBadCode;
    @BindView(R.id.rg_is_good)
    RadioGroup rgIsGood;
    @BindView(R.id.layout_product)
    View layoutProduct;

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
    /**
     * 产品编号是否校验通过  用于点击不良品时的判断（不良品点击的前提是先校验通过获取到产品别Id）
     */
    private boolean isCheckProduct = false;

    /**************不良代码组*****************************************************************************/
    private List<InjectPassBean.ErrorGroupsBean> mErrorGroups = new ArrayList<>();
    private List<String> mErrorGroupStrs = new ArrayList<>();
    //产品别Id
    private int categoryId;
    /**************不良代码*****************************************************************************/
    private BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean> mErrorAdapter;
    private List<InjectPassBean.ErrorCodesBean> mErrors = new ArrayList<>();
    private String errorCode = "";

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

        /**
         * 良品不良品选择的监听器
         */
        rgIsGood.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rd_bad:

                    break;
                case R.id.rd_good:

                    break;
                default:
                    break;
            }
        });
        setEdittextListener(etAddMaterialOrder, Constants.REQUEST_SCAN_CODE_BARCODE, R.string.input_product_code, R.string.input_no_low_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                // TODO: 2018/7/20  校验
                CheckRCardInfoRquest request = new CheckRCardInfoRquest();
                request.setRCard(result);
                request.setMoldingEqpCode(mInjectMolds.get(spinnerInjectMachine.getSelectedIndex()).getValue());
//                    request.setProcessCode(SpUtils.getInstance().getProcessSelectCode());
                // TODO: 2018/7/21 为了测试 正常请使用上面被注释的代码
                request.setProcessCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                showProgressDialog();
                getPresenter().checkRCardInfoAsync(request);
            }
        });
        /**
         * 设置输入备注的回车
         */
        setEdittextListener(etRemark, Constants.REQUEST_SCAN_CODE_BARCODE, R.string.input_product_code, R.string.input_no_low_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                showProgressDialog();
                getPresenter().getErrorInfoByErrorCodeAsync(categoryId, result);
            }
        });
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

    @Override
    public void checkRCardInfoAsync(InjectPassBean o) {
        ToastUtils.showShort("校验成功！");
        /**
         * 设置产品属性
         */
        layoutProduct.setVisibility(View.VISIBLE);
        tvProductCode.setText(o.getItemCode());
        tvProductName.setText(o.getItemName());
        tvProductModel.setText(o.getItemStandard());
        tvProductBatch.setText(o.getMaterialBatch());
        /**
         * 设置产品别Id
         */
        categoryId = o.getCategoryId();
        /**
         * 设置校验成功的标识
         */
        isCheckProduct = true;
        /**
         * 良品  直接提交
         */
        if (rdGood.isChecked()) {
            // TODO: 2018/7/21 提交产品序列号
            btnCommit.performClick();
        } else {
            ToastUtils.showShort("请选择不良代码！");
            // TODO: 2018/7/21 获取不良代码组
            /**
             * 判断是否需要获取，获取过则不需要重新获取
             */
            if (mErrorGroups.isEmpty()) {
                /**
                 * 获取不良代码组
                 */
                showProgressDialog();
                getPresenter().getErrorGroups(categoryId);
            }
        }
    }

    @Override
    public void checkRCardInfoAsyncFalse() {
        /**
         * 校验失败重置
         */
        isCheckProduct = false;
        layoutProduct.setVisibility(View.GONE);
    }

    @Override
    public void errorGroupHttpSubscriber(List<InjectPassBean.ErrorGroupsBean> errorGroups) {
        this.mErrorGroups.clear();
        this.mErrorGroupStrs.clear();
        this.mErrorGroups.addAll(errorGroups);
        for (int i = 0; i <mErrorGroups.size() ; i++) {
            mErrorGroupStrs.add(mErrorGroups.get(i).getErrorGroupName());
        }
        spinnerBadGroups.setItems(mErrorGroupStrs);
        /**
         * 监听事件
         */
        spinnerBadGroups.setOnItemSelectedListener((view, position, id, item) -> {
            showProgressDialog();
            getPresenter().getErrorInfoByGroupCode(mErrorGroups.get(position).getErrorGroupCode());
        });
        /**
         * 获取不良代码 默认第0个元素
         */
        showProgressDialog();
        getPresenter().getErrorInfoByGroupCode(mErrorGroups.get(0).getErrorGroupCode());
    }


    @Override
    public void getErrorInfoByGroupCode(List<InjectPassBean.ErrorCodesBean> errorCodes) {
        LogUitls.e("获取的不良代码---->", errorCodes.size());
        mErrorAdapter = null;
        mErrors.clear();
        mErrors.addAll(errorCodes);
        /**
         * 初始化适配器
         */
        if (null == mErrorAdapter) {
            mErrorAdapter = new BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean>(this, mErrors) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_bad_code;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, InjectPassBean.ErrorCodesBean item) {

                    TextView tvBad = holder.getTextView(R.id.tv_bad_code);
                    tvBad.setText(mErrors.get(position).getErrorName());
                    tvBad.setSelected(mErrors.get(position).isSelect());
                }
            };
            mErrorAdapter.setOnItemClickListener((itemView, pos) -> {
                /**
                 * 设置不良代码用于提交
                 */
                errorCode = mErrors.get(pos).getErrorCode();
                /**
                 * 设置选中
                 */
                itemView.setSelected(true);
                /**
                 * 设置标识
                 */
                for (int i = 0; i < mErrors.size(); i++) {
                    mErrors.get(i).setSelect(i == pos);
                }
                /**
                 * 更新Adapter
                 */
                mErrorAdapter.notifyDataSetChanged();
                /**
                 * 设置不良代码
                 */
                etBadCode.setText(mErrors.get(pos).getErrorName());
            });
            rlvBadCode.setAdapter(mErrorAdapter);
            rlvBadCode.setLayoutManager(new LinearLayoutManager(this));
            rlvBadCode.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.item_point_divider));
        }
        mErrorAdapter.notifyDataSetChanged();
    }

    @Override
    public void collectionMoldingAsync(InjectPassBean o) {
        ToastUtils.showShort("注塑过站提交成功！");
    }

    @Override
    public void setBarcodeSelected() {
        setEdittextSelected(etAddMaterialOrder);
    }

    @Override
    public void getErrorInfoByErrorCodeAsync(InjectPassBean.ErrorInfo errorInfo) {
        /**
         * 根据不良代码成功获取，设置errorcode
         */
        ToastUtils.showShort("获取不良代码成功！");
        errorCode = errorInfo.getErrorCode();
    }

    @OnClick({R.id.iv_scan, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, (requestCode, result) -> {
                    etAddMaterialOrder.setText(result);
                    // TODO: 2018/7/20  校验
                    CheckRCardInfoRquest request = new CheckRCardInfoRquest();
                    request.setRCard(result);
                    request.setMoldingEqpCode(mInjectMolds.get(spinnerInjectMachine.getSelectedIndex()).getValue());
//                    request.setProcessCode(SpUtils.getInstance().getProcessSelectCode());
                    // TODO: 2018/7/21 为了测试 正常请使用上面被注释的代码
                    request.setProcessCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                    request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                    showProgressDialog();
                    getPresenter().checkRCardInfoAsync(request);
                });
                break;
            case R.id.btn_commit:
                LogUitls.e("当前选择的位置--->", spinnerStation.getSelectedIndex());
                String barcode = etAddMaterialOrder.getText().toString().trim();
                if (TextUtils.isEmpty(barcode)) {
                    ToastUtils.showShort("请扫描上料单号！");
                    return;
                }
                InjectMouldCommitRequest request = new InjectMouldCommitRequest();
                /**
                 * 根据是否选择良品 设置不良代码
                 * rdGood.isChecked() 设置默认为空
                 * rdBad.isChecked()  设置errorCode
                 */
                request.setErrorCode(rdGood.isChecked() ? "" : errorCode);
                /**
                 * 设置是否为不良品
                 */
                request.setIsGood(rdGood.isChecked());
                /**
                 * 客户端默认为false
                 */
                request.setIsCollectRepeatNG(false);
                /**
                 * 注塑机Code
                 */
                request.setMoldingEqpCode(mInjectMolds.get(spinnerInjectMachine.getSelectedIndex()).getValue());
                /**
                 * 工序Code
                 */
                String processSelectCode = SpUtils.getInstance().getProcessSelectCode();
                request.setProcessCode(processSelectCode);
                /**
                 * 设置产品序列号
                 */
                request.setRCard(etAddMaterialOrder.getText().toString().trim());
                request.setRemark(rdGood.isChecked() ? "" : etRemark.getText().toString().trim());
                /**
                 * 设置工位
                 */
                request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                /**
                 * 发起请求
                 */
                showProgressDialog();
                getPresenter().collectionMoldingAsync(request);
                break;
            default:
                break;
        }
    }

    /**
     * 是否显示不良代码的控件
     * @param isShow
     */
    private void showOrHideBadCode(boolean isShow){

    }
}
