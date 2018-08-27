package com.jzk.hebi_wms.data.device;
/** 
  * 获取设备类型
  * @author   jzk
  * create at: 2018/8/27 12:59
  */  
public class DeviceResponse {

    /**
     * equipmentTypeCode : ANNEOLING
     * equipmentTypeName : 烘烤设备
     */

    private String equipmentTypeCode;
    private String equipmentTypeName;

    public String getEquipmentTypeCode() {
        return equipmentTypeCode;
    }

    public void setEquipmentTypeCode(String equipmentTypeCode) {
        this.equipmentTypeCode = equipmentTypeCode;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }
}
