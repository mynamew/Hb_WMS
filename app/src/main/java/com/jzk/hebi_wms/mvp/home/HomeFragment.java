package com.jzk.hebi_wms.mvp.home;

import android.content.Intent;
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
import com.jzk.hebi_wms.http.message.BaseMessage;
import com.jzk.hebi_wms.http.message.event.HomeEvent;
import com.jzk.hebi_wms.mvp.Polishing.PolishingActivity;
import com.jzk.hebi_wms.mvp.cnc.CNC1Activity;
import com.jzk.hebi_wms.mvp.inject_mold.InjectMoldActivity;
import com.jzk.hebi_wms.mvp.station.StationSelectActivity;
import com.jzk.hebi_wms.utils.SpUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
                case Constants.PERMISSION_CNC2:
                    it.setClass(Objects.requireNonNull(getActivity()), CNC1Activity.class);
                    break;
                case Constants.PERMISSION_POLISH:
                    it.setClass(Objects.requireNonNull(getActivity()), PolishingActivity.class);
                    break;
                default:
                    break;
            }
            getActivity().startActivity(it);
        });
    }

    /**
     * 处理权限数据
     */
    private void permissionDataDeal() {
        //获取登录实体中的权限内容
        Map<String, String> codePermission = new HashMap<>();
        String loginBeanStr = SpUtils.getInstance().getLoginBeanStr();
        LoginBean loginBean = new Gson().fromJson(loginBeanStr, LoginBean.class);
        List<LoginBean.GrantPermissionBean.ChildPermissionsBeanXX> childPermissions1 = loginBean.getGrantPermission().getChildPermissions();
        if (null != childPermissions1 && !childPermissions1.isEmpty()) {
            /**
             * 获取生产计划数据采集的权限
             */
            List<LoginBean.GrantPermissionBean.ChildPermissionsBeanXX.ChildPermissionsBeanX.ChildPermissionsBean> childPermissions = childPermissions1.get(1).getChildPermissions().get(0).getChildPermissions();
            if (null != childPermissions && !childPermissions.isEmpty()) {
                for (int i = 0; i < childPermissions.size(); i++) {
                    LoginBean.GrantPermissionBean.ChildPermissionsBeanXX.ChildPermissionsBeanX.ChildPermissionsBean childPermissionsBean = childPermissions.get(i);
                    switch (childPermissionsBean.getPermissionCode()) {
                        case Constants.PERMISSION_SUPPLY:
                            codePermission.put(Constants.PERMISSION_SUPPLY, childPermissionsBean.getPermissionName());
                            break;
                        case Constants.PERMISSION_INJECT:
                            codePermission.put(Constants.PERMISSION_INJECT, childPermissionsBean.getPermissionName());
                            break;
                        case Constants.PERMISSION_CNC1:
                            codePermission.put(Constants.PERMISSION_CNC1, childPermissionsBean.getPermissionName());
                            break;
                        case Constants.PERMISSION_CNC2:
                            codePermission.put(Constants.PERMISSION_CNC2, childPermissionsBean.getPermissionName());
                            break;
                        case Constants.PERMISSION_POLISH:
                            codePermission.put(Constants.PERMISSION_POLISH, childPermissionsBean.getPermissionName());
                            break;
                        default:
                            break;
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
                R.mipmap.home_polish};
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
}
