package com.jzk.hebi_wms.mvp.home;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.base.BaseFragment;
import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.data.LoginBean;
import com.jzk.hebi_wms.data.VersionBean;
import com.jzk.hebi_wms.http.message.BaseMessage;
import com.jzk.hebi_wms.http.message.event.HomeEvent;
import com.jzk.hebi_wms.mvp.Polishing.PolishingActivity;
import com.jzk.hebi_wms.mvp.cnc.CNC1Activity;
import com.jzk.hebi_wms.mvp.inject_mold.InjectMoldActivity;
import com.jzk.hebi_wms.mvp.ipqc.CheckAppearanceActivity;
import com.jzk.hebi_wms.mvp.paint.PaintActivity;
import com.jzk.hebi_wms.mvp.supply.StationSelectActivity;
import com.jzk.hebi_wms.utils.PackageUtils;
import com.jzk.hebi_wms.utils.SDCardUtils;
import com.jzk.hebi_wms.utils.SpUtils;
import com.jzk.hebi_wms.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;

/**
 * 主页的碎片
 * author: timi
 * create at: 2017-08-17 11:30
 */
public class HomeFragment extends BaseFragment<HomeFragmentView, HomeFragmentPresenter> implements HomeFragmentView {
    @BindView(R.id.grid_menu)
    GridView gridMenu;
    @BindView(R.id.rl_no_permission)
    RelativeLayout rlNoPermission;
    @BindView(R.id.tv_main_head)
    TextView tvMainHead;

    private List<Map<String, Object>> mMenusData = new ArrayList<>();
    private SimpleAdapter adapter;

    String[] from = {"img", "text"};

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
        int[] to = {R.id.iv_menu, R.id.tv_menu};
        /**
         * 处理数据
         */
        permissionDataDeal();
        /**
         * 初始化adapter
         */
        adapter = new SimpleAdapter(getActivity(), mMenusData, R.layout.item_home_menu, from, to);
        gridMenu.setAdapter(adapter);

        gridMenu.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            String permissonCode = (String) mMenusData.get(arg2).get("code");
            Intent it = new Intent();
            switch (permissonCode) {
                case Constants.PERMISSION_SUPPLY:
                    it.setClass(Objects.requireNonNull(getActivity()), StationSelectActivity.class);
                    break;
                case Constants.PERMISSION_INJECT:
                    it.setClass(Objects.requireNonNull(getActivity()), InjectMoldActivity.class);
                    break;
                case Constants.PERMISSION_CNC1:
                    it.putExtra("cnc", true);
                    it.setClass(Objects.requireNonNull(getActivity()), CNC1Activity.class);
                    break;
                case Constants.PERMISSION_CNC2:
                    it.putExtra("cnc", false);
                    it.setClass(Objects.requireNonNull(getActivity()), CNC1Activity.class);
                    break;
                case Constants.PERMISSION_POLISH:
                    it.setClass(Objects.requireNonNull(getActivity()), PolishingActivity.class);
                    break;
                case Constants.PERMISSION_QUALITY:
                    it.setClass(Objects.requireNonNull(getActivity()), CheckAppearanceActivity.class);
                    break;
                case Constants.PERMISSION_PAINT:
                    it.setClass(Objects.requireNonNull(getActivity()), PaintActivity.class);
                    break;
                default:
                    break;
            }
            getActivity().startActivity(it);
        });
        /**
         * 获取版本
         */
        showProgressDialog();
        getPresenter().getVersion();
    }

    /**
     * 处理权限数据
     */
    private void permissionDataDeal() {
        //获取登录实体中的权限内容
        Map<String, String> codePermission = new HashMap<>();
        String loginBeanStr = SpUtils.getInstance().getLoginBeanStr();
        LoginBean loginBean = new Gson().fromJson(loginBeanStr, LoginBean.class);
        List<LoginBean.GrantPermissionBean.ChildPermissionsBeanXXX> childPermissions1 = loginBean.getGrantPermission().getChildPermissions();
        if (null != childPermissions1 && !childPermissions1.isEmpty()) {
            for (int i = 0; i < childPermissions1.size(); i++) {
                /**
                 * 获取权限
                 */
                List<LoginBean.GrantPermissionBean.ChildPermissionsBeanXXX.ChildPermissionsBeanXX> childPermissions = childPermissions1.get(i).getChildPermissions();
                for (int j = 0; j < childPermissions.size(); j++) {
                    if (null != childPermissions.get(j).getChildPermissions()) {
                        List<LoginBean.GrantPermissionBean.ChildPermissionsBeanXXX.ChildPermissionsBeanXX.ChildPermissionsBeanX> childPermissions2 = childPermissions.get(j).getChildPermissions();
                        childPermissions.get(j).getChildPermissions();
                        for (int k = 0; k < childPermissions2.size(); k++) {
                            List<LoginBean.GrantPermissionBean.ChildPermissionsBeanXXX.ChildPermissionsBeanXX.ChildPermissionsBeanX.ChildPermissionsBean> childPermissions3 = childPermissions2.get(k).getChildPermissions();
                            if (null != childPermissions3) {
                                for (int l = 0; l < childPermissions3.size(); l++) {
                                    getUserPermission(codePermission, childPermissions3.get(l).getPermissionCode(), childPermissions3.get(l).getPermissionName());
                                }
                            } else {
                                getUserPermission(codePermission, childPermissions2.get(k).getPermissionCode(), childPermissions2.get(k).getPermissionName());
                            }
                        }
                    } else {
                        getUserPermission(codePermission, childPermissions.get(j).getPermissionCode(), childPermissions.get(j).getPermissionName());
                    }
                }
            }

        }
        /**
         * 如果未设置菜单的数据源证明用户还没有权限！则显示没有权限的界面,并返回
         */
        if (null == codePermission || codePermission.isEmpty()) {
            /**
             * 没有权限显示没有权限的界面
             */
            rlNoPermission.setVisibility(View.VISIBLE);
            gridMenu.setVisibility(View.GONE);
            return;
        }
        /**
         * 图标的ids
         */
        int[] imgs = new int[]{
                R.mipmap.home_add_material,
                R.mipmap.home_inject_pass,
                R.mipmap.home_cnc,
                R.mipmap.home_cnc,
                R.mipmap.home_polish,
                R.mipmap.qulity_inspection,
                R.mipmap.query_materail_sn_from
        };
        /**
         * 权限Codes
         */
        String[] menuStrs = new String[]
                {
                        Constants.PERMISSION_SUPPLY,
                        Constants.PERMISSION_INJECT,
                        Constants.PERMISSION_CNC1,
                        Constants.PERMISSION_CNC2,
                        Constants.PERMISSION_POLISH,
                        Constants.PERMISSION_QUALITY,
                        Constants.PERMISSION_PAINT,
                };
        /**
         * 设置菜单的数据源
         */
        for (int i = 0; i < menuStrs.length; i++) {
            //不为空证明有值
            if (!TextUtils.isEmpty(codePermission.get(menuStrs[i]))) {
                Map<String, Object> mMenu = new HashMap<>();
                mMenu.put(from[0], imgs[i]);
                mMenu.put("code", menuStrs[i]);
                mMenu.put(from[1], codePermission.get(menuStrs[i]));
                mMenusData.add(mMenu);
            }
        }
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
        if (null != adapter) {
            permissionDataDeal();
            adapter.notifyDataSetChanged();
        }
        tvMainHead.setText(R.string.home_title);
    }

    /**
     * 获取权限
     *
     * @param codePermission
     * @param permissionCode
     * @param permissionName
     */
    private void getUserPermission(Map<String, String> codePermission, String permissionCode, String permissionName) {
        switch (permissionCode) {
            case Constants.PERMISSION_SUPPLY:
                codePermission.put(Constants.PERMISSION_SUPPLY, permissionName);
                break;
            case Constants.PERMISSION_INJECT:
                codePermission.put(Constants.PERMISSION_INJECT, permissionName);
                break;
            case Constants.PERMISSION_CNC1:
                codePermission.put(Constants.PERMISSION_CNC1, permissionName);
                break;
            case Constants.PERMISSION_CNC2:
                codePermission.put(Constants.PERMISSION_CNC2, permissionName);
                break;
            case Constants.PERMISSION_POLISH:
                codePermission.put(Constants.PERMISSION_POLISH, permissionName);
                break;
            case Constants.PERMISSION_QUALITY:
                codePermission.put(Constants.PERMISSION_QUALITY, permissionName);
                break;
            case Constants.PERMISSION_PAINT:
                codePermission.put(Constants.PERMISSION_PAINT, permissionName);
                break;
            default:
                break;


        }
    }

    @Override
    public void getVersion(VersionBean versionBean) {
        try {
            String versionName = PackageUtils.getVersionName(getActivity());
            //新版本号：需要自己合成
            final String newVersion = versionBean.getVersion() / 100 + "." + versionBean.getVersion() % 100 / 10 + "." + versionBean.getVersion() % 100;

            String updateUrl = SpUtils.getInstance().getBaseUrl() + versionBean.getPath();
            //是否需要版本更新
            if (!versionName.equals(newVersion)) {
                showProgressDialog();
                getPresenter().downLoad(updateUrl, versionBean, newVersion);
            }
            //设置版本更新的标识
            SpUtils.getInstance().putBoolean(Constants.IS_HAVE_DOWNLOAD_NEW, versionName.equals(newVersion));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downLoadApk(File o, VersionBean versionBean, String newVersion) {
        MyDialog myDialog = new MyDialog(getActivity(), R.layout.dialog_update_version)
                .setButtonListener(R.id.versionchecklib_version_dialog_commit, null, new MyDialog.DialogClickListener() {
                    @Override
                    public void dialogClick(MyDialog dialog) {
                        Intent intent = new Intent();
                        // 执行动作
                        intent.setAction(Intent.ACTION_VIEW);
                        File file = new File(SDCardUtils.getAPKPath(getActivity()) + "/" + Constants.APK_NAME);
                        // 执行的数据类型
                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        getActivity().startActivity(intent);

                    }
                });
        /**
         * 是否是强制更新
         */
        if (versionBean.getUpdateMode() == 2) {
            myDialog.setCantCancelByBackPress();
        }
        myDialog.setTextViewContent(R.id.tv_title, newVersion + "版本更新")
                .setTextViewContent(R.id.tv_msg, versionBean.getRemark());
        myDialog.show();
    }
}
