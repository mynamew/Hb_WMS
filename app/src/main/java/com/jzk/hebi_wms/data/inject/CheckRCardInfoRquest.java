package com.jzk.hebi_wms.data.inject;
/** 
  * 注塑过站的实体
  * @author: timi    
  * create at: 2018/7/21 16:00
  */  
public class CheckRCardInfoRquest {

    /**
     * RCard : GM5X300S7963
     * MoldingEqpCode : MOLDING-01
     * ProcessCode : OP101
     * StationCode : OP101
     */

    private String RCard;
    private String MoldingEqpCode;
    private String ProcessCode;
    private String StationCode;

    public String getRCard() {
        return RCard;
    }

    public void setRCard(String RCard) {
        this.RCard = RCard;
    }

    public String getMoldingEqpCode() {
        return MoldingEqpCode;
    }

    public void setMoldingEqpCode(String MoldingEqpCode) {
        this.MoldingEqpCode = MoldingEqpCode;
    }

    public String getProcessCode() {
        return ProcessCode;
    }

    public void setProcessCode(String ProcessCode) {
        this.ProcessCode = ProcessCode;
    }

    public String getStationCode() {
        return StationCode;
    }

    public void setStationCode(String StationCode) {
        this.StationCode = StationCode;
    }
}
