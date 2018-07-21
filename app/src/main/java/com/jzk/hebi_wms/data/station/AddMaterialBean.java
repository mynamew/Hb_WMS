package com.jzk.hebi_wms.data.station;

import java.util.List;
/** 
  * 上料返回的实体（点击提交按钮）
  * @author: timi    
  * create at: 2018/7/20 15:27
  */  
public class AddMaterialBean {

    /**
     * stations : null
     * items : null
     * mos : null
     * eqpments : null
     * resultMessages : [{"messageType":2,"isSuccess":true,"messageText":"上料成功","result":null}]
     */

    private Object stations;
    private Object items;
    private Object mos;
    private Object eqpments;
    private List<ResultMessagesBean> resultMessages;

    public Object getStations() {
        return stations;
    }

    public void setStations(Object stations) {
        this.stations = stations;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public Object getMos() {
        return mos;
    }

    public void setMos(Object mos) {
        this.mos = mos;
    }

    public Object getEqpments() {
        return eqpments;
    }

    public void setEqpments(Object eqpments) {
        this.eqpments = eqpments;
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
