package com.jzk.hebi_wms.mvp.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzk.hebi_wms.MainActivity;
import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseFragment;
import com.jzk.hebi_wms.data.UserInfoBean;
import com.jzk.hebi_wms.http.message.BaseMessage;
import com.jzk.hebi_wms.http.message.event.HomeEvent;
import com.jzk.hebi_wms.mvp.about.AboutActivity;
import com.jzk.hebi_wms.mvp.deviceinfo.DeviceInfoActivity;
import com.jzk.hebi_wms.mvp.login.LoginActivity;
import com.jzk.hebi_wms.mvp.org_change.OrganizationSwitchActivity;
import com.jzk.hebi_wms.mvp.update_password.UpdatePasswordActivity;
import com.jzk.hebi_wms.mvp.userinfo.UserInfoActivity;
import com.jzk.hebi_wms.utils.LanguageUtils;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.view.MyDialog;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * 个人设置的碎片
 * author: timi
 * create at: 2017-08-17 11:34
 */
public class SettingFragment extends BaseFragment<SetFragmentView, SetFragmentPresenter> implements SetFragmentDataCallBack, SetFragmentView {
    @BindView(R.id.iv_set_need_update)
    ImageView ivSetNewVersion;
    @BindView(R.id.tv_set_userinfo)
    TextView tvSetUserinfo;
    @BindView(R.id.tv_set_username)
    TextView tvSetUserName;
    @BindView(R.id.tv_set_deviceinfo)
    TextView tvSetDeviceinfo;
    @BindView(R.id.tv_set_update_psw)
    TextView tvSetUpdatePsw;
    @BindView(R.id.tv_set_language)
    TextView tvSetLanguage;
    @BindView(R.id.tv_set_server)
    TextView tvSetServer;
    @BindView(R.id.tv_set_about)
    TextView tvSetAbout;
    @BindView(R.id.tv_set_update_version)
    TextView tvSetUpdateVersion;
    @BindView(R.id.tv_set_update_team)
    TextView tvSetUpdateTeam;
    @BindView(R.id.tv_set_exit)
    TextView btnSetExit;
    @BindView(R.id.rl_set_update_version)
    RelativeLayout rlSetUpdateVersion;
    private UserInfoBean bean = null;

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_set_update_team, R.id.tv_set_language, R.id.tv_set_exit, R.id.tv_set_server, R.id.btn_set_exit, R.id.rl_set_update_version, R.id.tv_set_userinfo, R.id.tv_set_deviceinfo, R.id.tv_set_update_psw, R.id.tv_set_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_set_userinfo://跳转到用户信息
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.tv_set_deviceinfo://跳转到设备信息
                startActivity(new Intent(getActivity(), DeviceInfoActivity.class));
                break;
            case R.id.tv_set_update_psw://跳转到设置密码
                startActivity(new Intent(getActivity(), UpdatePasswordActivity.class));
                break;
            case R.id.tv_set_about://跳转到关于
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.rl_set_update_version://更新版本

                break;
            case R.id.btn_set_exit://退出登录
                shwoLogoutDialog();
                break;
            case R.id.tv_set_exit://退出登录
                //退出登录 跳转到登录界面
                shwoLogoutDialog();
                break;
            case R.id.tv_set_language://选择语言
                showSelectLanguageDialog(view);
                break;
            case R.id.tv_set_server://服务配置
                shwoServerSetDialog();
                break;
            case R.id.tv_set_update_team://组织切换
                startActivity(new Intent(getActivity(), OrganizationSwitchActivity.class));
                break;
        }
    }


    /**
     * 显示退出登录的Dialog
     */
    private MyDialog mLogoutDialog = null;

    /**
     * 显示退出登录的提示框
     */
    public void shwoLogoutDialog() {
        if (null == mLogoutDialog) {
            mLogoutDialog = new MyDialog(getActivity(), R.layout.dialog_logout)
                    .setButtonListener(R.id.tv_logout_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.tv_logout_confirm, getString(R.string.confirm), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().onBackPressed();
                        }
                    })
                    .setAnimation(R.style.popWindow_animation_push)
                    .setImageViewListener(R.id.iv_close, dialog -> dialog.dismiss());
        }
        mLogoutDialog.show();
    }

    /**
     * 显示服务配置的Dialog
     */
    private MyDialog mServerSetDialog = null;

    /**
     * 显示服务配置的提示框
     */
    public void shwoServerSetDialog() {
        if (null == mServerSetDialog) {
            mServerSetDialog = new MyDialog(getActivity(), R.layout.dialog_logout)
                    .setTextViewContent(R.id.tv_title, getString(R.string.server_set))
                    .setTextViewContent(R.id.tv_content, getString(R.string.reset_server_will_logout))
                    .setButtonListener(R.id.tv_logout_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.tv_logout_confirm, getString(R.string.confirm), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            // TODO: 2017/8/25 做登录的数据的清除工作
                            dialog.dismiss();
                            //显示服务配置弹出框
                            ((MainActivity) getActivity()).jumpToLoginActivity(true);
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mServerSetDialog.show();
    }

    /**
     * 显示下拉框 选择语言
     *
     * @param view
     */
    private MyDialog mSelectLanguageDialog = null;

    public void showSelectLanguageDialog(View view) {
        if (null == mSelectLanguageDialog) {
            mSelectLanguageDialog = new MyDialog(getContext(), R.layout.popwindow_select_language)
                    .setTextViewListener(R.id.tv_language_simple, dialog -> setCurrentActivityLanguage(0))
                    .setTextViewListener(R.id.tv_language_trad, dialog -> setCurrentActivityLanguage(1))
                    .setTextViewListener(R.id.tv_language_en, dialog -> setCurrentActivityLanguage(2)).setAnimation(R.style.popWindow_animation_push);
        }
        mSelectLanguageDialog.setContentViewListener(v -> mSelectLanguageDialog.dismiss());
        mSelectLanguageDialog.show();
    }

    /**
     * 设置当前语言
     *
     * @param index
     */
    private void setCurrentActivityLanguage(int index) {
        switch (index) {
            case 0://简体
                SpUtils.getInstance().putLocaleLanguage("zh-CN");
                break;
            case 1://繁体
                SpUtils.getInstance().putLocaleLanguage("zh-TW");
                break;
            case 2://英文
                SpUtils.getInstance().putLocaleLanguage("en");
                break;
        }
        //存储选择的语言
        LanguageUtils.switchAppLanguage(getActivity());
        //设置弹出框的文字
        mSelectLanguageDialog.getTextView(R.id.tv_language_simple).setText(getResources().getString(R.string.language_simple));
        mSelectLanguageDialog.getTextView(R.id.tv_language_trad).setText(getResources().getString(R.string.language_tradtional));
        mSelectLanguageDialog.getTextView(R.id.tv_language_en).setText(getResources().getString(R.string.language_english));
        //设置 设置界面的文字
        tvSetUserinfo.setText(getResources().getString(R.string.set_userinfo));
        tvSetDeviceinfo.setText(getResources().getString(R.string.set_device_info));
        tvSetUpdatePsw.setText(getResources().getString(R.string.set_update_psw));
        tvSetLanguage.setText(getResources().getString(R.string.set_language));
        tvSetServer.setText(getResources().getString(R.string.server_set));
        tvSetAbout.setText(getResources().getString(R.string.set_about));
        tvSetUpdateVersion.setText(getResources().getString(R.string.set_update_version));
        btnSetExit.setText(getResources().getString(R.string.set_exit));
        tvSetUpdateTeam.setText(getResources().getString(R.string.set_team));
        //发送事件 更新主界面的文字
        BaseMessage.post(new HomeEvent(HomeEvent.LANGUAGE_UPDATE));
        //弹出框消失
        mSelectLanguageDialog.dismiss();
    }

    @Override
    public void setData(UserInfoBean bean) {
        this.bean = bean;
    }
    @Override
    public int setLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initData() {
        if (null != bean) {
            tvSetUserName.setText(bean.userName);
        }
    }

    @Override
    public void initBundle() {

    }

    @Override
    public SetFragmentPresenter createPresenter() {
        return new SetFragmentPresenter(getActivity());
    }

    /**
     * 设置是否需要更新
     *
     * @param isNeedUpdate
     */
    public void needUpdateVersionTip(boolean isNeedUpdate) {
        ivSetNewVersion.setVisibility(isNeedUpdate ? View.VISIBLE : View.GONE);
    }

    @Override
    public SetFragmentView createView() {
        return this;
    }
}
