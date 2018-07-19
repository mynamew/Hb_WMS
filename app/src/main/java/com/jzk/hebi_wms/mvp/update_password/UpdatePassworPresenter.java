package com.jzk.hebi_wms.mvp.update_password;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.set.ChangePasswordRequest;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;


/**
 * $dsc
 * author: timi
 * create at: 2017-08-24 17:17
 */

public class UpdatePassworPresenter extends MvpBasePresenter<UpdatePassworView> {
    UpdatePassworModel model=null;
    private HttpSubscriber<Object> changePasswordSubscriber;
    public UpdatePassworPresenter(Context context) {
        super(context);
        model=new UpdatePassworModel();
    }

    /**
     * \更改密码
     * @param request
     */
    public void changePassword(ChangePasswordRequest request){
        getView().showProgressDialog();
        if(null==changePasswordSubscriber){
            changePasswordSubscriber=new HttpSubscriber<Object>(new OnResultCallBack() {
                @Override
                public void onSuccess(Object o) {
                   getView().changePassword();
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.changePassword(request,changePasswordSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if(null!=changePasswordSubscriber){
            changePasswordSubscriber.unSubscribe();
            changePasswordSubscriber=null;
        }
    }
}
