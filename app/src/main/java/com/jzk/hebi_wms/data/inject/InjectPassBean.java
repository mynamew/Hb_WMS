package com.jzk.hebi_wms.data.inject;

import java.util.List;

public class InjectPassBean {

    /**
     * categoryId : 1
     * isNeedHandleTestData : false
     * isCheckedRCardInfo : true
     * errorGroups : [{"errorCode":null,"errorName":null,"errorGroupCode":"SV","errorGroupName":"外观不良"}]
     * errorCodes : [{"errorCode":"SV01","errorName":"外观不整齐","errorGroupCode":"SV","errorGroupName":"外观不良"},{"errorCode":"SV02","errorName":"外观有印","errorGroupCode":"SV","errorGroupName":"外观不良"}]
     * materialBatch : 20180717|9
     * moCode : MO20180718002
     * itemCode : 50101010002
     * itemName : 成品手机后壳
     * itemStandard : 成品手机后壳
     * resultMessages : []
     * isNeedAttachMO : false
     * needAttachMo : null
     * needAttachItemCode : null
     */

    private int categoryId;
    private boolean isNeedHandleTestData;
    private boolean isCheckedRCardInfo;
    private String materialBatch;
    private String moCode;
    private String itemCode;
    private String itemName;
    private String itemStandard;
    private boolean isNeedAttachMO;
    private Object needAttachMo;
    private Object needAttachItemCode;
    private List<ErrorGroupsBean> errorGroups;
    private List<ErrorCodesBean> errorCodes;
    private List<?> resultMessages;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isIsNeedHandleTestData() {
        return isNeedHandleTestData;
    }

    public void setIsNeedHandleTestData(boolean isNeedHandleTestData) {
        this.isNeedHandleTestData = isNeedHandleTestData;
    }

    public boolean isIsCheckedRCardInfo() {
        return isCheckedRCardInfo;
    }

    public void setIsCheckedRCardInfo(boolean isCheckedRCardInfo) {
        this.isCheckedRCardInfo = isCheckedRCardInfo;
    }

    public String getMaterialBatch() {
        return materialBatch;
    }

    public void setMaterialBatch(String materialBatch) {
        this.materialBatch = materialBatch;
    }

    public String getMoCode() {
        return moCode;
    }

    public void setMoCode(String moCode) {
        this.moCode = moCode;
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

    public boolean isIsNeedAttachMO() {
        return isNeedAttachMO;
    }

    public void setIsNeedAttachMO(boolean isNeedAttachMO) {
        this.isNeedAttachMO = isNeedAttachMO;
    }

    public Object getNeedAttachMo() {
        return needAttachMo;
    }

    public void setNeedAttachMo(Object needAttachMo) {
        this.needAttachMo = needAttachMo;
    }

    public Object getNeedAttachItemCode() {
        return needAttachItemCode;
    }

    public void setNeedAttachItemCode(Object needAttachItemCode) {
        this.needAttachItemCode = needAttachItemCode;
    }

    public List<ErrorGroupsBean> getErrorGroups() {
        return errorGroups;
    }

    public void setErrorGroups(List<ErrorGroupsBean> errorGroups) {
        this.errorGroups = errorGroups;
    }

    public List<ErrorCodesBean> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<ErrorCodesBean> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public List<?> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<?> resultMessages) {
        this.resultMessages = resultMessages;
    }

    public static class ErrorGroupsBean {
        /**
         * errorCode : null
         * errorName : null
         * errorGroupCode : SV
         * errorGroupName : 外观不良
         */

        private Object errorCode;
        private Object errorName;
        private String errorGroupCode;
        private String errorGroupName;

        public Object getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Object errorCode) {
            this.errorCode = errorCode;
        }

        public Object getErrorName() {
            return errorName;
        }

        public void setErrorName(Object errorName) {
            this.errorName = errorName;
        }

        public String getErrorGroupCode() {
            return errorGroupCode;
        }

        public void setErrorGroupCode(String errorGroupCode) {
            this.errorGroupCode = errorGroupCode;
        }

        public String getErrorGroupName() {
            return errorGroupName;
        }

        public void setErrorGroupName(String errorGroupName) {
            this.errorGroupName = errorGroupName;
        }
    }

    public static class ErrorCodesBean {
        /**
         * errorCode : SV01
         * errorName : 外观不整齐
         * errorGroupCode : SV
         * errorGroupName : 外观不良
         */

        private String errorCode;
        private String errorName;
        private String errorGroupCode;
        private String errorGroupName;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorName() {
            return errorName;
        }

        public void setErrorName(String errorName) {
            this.errorName = errorName;
        }

        public String getErrorGroupCode() {
            return errorGroupCode;
        }

        public void setErrorGroupCode(String errorGroupCode) {
            this.errorGroupCode = errorGroupCode;
        }

        public String getErrorGroupName() {
            return errorGroupName;
        }

        public void setErrorGroupName(String errorGroupName) {
            this.errorGroupName = errorGroupName;
        }
    }
}
