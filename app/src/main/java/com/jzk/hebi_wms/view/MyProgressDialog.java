package com.jzk.hebi_wms.view;

import android.app.Activity;
import android.content.Context;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.utils.LogUitls;


/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 11:21
 */

public class MyProgressDialog  extends MyDialog {
    private static volatile MyProgressDialog instance = null;

    private static MyProgressDialog getInstance(Context context) {
        if (null == instance) {
            synchronized (MyProgressDialog.class) {
                if (null == instance) {
                    instance = new MyProgressDialog(context, R.layout.dialog_progress);
                    instance.setCantCancelByBackPress();
                    instance.setCanceledOnTouchOutside(false);
                }
            }
        }
        return instance;
    }

    private MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 显示进度条
     */
    public static void showProgressDialog(Activity context) {
        if (null == instance) {
            getInstance(context);
        }
        /**
         * 解决切换 activity dialog 崩溃
         */
        if(!instance.getContext().equals(context)){
            instance=null;
            getInstance(context);
        }
        if(!instance.isShowing()){
            instance.show();
//            LogUitls.e("显示Dialog--->",context.getPackageName());
        }
    }

    /**
     * 隐藏进度条
     */
    public static void hideProgressDialog() {
        if(null!=instance&&instance.isShowing()){
            LogUitls.e("隐藏Dialog--->",instance.getContext().getPackageName());
            instance.dismiss();
            instance=null;
        }
    }
}
