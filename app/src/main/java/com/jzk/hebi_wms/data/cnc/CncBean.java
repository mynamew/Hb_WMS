package com.jzk.hebi_wms.data.cnc;

import java.util.List;

/**
 * cnc 提交的返回
 *
 * @author: timi
 * create at: 2018/7/24 14:49
 */
public class CncBean {
    private String mOCode;
    private String itemCode;
    private String itemName;
    private String itemStandard;
    private String needAttachMo;
    private String materialBatch;
    private boolean isNeedAttachMO;
    private boolean needAttachItemCode;
    private List<?> resultMessages;

    public String getmOCode() {
        return mOCode;
    }

    public void setmOCode(String mOCode) {
        this.mOCode = mOCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStandard() {
        return itemStandard;
    }

    public void setItemStandard(String itemStandard) {
        this.itemStandard = itemStandard;
    }

    public String getNeedAttachMo() {
        return needAttachMo;
    }

    public void setNeedAttachMo(String needAttachMo) {
        this.needAttachMo = needAttachMo;
    }

    public boolean isNeedAttachMO() {
        return isNeedAttachMO;
    }

    public void setNeedAttachMO(boolean needAttachMO) {
        isNeedAttachMO = needAttachMO;
    }

    public boolean isNeedAttachItemCode() {
        return needAttachItemCode;
    }

    public void setNeedAttachItemCode(boolean needAttachItemCode) {
        this.needAttachItemCode = needAttachItemCode;
    }

    public List<?> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<?> resultMessages) {
        this.resultMessages = resultMessages;
    }

    public String getMaterialBatch() {
        return materialBatch;
    }

    public void setMaterialBatch(String materialBatch) {
        this.materialBatch = materialBatch;
    }
}
