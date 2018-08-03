package com.jzk.hebi_wms.mvp.oqc.ticket;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;

public class OqcJobTocketPresenter extends MvpBasePresenter<OqcJobTocketView> {
    OqcJobTocketModel model;

    public OqcJobTocketPresenter(Context context) {
        super(context);
        model = new OqcJobTocketModel();
    }
}
