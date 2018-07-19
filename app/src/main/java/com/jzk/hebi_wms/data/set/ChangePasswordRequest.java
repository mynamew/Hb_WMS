package com.jzk.hebi_wms.data.set;

/**
 * $dsc 修改密码
 * author: timi
 * create at: 2018-04-02 10:28
 */

public class ChangePasswordRequest {


    /**
     * tenancyName  : default
     * usernameOrEmailAddress  : mes
     * CurrentPassword : timi897321
     * NewPassword : cps,123
     * deviceType : 2
     * mac : E8-2A-EA-D0-BB-D2
     */

    private String tenancyName;
    private String usernameOrEmailAddress;
    private String CurrentPassword;
    private String NewPassword;
    private String deviceType;
    private String mac;

    public String getTenancyName() {
        return tenancyName;
    }

    public void setTenancyName(String tenancyName) {
        this.tenancyName = tenancyName;
    }

    public String getUsernameOrEmailAddress() {
        return usernameOrEmailAddress;
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }

    public String getCurrentPassword() {
        return CurrentPassword;
    }

    public void setCurrentPassword(String CurrentPassword) {
        this.CurrentPassword = CurrentPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
