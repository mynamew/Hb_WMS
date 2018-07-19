package com.jzk.hebi_wms.mvp.update_password;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;


import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseActivity;
import com.jzk.hebi_wms.data.set.ChangePasswordRequest;
import com.jzk.hebi_wms.utils.PackageUtils;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.hebi_wms.view.MyDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
  * 更改密码
  * author: timi    
  * create at: 2018/4/2 11:06
  */  
public class UpdatePasswordActivity extends BaseActivity<UpdatePassworView, UpdatePassworPresenter> implements UpdatePassworView {
    @BindView(R.id.et_set_update_password_oldpsw)
    EditText etSetUpdatePasswordOldpsw;
    @BindView(R.id.iv_set_update_password_clear_old_password)
    ImageView ivSetUpdatePasswordClearOldPassword;
    @BindView(R.id.et_set_update_password_newpsw)
    EditText etSetUpdatePasswordNewpsw;
    @BindView(R.id.iv_set_update_password_clear_new_password)
    ImageView ivSetUpdatePasswordClearNewPassword;
    @BindView(R.id.et_set_update_password_repeat)
    EditText etSetUpdatePasswprdRepeat;
    @BindView(R.id.iv_set_update_password_clear_repeat_password)
    ImageView ivSetUpdatePasswordClearRepeatPassword;
    @BindView(R.id.cb_set_update_password_showpsw)
    CheckBox cbSetUpdatePasswordShowpsw;
    @BindView(R.id.btn_set_update_password_confirm)
    Button btnSetUpdatePasswordConfirm;

    @Override
    public int setLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.update_password));
    }

    @Override
    public void initView() {
        etSetUpdatePasswordNewpsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username=s.toString();
                if(!TextUtils.isEmpty(username)){
                    ivSetUpdatePasswordClearNewPassword.setVisibility(View.VISIBLE);
                }else{
                    ivSetUpdatePasswordClearNewPassword.setVisibility(View.GONE);
                }
            }
        });
        etSetUpdatePasswordOldpsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username=s.toString();
                if(!TextUtils.isEmpty(username)){
                    ivSetUpdatePasswordClearOldPassword.setVisibility(View.VISIBLE);
                }else{
                    ivSetUpdatePasswordClearOldPassword.setVisibility(View.GONE);
                }
            }
        });
        etSetUpdatePasswprdRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username=s.toString();
                if(!TextUtils.isEmpty(username)){
                    ivSetUpdatePasswordClearRepeatPassword.setVisibility(View.VISIBLE);
                }else{
                    ivSetUpdatePasswordClearRepeatPassword.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public UpdatePassworPresenter createPresenter() {
        return new UpdatePassworPresenter(this);
    }

    @Override
    public UpdatePassworView createView() {
        return this;
    }

    @OnClick({R.id.iv_set_update_password_clear_old_password, R.id.iv_set_update_password_clear_new_password, R.id.iv_set_update_password_clear_repeat_password, R.id.cb_set_update_password_showpsw, R.id.btn_set_update_password_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_set_update_password_clear_old_password:
                etSetUpdatePasswordOldpsw.setText("");
                view.setVisibility(View.GONE);
                break;
            case R.id.iv_set_update_password_clear_new_password:
                etSetUpdatePasswordNewpsw.setText("");
                view.setVisibility(View.GONE);
                break;
            case R.id.iv_set_update_password_clear_repeat_password:
                etSetUpdatePasswprdRepeat.setText("");
                view.setVisibility(View.GONE);
                break;
            case R.id.cb_set_update_password_showpsw:
                //设置是否显示密码
                if (!((CheckBox)view).isChecked()) {
                    etSetUpdatePasswprdRepeat.setTransformationMethod(PasswordTransformationMethod.getInstance());    //将文本框的内容以密文形式显示
                    etSetUpdatePasswordOldpsw.setTransformationMethod(PasswordTransformationMethod.getInstance());    //将文本框的内容以密文形式显示
                    etSetUpdatePasswordNewpsw.setTransformationMethod(PasswordTransformationMethod.getInstance());    //将文本框的内容以密文形式显示
                } else {
                    etSetUpdatePasswprdRepeat.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // 以明文显示
                    etSetUpdatePasswordOldpsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // 以明文显示
                    etSetUpdatePasswordNewpsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // 以明文显示
                }
                Selection.setSelection(etSetUpdatePasswprdRepeat.getText(), etSetUpdatePasswprdRepeat.getText().length());
                Selection.setSelection(etSetUpdatePasswordOldpsw.getText(), etSetUpdatePasswordOldpsw.getText().length());
                Selection.setSelection(etSetUpdatePasswprdRepeat.getText(), etSetUpdatePasswprdRepeat.getText().length());
                break;
            case R.id.btn_set_update_password_confirm:
                //旧密码
                String oldPswStr=etSetUpdatePasswordOldpsw.getText().toString().trim();
                if(TextUtils.isEmpty(oldPswStr)){
                    ToastUtils.showShort(this,getString(R.string.please_input_old_psw));
                    return;
                }
                //新密码
                String newPswStr=etSetUpdatePasswordNewpsw.getText().toString().trim();
                if(TextUtils.isEmpty(newPswStr)){
                    ToastUtils.showShort(this,getString(R.string.please_input_new_password));
                    return;
                }
                //再次输入密码
                String repeatPswStr=etSetUpdatePasswordNewpsw.getText().toString().trim();
                if(TextUtils.isEmpty(repeatPswStr)){
                    ToastUtils.showShort(this,getString(R.string.please_repeat_input_password));
                    return;
                }
                if(!(repeatPswStr).equals(newPswStr)){
                    ToastUtils.showShort(this,getString(R.string.twice_password_no_same));
                    return;
                }
                ChangePasswordRequest request=new ChangePasswordRequest();
                request.setDeviceType("8");
                request.setMac(PackageUtils.getMac());
                request.setCurrentPassword(oldPswStr);
                request.setNewPassword(newPswStr);
                request.setTenancyName(SpUtils.getInstance().gettenancyName());
                request.setUsernameOrEmailAddress(SpUtils.getInstance().getUserName());
                getPresenter().changePassword(request);
                break;
        }
    }

    @Override
    public void changePassword() {
        /**
         * 显示 错误提示的对话框
         */
        new MyDialog(BaseActivity.getCurrentActivty(), R.layout.dialog_go_login_tip)
                .setButtonListener(R.id.btn_cancel, null, dialog -> closeAndLogin(dialog))
                .setTextViewContent(R.id.tv_content, getString(R.string.change_password_tip))
                .setImageViewListener(R.id.iv_close, dialog -> closeAndLogin(dialog))
                .setCancelByOutside(false)
                .setCantCancelByBackPress()
                .show();
    }

    /**
     * 关闭当前界面并且去登录
     */
    public void closeAndLogin(MyDialog dialog){
        dialog.dismiss();
        finish();
        BaseActivity currentActivty = (BaseActivity) BaseActivity.getCurrentActivty();
        currentActivty.jumpToLoginActivity();
    }
}
