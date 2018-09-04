package com.jzk.hebi_wms.data.ipqc;

import com.jzk.hebi_wms.data.inject.InjectPassBean;

import java.util.List;
/**
  * 保存采集数据的请求
  * @author   jzk
  * create at: 2018/8/6 8:54
  */
public class SaveCheckResultRequest {

    /**
     * RCard : GM5X504B7963
     * LotNo : IPQC201808040002
     * ProcessCode : MD
     * IPQCName : IPQC抽检外观
     * Planday : 2018-07-30
     * PlanTpCode : 00-02
     * ErrorCodes : [{"ErrorCode":"Coating inclusion@Sidewall","ErrorName":"喷漆颗粒 @ 侧边","ErrorGroupCode":"COATING","ErrorGroupName":"Coating"}]
     * ExtendIPQCDatas : [{"IPQCName":"IPQC抽检外观","Unit":null,"LimitLow":null,"LimitHigh":null,"Standard":null,"ResultType":"Result","JudgeType":"2","CheckTypeId":1,"CheckTypeCode":"IPQC_PPL","CheckTypeName":"IPQC外观","CheckItemId":1,"CheckItemCode":"IPQC-01","CheckItemName":"IPQC外观","Actual":"1","Result":true,"Remark":null,"ResultNumber":0},{"IPQCName":"IPQC抽检外观","Unit":"mm","LimitLow":"1.00000","LimitHigh":"3.00000","Standard":"2.00000","ResultType":"Value","JudgeType":"1","CheckTypeId":1,"CheckTypeCode":"IPQC_PPL","CheckTypeName":"厚度","CheckItemId":5,"CheckItemCode":"IPQC-02","CheckItemName":"厚度","Actual":"2","Result":true,"Remark":null,"ResultNumber":0}]
     */

    private String RCard;
    private String LotNo;
    private String ProcessCode;
    private String IPQCName;
    private String Planday;
    private String PlanTpCode;
    private String EqTypeCode;
    private String  EqCode;
    private List<InjectPassBean.ErrorCodesBean> ErrorCodes;
    private List<CollectionIpqcData.CheckItemsBean> ExtendIPQCDatas;

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

    public String getProcessCode() {
        return ProcessCode;
    }

    public void setProcessCode(String ProcessCode) {
        this.ProcessCode = ProcessCode;
    }

    public String getIPQCName() {
        return IPQCName;
    }

    public void setIPQCName(String IPQCName) {
        this.IPQCName = IPQCName;
    }

    public String getPlanday() {
        return Planday;
    }

    public void setPlanday(String Planday) {
        this.Planday = Planday;
    }

    public String getPlanTpCode() {
        return PlanTpCode;
    }

    public void setPlanTpCode(String PlanTpCode) {
        this.PlanTpCode = PlanTpCode;
    }

    public List<InjectPassBean.ErrorCodesBean> getErrorCodes() {
        return ErrorCodes;
    }

    public void setErrorCodes(List<InjectPassBean.ErrorCodesBean> ErrorCodes) {
        this.ErrorCodes = ErrorCodes;
    }

    public List<CollectionIpqcData.CheckItemsBean> getExtendIPQCDatas() {
        return ExtendIPQCDatas;
    }

    public void setExtendIPQCDatas(List<CollectionIpqcData.CheckItemsBean> ExtendIPQCDatas) {
        this.ExtendIPQCDatas = ExtendIPQCDatas;
    }

    public String getEqTypeCode() {
        return EqTypeCode;
    }

    public void setEqTypeCode(String eqTypeCode) {
        EqTypeCode = eqTypeCode;
    }

    public String getEqCode() {
        return EqCode;
    }

    public void setEqCode(String eqCode) {
        EqCode = eqCode;
    }
}
