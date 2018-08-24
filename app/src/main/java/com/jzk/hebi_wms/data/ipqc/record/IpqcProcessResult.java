package com.jzk.hebi_wms.data.ipqc.record;

import java.util.List;
/** 
  * Ipqc抽检记录的工序返回值
  * @author   jzk
  * create at: 2018/8/24 13:28
  */  
public class IpqcProcessResult {

    /**
     * lotNo : null
     * actualLotSize : 0
     * actualSampleSize : 0
     * goodSampleSize : 0
     * ngSampleSize : 0
     * isCheckPass : false
     * ipqcName : null
     * ipqcDesc : null
     * planDay : 0
     * planTpCode : null
     * processCode : null
     * totalCount : 0
     * lot2RcardList : null
     * resultMessages : []
     * dpList : [{"value":"MD","displayText":"注塑后","isSelected":false},{"value":"CNC1INSPECTION","displayText":"CNC1全检后","isSelected":false},{"value":"PRLINSPECTION","displayText":"抛光全检后","isSelected":false}]
     */

    private Object lotNo;
    private int actualLotSize;
    private int actualSampleSize;
    private int goodSampleSize;
    private int ngSampleSize;
    private boolean isCheckPass;
    private Object ipqcName;
    private Object ipqcDesc;
    private int planDay;
    private Object planTpCode;
    private Object processCode;
    private int totalCount;
    private Object lot2RcardList;
    private List<?> resultMessages;
    private List<DpListBean> dpList;

    public Object getLotNo() {
        return lotNo;
    }

    public void setLotNo(Object lotNo) {
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

    public Object getIpqcName() {
        return ipqcName;
    }

    public void setIpqcName(Object ipqcName) {
        this.ipqcName = ipqcName;
    }

    public Object getIpqcDesc() {
        return ipqcDesc;
    }

    public void setIpqcDesc(Object ipqcDesc) {
        this.ipqcDesc = ipqcDesc;
    }

    public int getPlanDay() {
        return planDay;
    }

    public void setPlanDay(int planDay) {
        this.planDay = planDay;
    }

    public Object getPlanTpCode() {
        return planTpCode;
    }

    public void setPlanTpCode(Object planTpCode) {
        this.planTpCode = planTpCode;
    }

    public Object getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Object processCode) {
        this.processCode = processCode;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Object getLot2RcardList() {
        return lot2RcardList;
    }

    public void setLot2RcardList(Object lot2RcardList) {
        this.lot2RcardList = lot2RcardList;
    }

    public List<?> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<?> resultMessages) {
        this.resultMessages = resultMessages;
    }

    public List<DpListBean> getDpList() {
        return dpList;
    }

    public void setDpList(List<DpListBean> dpList) {
        this.dpList = dpList;
    }

    public static class DpListBean {
        /**
         * value : MD
         * displayText : 注塑后
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
}
