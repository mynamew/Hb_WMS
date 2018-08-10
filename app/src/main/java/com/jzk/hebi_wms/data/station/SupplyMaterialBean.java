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
    private List<InjectMoldBean.EqpmentsBean> eqpments;

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

    public List<InjectMoldBean.EqpmentsBean> getEqpments() {
        return eqpments;
    }

    public void setEqpments(List<InjectMoldBean.EqpmentsBean> eqpments) {
        this.eqpments = eqpments;
    }

}
