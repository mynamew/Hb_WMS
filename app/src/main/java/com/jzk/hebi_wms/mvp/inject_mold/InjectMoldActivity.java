package com.jzk.hebi_wms.mvp.inject_mold;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.inject.InjectMouldCommitRequest;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注塑过站
 *
 * @author: timi
 * create at: 2018/7/20 9:21
 */
public class InjectMoldActivity extends BaseActivity<InjectMoldView, InjectMoldPresenter> implements InjectMoldView {
    @BindView(R.id.tv_process_code)
    TextView tvProcessCode;
    @BindView(R.id.spinner_station)
    MaterialSpinner spinnerStation;
    @BindView(R.id.tv_work_line_code)
    TextView tvWorkLineCode;
    @BindView(R.id.dv_inject_machine)
    DeviceView dvInjectMachine;
    @BindView(R.id.dv_mold)
    DeviceView dvMold;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.rd_good)
    RadioButton rdGood;
    @BindView(R.id.rd_bad)
    RadioButton rdBad;
    @BindView(R.id.rg_is_good)
    RadioGroup rgIsGood;
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
    @BindView(R.id.divider_bad_code)
    View dividerBadCode;
    @BindView(R.id.et_bad_code)
    EditText etBadCode;
    @BindView(R.id.ll_input_bad_code)
    LinearLayout llInputBadCode;
    @BindView(R.id.tv_bad_group_tip)
    TextView tvBadGroupTip;
    @BindView(R.id.spinner_bad_groups)
    MaterialSpinner spinnerBadGroups;
    @BindView(R.id.ll_bad_group)
    LinearLayout llBadGroup;
    @BindView(R.id.tv_have_select_tip)
    TextView tvHaveSelectTip;
    @BindView(R.id.rlv_have_select_bad_code)
    RecyclerView rlvHaveSelectBadCode;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.ll_bad_code_remark)
    LinearLayout llBadCodeRemark;
    @BindView(R.id.btn_commit)
    TextView btnCommit;
    @BindView(R.id.tv_count_pass_qty)
    TextView tvCountPassQty;

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
    private List<InjectMoldBean.EqpmentsBean> mOldMoulds = new ArrayList<>();
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
                request.setMoldingEqpCode(mInjectMolds.get(dvInjectMachine.getSpinnerSelectIndex()).getValue());
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
        dvInjectMachine.setEdittextListener(new DeviceView.EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }

            @Override
            public void hideInputSoftware() {
                InputMethodUtils.hide(InjectMoldActivity.this);
            }
        });
        dvMold.setEdittextListener(new DeviceView.EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {

            }

            @Override
            public void hideInputSoftware() {
                InputMethodUtils.hide(InjectMoldActivity.this);
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
        if (!getString(R.string.process_inject).equals(processSelectCode)) {
            new MyDialog(this, R.layout.dialog_error_tip)
                    .setTextViewContent(R.id.tv_title, getString(R.string.error_title))
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
    public void getInjectionMoldings(EquipmentByTypeList o) {
        if (null == o.getEquipmentList() || o.getEquipmentList().isEmpty()) {
            dvInjectMachine.setSpinnerText(R.string.tip_no_inject_machine_info);
        } else {
            List<InjectMoldBean.EqpmentsBean> stations = o.getEquipmentList();
            mInjectMolds.clear();
            mInjectMolds.addAll(stations);
            //设置数据源
            dvInjectMachine.initDeviceData(mInjectMolds, new DeviceView.DeviceListener() {
                @Override
                public void deviceSelect(int position) {
                    dealWithInjectAndSupply(position);
                }
            });
            dvInjectMachine.setEdittextContent(mInjectMolds.get(0).getValue());
            dvInjectMachine.setSpinnerEdittextSelect();
        }
        if (!mMoulds.isEmpty() && !mInjectMolds.isEmpty()) {
            dealWithInjectAndSupply(0);
        }
        dismissProgressDialog();
    }

    /**
     * 处理 注塑机和供料机的数据
     *
     * @param position
     */
    private void dealWithInjectAndSupply(int position) {
        /**
         * 清空链表
         */
        mMoulds.clear();
        if (TextUtils.isEmpty(mInjectMolds.get(position).getRelatedEquipment())) {
            mMoulds.addAll(mOldMoulds);
            /**
             * 初始化数据
             */
            dvMold.setEdittextContent(mMoulds.get(0).getValue());
            dvMold.setSpinnerSelectIndex(0);
            //设置数据源
            dvMold.initDeviceData(mMoulds);
            return;
        }

        /**
         * 处理点击事件，获取供料机的列表
         */
        String[] split = mInjectMolds.get(position).getRelatedEquipment().trim().split("\\|");
        if (split.length >= 3) {
            boolean isHaveData = false;
            for (int i = 0; i < mOldMoulds.size(); i++) {
                if (mOldMoulds.get(i).getValue().equals(split[2])) {
                    isHaveData = true;
                    mMoulds.add(mOldMoulds.get(i));
                }
            }
            if (!isHaveData) {
                mMoulds.addAll(mOldMoulds);
            }
        } else {
            mMoulds.addAll(mOldMoulds);
        }
        /**
         * 初始化数据
         */
        dvMold.setEdittextContent(mMoulds.get(0).getValue());
        dvMold.setSpinnerSelectIndex(0);
        //设置数据源
        dvMold.initDeviceData(mMoulds);
    }

    @Override
    public void getMould(InjectMoldBean o) {
        if (null == o.getEqpments() || o.getEqpments().isEmpty()) {
            dvMold.setSpinnerText(R.string.tip_no_mould_info);
        } else {
            List<InjectMoldBean.EqpmentsBean> stations = o.getEqpments();
            mMoulds.clear();
            mMoulds.addAll(stations);
            mOldMoulds.clear();
            mOldMoulds.addAll(stations);
            dvMold.initDeviceData(mMoulds);
        }
        if (!mMoulds.isEmpty() && !mInjectMolds.isEmpty()) {
            dealWithInjectAndSupply(0);
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
        findViewById(R.id.layout_product).setVisibility(View.VISIBLE);
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
            injectMoldingRequest(false);
        } else {
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
                getPresenter().getErrorGroups(categoryId);
            } else {
                dismissProgressDialog();
            }
        }
    }

    @Override
    public void checkRCardInfoAsyncFalse() {
        /**
         * 校验失败重置
         */
        isCheckProduct = false;
        findViewById(R.id.layout_product).setVisibility(View.GONE);
    }

    @Override
    public void errorGroupHttpSubscriber(List<InjectPassBean.ErrorGroupsBean> errorGroups) {
        this.mErrorGroups.clear();
        this.mErrorGroupStrs.clear();
        /**
         * 判断如那些不良代码组
         */
        for (int i = 0; i < errorGroups.size(); i++) {
            if (errorGroups.get(i).getErrorGroupCode().contains("MOLDING")) {
                this.mErrorGroups.add(errorGroups.get(i));
            }
        }
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
        getPresenter().getErrorInfoByGroupCode(mErrorGroups.get(0).getErrorGroupCode());
    }


    @Override
    public void getErrorInfoByGroupCode(List<InjectPassBean.ErrorCodesBean> errorCodes) {
        mErrorsSelect.clear();
        mErrorsSelect.addAll(errorCodes);
        /**
         * 初始化已选不良代码
         */
        if (null == mErrorSelectAdapter) {
            mErrorSelectAdapter = new BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean>(this, mErrorsSelect) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_selected_bad_code;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, InjectPassBean.ErrorCodesBean item) {
                    CheckBox checkBox = holder.getCheckBox(R.id.cb_bad_code);
                    checkBox.setChecked(item.isSelect());
                    checkBox.setText(item.getErrorName());
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            mErrorsSelect.get(position).setSelect(isChecked);
                        }
                    });
                }
            };
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvHaveSelectBadCode.setLayoutManager(linearLayoutManager);
            rlvHaveSelectBadCode.setAdapter(mErrorSelectAdapter);
            rlvHaveSelectBadCode.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            setRecycleViewScrollSmooth(rlvHaveSelectBadCode, linearLayoutManager);
        } else {
            mErrorSelectAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void collectionMoldingAsync(InjectPassBean o) {
        tvCountPassQty.setText(String.valueOf(Integer.parseInt(tvCountPassQty.getText().toString().trim())+1));
        dismissProgressDialog();
        ToastUtils.showShort(R.string.tip_inject_pass_success);
        /**
         * 如果是bad
         */
        if (rdBad.isChecked()) {
            /**
             * 根据用户设置是否充值注塑的状态： 良品：不良品
             */
            if (SpUtils.getInstance().getBoolean(Constants.USER_RESET_INJECT)) {
                /**
                 * 初始化选择良品
                 * 设置所有不良代码全不选中
                 */
                for (int i = 0; i < mErrorsSelect.size(); i++) {
                    mErrorsSelect.get(i).setSelect(false);
                }
                mErrorSelectAdapter.notifyDataSetChanged();
                rdGood.performClick();
            }
        }
        /**
         * 如果是从不良品检验，到良品检验则需要重置
         */
        if (rdGood.isChecked() && null != mErrorsSelect && !mErrorsSelect.isEmpty()) {
            /**
             * 初始化选择良品
             * 设置所有不良代码全不选中
             */
            for (int i = 0; i < mErrorsSelect.size(); i++) {
                mErrorsSelect.get(i).setSelect(false);
            }
            mErrorSelectAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setBarcodeSelected() {
        etAddMaterialOrder.setText("");
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
            ToastUtils.showShort(R.string.tip_bad_code_have_selected);
        }
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
                    request.setMoldingEqpCode(mInjectMolds.get(dvInjectMachine.getSpinnerSelectIndex()).getValue());
                    request.setProcessCode(processSelectCode);
                    request.setStationCode(mStations.get(spinnerStation.getSelectedIndex()).getStationCode());
                    showProgressDialog();
                    getPresenter().checkRCardInfoAsync(request);
                });
                break;
            case R.id.btn_commit:
                injectMoldingRequest(true);
                break;
            default:
                break;
        }
    }

    /**
     * 注塑机过站提交请求
     */
    private void injectMoldingRequest(boolean isShowProgressDialog) {
        String barcode = etAddMaterialOrder.getText().toString().trim();
        if (TextUtils.isEmpty(barcode)) {
            ToastUtils.showShort(R.string.input_product_code);
            return;
        }
        /**
         * 是否选择过不良代码
         */
        boolean isHaveSelectBadCode = false;
        for (int i = 0; i < mErrorsSelect.size(); i++) {
            if (mErrorsSelect.get(i).isSelect()) {
                isHaveSelectBadCode = true;
            }
        }
        /**
         * 判断是否选择过不良代码
         */
        if (rdBad.isChecked() && !isHaveSelectBadCode) {
            ToastUtils.showShort(R.string.tip_please_select_bad_code);
            return;
        }
        /**
         * 是否显示加载框
         */
        if (isShowProgressDialog) {
            showProgressDialog();
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
        List<InjectPassBean.ErrorCodesBean> commitErrorSelects = new ArrayList<>();
        for (int i = 0; i < mErrorsSelect.size(); i++) {
            if (mErrorsSelect.get(i).isSelect()) {
                commitErrorSelects.add(mErrorsSelect.get(i));
            }
        }
        request.setErrorCodes(rdGood.isChecked() ? null : commitErrorSelects);
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
        request.setMoldingEqpCode(mInjectMolds.get(dvInjectMachine.getSpinnerSelectIndex()).getValue());
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
        request.setMouldcode(mMoulds.get(dvMold.getSpinnerSelectIndex()).getValue());
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
        tvBadGroupTip.setVisibility(isShow ? View.VISIBLE : View.GONE);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
