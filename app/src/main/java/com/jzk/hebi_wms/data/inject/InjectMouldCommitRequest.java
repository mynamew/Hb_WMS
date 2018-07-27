package com.jzk.hebi_wms.data.inject;

import java.util.List;

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

    private String rcard;
    private String moldingeqpcode;
    private String processcode;
    private String stationcode;
    private boolean iscollectrepeatng;
    private boolean isgood;
    private String errorcode;
    private String remark;
    private String mouldcode;
    private String materialCard;
    private String materialbatch;
    private String employeecode;

    public List<InjectPassBean.ErrorCodesBean> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<InjectPassBean.ErrorCodesBean> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public List<InjectPassBean.ErrorCodesBean> errorCodes;
    public String getRcard() {
        return rcard;
    }

    public void setRcard(String rcard) {
        this.rcard = rcard;
    }

    public String getMoldingeqpcode() {
        return moldingeqpcode;
    }

    public void setMoldingeqpcode(String moldingeqpcode) {
        this.moldingeqpcode = moldingeqpcode;
    }

    public String getProcesscode() {
        return processcode;
    }

    public void setProcesscode(String processcode) {
        this.processcode = processcode;
    }

    public String getStationcode() {
        return stationcode;
    }

    public void setStationcode(String stationcode) {
        this.stationcode = stationcode;
    }

    public boolean isIscollectrepeatng() {
        return iscollectrepeatng;
    }

    public void setIscollectrepeatng(boolean iscollectrepeatng) {
        this.iscollectrepeatng = iscollectrepeatng;
    }

    public boolean isIsgood() {
        return isgood;
    }

    public void setIsgood(boolean isgood) {
        this.isgood = isgood;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getMouldcode() {
        return mouldcode;
    }

    public void setMouldcode(String mouldcode) {
        this.mouldcode = mouldcode;
    }

    public String getMaterialbatch() {
        return materialbatch;
    }

    public void setMaterialbatch(String materialbatch) {
        this.materialbatch = materialbatch;
    }

    public String getEmployeecode() {
        return employeecode;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    private String employeename;

    public String getRCard() {
        return rcard;
    }

    public void setRCard(String RCard) {
        this.rcard = RCard;
    }

    public String getMoldingEqpCode() {
        return moldingeqpcode;
    }

    public void setMoldingEqpCode(String MoldingEqpCode) {
        this.moldingeqpcode = MoldingEqpCode;
    }

    public String getProcessCode() {
        return processcode;
    }

    public void setProcessCode(String ProcessCode) {
        this.processcode = ProcessCode;
    }

    public String getStationCode() {
        return stationcode;
    }

    public void setStationCode(String StationCode) {
        this.stationcode = StationCode;
    }

    public boolean isIsCollectRepeatNG() {
        return iscollectrepeatng;
    }

    public void setIsCollectRepeatNG(boolean IsCollectRepeatNG) {
        this.iscollectrepeatng = IsCollectRepeatNG;
    }

    public boolean isIsGood() {
        return isgood;
    }

    public void setIsGood(boolean IsGood) {
        this.isgood = IsGood;
    }

    public String getErrorCode() {
        return errorcode;
    }

    public void setErrorCode(String ErrorCode) {
        this.errorcode = ErrorCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String Remark) {
        this.remark = Remark;
    }

    public String getMaterialCard() {
        return materialCard;
    }

    public void setMaterialCard(String materialCard) {
        this.materialCard = materialCard;
    }
}
