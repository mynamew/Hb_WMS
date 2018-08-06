package com.jzk.hebi_wms.http.message.event;

import com.jzk.hebi_wms.http.message.BaseEvent;

public class CheckAppearanceEvent extends BaseEvent {
    /**
     * 更新外观抽检的数据
     */
    public static  String REFRESH_CHECK_DATA="REFRESH_CHECK_DATA";
    public CheckAppearanceEvent(String event) {
        super(event);
    }

}
