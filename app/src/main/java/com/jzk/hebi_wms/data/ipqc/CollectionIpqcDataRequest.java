package com.jzk.hebi_wms.data.ipqc;
/** 
  * 抽检采集数据的请求
  * @author   jzk
  * create at: 2018/8/5 11:19
  */  
public class CollectionIpqcDataRequest {

    /**
     * RCard : GM5X504B7963
     * LotNo : IPQC201808040002
     * IPQCName : IPQC抽检外观
     */

    private String RCard;
    private String LotNo;
    private String IPQCName;

    public String getRCard() {
        return RCard;
    }

    public void setRCard(String RCard) {
        this.RCard = RCard;
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String LotNo) {
        this.LotNo = LotNo;
    }

    public String getIPQCName() {
        return IPQCName;
    }

    public void setIPQCName(String IPQCName) {
        this.IPQCName = IPQCName;
    }
}
