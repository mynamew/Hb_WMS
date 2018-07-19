package com.jzk.hebi_wms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzk.hebi_wms.base.BaseNoMvpActivity;
import com.jzk.hebi_wms.http.message.BaseMessage;
import com.jzk.hebi_wms.http.message.event.HomeEvent;
import com.jzk.hebi_wms.mvp.home.HomeFragment;
import com.jzk.hebi_wms.mvp.home.SettingFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseNoMvpActivity {

    @BindView(R.id.fl_home_content)
    FrameLayout flHomeContent;
    @BindView(R.id.iv_home_img)
    ImageView ivHomeImg;
    @BindView(R.id.iv_home_txt)
    ImageView ivHomeTxt;
    @BindView(R.id.tv_home_set)
    TextView tvHomeSet;
    //data
    private Fragment homeFM;
    private Fragment setFM;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        BaseMessage.register(this);
    }

    @Override
    public void initView() {
        //加载首页
        intentIndex(0);
    }

    @Override
    public void initData() {

    }
    /**
     * 主页下方按钮的点击事件
     *
     * @param index
     */
    private void intentIndex(int index) {
        FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
        hideFragment(trans);
        //设置按钮状态
        setHomeTabCheckStatus(index == 0);
        //首页上方的文字
        switch (index) {
            //主页
            case 0:
                if (homeFM == null) {
                    homeFM = new HomeFragment();
                    trans.add(R.id.fl_home_content, homeFM);
                } else {
                    trans.show(homeFM);
                }
                break;
            //个人设置
            case 1:
                //初始化fragment
                if (setFM == null) {
                    setFM = new SettingFragment();
                    trans.add(R.id.fl_home_content, setFM);
                } else {
                    trans.show(setFM);
                }
                break;

            default:

                break;
        }

        try {
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏
     *
     * @param trans
     */
    private void hideFragment(FragmentTransaction trans) {
        if (homeFM != null) {
            trans.hide(homeFM);
        }
        if (setFM != null) {
            trans.hide(setFM);
        }
    }

    /**
     * 接受语言改变的事件 更改文字
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageLanguageUpdata(HomeEvent event) {
        tvHomeSet.setText(getResources().getString(R.string.home_set));
    }

    /**
     * 设置主页下方tab 的选中状态
     *
     * @param isHome
     */
    public void setHomeTabCheckStatus(boolean isHome) {
        ivHomeImg.setSelected(isHome);
        ivHomeTxt.setSelected(isHome);
        tvHomeSet.setSelected(!isHome);
    }
    @OnClick({R.id.rl_home, R.id.rl_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                intentIndex(0);
                break;
            case R.id.rl_mine:
                intentIndex(1);
                break;
        }
    }

    /**
     * 2次退出
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
