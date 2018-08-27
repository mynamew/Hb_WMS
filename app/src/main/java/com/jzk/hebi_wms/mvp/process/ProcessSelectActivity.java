package com.jzk.hebi_wms.mvp.process;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.data.process.ProcessSelectBean;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jzk.hebi_wms.base.Constants.USER_RESET_INJECT;

/**
 * 工序选择
 * author: timi
 * create at: 2018/7/19 14:36
 */
public class ProcessSelectActivity extends BaseActivity<ProcessSelectView, ProcessSelectPresenter> implements ProcessSelectView {

    List<ProcessSelectBean> mData = new ArrayList<>();
    List<String> mProcesses = new ArrayList<>();
    @BindView(R.id.spinner_process)
    MaterialSpinner spinnerProcess;
    @BindView(R.id.btn_process_select)
    TextView btnProcessSelect;
    @BindView(R.id.ll_process_unselected)
    LinearLayout llProcessUnselected;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.ll_process_selected)
    LinearLayout llProcessSelected;

    //工序
    String processSelect = "";
    @BindView(R.id.cb_reset_status)
    CheckBox cbResetStatus;

    @Override
    public int setLayoutId() {
        return R.layout.activity_process_select;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_process_select);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        /**
         * 根据是否选择过工序显示不同的提示
         */
        processSelect = SpUtils.getInstance().getProcessSelect();
        if (TextUtils.isEmpty(processSelect)) {
            llProcessUnselected.setVisibility(View.VISIBLE);
            llProcessSelected.setVisibility(View.GONE);
        } else {
            llProcessUnselected.setVisibility(View.GONE);
            llProcessSelected.setVisibility(View.VISIBLE);
        }
        showProgressDialog();
        getPresenter().getProcessSelectSubscriber();
        boolean isResetInject=SpUtils.getInstance().getBoolean(USER_RESET_INJECT);
        cbResetStatus.setChecked(isResetInject);
    }

    @Override
    public ProcessSelectPresenter createPresenter() {
        return new ProcessSelectPresenter(this);
    }

    @Override
    public ProcessSelectView createView() {
        return this;
    }

    @Override
    public void getProcessSelectSubscriber(List<ProcessSelectBean> data) {
        if (null == data || data.isEmpty()) {
            spinnerProcess.setText(R.string.no_process);
        } else {
            mData.clear();
            mData.addAll(data);
            mProcesses.clear();
            int spinnerPosition = 0;
            for (int i = 0; i < data.size(); i++) {
                mProcesses.add(data.get(i).getProcessName());
                /**
                 * 设置选中的位置
                 */
                if (processSelect.equals(data.get(i).getProcessName())) {
                    spinnerPosition = i;

                }
            }
            spinnerProcess.setItems(mProcesses);
            /**
             * 设置位置和内容
             */
            spinnerProcess.setSelectedIndex(spinnerPosition);
            tvTip.setText(data.get(spinnerPosition).getProcessName());
            /**
             * 设置监听器
             */
            spinnerProcess.setOnItemSelectedListener((view, position, id, item) -> {
                //设置文字
                processSelect = mProcesses.get(position);
                tvTip.setText(processSelect);
                llProcessUnselected.setVisibility(View.GONE);
                llProcessSelected.setVisibility(View.VISIBLE);
            });
        }
    }


    @OnClick(R.id.btn_process_select)
    public void onViewClicked() {
        if (null == mData || mData.isEmpty()) {
            ToastUtils.showShort(R.string.no_process_no_commit);
            return;
        }
        SpUtils.getInstance().putProcessSelect(mData.get(spinnerProcess.getSelectedIndex()).getProcessName());
        SpUtils.getInstance().putProcessSelectCode(mData.get(spinnerProcess.getSelectedIndex()).getProcessCode());
        SpUtils.getInstance().putBoolean(USER_RESET_INJECT,cbResetStatus.isChecked());
        ToastUtils.showShort(R.string.commit_success);
        onBackPressed();
    }
}
