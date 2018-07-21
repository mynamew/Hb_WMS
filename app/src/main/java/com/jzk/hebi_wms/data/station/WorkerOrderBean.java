package com.jzk.hebi_wms.data.station;

import java.util.List;
/**
  *
  * @author: timi
  * create at: 2018/7/20 11:17
  */
public class WorkerOrderBean {

    /**
     * stations : null
     * items : null
     * mos : [{"moCode":"MO20180710001","itemCode":"50101010002"},{"moCode":"MO20180710002","itemCode":"50101010002"},{"moCode":"MO20180717001","itemCode":"50101010002"},{"moCode":"MO20180718001","itemCode":"50101010002"},{"moCode":"MO20180718002","itemCode":"50101010002"}]
     * eqpments : null
     * resultMessages : null
     */

    private Object stations;
    private Object items;
    private Object eqpments;
    private Object resultMessages;
    private List<MosBean> mos;

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

    public Object getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(Object resultMessages) {
        this.resultMessages = resultMessages;
    }

    public List<MosBean> getMos() {
        return mos;
    }

    public void setMos(List<MosBean> mos) {
        this.mos = mos;
    }

    public static class MosBean {
        /**
         * moCode : MO20180710001
         * itemCode : 50101010002
         */

        private String moCode;
        private String itemCode;

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
    }
}
