package com.jzk.hebi_wms.mvp.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.data.device.DeviceResponse;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.spinnerlibrary.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备类型的界面
 *
 * @author jzk
 * create at: 2018/8/27 13:04
 */
public class SelectDeviceTypeActivity extends BaseActivity<SelectDeviceTypeView, SelectDeviceTypePresenter> implements SelectDeviceTypeView {
    @BindView(R.id.tv_current_org)
    TextView tvCurrentOrg;
    @BindView(R.id.spinner_device)
    MaterialSpinner spinnerDevice;
    @BindView(R.id.ll_process_unselected)
    LinearLayout llProcessUnselected;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.ll_process_selected)
    LinearLayout llProcessSelected;
    @BindView(R.id.btn_process_select)
    TextView btnProcessSelect;
    private String deviceSelectCode;
    private String deviceSelectName;
    List<DeviceResponse> mData = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_select_device_type;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_select_device);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        deviceSelectCode = SpUtils.getInstance().getDeivceSelectCode();
        deviceSelectName = SpUtils.getInstance().getDeviceSelectName();

        if (TextUtils.isEmpty(deviceSelectCode)) {
            llProcessUnselected.setVisibility(View.VISIBLE);
            llProcessSelected.setVisibility(View.GONE);
        } else {
            llProcessUnselected.setVisibility(View.GONE);
            llProcessSelected.setVisibility(View.VISIBLE);
        }
        showProgressDialog();
        getPresenter().getEquipmentTypeListasync();
    }

    @Override
    public SelectDeviceTypePresenter createPresenter() {
        return new SelectDeviceTypePresenter(this);
    }

    @Override
    public SelectDeviceTypeView createView() {
        return this;
    }

    @Override
    public void getEquipmentTypeListasync(List<DeviceResponse> data) {
        if (null == data || data.isEmpty()) {
            spinnerDevice.setText(R.string.no_process);
        } else {
            mData.clear();
            mData.addAll(data);
            int spinnerPosition = 0;
            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                strs.add(data.get(i).getEquipmentTypeName());
                /**
                 * 设置选中的位置
                 */
                if (deviceSelectCode.equals(data.get(i).getEquipmentTypeCode())) {
                    spinnerPosition = i;

                }
            }
            spinnerDevice.setItems(strs);
            /**
             * 设置位置和内容
             */
            spinnerDevice.setSelectedIndex(spinnerPosition);
            tvTip.setText(data.get(spinnerPosition).getEquipmentTypeName());
            /**
             * 设置监听器
             */
            spinnerDevice.setOnItemSelectedListener((view, position, id, item) -> {
                //设置文字
                deviceSelectName = mData.get(position).getEquipmentTypeName();
                deviceSelectCode = mData.get(position).getEquipmentTypeCode();
                tvTip.setText(deviceSelectName);
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
        SpUtils.getInstance().putDeivceSelectCode(mData.get(spinnerDevice.getSelectedIndex()).getEquipmentTypeCode());
        SpUtils.getInstance().putDeviceSelectName(mData.get(spinnerDevice.getSelectedIndex()).getEquipmentTypeName());
        ToastUtils.showShort(R.string.commit_success);
        onBackPressed();
    }
}
