package com.jzk.hebi_wms.data.paint;
/** 
  * 喷漆的请求
  * @author   jzk
  * create at: 2018/8/23 17:44
  */  
public class PaintRequest {

    /**
     * BarCode : MP2018080100002
     * InjectionMoldingEqpCode : SPA
     * ItemCode : Q1APP41603Y
     * MoCode : MO000000
     * ProcessCode : MD
     * ProductionLineCode : L-D01
     * StationCode : OP101D
     * ThinnerCard : MT2018080100002
     */

    private String BarCode;
    private String InjectionMoldingEqpCode;
    private String ItemCode;
    private String MoCode;
    private String ProcessCode;
    private String ProductionLineCode;
    private String StationCode;
    private String ThinnerCard;

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public String getInjectionMoldingEqpCode() {
        return InjectionMoldingEqpCode;
    }

    public void setInjectionMoldingEqpCode(String InjectionMoldingEqpCode) {
        this.InjectionMoldingEqpCode = InjectionMoldingEqpCode;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    public String getMoCode() {
        return MoCode;
    }

    public void setMoCode(String MoCode) {
        this.MoCode = MoCode;
    }

    public String getProcessCode() {
        return ProcessCode;
    }

    public void setProcessCode(String ProcessCode) {
        this.ProcessCode = ProcessCode;
    }

    public String getProductionLineCode() {
        return ProductionLineCode;
    }

    public void setProductionLineCode(String ProductionLineCode) {
        this.ProductionLineCode = ProductionLineCode;
    }

    public String getStationCode() {
        return StationCode;
    }

    public void setStationCode(String StationCode) {
        this.StationCode = StationCode;
    }

    public String getThinnerCard() {
        return ThinnerCard;
    }

    public void setThinnerCard(String ThinnerCard) {
        this.ThinnerCard = ThinnerCard;
    }
}
