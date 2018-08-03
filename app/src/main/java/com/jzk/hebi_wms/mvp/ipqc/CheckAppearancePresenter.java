package com.jzk.hebi_wms.mvp.ipqc;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;

public class CheckAppearancePresenter extends MvpBasePresenter<CheckAppearanceView> {
    CheckAppearanceModel model;

    public CheckAppearancePresenter(Context context) {
        super(context);
        model = new CheckAppearanceModel();
    }
}
