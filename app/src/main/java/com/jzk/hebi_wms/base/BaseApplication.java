package com.jzk.hebi_wms.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.jzk.hebi_wms.utils.LanguageUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * base  appliacation
 */

public class BaseApplication extends MultiDexApplication {
    public static Application mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //初始化 Log
        Logger.addLogAdapter(new AndroidLogAdapter());
//        LogUitls.d("程序是否是 debug状态--->"+IS_DEBUG);
        LanguageUtils.switchAppLanguage(this);
    }


    /**
     * 获取 application context
     *
     * @return
     */
    public static Context getMApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
