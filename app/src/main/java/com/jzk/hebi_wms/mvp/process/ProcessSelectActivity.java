package com.jzk.hebi_wms.mvp.process;

import android.os.Bundle;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private int mPosition=0;
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
        showProgressDialog();
        getPresenter().getProcessSelectSubscriber();
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
            for (int i = 0; i < data.size(); i++) {
                mProcesses.add(data.get(i).getProcessName());
            }
            spinnerProcess.setItems(mProcesses);
            spinnerProcess.setOnItemSelectedListener((view, position, id, item) -> {
                //设置文字
                spinnerProcess.setText(mProcesses.get(position));
                //保存位置
                mPosition=position;
            });
        }
    }



    @OnClick(R.id.btn_process_select)
    public void onViewClicked() {
        if(null==mData||mData.isEmpty()){
            ToastUtils.showShort(R.string.no_process_no_commit);
            return;
        }
        SpUtils.getInstance().putProcessSelect(mProcesses.get(mPosition));
        SpUtils.getInstance().putProcessSelectCode(mData.get(mPosition).getProcessCode());
        ToastUtils.showShort(R.string.commit_success);
        onBackPressed();
    }
}
