package com.jzk.hebi_wms.mvp.oqc.result;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;

/**
 * 抽检采集的view
 *
 * @author jzk
 * create at: 2018/8/3 11:25
 */
public class CheckResultPresenter extends MvpBasePresenter<CheckResultView> {
    CheckResultModel model;

    public CheckResultPresenter(Context context) {
        super(context);
        model = new CheckResultModel();
    }
}
