package com.jzk.hebi_wms.data.cnc;

/**
 * cnc 的请求接口
 *
 * @author: timi
 * create at: 2018/7/24 14:46
 */
public class CncRequest {

    private String cNCEqpCode;
    private String cNCFixture;
    private String employeeCode;
    private String employeeName;
    private String rCard;
    private String moCode;
    private String itemCode;
    private String routeCode;
    private String routeVersion;
    private String stationCode;
    private String processCode;
    //默认为false
    private String IsCollectRepeatNG;
   
    public String getcNCEqpCode() {
        return cNCEqpCode;
    }

    public void setcNCEqpCode(String cNCEqpCode) {
        this.cNCEqpCode = cNCEqpCode;
    }

    public String getcNCFixture() {
        return cNCFixture;
    }

    public void setcNCFixture(String cNCFixture) {
        this.cNCFixture = cNCFixture;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getrCard() {
        return rCard;
    }

    public void setrCard(String rCard) {
        this.rCard = rCard;
    }

    public String getMoCode() {
        return moCode;
    }

    public void setMoCode(String moCode) {
        this.moCode = moCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getRouteVersion() {
        return routeVersion;
    }

    public void setRouteVersion(String routeVersion) {
        this.routeVersion = routeVersion;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getIsCollectRepeatNG() {
        return IsCollectRepeatNG;
    }

    public void setIsCollectRepeatNG(String isCollectRepeatNG) {
        IsCollectRepeatNG = isCollectRepeatNG;
    }
}
