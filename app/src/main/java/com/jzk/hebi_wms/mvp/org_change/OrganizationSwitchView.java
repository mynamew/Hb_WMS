package com.jzk.hebi_wms.mvp.org_change;

import com.jzk.hebi_wms.base.view.iml.MvpBaseView;
import com.jzk.hebi_wms.data.LoginBean;

/**
 * $dsc
 * author: timi
 * create at: 2018-04-16 09:29
 */

public interface OrganizationSwitchView extends MvpBaseView {
    void changeOrg(LoginBean o);
}
