package com.jzk.hebi_wms.data.station;
/** 
  * 校验注入物料批号是否是同一批的请求
  * @author: timi    
  * create at: 2018/7/20 12:17
  */  
public class ValIsInjectSameBatchRequest {

    /**
     * SuppliyEqpCode : MOLDING-01
     * InjectionMoldingEqpCode : FEED-01
     * Barcode : ML201830729170001
     */

    private String SuppliyEqpCode;
    private String InjectionMoldingEqpCode;
    private String Barcode;

    public String getSuppliyEqpCode() {
        return SuppliyEqpCode;
    }

    public void setSuppliyEqpCode(String SuppliyEqpCode) {
        this.SuppliyEqpCode = SuppliyEqpCode;
    }

    public String getInjectionMoldingEqpCode() {
        return InjectionMoldingEqpCode;
    }

    public void setInjectionMoldingEqpCode(String InjectionMoldingEqpCode) {
        this.InjectionMoldingEqpCode = InjectionMoldingEqpCode;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }
}
