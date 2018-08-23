package com.jzk.hebi_wms.data.paint;

import java.util.List;

public class PaintResult {

    /**
     * stations : null
     * items : null
     * eqpments : null
     * resultMessages : [{"messageType":2,"isSuccess":true,"messageText":"漆料上料成功","result":null}]
     */

    private Object stations;
    private Object items;
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
         * messageText : 漆料上料成功
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
