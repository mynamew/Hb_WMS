package com.jzk.hebi_wms.data;

/**
 * $dsc app版本
 * author: timi
 * create at: 2017-09-04 13:55
 */

public class VersionBean {


    /**
     * tenantId : 1
     * clientCode : WPDA
     * path : ApkVersion\WMS_1.0.3.apk
     * version : 103
     * remark : null
     * updateMode : 2
     * lastModificationTime : null
     * lastModifierUserId : null
     * lastModifierFullName : null
     * creationTime : 2018-06-26T14:51:16+08:00
     * creatorUserId : 2
     * creatorFullName : ADMIN
     * id : 24
     */

    private int tenantId;
    private String clientCode;
    private String path;
    private int version;
    private String remark;
    private int updateMode;
    private String lastModificationTime;
    private int lastModifierUserId;
    private String lastModifierFullName;
    private String creationTime;
    private int creatorUserId;
    private String creatorFullName;
    private int id;

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(int updateMode) {
        this.updateMode = updateMode;
    }

    public String getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(String lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public int getLastModifierUserId() {
        return lastModifierUserId;
    }

    public void setLastModifierUserId(int lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }

    public String getLastModifierFullName() {
        return lastModifierFullName;
    }

    public void setLastModifierFullName(String lastModifierFullName) {
        this.lastModifierFullName = lastModifierFullName;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public int getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(int creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getCreatorFullName() {
        return creatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        this.creatorFullName = creatorFullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "tenantId=" + tenantId +
                ", clientCode='" + clientCode + '\'' +
                ", path='" + path + '\'' +
                ", version=" + version +
                ", remark=" + remark +
                ", updateMode='" + updateMode + '\'' +
                ", lastModificationTime=" + lastModificationTime +
                ", lastModifierUserId=" + lastModifierUserId +
                ", lastModifierFullName=" + lastModifierFullName +
                ", creationTime='" + creationTime + '\'' +
                ", creatorUserId=" + creatorUserId +
                ", creatorFullName='" + creatorFullName + '\'' +
                ", id=" + id +
                '}';
    }
}
