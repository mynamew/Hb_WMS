package com.jzk.hebi_wms.data.cnc;

import java.util.List;

/**
 * cnc 提交的返回
 *
 * @author: timi
 * create at: 2018/7/24 14:49
 */
public class CncBean {

    /**
     * moCode : MO201807240001
     * itemCode : 50101010002
     * itemName : 成品手机后壳
     * itemStandard : 成品手机后壳
     * resultMessages : [{"messageType":2,"isSuccess":true,"messageText":" << 良品采集成功","result":null}]
     * isNeedAttachMO : false
     * needAttachMo : null
     * needAttachItemCode : null
     */

    private String moCode;
    private String itemCode;
    private String itemName;
    private String itemStandard;
    private boolean isNeedAttachMO;
    private Object needAttachMo;
    private Object needAttachItemCode;
    private List<ResultMessagesBean> resultMessages;

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

    public List<ResultMessagesBean> getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(List<ResultMessagesBean> resultMessages) {
        this.resultMessages = resultMessages;
    }

    public static class ResultMessagesBean {
        /**
         * messageType : 2
         * isSuccess : true
         * messageText :  << 良品采集成功
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
