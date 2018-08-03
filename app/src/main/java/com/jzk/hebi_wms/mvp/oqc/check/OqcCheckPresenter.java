package com.jzk.hebi_wms.mvp.oqc.check;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
/** 
  * 抽检采集呃presenter
  * @author   jzk
  * create at: 2018/8/3 11:03
  */  
public class OqcCheckPresenter extends MvpBasePresenter<OqcCheckView> {
    OqcCheckModel model;

    public OqcCheckPresenter(Context context) {
        super(context);
        model = new OqcCheckModel();
    }
}
