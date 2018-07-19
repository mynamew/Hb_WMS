package com.jzk.hebi_wms.mvp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.mvp.login.LoginActivity;
import com.jzk.hebi_wms.utils.statusutils.StatusBarUtil;

public class SplashActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //是否是第一次登录  跳转登录还是主页
//            if (!SpUtils.getInstance().getBoolean(Constants.IS_FIRST_LOG)) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            } else {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            }
//            if (SpUtils.getInstance().getBoolean(Constants.IS_FIRST_LOG)) {
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            } else {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            }
            onBackPressed();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTranslucentBackground(this);
        handler.sendEmptyMessageDelayed(0, 2000);
    }
}
