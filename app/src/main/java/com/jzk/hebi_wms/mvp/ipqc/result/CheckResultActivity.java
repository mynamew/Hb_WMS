package com.jzk.hebi_wms.mvp.ipqc.result;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.ScanQRCodeResultListener;
import com.jzk.hebi_wms.base.adapter.BaseRecyclerAdapter;
import com.jzk.hebi_wms.base.adapter.RecyclerViewHolder;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcDataRequest;
import com.jzk.hebi_wms.data.ipqc.SaveCheckResultRequest;
import com.jzk.hebi_wms.http.message.BaseMessage;
import com.jzk.hebi_wms.http.message.event.CheckAppearanceEvent;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.hebi_wms.view.MyDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置抽检采集结果
 *
 * @author jzk
 * create at: 2018/8/3 11:22
 */
public class CheckResultActivity extends BaseActivity<CheckResultView, CheckResultPresenter> implements CheckResultView {


    /**
     * 提交的实体
     */
    SaveCheckResultRequest resultRequest;
    /******检验项目********************************************************************************/
    List<CollectionIpqcData.CheckItemsBean> mCheckItems = new ArrayList<>();
    /*****不良代码组*******************************************************************************/
    List<CollectionIpqcData.ErrorGroupsBean> mErrorGroups = new ArrayList<>();
    /*****不良代码*********************************************************************************/
    List<InjectPassBean.ErrorCodesBean> mErrorCodes = new ArrayList<>();
    BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean> adapter;
    @BindView(R.id.tv_product_standard)
    TextView tvProductStandard;
    @BindView(R.id.spinner_quality_type)
    MaterialSpinner spinnerQualityType;
    @BindView(R.id.btn_quality)
    Button btnQuality;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_bad_code)
    EditText etBadCode;
    @BindView(R.id.iv_bad_code_scan)
    ImageView ivBadCodeScan;
    @BindView(R.id.tv_bad_group_tip)
    TextView tvBadGroupTip;
    @BindView(R.id.spinner_bad_groups)
    MaterialSpinner spinnerBadGroups;
    @BindView(R.id.ll_bad_group)
    LinearLayout llBadGroup;
    @BindView(R.id.ll_bad_code)
    LinearLayout llBadCode;
    @BindView(R.id.rlv_bac_code)
    RecyclerView rlvBacCode;
    @BindView(R.id.btn_save)
    TextView btnSave;

    String  process;
    @Override
    public int setLayoutId() {
        return R.layout.activity_check_result;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_product_result);
        resultRequest = new Gson().fromJson(getIntent().getStringExtra(Constants.QUALITY_APPEARANCE_BEAN), SaveCheckResultRequest.class);
        process=getIntent().getStringExtra(Constants.QUALITY_APPEARANCE_RESULT_PROCESS);
    }

    @Override
    public void initView() {
        /**
         * 不良代码组的方法
         */
        spinnerBadGroups.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                showProgressDialog();
                getPresenter().getErrorInfoByGroupCodeAsyncByQuality(mErrorGroups.get(position).getErrorGroupCode());
            }
        });
        /**
         * 检验项目
         */
        spinnerQualityType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                currentCheckIremPosition = position;
            }
        });
        /**
         * 不良代码扫描或输入
         */
        setEdittextListener(etBadCode, Constants.REQUEST_SCAN_CODE_BAD_CODE, R.string.input_bad_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                judgeBadCodeByinput(result);
            }
        });
    }

    @Override
    public void initData() {
        showProgressDialog();
        CollectionIpqcDataRequest request = new CollectionIpqcDataRequest();
        request.setIPQCName(resultRequest.getIPQCName());
        request.setLotNo(resultRequest.getLotNo());
        request.setRCard(resultRequest.getRCard());
        getPresenter().getCollectionIPQCDataAsync(request);
    }

    @Override
    public CheckResultPresenter createPresenter() {
        return new CheckResultPresenter(this);
    }

    @Override
    public CheckResultView createView() {
        return this;
    }

    /**
     * 检验项目的位置
     */
    int currentCheckIremPosition = 0;

    @OnClick({R.id.btn_quality, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bad_code_scan:
                scan(Constants.REQUEST_SCAN_CODE_BAD_CODE, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etBadCode.setText(result);
                        judgeBadCodeByinput(result);
                    }
                });
                break;
            case R.id.btn_quality:
                /**
                 * 检验项目是否完成的判断
                 */
                if (currentCheckIremPosition + 1 > resultRequest.getExtendIPQCDatas().size()) {
                    ToastUtils.showShort(R.string.tip_all_checkiten_have_complete);
                    return;
                }
                MyDialog myDialog = new MyDialog(this, R.layout.dialog_quality);
                myDialog.setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                    }
                });

                setCheckItemDialogData(myDialog);
                myDialog.setButtonListener(R.id.btn_next, currentCheckIremPosition + 1 >= resultRequest.getExtendIPQCDatas().size() ? getString(R.string.complete) : getString(R.string.next), new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        RadioButton rdGood = (RadioButton) myDialog.getView(R.id.rd_good);
                        List<CollectionIpqcData.CheckItemsBean> extendIPQCDatas = resultRequest.getExtendIPQCDatas();
                        CollectionIpqcData.CheckItemsBean checkItemsBean = extendIPQCDatas.get(currentCheckIremPosition);
                        /**
                         * 设置结果，设置是否显示不良代码
                         */
                        checkItemsBean.setResult(rdGood.isChecked());
                        isShowBadCode(extendIPQCDatas);

                        /**
                         * 如果是最后一个检验项目则显示完成
                         */
                        if (currentCheckIremPosition + 1 >= resultRequest.getExtendIPQCDatas().size()) {
                            ((Button) myDialog.getView(R.id.btn_next)).setText(R.string.complete);
                        }
                        /**
                         * 设置检验项目已经被检验过
                         */
                        checkItemsBean.setHaveChecked(true);
                        /**
                         * 设置检验项目的下拉框的选择
                         */
                        spinnerQualityType.setText(mCheckItems.get(currentCheckIremPosition).getCheckItemName());
                        spinnerQualityType.setSelectedIndex(currentCheckIremPosition);

                        /**
                         * 设置数据
                         */
                        currentCheckIremPosition = currentCheckIremPosition + 1;
                        if (currentCheckIremPosition + 1 >= resultRequest.getExtendIPQCDatas().size()) {
                            myDialog.dismiss();
                        } else {
                            setCheckItemDialogData(myDialog);
                        }
                    }
                });
                myDialog.show();
                break;
            case R.id.btn_save:
                /**
                 * 判断是否做过检验项目
                 */
                List<CollectionIpqcData.CheckItemsBean> extendIPQCDatas = resultRequest.getExtendIPQCDatas();
                boolean isHaveChecked = false;
                for (int i = 0; i < extendIPQCDatas.size(); i++) {
                    if (extendIPQCDatas.get(i).isHaveChecked()) {
                        isHaveChecked = true;
                    }
                }
                /**
                 * 未做过检验提醒
                 */
                if (!isHaveChecked) {
                    ToastUtils.showShort(R.string.tip_complete_checkitems);
                    return;
                }
                /**
                 * 判断是否有不良品提示选择不良代码
                 */
                boolean isHaveBad = false;
                for (int i = 0; i < extendIPQCDatas.size(); i++) {
                    if (!extendIPQCDatas.get(i).isResult()) {
                        isHaveBad = true;
                    }
                }
                /**
                 * 加入不良代码
                 * 如果不良代码是选中则加入，否则则不加入
                 */
                if (null == resultRequest.getErrorCodes()) {
                    resultRequest.setErrorCodes(new ArrayList<>());
                } else {
                    resultRequest.getErrorCodes().clear();
                }
                for (int i = 0; i < mErrorCodes.size(); i++) {
                    if (mErrorCodes.get(i).isSelect()) {
                        resultRequest.getErrorCodes().add(mErrorCodes.get(i));
                    }
                }
                /**
                 * 如果有不良代码
                 */
                if (isHaveBad && resultRequest.getErrorCodes().isEmpty()) {
                    ToastUtils.showShort(R.string.tip_select_bad_code);
                    return;
                }
                showProgressDialog();
                getPresenter().saveCheckResult(resultRequest);
                break;
            default:
                break;
        }
    }

    @Override
    public void getCollectionIPQCDataAsync(CollectionIpqcData o) {
        if (null != o.getCheckItems() && !o.getCheckItems().isEmpty()) {
            mCheckItems.addAll(o.getCheckItems());
            /**
             * 抽检项目的下拉框数据初始化
             */
            ArrayList<String> mstrs = new ArrayList<>();
            for (int i = 0; i < mCheckItems.size(); i++) {
                mstrs.add(mCheckItems.get(i).getCheckItemName());
            }
            spinnerQualityType.setItems(mstrs);
        }
        /**
         * 获取errorgroup
         */
        if (null != o.getErrorGroups() && !o.getErrorGroups().isEmpty()) {
            /**
             * 注塑工序
             */
            for (int i = 0; i <o.getErrorGroups().size() ; i++) {
                if(o.getErrorGroups().get(i).getErrorGroupCode().contains(process)){
                    mErrorGroups.add(o.getErrorGroups().get(i));
                }
            }
            /**
             *不良代码组的下拉框数据初始化
             */
            ArrayList<String> mstrs = new ArrayList<>();
            for (int i = 0; i < mErrorGroups.size(); i++) {
                mstrs.add(mErrorGroups.get(i).getErrorGroupName());
            }
            spinnerBadGroups.setItems(mstrs);
            showProgressDialog();
            getPresenter().getErrorInfoByGroupCodeAsyncByQuality(mErrorGroups.get(0).getErrorGroupCode());
        }
        /**
         * 设置检验项目
         */
        resultRequest.setExtendIPQCDatas(o.getCheckItems());
    }

    @Override
    public void saveCheckResult(CollectionIpqcData o) {
        ToastUtils.showShort(R.string.tip_save_check_result_success);
        /**
         * 发送事件更新前个界面的数据
         */
        BaseMessage.post(new CheckAppearanceEvent(CheckAppearanceEvent.REFRESH_CHECK_DATA));
        onBackPressed();
    }

    @Override
    public void getErrorInfoByGroupCode(List<InjectPassBean.ErrorCodesBean> errorCodes) {
        /**
         * 初始化不良代码
         */
        if (null != errorCodes && !errorCodes.isEmpty()) {
            mErrorCodes.addAll(errorCodes);
            if (null == adapter) {
                adapter = new BaseRecyclerAdapter<InjectPassBean.ErrorCodesBean>(this, mErrorCodes) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_selected_bad_code;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, InjectPassBean.ErrorCodesBean item) {
                        CheckBox checkBox = holder.getCheckBox(R.id.cb_bad_code);
                        checkBox.setText(item.getErrorName());
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                mErrorCodes.get(position).setSelect(isChecked);
                            }
                        });
                    }
                };
                rlvBacCode.setAdapter(adapter);
                rlvBacCode.setLayoutManager(new LinearLayoutManager(this));
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 设置检验项目弹出框的数据
     *
     * @param dialog
     */
    private void setCheckItemDialogData(MyDialog dialog) {
        List<CollectionIpqcData.CheckItemsBean> extendIPQCDatas = resultRequest.getExtendIPQCDatas();
        CollectionIpqcData.CheckItemsBean checkItemsBean = extendIPQCDatas.get(currentCheckIremPosition);
        /**
         * 设置检验项目
         */
        dialog.setTextViewContent(R.id.tv_sample_code, checkItemsBean.getCheckItemName());
        /**
         * 设置上下限
         */
        dialog.setTextViewContent(R.id.tv_sample_up, checkItemsBean.getLimitHigh());
        dialog.setTextViewContent(R.id.tv_sample_down, checkItemsBean.getLimitLow());
        /**
         * 设置单位
         */
        dialog.setTextViewContent(R.id.tv_sample_unit, checkItemsBean.getUnit());
        /**
         * 设置标准
         */
        dialog.setTextViewContent(R.id.tv_standard_value, checkItemsBean.getActual());
    }

    /**
     * 是否显示不良代码的布局
     *
     * @param extendIPQCDatas
     */
    private void isShowBadCode(List<CollectionIpqcData.CheckItemsBean> extendIPQCDatas) {
        /**
         * 判断是否需要显示不良代码
         */
        boolean isHaveBad = false;
        for (int i = 0; i < extendIPQCDatas.size(); i++) {
            if (!extendIPQCDatas.get(i).isResult()) {
                isHaveBad = true;
            }
        }
        /**
         * 是否显示不良代码布局
         */
        if (!isHaveBad) {
            findViewById(R.id.ll_bad_code).setVisibility(View.GONE);
            rlvBacCode.setVisibility(View.GONE);
        } else {
            findViewById(R.id.ll_bad_code).setVisibility(View.VISIBLE);
            rlvBacCode.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 判断不良代码是否存在
     *
     * @param result
     */
    private void judgeBadCodeByinput(String result) {
        boolean isResultHave = false;
        for (int i = 0; i < mErrorCodes.size(); i++) {
            if (result.equals(mErrorCodes.get(i).getErrorCode())) {
                isResultHave = true;
                mErrorCodes.get(i).setSelect(true);
                adapter.notifyDataSetChanged();
            }
        }
        if (!isResultHave) {
            ToastUtils.showShort(R.string.tip_bad_code_no_alive);
            etBadCode.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
