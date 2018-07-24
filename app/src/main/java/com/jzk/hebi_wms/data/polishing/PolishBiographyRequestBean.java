package com.jzk.hebi_wms.data.polishing;

/**
 * Created by JuQent on 2018-07-24.
 */

public class PolishBiographyRequestBean {
    String PolishEqpCode;
    String EmployeeCode;
    String EmployeeName;
    String RCard;
    String MoCode;
    String ItemCode;
    String RouteCode;
    String RouteVersion;
    String StationCode;
    String ProcessCode;
    Boolean IsCollectRepeatNG;

    public String getPolishEqpCode() {
        return PolishEqpCode;
    }

    public void setPolishEqpCode(String polishEqpCode) {
        PolishEqpCode = polishEqpCode;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getRCard() {
        return RCard;
    }

    public void setRCard(String RCard) {
        this.RCard = RCard;
    }

    public String getMoCode() {
        return MoCode;
    }

    public void setMoCode(String moCode) {
        MoCode = moCode;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getRouteCode() {
        return RouteCode;
    }

    public void setRouteCode(String routeCode) {
        RouteCode = routeCode;
    }

    public String getRouteVersion() {
        return RouteVersion;
    }

    public void setRouteVersion(String routeVersion) {
        RouteVersion = routeVersion;
    }

    public String getStationCode() {
        return StationCode;
    }

    public void setStationCode(String stationCode) {
        StationCode = stationCode;
    }

    public String getProcessCode() {
        return ProcessCode;
    }

    public void setProcessCode(String processCode) {
        ProcessCode = processCode;
    }

    public Boolean getCollectRepeatNG() {
        return IsCollectRepeatNG;
    }

    public void setCollectRepeatNG(Boolean collectRepeatNG) {
        IsCollectRepeatNG = collectRepeatNG;
    }
 }
