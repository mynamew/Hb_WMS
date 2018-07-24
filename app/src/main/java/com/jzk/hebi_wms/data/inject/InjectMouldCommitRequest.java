package com.jzk.hebi_wms.data.inject;

/**
 * 注塑机过站提交
 *
 * @author: timi
 * create at: 2018/7/23 18:14
 */
public class InjectMouldCommitRequest {

    /**
     * RCard : GM5X300S7963
     * MoldingEqpCode : MOLDING-01
     * ProcessCode : OP101
     * StationCode : OP101
     * IsCollectRepeatNG : false
     * IsGood : true
     * ErrorCode :
     * Remark :
     */

    private String RCard;
    private String MoldingEqpCode;
    private String ProcessCode;
    private String StationCode;
    private boolean IsCollectRepeatNG;
    private boolean IsGood;
    private String ErrorCode;
    private String Remark;

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

    public boolean isIsCollectRepeatNG() {
        return IsCollectRepeatNG;
    }

    public void setIsCollectRepeatNG(boolean IsCollectRepeatNG) {
        this.IsCollectRepeatNG = IsCollectRepeatNG;
    }

    public boolean isIsGood() {
        return IsGood;
    }

    public void setIsGood(boolean IsGood) {
        this.IsGood = IsGood;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
}
