package com.jzk.hebi_wms.data.polishing;

import java.util.List;

/**
 * Created by JuQent on 2018-07-24.
 */

public class PolishResultBean {
    String MOCode;
    String ItemCode;
    String ItemName;
    String ItemStandard;
    String NeedAttachMo;
    String NeedAttachItemCode;
    Boolean IsNeedAttachMO;
    List<String> ResultMessages;

    public String getMOCode() {
        return MOCode;
    }

    public void setMOCode(String MOCode) {
        this.MOCode = MOCode;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemStandard() {
        return ItemStandard;
    }

    public void setItemStandard(String itemStandard) {
        ItemStandard = itemStandard;
    }

    public String getNeedAttachMo() {
        return NeedAttachMo;
    }

    public void setNeedAttachMo(String needAttachMo) {
        NeedAttachMo = needAttachMo;
    }

    public String getNeedAttachItemCode() {
        return NeedAttachItemCode;
    }

    public void setNeedAttachItemCode(String needAttachItemCode) {
        NeedAttachItemCode = needAttachItemCode;
    }

    public Boolean getNeedAttachMO() {
        return IsNeedAttachMO;
    }

    public void setNeedAttachMO(Boolean needAttachMO) {
        IsNeedAttachMO = needAttachMO;
    }

    public List<String> getResultMessages() {
        return ResultMessages;
    }

    public void setResultMessages(List<String> resultMessages) {
        ResultMessages = resultMessages;
    }


}
