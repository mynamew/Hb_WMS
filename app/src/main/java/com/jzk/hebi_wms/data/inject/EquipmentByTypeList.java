package com.jzk.hebi_wms.data.inject;

import com.jzk.hebi_wms.data.station.InjectMoldBean;

import java.util.List;

public class EquipmentByTypeList {

    /**
     * stations : null
     * items : null
     * mos : null
     * eqpments : null
     * equipmentList : [{"value":"1A01","displayText":"注塑机1A01","relatedEquipment":"H01|H02"},{"value":"1A02","displayText":"注塑机1A02","relatedEquipment":null},{"value":"1A03","displayText":"注塑机1A03","relatedEquipment":null},{"value":"1A05","displayText":"注塑机1A05","relatedEquipment":null},{"value":"1A06","displayText":"注塑机1A06","relatedEquipment":null},{"value":"1A07","displayText":"注塑机1A07","relatedEquipment":""}]
     * resultMessages : null
     */

    private Object stations;
    private Object items;
    private Object mos;
    private Object eqpments;
    private Object resultMessages;
    private List<InjectMoldBean.EqpmentsBean> equipmentList;

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

    public Object getResultMessages() {
        return resultMessages;
    }

    public void setResultMessages(Object resultMessages) {
        this.resultMessages = resultMessages;
    }

    public List<InjectMoldBean.EqpmentsBean> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<InjectMoldBean.EqpmentsBean> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
