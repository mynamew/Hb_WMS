package com.jzk.hebi_wms.data.station;

import java.util.List;
/** 
  * 供料机实体
  * @author: timi    
  * create at: 2018/7/20 12:17
  */  
public class SupplyMaterialBean {

    /**
     * stations : null
     * items : null
     * mos : null
     * eqpments : [{"value":"FEED-01","displayText":"供料机-01","isSelected":false},{"value":"FEED-02","displayText":"供料机-02","isSelected":false}]
     * resultMessages : null
     */

    private Object stations;
    private Object items;
    private Object mos;
    private Object resultMessages;
    private List<EqpmentsBean> eqpments;

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

    public Object getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(Object resultMessages) {
        this.resultMessages = resultMessages;
    }

    public List<EqpmentsBean> getEqpments() {
        return eqpments;
    }

    public void setEqpments(List<EqpmentsBean> eqpments) {
        this.eqpments = eqpments;
    }

    public static class EqpmentsBean {
        /**
         * value : FEED-01
         * displayText : 供料机-01
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

        @Override
        public String toString() {
            return "EqpmentsBean{" +
                    "value='" + value + '\'' +
                    ", displayText='" + displayText + '\'' +
                    ", isSelected=" + isSelected +
                    '}';
        }
    }
}
