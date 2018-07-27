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
    @BindView(R.id.divider_bad_code)
    View dividerBadCode;
    @BindView(R.id.ll_input_bad_code)
    LinearLayout llInputBadCode;
    @BindView(R.id.tv_bad_group_tip)
    TextView tvBadGroupTip;
    @BindView(R.id.ll_bad_group)
    LinearLayout llBadGroup;
    @BindView(R.id.tv_bad_code_tip)
    TextView tvBadCodeTip;
    @BindView(R.id.ll_bad_code_remark)
    LinearLayout llBadCodeRemark;
    @BindView(R.id.tv_process_code)
    TextView tvProcessCode;
    @BindView(R.id.tv_inject_tip)
    TextView tvInjectTip;
    @BindView(R.id.et_inject_machine)
    EditText etInjectMachine;
    @BindView(R.id.iv_inject_scan)
    ImageView ivInjectScan;
    @BindView(R.id.tv_have_select_tip)
    TextView tvHaveSelectTip;
    @BindView(R.id.rlv_have_select_bad_code)
    RecyclerView rlvHaveSelectBadCode;

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
    /**************可选不良代码*****************************************************************************/
    private BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean> mErrorAdapter;
    private List<InjectPassBean.ErrorCodesBean> mErrors = new ArrayList<>();
    /**************不良代码*****************************************************************************/
    private BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean> mErrorSelectAdapter;
    private List<InjectPassBean.ErrorCodesBean> mErrorsSelect = new ArrayList<>();

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
                    showOrHideBadCode(true);
                    /**
                     * 1、如果未检验或者校验失败则提示
                     * 2、如果校验成功并且不良代码组链表为空则证明先选择的良品，再点击的不良品需要获取一次数据
                     */
                    if (isCheckProduct && mErrorGroups.isEmpty()) {
                        getPresenter().getErrorGroups(categoryId);
                    }
                    break;
                case R.id.rd_good:
                    showOrHideBadCode(false);
                    break;
                default:
                    break;
            }
        });
        setEdittextListener(etAddMaterialOrder, Constants.REQUEST_SCAN_CODE_BARCODE, R.string.input_product_code, R.string.input_no_low_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 校验的方法
                 */
                CheckRCardInfoRquest request = new CheckRCardInfoRquest();
                request.setRCard(result);
                request.setMoldingEqpCode(mInjectMolds.get(spinnerInjectMachine.getSelectedIndex()).getValue());
                request.setProcessCode(processSelectCode);
                request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                showProgressDialog();
                getPresenter().checkRCardInfoAsync(request);
            }
        });
        /**
         * 设置输入不良代码的回车
         */
        setEdittextListener(etBadCode, Constants.REQUEST_SCAN_CODE_BAD_CODE, R.string.input_bad_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                showProgressDialog();
                getPresenter().getErrorInfoByErrorCodeAsync(categoryId, result);
            }
        });
        /**
         * 注塑机
         */
        setEdittextListener(etInjectMachine, Constants.REQUEST_SCAN_CODE_INJECT_MACHINE, R.string.input_inject_machine, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 注塑机判断的方法
                 */
                injectMachineScanResultDeal(result);
            }
        });
        /**
         * 设置默认不显示不良操作模块
         */
        showOrHideBadCode(false);

    }

    @Override
    public void initData() {
        StationRequest request = new StationRequest();
        processSelectCode = SpUtils.getInstance().getProcessSelectCode();
        /**
         * 设置工序
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
        dismissProgressDialog();
    }

    @Override
    public void getInjectionMoldings(InjectMoldBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            spinnerInjectMachine.setText(R.string.tip_no_inject_machine_info);
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
        dismissProgressDialog();
    }

    @Override
    public void getMould(InjectMoldBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            spinnerMold.setText(R.string.tip_no_mould_info);
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
        dismissProgressDialog();
    }

    /**
     * 校验返回的实体
     */
    InjectPassBean mCheckRcardResult = null;

    @Override
    public void checkRCardInfoAsync(InjectPassBean o) {
        /**
         * 存储校验实体
         */
        mCheckRcardResult = o;
        ToastUtils.showShort(R.string.tip_check_success);
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
            /**
             * 设置提交按钮不显示直接一步提交
             */
            btnCommit.setVisibility(View.GONE);
            /**
             * 提交
             */
            injectMoldingRequest();
        } else {
            ToastUtils.showShort(R.string.input_bad_code);
            /**
             * 设置提交按钮显示，不良品手动提交
             */
            btnCommit.setVisibility(View.VISIBLE);
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
        for (int i = 0; i < mErrorGroups.size(); i++) {
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
                 * 初始化已选不良代码
                 */
                mErrorsSelect.add(mErrors.get(pos));
                if (null == mErrorSelectAdapter) {
                    mErrorSelectAdapter = new BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean>(this, mErrorsSelect) {
                        @Override
                        protected int getItemLayoutId(int viewType) {
                            return R.layout.item_bad_code_selected;
                        }

                        @Override
                        protected void bindData(RecyclerViewHolder holder, int position, InjectPassBean.ErrorCodesBean item) {
                            TextView tvBad = holder.getTextView(R.id.tv_bad_code);
                            tvBad.setText(mErrorsSelect.get(position).getErrorName());
                            holder.getImageView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mErrors.add(mErrorsSelect.get(pos));
                                    mErrorAdapter.notifyDataSetChanged();
                                    mErrorsSelect.remove(pos);
                                    mErrorSelectAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    };
                    rlvHaveSelectBadCode.setLayoutManager(new LinearLayoutManager(this));
                    rlvHaveSelectBadCode.setAdapter(mErrorSelectAdapter);
                    rlvHaveSelectBadCode.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
                }else {
                    mErrorSelectAdapter.notifyDataSetChanged();
                }
                mErrors.remove(pos);
                /**
                 * 更新Adapter
                 */
                mErrorAdapter.notifyDataSetChanged();

            });
            rlvBadCode.setAdapter(mErrorAdapter);
            rlvBadCode.setLayoutManager(new LinearLayoutManager(this));
            rlvBadCode.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
        }
        mErrorAdapter.notifyDataSetChanged();
    }

    @Override
    public void collectionMoldingAsync(InjectPassBean o) {
        ToastUtils.showShort(R.string.tip_inject_pass_success);
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
        /**
         * 查找已选不良代码更新链表和Adapter
         */
        boolean isHaveSelectError = false;
        for (int i = 0; i < mErrorsSelect.size(); i++) {
            if (errorInfo.getErrorCode().equals(mErrorsSelect.get(i).getErrorCode())) {
                isHaveSelectError = true;
            }
        }
        /**
         * 可选不良代码更新
         */
        for (int i = 0; i < mErrors.size(); i++) {
            if (errorInfo.getErrorCode().equals(mErrors.get(i).getErrorCode())) {
                mErrors.remove(i);
                mErrorAdapter.notifyDataSetChanged();
            }
        }
        /**
         * 如果没有则添加进去并且更新
         */
        if (!isHaveSelectError) {
            InjectPassBean.ErrorCodesBean errorCodesBean = new InjectPassBean.ErrorCodesBean();
            errorCodesBean.setErrorCode(errorInfo.getErrorCode());
            errorCodesBean.setErrorName(errorInfo.getErrorName());
            errorCodesBean.setErrorGroupCode(errorInfo.getErrorGroupCode());
            errorCodesBean.setErrorGroupName(errorInfo.getErrorGroupName());
            mErrorsSelect.add(errorCodesBean);
            mErrorSelectAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.showShort("您输入的不良代码已选中，请重新输入！");
        }
    }

    @OnClick({R.id.iv_scan, R.id.btn_commit, R.id.iv_inject_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, (requestCode, result) -> {
                    etAddMaterialOrder.setText(result);
                    // TODO: 2018/7/20  校验
                    CheckRCardInfoRquest request = new CheckRCardInfoRquest();
                    request.setRCard(result);
                    request.setMoldingEqpCode(mInjectMolds.get(spinnerInjectMachine.getSelectedIndex()).getValue());
                    request.setProcessCode(processSelectCode);
                    request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                    showProgressDialog();
                    getPresenter().checkRCardInfoAsync(request);
                });
                break;
            /**
             * 注塑机
             */
            case R.id.iv_inject_scan:
                scan(Constants.REQUEST_SCAN_CODE_INJECT_MACHINE, (requestCode, result) -> {
                    injectMachineScanResultDeal(result);
                });
                break;
            case R.id.btn_commit:
                injectMoldingRequest();
                break;
            default:
                break;
        }
    }

    /**
     * 注塑机扫描/输入结果处理
     *
     * @param result
     */
    private void injectMachineScanResultDeal(String result) {
        boolean isInjectNameTrue = false;
        for (int i = 0; i < mInjectMolds.size(); i++) {
            /**
             * 如果在数据中查找到注塑机则设置注塑机
             */
            if (mInjectMolds.get(i).getValue().equals(result)) {
                /**
                 * 设置是否找到所扫描或输入的注塑机
                 */
                isInjectNameTrue = true;
                /**
                 * 设置注塑机文字及位置
                 */
                etInjectMachine.setText(result);
                spinnerInjectMachine.setText(result);
                spinnerInjectMachine.setSelectedIndex(i);
                /**
                 * 设置产品序列号获取焦点
                 */
                setBarcodeSelected();
            }
        }
        /**
         * 如果扫描的注塑机不村子啊
         */
        if (!isInjectNameTrue) {
            etAddMaterialOrder.setText("");
            ToastUtils.showShort("您输入/扫描注塑机不存在！");
            setEdittextSelected(etInjectMachine);
        }
    }

    /**
     * 注塑机过站提交请求
     */
    private void injectMoldingRequest() {
        String barcode = etAddMaterialOrder.getText().toString().trim();
        if (TextUtils.isEmpty(barcode)) {
            ToastUtils.showShort(R.string.input_product_code);
            return;
        }
        if (rdBad.isChecked() && mErrorsSelect.isEmpty()) {
            ToastUtils.showShort(R.string.tip_please_select_bad_code);
            return;
        }
        InjectMouldCommitRequest request = new InjectMouldCommitRequest();
        /**
         * 设置物料代码
         */
        request.setMaterialCard(mCheckRcardResult.getMaterialCard());
        /**
         * 根据是否选择良品 设置不良代码
         * rdGood.isChecked() 设置默认为null
         * rdBad.isChecked()  设置errorCode
         */
        request.setErrorCodes(rdGood.isChecked() ? null : mErrorsSelect);
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
        request.setProcessCode(processSelectCode);
        /**
         * 设置产品序列号
         */
        request.setRCard(etAddMaterialOrder.getText().toString().trim());
        request.setRemark(rdGood.isChecked() ? "" : etRemark.getText().toString().trim());
        /**
         * 设置操作员
         */
        request.setEmployeecode(SpUtils.getInstance().getUserName());
        request.setEmployeename(SpUtils.getInstance().getNickName());
        /**
         * 设置模具
         */
        request.setMouldcode(mMoulds.get(spinnerMold.getSelectedIndex()).getValue());
        /**
         * 设置工位
         */
        request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
        /**
         * 设置批次
         */
        request.setMaterialbatch(mCheckRcardResult.getMaterialBatch());
        /**
         * 发起请求
         */
        getPresenter().collectionMoldingAsync(request);
    }

    /**
     * 是否显示不良代码的控件
     *
     * @param isShow
     */
    private void showOrHideBadCode(boolean isShow) {
        tvBadCodeTip.setVisibility(isShow ? View.VISIBLE : View.GONE);
        tvBadGroupTip.setVisibility(isShow ? View.VISIBLE : View.GONE);
        rlvBadCode.setVisibility(isShow ? View.VISIBLE : View.GONE);
        llBadCodeRemark.setVisibility(isShow ? View.VISIBLE : View.GONE);
        llBadGroup.setVisibility(isShow ? View.VISIBLE : View.GONE);
        llInputBadCode.setVisibility(isShow ? View.VISIBLE : View.GONE);
        tvHaveSelectTip.setVisibility(isShow ? View.VISIBLE : View.GONE);
        rlvHaveSelectBadCode.setVisibility(isShow ? View.VISIBLE : View.GONE);
        btnCommit.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    /**
     * 判断是否隐藏加载框
     */
    public void dismissProgressDialog() {
        if (!mStations.isEmpty() && !mInjectMolds.isEmpty() && !mMoulds.isEmpty()) {
            dismisProgressDialog();
        }
    }
}
