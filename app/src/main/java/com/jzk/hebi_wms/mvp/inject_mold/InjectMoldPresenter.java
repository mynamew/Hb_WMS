package com.jzk.hebi_wms.mvp.inject_mold;

import android.content.Context;

import com.jzk.hebi_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.hebi_wms.data.inject.CheckRCardInfoRquest;
import com.jzk.hebi_wms.data.inject.InjectMouldCommitRequest;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.http.callback.OnResultCallBack;
import com.jzk.hebi_wms.http.subscriber.HttpSubscriber;

/**
 * @author: timi
 * create at: 2018/7/20 10:18
 */
public class InjectMoldPresenter extends MvpBasePresenter<InjectMoldView> {

    private final InjectMoldModel model;
    private HttpSubscriber<StationBean> stationBeanHttpSubscriber;
    private HttpSubscriber<InjectMoldBean> injectMoldBeanHttpSubscriber;
    private HttpSubscriber<InjectMoldBean> mouldHttpSubscriber;
    /**
     * 校验的观察者
     */
    private HttpSubscriber<InjectPassBean> injectPassBeanHttpSubscriber;
    /**
     * 不良代码组的观察者
     */
    private HttpSubscriber<InjectPassBean> errorGroupHttpSubscriber;
    /**
     * 根据不良代码组获取不良代码
     */
    private HttpSubscriber<InjectPassBean> errorCodeHttpSubscriber;

    /**
     * 根据输入的不良代码
     */
    private HttpSubscriber<InjectPassBean> errorCodeByInputHttpSubscriber;
    /**
     * 注塑过站提交
     */
    private HttpSubscriber<InjectPassBean> injectPassCommitSubscriber;

    public InjectMoldPresenter(Context context) {
        super(context);
        model = new InjectMoldModel();
    }


    /**
     * 获取工位
     *
     * @param request
     */
    public void getStations(StationRequest request) {
        if (null == stationBeanHttpSubscriber) {
            stationBeanHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<StationBean>() {
                @Override
                public void onSuccess(StationBean o) {
                    getView().getStations(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getStations(request, stationBeanHttpSubscriber);
    }

    /**
     * 获取注塑机
     */
    public void getInjectionMoldings() {
        if (null == injectMoldBeanHttpSubscriber) {
            injectMoldBeanHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<InjectMoldBean>() {
                @Override
                public void onSuccess(InjectMoldBean o) {
                    getView().getInjectionMoldings(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getInjectionMoldings(injectMoldBeanHttpSubscriber);
    }

    /**
     * 获取模具
     */
    public void getMould() {
        if (null == mouldHttpSubscriber) {
            mouldHttpSubscriber = new HttpSubscriber<>(false, new OnResultCallBack<InjectMoldBean>() {
                @Override
                public void onSuccess(InjectMoldBean o) {
                    getView().getMould(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getMould(mouldHttpSubscriber);
    }

    /**
     * 校验
     */
    public void checkRCardInfoAsync(CheckRCardInfoRquest request) {
        if (null == injectPassBeanHttpSubscriber) {
            injectPassBeanHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<InjectPassBean>() {
                @Override
                public void onSuccess(InjectPassBean o) {
                    getView().checkRCardInfoAsync(o);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().checkRCardInfoAsyncFalse();
                    getView().dismisProgressDialog();
                }
            });
        }
        model.checkRCardInfoAsync(injectPassBeanHttpSubscriber, request);
    }

    /**
     * 获取不良代码组
     */
    public void getErrorGroups(int categoryId) {
        if (null == errorGroupHttpSubscriber) {
            errorGroupHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<InjectPassBean>() {
                @Override
                public void onSuccess(InjectPassBean o) {
                    getView().errorGroupHttpSubscriber(o.getErrorGroups());
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getErrorInfosAsync(errorGroupHttpSubscriber, categoryId);
    }

    /**
     * 获取不良代码！
     */
    public void getErrorInfoByGroupCode(String errorGroupId) {
        if (null == errorCodeHttpSubscriber) {
            errorCodeHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectPassBean>() {
                @Override
                public void onSuccess(InjectPassBean o) {
                    getView().dismisProgressDialog();
                    getView().getErrorInfoByGroupCode(o.getErrorCodes());
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getErrorInfoByGroupCodeAsync(errorCodeHttpSubscriber, errorGroupId);
    }

    /**
     * 根据输入获取不良代码组
     */
    public void getErrorInfoByErrorCodeAsync(int categoryId, String errorCode) {
        if (null == errorCodeByInputHttpSubscriber) {
            errorCodeByInputHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectPassBean>() {
                @Override
                public void onSuccess(InjectPassBean o) {
                    getView().getErrorInfoByErrorCodeAsync(o.getErrorInfo());
                }

                @Override
                public void onError(String errorMsg) {
//                    InjectPassBean bean = new InjectPassBean();
//                    bean.setCategoryId(1);
//                    InjectPassBean.ErrorInfo info=new InjectPassBean.ErrorInfo();
//                    info.setErrorCode("2313");
//                    bean.setErrorInfo(info);
//                    getView().getErrorInfoByGroupCode(bean.getErrorCodes());
                }
            });
        }
        model.getErrorInfoByErrorCodeAsync(errorCodeByInputHttpSubscriber, categoryId, errorCode);
    }

    /**
     * 注塑过站提交
     *
     * @param request
     */
    public void collectionMoldingAsync(InjectMouldCommitRequest request) {
        if (null == injectPassCommitSubscriber) {
            injectPassCommitSubscriber = new HttpSubscriber<>(new OnResultCallBack<InjectPassBean>() {
                @Override
                public void onSuccess(InjectPassBean o) {
                    getView().dismisProgressDialog();
                    getView().collectionMoldingAsync(o);
                    getView().setBarcodeSelected();
                }

                @Override
                public void onError(String errorMsg) {
                    getView().dismisProgressDialog();
                    getView().setBarcodeSelected();
                }
            });
        }
        model.collectionMoldingAsync(injectPassCommitSubscriber, request);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != injectPassCommitSubscriber) {
            injectPassCommitSubscriber.unSubscribe();
            injectPassCommitSubscriber = null;
        }
        if (null != errorCodeByInputHttpSubscriber) {
            errorCodeByInputHttpSubscriber.unSubscribe();
            errorCodeByInputHttpSubscriber = null;
        }
        if (null != errorCodeHttpSubscriber) {
            errorCodeHttpSubscriber.unSubscribe();
            errorCodeHttpSubscriber = null;
        }
        if (null != errorGroupHttpSubscriber) {
            errorGroupHttpSubscriber.unSubscribe();
            errorGroupHttpSubscriber = null;
        }
        if (null != injectPassBeanHttpSubscriber) {
            injectPassBeanHttpSubscriber.unSubscribe();
            injectPassBeanHttpSubscriber = null;
        }
        if (null != mouldHttpSubscriber) {
            mouldHttpSubscriber.unSubscribe();
            mouldHttpSubscriber = null;
        }
        if (null != injectMoldBeanHttpSubscriber) {
            injectMoldBeanHttpSubscriber.unSubscribe();
            injectMoldBeanHttpSubscriber = null;
        }
        if (null != stationBeanHttpSubscriber) {
            stationBeanHttpSubscriber.unSubscribe();
            stationBeanHttpSubscriber = null;
        }
    }
}
