package com.jzk.hebi_wms.data.ipqc;

import java.util.List;

/**
  * IPQC的通用返回
  * @author   jzk
  * create at: 2018/8/5 10:45
  */  
public class IpqcCommonResult {

    /**
     * lotNo : IPQC201808050003
     * actualLotSize : 0
     * actualSampleSize : 0
     * goodSampleSize : 0
     * ngSampleSize : 0
     * isCheckPass : false
     * ipqcName : IPQC抽检外观
     * ipqcDesc : IPQC抽检（外观）
     * planDay : 20180730
     * planTpCode : 00-02
     * processCode : MD
     * lot2RcardList : [{"isSelected":false,"rCard":"GM5X504B7963","status":"GOOD"}]
     * resultMessages : []
     * dpList : [{"value":"00-02","displayText":"00-02","isSelected":false},{"value":"02-04","displayText":"02-04","isSelected":false},{"value":"04-06","displayText":"04-06","isSelected":false},{"value":"06-08","displayText":"06-08","isSelected":false},{"value":"08-10","displayText":"08-10","isSelected":false},{"value":"10-12","displayText":"10-12","isSelected":false},{"value":"12-14","displayText":"12-14","isSelected":false},{"value":"14-16","displayText":"14-16","isSelected":false},{"value":"16-18","displayText":"16-18","isSelected":false},{"value":"18-20","displayText":"18-20","isSelected":false},{"value":"20-22","displayText":"20-22","isSelected":false},{"value":"20-24","displayText":"22-24","isSelected":false}]
     */

    private String lotNo;
    private int actualLotSize;
    private int actualSampleSize;
    private int goodSampleSize;
    private int ngSampleSize;
    private boolean isCheckPass;
    private String ipqcName;
    private String ipqcDesc;
    private int planDay;
    private String planTpCode;
    private String processCode;
    private List<Lot2RcardListBean> lot2RcardList;
    private List<ResultMessagesBean> resultMessages;
    private List<DpListBean> dpList;

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public int getActualLotSize() {
        return actualLotSize;
    }

    public void setActualLotSize(int actualLotSize) {
        this.actualLotSize = actualLotSize;
    }

    public int getActualSampleSize() {
        return actualSampleSize;
    }

    public void setActualSampleSize(int actualSampleSize) {
        this.actualSampleSize = actualSampleSize;
    }

    public int getGoodSampleSize() {
        return goodSampleSize;
    }

    public void setGoodSampleSize(int goodSampleSize) {
        this.goodSampleSize = goodSampleSize;
    }

    public int getNgSampleSize() {
        return ngSampleSize;
    }

    public void setNgSampleSize(int ngSampleSize) {
        this.ngSampleSize = ngSampleSize;
    }

    public boolean isIsCheckPass() {
        return isCheckPass;
    }

    public void setIsCheckPass(boolean isCheckPass) {
        this.isCheckPass = isCheckPass;
    }

    public String getIpqcName() {
        return ipqcName;
    }

    public void setIpqcName(String ipqcName) {
        this.ipqcName = ipqcName;
    }

    public String getIpqcDesc() {
        return ipqcDesc;
    }

    public void setIpqcDesc(String ipqcDesc) {
        this.ipqcDesc = ipqcDesc;
    }

    public int getPlanDay() {
        return planDay;
    }

    public void setPlanDay(int planDay) {
        this.planDay = planDay;
    }

    public String getPlanTpCode() {
        return planTpCode;
    }

    public void setPlanTpCode(String planTpCode) {
        this.planTpCode = planTpCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public List<Lot2RcardListBean> getLot2RcardList() {
        return lot2RcardList;
    }

    public void setLot2RcardList(List<Lot2RcardListBean> lot2RcardList) {
        this.lot2RcardList = lot2RcardList;
    }

    public List<ResultMessagesBean> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<ResultMessagesBean> resultMessages) {
        this.resultMessages = resultMessages;
    }

    public List<DpListBean> getDpList() {
        return dpList;
    }

    public void setDpList(List<DpListBean> dpList) {
        this.dpList = dpList;
    }

    public static class Lot2RcardListBean {
        /**
         * isSelected : false
         * rCard : GM5X504B7963
         * status : GOOD
         */

        private boolean isSelected;
        private String rCard;
        private String status;

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String getRCard() {
            return rCard;
        }

        public void setRCard(String rCard) {
            this.rCard = rCard;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class DpListBean {
        /**
         * value : 00-02
         * displayText : 00-02
         * isSelected : false
         */

        private String value;
        private String displayText;
        private boolean isSelected;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDisplayText() {
            return displayText;
        }

        public void setDisplayText(String displayText) {
            this.displayText = displayText;
        }

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }
    }
    public static class ResultMessagesBean {
        /**
         * messageType : 2
         * isSuccess : true
         * messageText : 上料成功
         * result : null
         */

        private int messageType;
        private boolean isSuccess;
        private String messageText;
        private Object result;

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getMessageText() {
            return messageText;
        }

        public void setMessageText(String messageText) {
            this.messageText = messageText;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }
    }
}
