package com.jzk.hebi_wms.mvp.home;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;


/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 15:57
 */

public class SetFragmentPresenter extends MvpBasePresenter<SetFragmentView> {
    private SetFragmentMode setFragmentMode;

    public SetFragmentPresenter(Context context) {
        super(context);
        setFragmentMode=new SetFragmentMode();
    }
}
