package com.jzk.hebi_wms.data.ipqc.record;

import java.util.List;
/** 
  * ipqc抽检记录的返回
  * @author   jzk
  * create at: 2018/8/24 13:32
  */  
public class IpqcRecordResult {

    private List<IPQCRcardListBean> iPQCRcardList;
    private List<IPQCRecordListBean> iPQCRecordList;
    private List<?> resultMessages;

    public List<IPQCRcardListBean> getIPQCRcardList() {
        return iPQCRcardList;
    }

    public void setIPQCRcardList(List<IPQCRcardListBean> iPQCRcardList) {
        this.iPQCRcardList = iPQCRcardList;
    }

    public List<IPQCRecordListBean> getIPQCRecordList() {
        return iPQCRecordList;
    }

    public void setIPQCRecordList(List<IPQCRecordListBean> iPQCRecordList) {
        this.iPQCRecordList = iPQCRecordList;
    }

    public List<?> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<?> resultMessages) {
        this.resultMessages = resultMessages;
    }

    public static class IPQCRcardListBean {
        /**
         * rCard : GM5X600C7963
         * status : GOOD
         * lotNo : IPQC201808080001
         */

        private String rCard;
        private String status;
        private String lotNo;

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

        public String getLotNo() {
            return lotNo;
        }

        public void setLotNo(String lotNo) {
            this.lotNo = lotNo;
        }
    }

    public static class IPQCRecordListBean {
        /**
         * lotNo : IPQC201808080001
         * ipqcName : IPQC抽检外观
         * planTpCode : 08-10
         * processCode : MD
         * planDay : 20180808
         * result : 合格
         */

        private String lotNo;
        private String ipqcName;
        private String planTpCode;
        private String processCode;
        private String planDay;
        private String result;

        public String getLotNo() {
            return lotNo;
        }

        public void setLotNo(String lotNo) {
            this.lotNo = lotNo;
        }

        public String getIpqcName() {
            return ipqcName;
        }

        public void setIpqcName(String ipqcName) {
            this.ipqcName = ipqcName;
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

        public String getPlanDay() {
            return planDay;
        }

        public void setPlanDay(String planDay) {
            this.planDay = planDay;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
