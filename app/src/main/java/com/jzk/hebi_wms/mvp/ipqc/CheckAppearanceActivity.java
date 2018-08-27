package com.jzk.hebi_wms.mvp.ipqc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.adapter.BaseRecyclerAdapter;
import com.jzk.hebi_wms.base.adapter.RecyclerViewHolder;
import com.jzk.hebi_wms.data.ipqc.CalculateCheckCountRequest;
import com.jzk.hebi_wms.data.ipqc.CheckRecardInfoRequest;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.data.ipqc.SaveCheckResultRequest;
import com.jzk.hebi_wms.http.message.BaseMessage;
import com.jzk.hebi_wms.http.message.event.CheckAppearanceEvent;
import com.jzk.hebi_wms.mvp.ipqc.result.CheckResultActivity;
import com.jzk.hebi_wms.utils.DateUtils;
import com.jzk.hebi_wms.utils.LogUitls;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.hebi_wms.view.MyDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 外观抽检的界面
 *
 * @author jzk
 * create at: 2018/8/3 16:23
 */
public class CheckAppearanceActivity extends BaseActivity<CheckAppearanceView, CheckAppearancePresenter> implements CheckAppearanceView {


    @BindView(R.id.tv_bottom_product_serial_no_tip)
    TextView tvBottomProductSerialNoTip;
    @BindView(R.id.et_bottom_product_serial_no)
    EditText etBottomProductSerialNo;
    @BindView(R.id.iv_bottom_product_serial_no_scan)
    ImageView ivBottomProductSerialNoScan;
    @BindView(R.id.btn_batch_pass)
    Button btnBatchPass;
    @BindView(R.id.btn_batch_return)
    Button btnBatchReturn;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.tv_add_material_tip)
    TextView tvAddMaterialTip;
    @BindView(R.id.et_batch_no)
    EditText etBatchNo;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.btn_create_batchno)
    Button btnCreateBatchNo;
    @BindView(R.id.spinner_quality_type)
    MaterialSpinner spinnerQualityType;
    @BindView(R.id.tv_quality_des)
    TextView tvQualityDes;
    @BindView(R.id.spinner_process)
    MaterialSpinner spinnerProcess;
    @BindView(R.id.tv_process_name)
    TextView tvProcessName;
    @BindView(R.id.spinner_time_frame)
    MaterialSpinner spinnerTimeFrame;
    @BindView(R.id.tv_time_frame_name)
    TextView tvTimeFrameName;
    @BindView(R.id.spinner_project_date)
    MaterialSpinner spinnerProjectDate;
    @BindView(R.id.tv_time_actual_sample)
    TextView tvTimeActualSample;
    @BindView(R.id.tv_pass_sample)
    TextView tvPassSample;
    @BindView(R.id.tv_unpass_sample)
    TextView tvUnpassSample;
    @BindView(R.id.rlv_product)
    RecyclerView rlvProduct;
    @BindView(R.id.tv_quality_total)
    TextView tvQualityTotal;
    @BindView(R.id.btn_mark_total)
    Button btnMarkTotal;
    @BindView(R.id.tv_product_serial_no)
    TextView tvProductSerialNo;
    @BindView(R.id.tv_product_status)
    TextView tvProductStatus;
    /***日期选择***********************************************************************************/
    private List<String> canSelectDate = new ArrayList<>();

    /*****质检名称*****************************************************************************/
    List<IpqcCommonResult.DpListBean> qualityNames = null;
    /*****时段*****************************************************************************/
    List<IpqcCommonResult.DpListBean> qualityTimes = null;
    /*****工序*****************************************************************************/
    List<IpqcCommonResult.DpListBean> qualityProcesses = null;

    /**
     * 产品信息
     ****************************************************************************/
    BaseRecyclerAdapter<IpqcCommonResult.Lot2RcardListBean> adapter;
    List<IpqcCommonResult.Lot2RcardListBean> mDatas = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_check_appearance;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_appearance_quality);
        BaseMessage.register(this);
    }

    @Override
    public void initView() {
        setEdittextListener(etBatchNo, Constants.REQUEST_SCAN_CODE_BATCH_NO, R.string.input_batch_no, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                showProgressDialog();
                getPresenter().getLotInfoAsync(result);
            }
        });
        setEdittextListener(etBottomProductSerialNo, Constants.REQUEST_SCAN_CODE_PRODUCT_SERIAL_NO, R.string.input_serial_no, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
//                long selectDateMs = DateUtils.Date2msOnlyDay(canSelectDate.get(1));
//                long currentDateMs = DateUtils.Date2msOnlyDay(DateUtils.ms2DateOnlyDay(System.currentTimeMillis()));
//                if (selectDateMs <= currentDateMs) {
//                    /**
//                     * 判断时间段是否可操作
//                     */
//                    String timeFrame = qualityTimes.get(spinnerTimeFrame.getSelectedIndex()).getValue();
//                    String[] split = timeFrame.split("-");
//                    StringBuffer timeFrameBuffer = new StringBuffer();
//                    timeFrameBuffer.append(DateUtils.ms2DateOnlyDay(System.currentTimeMillis()));
//                    timeFrameBuffer.append(" " + split[1]);
//                    timeFrameBuffer.append(":00:00");
//                    long timeFrameMs = DateUtils.Date2ms(timeFrameBuffer.toString());
//                    if (System.currentTimeMillis() < timeFrameMs) {
//                        ToastUtils.showShort(R.string.tip_select_timeframe_low);
//                        setProductSerialNoSelect();
//                        return;
//                    }
//                }
                if (TextUtils.isEmpty(etBatchNo.getText().toString().trim())) {
                    ToastUtils.showShort("请生成或输入扫描批次号！");
                    return;
                }
                /**
                 * 发起请求
                 */
                showProgressDialog();
                CheckRecardInfoRequest recardInfoRequest = new CheckRecardInfoRequest();
                recardInfoRequest.setLotNo(etBatchNo.getText().toString().trim());
                recardInfoRequest.setInputBox(result);
                recardInfoRequest.setPlanDate(canSelectDate.get(spinnerProjectDate.getSelectedIndex()));
                recardInfoRequest.setProcess(qualityProcesses.get(spinnerProcess.getSelectedIndex()).getValue());
                recardInfoRequest.setTimePerod(qualityTimes.get(spinnerTimeFrame.getSelectedIndex()).getValue());
                getPresenter().checkRCardInfoAsync(recardInfoRequest);
            }
        });

        setBatchNoSelect();

    }

    @Override
    public void initData() {
        /**
         * 获取当前年月日
         */
        int[] yearAndMonthAndDay = DateUtils.getYearAndMonthAndDay(DateUtils.ms2DateOnlyDay(System.currentTimeMillis()));
        getCurrentAndLastDate(yearAndMonthAndDay[0], yearAndMonthAndDay[1], yearAndMonthAndDay[2]);

        showProgressDialog();
        getPresenter().getIQPCNameAsync();
        getPresenter().getProcessAsync();
        getPresenter().getTimePerodAsync();
    }

    @Override
    public CheckAppearancePresenter createPresenter() {
        return new CheckAppearancePresenter(this);
    }

    @Override
    public CheckAppearanceView createView() {
        return this;
    }


    @OnClick({R.id.iv_scan, R.id.iv_bottom_product_serial_no_scan, R.id.btn_batch_pass, R.id.btn_batch_return, R.id.btn_create_batchno})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /**
             * 批号的扫描
             */
            case R.id.iv_scan:
                scan(Constants.REQUEST_SCAN_CODE_BATCH_NO, (requestCode, result) -> {
                    etBatchNo.setText(result);
                    showProgressDialog();
                    getPresenter().getLotInfoAsync(result);
                });
                break;
            case R.id.iv_bottom_product_serial_no_scan:
                scan(Constants.REQUEST_SCAN_CODE_PRODUCT_SERIAL_NO, (requestCode, result) -> {
                    etBottomProductSerialNo.setText(result);
                    showProgressDialog();
                    CheckRecardInfoRequest recardInfoRequest = new CheckRecardInfoRequest();
                    recardInfoRequest.setLotNo(etBatchNo.getText().toString().trim());
                    recardInfoRequest.setInputBox(result);
                    recardInfoRequest.setPlanDate(canSelectDate.get(spinnerProjectDate.getSelectedIndex()));
                    recardInfoRequest.setProcess(qualityProcesses.get(spinnerProcess.getSelectedIndex()).getValue());
                    recardInfoRequest.setTimePerod(qualityTimes.get(spinnerTimeFrame.getSelectedIndex()).getValue());
                    getPresenter().checkRCardInfoAsync(recardInfoRequest);
                });
                break;
            case R.id.btn_batch_pass:
                new MyDialog(this, R.layout.dialog_logout).setTextViewContent(R.id.tv_title, "外观抽检")
                        .setTextViewContent(R.id.tv_content, "是否确定批通过？")
                        .setButtonListener(R.id.tv_logout_confirm, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                                showProgressDialog();
                                getPresenter().ipacLotPassAsync(etBatchNo.getText().toString().trim());
                            }
                        }).setButtonListener(R.id.tv_logout_cancel, null, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                    }
                }).setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();

                break;
            case R.id.btn_batch_return:
                new MyDialog(this, R.layout.dialog_logout).setTextViewContent(R.id.tv_title, "外观抽检")
                        .setTextViewContent(R.id.tv_content, "是否确定批退？")
                        .setButtonListener(R.id.tv_logout_confirm, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                                showProgressDialog();
                                getPresenter().ipqcLotRejectAsync(etBatchNo.getText().toString().trim());
                            }
                        }).setButtonListener(R.id.tv_logout_cancel, null, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                    }
                }).setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();

                break;
            case R.id.btn_create_batchno:
                showProgressDialog();
                getPresenter().createNewLotNoAsync();
                break;
            default:
                break;
        }
    }

    /**
     * 获取当前和前一天时间供用户选择
     */
    private void getCurrentAndLastDate(int yearInput, int monthInput, int dayInput) {
        canSelectDate.clear();
        Calendar calendar = Calendar.getInstance();
        /**
         * 设置时间
         */
        calendar.set(Calendar.YEAR, yearInput);
        calendar.set(Calendar.MONTH, monthInput);
        calendar.set(Calendar.DAY_OF_MONTH, dayInput);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        int yearLast = calendar.get(Calendar.YEAR);
        int monthLast = calendar.get(Calendar.MONTH);
        int dayLast = calendar.get(Calendar.DAY_OF_MONTH);
        canSelectDate.add(DateUtils.dateStr2CommonDateStr(year, month, day));
        canSelectDate.add(DateUtils.dateStr2CommonDateStr(yearLast, monthLast, dayLast));
        spinnerProjectDate.setItems(canSelectDate);
    }

    @Override
    public void getLotInfoAsync(IpqcCommonResult o) {
        /**
         * 如果产品信息不为空则显示产品信息
         */
        if (null != o.getLot2RcardList() && !o.getLot2RcardList().isEmpty()) {
            /**
             * 产品信息链表
             */
            mDatas.clear();
            mDatas.addAll(o.getLot2RcardList());
            /**
             * 初始化adapter
             */
            if (null == adapter) {
                adapter = new BaseRecyclerAdapter<IpqcCommonResult.Lot2RcardListBean>(this, mDatas) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_check_appearance_product_info;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, IpqcCommonResult.Lot2RcardListBean item) {
                        holder.setTextView(R.id.tv_product_serial_no, item.getRCard());
                        holder.setTextView(R.id.tv_product_status, item.getStatus());
                    }
                };
                rlvProduct.setAdapter(adapter);
                rlvProduct.setLayoutManager(new LinearLayoutManager(this));
            } else {
                adapter.notifyDataSetChanged();
            }
            /**
             * 计算实际样本，合格，不合格样本
             */
            int actualQty = o.getLot2RcardList().size();
            int passQty = 0;
            int unpassQty = 0;
            for (int i = 0; i < actualQty; i++) {
                if (o.getLot2RcardList().get(i).getStatus().equals("GOOD")) {
                    passQty = passQty + 1;
                } else {
                    unpassQty = unpassQty + 1;
                }
            }
            setTextViewContent(tvPassSample, passQty);
            setTextViewContent(tvUnpassSample, unpassQty);
            setTextViewContent(tvTimeActualSample, actualQty);
        }

        /**
         * 设置工序
         */
        for (int i = 0; i < qualityProcesses.size(); i++) {
            if (o.getProcessCode().equals(qualityProcesses.get(i).getValue())) {
                spinnerProcess.setText(qualityProcesses.get(i).getValue());
                spinnerProcess.setSelectedIndex(i);
                tvProcessName.setText(qualityProcesses.get(i).getDisplayText());
            }
        }
        /**
         * 设置质检名称
         */
        for (int i = 0; i < qualityNames.size(); i++) {
            if (o.getIpqcName().equals(qualityNames.get(i).getValue())) {
                spinnerQualityType.setText(qualityNames.get(i).getValue());
                spinnerQualityType.setSelectedIndex(i);
                tvQualityDes.setText(qualityNames.get(i).getDisplayText());
            }
        }
        /**
         * 设置时段
         */
        for (int i = 0; i < qualityTimes.size(); i++) {
            if (o.getPlanTpCode().equals(qualityTimes.get(i).getValue())) {
                spinnerTimeFrame.setText(qualityTimes.get(i).getValue());
                spinnerTimeFrame.setSelectedIndex(i);
                tvTimeFrameName.setText(qualityTimes.get(i).getDisplayText());
            }
        }
        /**
         * 设置计划时间
         */
        int[] ints = DateUtils.dateExchangeByServerLet(o.getPlanDay());
        getCurrentAndLastDate(ints[0], ints[1], ints[2]);

    }

    @Override
    public void createNewLotNoAsync(IpqcCommonResult o) {
        ToastUtils.showShort(R.string.tip_create_batch_no_success);
        etBatchNo.setText(o.getLotNo());
        setProductSerialNoSelect();
    }

    @Override
    public void getIQPCNameAsync(IpqcCommonResult o) {
        if (null != o.getDpList() && !o.getDpList().isEmpty()) {
            qualityNames = o.getDpList();

            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < qualityNames.size(); i++) {
                strs.add(qualityNames.get(i).getValue());
            }

            spinnerQualityType.setItems(strs);

            dismissLoadingDataDialog();
        }
    }

    @Override
    public void getTimePerodAsync(IpqcCommonResult o) {
        if (null != o.getDpList() && !o.getDpList().isEmpty()) {
            qualityTimes = o.getDpList();

            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < qualityTimes.size(); i++) {
                strs.add(qualityTimes.get(i).getValue());
            }
            spinnerTimeFrame.setItems(strs);
            spinnerTimeFrame.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                    tvTimeFrameName.setText(strs.get(position));
                }
            });
            tvTimeFrameName.setText(strs.get(0));
            dismissLoadingDataDialog();
        }
    }

    @Override
    public void getProcessAsync(IpqcCommonResult o) {
        if (null != o.getDpList() && !o.getDpList().isEmpty()) {
            qualityProcesses = o.getDpList();

            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < qualityProcesses.size(); i++) {
                strs.add(qualityProcesses.get(i).getValue());
            }
            spinnerProcess.setItems(strs);

            dismissLoadingDataDialog();
        }
    }

    @Override
    public void checkRCardInfoAsync(IpqcCommonResult o) {
        setEdittextSelected(etBottomProductSerialNo);
        ToastUtils.showShort(R.string.tip_product_serial_check_success);
        SaveCheckResultRequest resultRequest = new SaveCheckResultRequest();
        resultRequest.setLotNo(etBatchNo.getText().toString().trim());
        resultRequest.setIPQCName(qualityNames.get(spinnerQualityType.getSelectedIndex()).getDisplayText());
        resultRequest.setPlanday(canSelectDate.get(spinnerProjectDate.getSelectedIndex()));
        resultRequest.setRCard(etBottomProductSerialNo.getText().toString().trim());
        resultRequest.setProcessCode(qualityProcesses.get(spinnerProcess.getSelectedIndex()).getValue());
        resultRequest.setPlanTpCode(qualityTimes.get(spinnerTimeFrame.getSelectedIndex()).getValue());

        Intent intent = new Intent(this, CheckResultActivity.class);
        intent.putExtra(Constants.QUALITY_APPEARANCE_BEAN, new Gson().toJson(resultRequest));
        startActivity(intent);
    }

    @Override
    public void setBatchNoSelect() {
        setEdittextSelected(etBatchNo);
    }

    @Override
    public void setProductSerialNoSelect() {
        etBottomProductSerialNo.setText("");
        setEdittextSelected(etBottomProductSerialNo);
    }

    @Override
    public void ipacLotPassAsync(IpqcCommonResult o) {
        ToastUtils.showShort(o.getResultMessages().get(0).getMessageText());
        etBatchNo.setText("");
        setEdittextSelected(etBatchNo);
    }

    @Override
    public void ipqcLotRejectAsync(IpqcCommonResult o) {
        ToastUtils.showShort(o.getResultMessages().get(0).getMessageText());
        etBatchNo.setText("");
        setEdittextSelected(etBatchNo);
    }

    @Override
    public void calculateCheckCountAsync(IpqcCommonResult o) {
        setTextViewContent(tvQualityTotal, o.getTotalCount());

    }

    /**
     * 隐藏初始化数据的加载框
     */
    private void dismissLoadingDataDialog() {
        if (null != qualityProcesses && null != qualityTimes && null != qualityNames) {
            dismisProgressDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshCheckAppearanceData(CheckAppearanceEvent event) {
        LogUitls.e("更新数据-->", "更新了数据");
        showProgressDialog();
        getPresenter().getLotInfoAsync(etBatchNo.getText().toString().trim());
        getView().setProductSerialNoSelect();
    }

    @OnClick(R.id.btn_mark_total)
    public void onViewClicked() {
        showProgressDialog();
        CalculateCheckCountRequest request = new CalculateCheckCountRequest();
        request.setPlanDate(canSelectDate.get(spinnerProjectDate.getSelectedIndex()));
        request.setProcess(qualityProcesses.get(spinnerProcess.getSelectedIndex()).getValue());
        request.setTimePerod(qualityTimes.get(spinnerTimeFrame.getSelectedIndex()).getValue());
        getPresenter().calculateCheckCountAsync(request);
    }
}
