package com.jzk.hebi_wms.mvp.ipqc.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.base.adapter.BaseRecyclerAdapter;
import com.jzk.hebi_wms.base.adapter.RecyclerViewHolder;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcProcessResult;
import com.jzk.hebi_wms.data.ipqc.record.IpqcRecordRequest;
import com.jzk.hebi_wms.data.ipqc.record.IpqcRecordResult;
import com.jzk.hebi_wms.utils.CommonDialogUtils;
import com.jzk.hebi_wms.utils.DateUtils;
import com.jzk.hebi_wms.utils.LogUitls;
import com.jzk.hebi_wms.view.MyDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class IpqcRecordActivity extends BaseActivity<IpqcRecordView, IpqcRecordPresenter> implements IpqcRecordView {


    @BindView(R.id.rlv_ipqc_record)
    RecyclerView rlvIpqcRecord;
    /**
     * 查询的弹出框
     */
    MyDialog myQueryConditionDialog;
    /**
     * 工序
     */
    private MaterialSpinner spinnerProcess;
    List<IpqcProcessResult.DpListBean> mData = new ArrayList<>();
    /********工单***********************************************************************************************/
    /**
     * 工单
     */
    private MaterialSpinner spinnerTime;
    List<IpqcCommonResult.DpListBean> qualityTimes = new ArrayList<>();
    /**
     * 批次号
     */
    EditText etBatchNo;
    /**
     * 适配器及数据源
     */
    BaseRecyclerAdapter<IpqcRecordResult.IPQCRecordListBean> adapter;
    List<IpqcRecordResult.IPQCRecordListBean> mRecordList = new ArrayList<>();

    IpqcRecordResult mIpqcRecordResult;
    List<IpqcRecordResult.IPQCRcardListBean> mRecardList = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_ipqc_record;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_check_record);
    }

    @Override
    public void initView() {
        myQueryConditionDialog = new MyDialog(IpqcRecordActivity.this, R.layout.dialog_ipqc_record_query);
        spinnerProcess = myQueryConditionDialog.findViewById(R.id.spinner_process);
        spinnerTime = myQueryConditionDialog.findViewById(R.id.spinner_time_frame);
        etBatchNo = myQueryConditionDialog.findViewById(R.id.et_batch_no);
        myQueryConditionDialog.setTextViewListener(R.id.tv_start_time, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(final MyDialog dialog) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        (view, year, monthOfYear, dayOfMonth, yearEnd, monthOfYearEnd, dayOfMonthEnd) -> {
                            /**
                             * 选择 开始和结束时间的返回
                             */
                            String date = "You picked the following date: From- " + dayOfMonth + "/" + (++monthOfYear) + "/" + year + " To " + dayOfMonthEnd + "/" + (++monthOfYearEnd) + "/" + yearEnd;
                            LogUitls.e("时间--->", date);
                            myQueryConditionDialog.getTextView(R.id.tv_start_time).setText(DateUtils.dateStr2CommonDateStr(year,monthOfYear,dayOfMonth));
                            myQueryConditionDialog.getTextView(R.id.tv_end_time).setText(DateUtils.dateStr2CommonDateStr(yearEnd,monthOfYearEnd,dayOfMonthEnd));

                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAutoHighlight(true);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        myQueryConditionDialog.setImageViewListener(R.id.iv_batch_scan, dialog
                -> scan(Constants.REQUEST_SCAN_CODE_BATCH_NO, (requestCode, result)
                        -> etBatchNo.setText(result)));
        setEdittextListener(etBatchNo, Constants.REQUEST_SCAN_CODE_BATCH_NO,
                R.string.input_batch_no_quality, 0, result -> {

        });
        /**
         * 设置默认当前时间
         */
        myQueryConditionDialog.getTextView(R.id.tv_start_time).setText(DateUtils.ms2DateOnlyDay(System.currentTimeMillis()));
        myQueryConditionDialog.getTextView(R.id.tv_end_time).setText(DateUtils.ms2DateOnlyDay(System.currentTimeMillis()));
        /**
         * 选择时间弹出框
         */
        myQueryConditionDialog.setTextViewListener(R.id.tv_end_time,
                dialog -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    (view, year, monthOfYear, dayOfMonth, yearEnd, monthOfYearEnd, dayOfMonthEnd) -> {
                        /**
                         * 选择 开始和结束时间的返回
                         */
                        String date = "You picked the following date: From- " + dayOfMonth + "/" + (++monthOfYear) + "/" + year + " To " + dayOfMonthEnd + "/" + (++monthOfYearEnd) + "/" + yearEnd;
                        LogUitls.e("时间--->", date);
                        myQueryConditionDialog.getTextView(R.id.tv_start_time).setText(DateUtils.dateStr2CommonDateStr(year,monthOfYear,dayOfMonth));
                        myQueryConditionDialog.getTextView(R.id.tv_end_time).setText(DateUtils.dateStr2CommonDateStr(yearEnd,monthOfYearEnd,dayOfMonthEnd));

                    },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.setAutoHighlight(true);
            dpd.show(getFragmentManager(), "Datepickerdialog");
        });
        myQueryConditionDialog.setViewListener(R.id.view_left,
                dialog -> dialog.dismiss())
                .setButtonListener(R.id.btn_cancel, null,
                        dialog -> dialog.dismiss())
                .setButtonListener(R.id.btn_query, null, dialog -> {
                    dialog.dismiss();
                    IpqcRecordRequest recordRequest = new IpqcRecordRequest();
                    recordRequest.setLotNo(etBatchNo.getText().toString().trim());
                    recordRequest.setPlanDateStart( myQueryConditionDialog.getTextView(R.id.tv_start_time).getText().toString().trim());
                    recordRequest.setPlanDateEnd(myQueryConditionDialog.getTextView(R.id.tv_end_time).getText().toString().trim());
                    recordRequest.setProcess(mData.get(spinnerProcess.getSelectedIndex()).getValue());
                    recordRequest.setTimePerod(qualityTimes.get(spinnerTime.getSelectedIndex()).getValue());
                    showProgressDialog();
                    getPresenter().getIPQCInfoAsync(recordRequest);
                });
        setRightImg(R.mipmap.quatily_fliter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myQueryConditionDialog.show();
            }
        });
    }

    @Override
    public void initData() {
        showProgressDialog();
        //工序和时段
        getPresenter().getTimePerodAsync();
        getPresenter().getProcessSelectSubscriber();
    }

    @Override
    public IpqcRecordPresenter createPresenter() {
        return new IpqcRecordPresenter(this);
    }

    @Override
    public IpqcRecordView createView() {
        return this;
    }

    @Override
    public void getTimePerodAsync(IpqcCommonResult o) {
        if (null != o.getDpList() && !o.getDpList().isEmpty()) {
            IpqcCommonResult.DpListBean dpListBean = new IpqcCommonResult.DpListBean();
            dpListBean.setDisplayText(getString(R.string.please_select));
            dpListBean.setValue("");
            qualityTimes.add(dpListBean);
            qualityTimes.addAll( o.getDpList());

            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < qualityTimes.size(); i++) {
                strs.add(qualityTimes.get(i).getDisplayText());
            }
            spinnerTime.setItems(strs);
        } else {
            spinnerProcess.setText(R.string.tip_no_time);
        }
        //隐藏加载框
        dismissProgressDialog();
    }

    @Override
    public void getProcessSelectSubscriber(IpqcProcessResult data) {
        if (null == data.getDpList() || data.getDpList().isEmpty()) {
            spinnerProcess.setText(R.string.no_process);
        } else {
            mData.clear();
            IpqcProcessResult.DpListBean dpListBean = new IpqcProcessResult.DpListBean();
            dpListBean.setDisplayText(getString(R.string.please_select));
            dpListBean.setValue("");
            mData.add(dpListBean);
            mData.addAll(data.getDpList());
            List<String> mStrs = new ArrayList<>();
            for (int i = 0; i < mData.size(); i++) {
                mStrs.add(mData.get(i).getDisplayText());
            }
            spinnerProcess.setItems(mStrs);
        }
        //隐藏加载框
        dismissProgressDialog();
    }

    @Override
    public void getIPQCInfoAsync(IpqcRecordResult o) {
        if (null != o&&!o.getIPQCRecordList().isEmpty()&&!o.getIPQCRcardList().isEmpty()) {
            mIpqcRecordResult = o;
            mRecordList.clear();
            mRecordList.addAll(mIpqcRecordResult.getIPQCRecordList());
            mRecardList.clear();
            mRecardList.addAll(mIpqcRecordResult.getIPQCRcardList());
            if (null == adapter) {
                adapter = new BaseRecyclerAdapter<IpqcRecordResult.IPQCRecordListBean>(this, mRecordList) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_ipqc_record;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, IpqcRecordResult.IPQCRecordListBean item) {
                        holder.setTextView(R.id.tv_process_code, item.getProcessCode());
                        int[] ints = DateUtils.dateExchangeByServerLet(Integer.parseInt(item.getPlanDay()));
                        String time = DateUtils.dateStr2CommonDateStr(ints[0], ints[1], ints[2]);
                        holder.setTextView(R.id.tv_ipqc_date, time);
                        holder.setTextView(R.id.tv_time_frame_name, item.getPlanTpCode());
                        holder.setTextView(R.id.tv_ipqc_batch, item.getLotNo());
                        for (int i = 0; i <mRecardList.size() ; i++) {
                            IpqcRecordResult.IPQCRcardListBean ipqcRcardListBean = mRecardList.get(i);
                            if(ipqcRcardListBean.getLotNo().equals(item.getLotNo())){
                                boolean good = ipqcRcardListBean.getStatus().equals("GOOD");
                                holder.setTextView(R.id.tv_ipqc_result,good?getString(R.string.pass):getString(R.string.unpass));
                                holder.getTextView(R.id.tv_ipqc_result)
                                        .setTextColor(good?getResources().getColor(R.color.colorPrimary)
                                                :getResources().getColor(R.color.red));
                                holder.setTextView(R.id.tv_status,ipqcRcardListBean.getStatus());

                            }
                        }
                    }
                };
                rlvIpqcRecord.setAdapter(adapter);
                rlvIpqcRecord.setLayoutManager(new LinearLayoutManager(this));
            } else {
                adapter.notifyDataSetChanged();
            }
        } else {
            CommonDialogUtils.showErrorTipDialog(this,getString(R.string.query_tip),getString(R.string.tip_no_check_record));
        }
    }

    /**
     * 判断是否隐藏加载框
     */
    public void dismissProgressDialog() {
        if (!mData.isEmpty() && !qualityTimes.isEmpty()) {
            dismisProgressDialog();
        }
    }
}
