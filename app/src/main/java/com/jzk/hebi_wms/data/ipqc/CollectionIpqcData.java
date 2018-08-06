package com.jzk.hebi_wms.data.ipqc;

import java.util.List;
/** 
  * 抽检采集数据
  * @author   jzk
  * create at: 2018/8/5 11:18
  */  
public class CollectionIpqcData {

    /**
     * checkTypes : [{"ipqcId":1,"ipqcName":"IPQC抽检外观","checkTypeId":1,"checkTypeCode":"IPQC_PPL","checkTypeName":"IPQC外观抽检"}]
     * checkItems : [{"ipqcName":"IPQC抽检外观","unit":null,"limitLow":null,"limitHigh":null,"standard":null,"resultType":"Result","judgeType":"2","checkTypeId":1,"checkTypeCode":"IPQC_PPL","checkTypeName":"IPQC外观抽检","checkItemId":1,"checkItemCode":"IPQC-01","checkItemName":"IPQC外观","actual":"","result":true,"remark":null,"resultNumber":1},{"ipqcName":"IPQC抽检外观","unit":"mm","limitLow":"1.00000","limitHigh":"3.00000","standard":"2.00000","resultType":"Value","judgeType":"1","checkTypeId":1,"checkTypeCode":"IPQC_PPL","checkTypeName":"IPQC外观抽检","checkItemId":5,"checkItemCode":"IPQC-02","checkItemName":"厚度","actual":"","result":true,"remark":null,"resultNumber":1}]
     * errorGroups : [{"errorCode":null,"errorName":null,"errorGroupCode":"CNC1","errorGroupName":"CNC1"},{"errorCode":null,"errorName":null,"errorGroupCode":"CNC2","errorGroupName":"CNC2"},{"errorCode":null,"errorName":null,"errorGroupCode":"COATING","errorGroupName":"Coating"},{"errorCode":null,"errorName":null,"errorGroupCode":"LASER SN","errorGroupName":"Laser SN"},{"errorCode":null,"errorName":null,"errorGroupCode":"MOLDING & DE-MOLDING","errorGroupName":"Molding & De-Molding"},{"errorCode":null,"errorName":null,"errorGroupCode":"SANDING&POLISH","errorGroupName":"Sanding&Polish"},{"errorCode":null,"errorName":null,"errorGroupCode":"USC1","errorGroupName":"USC1"}]
     * errorCodes : []
     * errorInfo : null
     * moCode : null
     * itemCode : null
     * itemName : null
     * itemStandard : null
     * resultMessages : []
     * isNeedAttachMO : false
     * needAttachMo : null
     * needAttachItemCode : null
     */

    private Object errorInfo;
    private Object moCode;
    private Object itemCode;
    private Object itemName;
    private Object itemStandard;
    private boolean isNeedAttachMO;
    private Object needAttachMo;
    private Object needAttachItemCode;
    private List<CheckTypesBean> checkTypes;
    private List<CheckItemsBean> checkItems;
    private List<ErrorGroupsBean> errorGroups;
    private List<?> errorCodes;
    private List<?> resultMessages;

    public Object getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(Object errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getMoCode() {
        return moCode;
    }

    public void setMoCode(Object moCode) {
        this.moCode = moCode;
    }

    public Object getItemCode() {
        return itemCode;
    }

    public void setItemCode(Object itemCode) {
        this.itemCode = itemCode;
    }

    public Object getItemName() {
        return itemName;
    }

    public void setItemName(Object itemName) {
        this.itemName = itemName;
    }

    public Object getItemStandard() {
        return itemStandard;
    }

    public void setItemStandard(Object itemStandard) {
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

    public List<CheckTypesBean> getCheckTypes() {
        return checkTypes;
    }

    public void setCheckTypes(List<CheckTypesBean> checkTypes) {
        this.checkTypes = checkTypes;
    }

    public List<CheckItemsBean> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<CheckItemsBean> checkItems) {
        this.checkItems = checkItems;
    }

    public List<ErrorGroupsBean> getErrorGroups() {
        return errorGroups;
    }

    public void setErrorGroups(List<ErrorGroupsBean> errorGroups) {
        this.errorGroups = errorGroups;
    }

    public List<?> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<?> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public List<?> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<?> resultMessages) {
        this.resultMessages = resultMessages;
    }

    public static class CheckTypesBean {
        /**
         * ipqcId : 1
         * ipqcName : IPQC抽检外观
         * checkTypeId : 1
         * checkTypeCode : IPQC_PPL
         * checkTypeName : IPQC外观抽检
         */

        private int ipqcId;
        private String ipqcName;
        private int checkTypeId;
        private String checkTypeCode;
        private String checkTypeName;

        public int getIpqcId() {
            return ipqcId;
        }

        public void setIpqcId(int ipqcId) {
            this.ipqcId = ipqcId;
        }

        public String getIpqcName() {
            return ipqcName;
        }

        public void setIpqcName(String ipqcName) {
            this.ipqcName = ipqcName;
        }

        public int getCheckTypeId() {
            return checkTypeId;
        }

        public void setCheckTypeId(int checkTypeId) {
            this.checkTypeId = checkTypeId;
        }

        public String getCheckTypeCode() {
            return checkTypeCode;
        }

        public void setCheckTypeCode(String checkTypeCode) {
            this.checkTypeCode = checkTypeCode;
        }

        public String getCheckTypeName() {
            return checkTypeName;
        }

        public void setCheckTypeName(String checkTypeName) {
            this.checkTypeName = checkTypeName;
        }
    }

    public static class CheckItemsBean {
        /**
         * ipqcName : IPQC抽检外观
         * unit : null
         * limitLow : null
         * limitHigh : null
         * standard : null
         * resultType : Result
         * judgeType : 2
         * checkTypeId : 1
         * checkTypeCode : IPQC_PPL
         * checkTypeName : IPQC外观抽检
         * checkItemId : 1
         * checkItemCode : IPQC-01
         * checkItemName : IPQC外观
         * actual : 
         * result : true
         * remark : null
         * resultNumber : 1
         */

        private String ipqcName;
        private String unit;
        private String limitLow;
        private String limitHigh;
        private String standard;
        private String resultType;
        private String judgeType;
        private int checkTypeId;
        private String checkTypeCode;
        private String checkTypeName;
        private int checkItemId;
        private String checkItemCode;
        private String checkItemName;
        private String actual;
        private boolean result;
        private String remark;
        private int resultNumber;
        private boolean isHaveChecked;
        public String getIpqcName() {
            return ipqcName;
        }

        public void setIpqcName(String ipqcName) {
            this.ipqcName = ipqcName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getLimitLow() {
            return limitLow;
        }

        public void setLimitLow(String limitLow) {
            this.limitLow = limitLow;
        }

        public String getLimitHigh() {
            return limitHigh;
        }

        public void setLimitHigh(String limitHigh) {
            this.limitHigh = limitHigh;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public String getJudgeType() {
            return judgeType;
        }

        public void setJudgeType(String judgeType) {
            this.judgeType = judgeType;
        }

        public int getCheckTypeId() {
            return checkTypeId;
        }

        public void setCheckTypeId(int checkTypeId) {
            this.checkTypeId = checkTypeId;
        }

        public String getCheckTypeCode() {
            return checkTypeCode;
        }

        public void setCheckTypeCode(String checkTypeCode) {
            this.checkTypeCode = checkTypeCode;
        }

        public String getCheckTypeName() {
            return checkTypeName;
        }

        public void setCheckTypeName(String checkTypeName) {
            this.checkTypeName = checkTypeName;
        }

        public int getCheckItemId() {
            return checkItemId;
        }

        public void setCheckItemId(int checkItemId) {
            this.checkItemId = checkItemId;
        }

        public String getCheckItemCode() {
            return checkItemCode;
        }

        public void setCheckItemCode(String checkItemCode) {
            this.checkItemCode = checkItemCode;
        }

        public String getCheckItemName() {
            return checkItemName;
        }

        public void setCheckItemName(String checkItemName) {
            this.checkItemName = checkItemName;
        }

        public String getActual() {
            return actual;
        }

        public void setActual(String actual) {
            this.actual = actual;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getResultNumber() {
            return resultNumber;
        }

        public void setResultNumber(int resultNumber) {
            this.resultNumber = resultNumber;
        }

        public boolean isHaveChecked() {
            return isHaveChecked;
        }

        public void setHaveChecked(boolean haveChecked) {
            isHaveChecked = haveChecked;
        }
    }

    public static class ErrorGroupsBean {
        /**
         * errorCode : null
         * errorName : null
         * errorGroupCode : CNC1
         * errorGroupName : CNC1
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
}
