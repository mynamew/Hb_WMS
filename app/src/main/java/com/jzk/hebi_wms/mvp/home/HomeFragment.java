package com.jzk.hebi_wms.mvp.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseFragment;
import com.jzk.hebi_wms.http.message.BaseMessage;
import com.jzk.hebi_wms.http.message.event.HomeEvent;
import com.jzk.hebi_wms.mvp.process.ProcessSelectActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页的碎片
 * author: timi
 * create at: 2017-08-17 11:30
 */
public class HomeFragment extends BaseFragment<HomeFragmentView, HomeFragmentPresenter> implements HomeFragmentView {
    @BindView(R.id.tv_home_inject_mold)
    TextView tvHomeInjectMold;
    @BindView(R.id.tv_home_process)
    TextView tvHomeProcess;
    @BindView(R.id.tv_home_aplly_material)
    TextView tvHomeApllyMaterial;
    @BindView(R.id.tv_home_other1)
    TextView tvHomeOther1;
    @BindView(R.id.tv_home_other2)
    TextView tvHomeOther2;

    @Override
    public HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter(getActivity());
    }

    @Override
    public HomeFragmentView createView() {
        return this;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initBundle() {
        BaseMessage.register(this);
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    /**
     * 接受语言改变的事件 更改文字
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageLanguageUpdata(HomeEvent event) {
        tvHomeInjectMold.setText(getResources().getString(R.string.inject_mold));
        tvHomeApllyMaterial.setText(getResources().getString(R.string.aplly_material));
        tvHomeProcess.setText(getResources().getString(R.string.home_process_select));
    }


    @OnClick({R.id.tv_home_process, R.id.tv_home_inject_mold, R.id.tv_home_aplly_material, R.id.tv_home_other1, R.id.tv_home_other2})
    public void onViewClicked(View view) {
        Intent it = new Intent();
        switch (view.getId()) {
            case R.id.tv_home_process:
                it.setClass(getActivity(), ProcessSelectActivity.class);
                break;
            case R.id.tv_home_inject_mold:
                break;
            case R.id.tv_home_aplly_material:
                break;
            case R.id.tv_home_other1:
                break;
            case R.id.tv_home_other2:
                break;
            default:
                break;
        }
        getActivity().startActivity(it);
    }
}
