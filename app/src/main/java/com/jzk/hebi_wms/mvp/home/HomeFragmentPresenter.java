package com.jzk.hebi_wms.mvp.home;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;


/**
 * $dsc
 * author: timi
 * create at: 2018-07-05 08:33
 */
public class HomeFragmentPresenter extends MvpBasePresenter<HomeFragmentView> {
    HomeFragmentModel model;
    public HomeFragmentPresenter(Context context) {
        super(context);
        model=new HomeFragmentModel();
    }
}
