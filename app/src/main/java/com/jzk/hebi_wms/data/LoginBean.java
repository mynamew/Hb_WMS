package com.jzk.hebi_wms.data;

import java.util.List;

/**
 * 登录的返回
 */

public class LoginBean {


    /**
     * userId : 2
     * userName : ADMIN
     * fullName : ADMIN
     * currentOrgUnit : {"id":2,"parentId":null,"code":"00000","displayName":"默认组织"}
     * token : qppJwZSxjmv_g4DVKp2lIx19VJ1H8ezm8bUaabsjmPpsW_e5nAIG43SoEe8HzVi99Vw2P5w-trAPvHrCDxtrTwwy6HCPvcmCy-bP0cgveWn3jsObyPajqPl2BGGaQAI_1hOfjb8KsHOfl0g4UycypwAmOtuv0N6zGeh_DkwCULH1N5nNcbiyTpfHIH2hbbDNwS__T5nZPxsvuFL1NLzWrUXxWVAJ5pUyeYtn_8SP1ITdjzqN1MWWcHDEQZ3Qw3gJS2v1X3R99XiGgAalcsuZpr64VbxyVOH72MCJWdbPZriEA0EwHd5QOTOcNtosSGSWOR-RTGmXfsRawtHK_StQTusb_fJQEE59jrJ6DfAeVuxmqokY84_tnA9btSmZpg-S0lFKJf1ksD2kbvfg4DNp74mRZvOPVRX5hkZulhxeQdTOTI2hxex9ohATa3WZV6R2RdYNoywXgcryXFB-rKwJMcYyWtZxTFdeDDw9yhH8iw9eKR84nJvdIVH3ULb0pk-AaX55funtfWENSDMuBz_UBCttmFw6s7hVttM-0bm438xQbIb7sf8Gq6xmMQ_AzBzDPKZQbsltvUawKka9aVAydw
     * grantPermission : {"permissionCode":"Pages","permissionName":"Pages","childPermissions":[{"permissionCode":"Pages.QualityControl","permissionName":"质检管理","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC","permissionName":"IPQC质检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollectionSizeFunction","permissionName":"IPQC抽检尺寸功能","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.LQC","permissionName":"成品抽检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage","permissionName":"批抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCLotQA","permissionName":"栈板Lot绑定维护","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCCollection","permissionName":"批抽检采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCQuery","permissionName":"OQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.REL","permissionName":"REL实验室","childPermissions":[{"permissionCode":"Pages.QualityControl.REL.RELCollection","permissionName":"REL数据采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.REL.RELQuery","permissionName":"REL查询","childPermissions":null}]}]},{"permissionCode":"Pages.ProductionPlan","permissionName":"生产计划","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection","permissionName":"数据采集","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard","permissionName":"产品序列号打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.CreateRCard","permissionName":"生成序列号","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Export","permissionName":"导出","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Print","permissionName":"打印序列号","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.RMO2RCard","permissionName":"产品序列号重打印","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Molding","permissionName":"注塑过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Laser","permissionName":"镭雕过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC","permissionName":"CNC过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNCInspection","permissionName":"CNC全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Polish","permissionName":"抛光过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.PolishInspection","permissionName":"抛光全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning","permissionName":"清洗过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Anneoling","permissionName":"烘烤过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning2","permissionName":"清洗(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Paint","permissionName":"漆料上料","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Coating","permissionName":"喷漆过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CoatingInspection","permissionName":"喷漆全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2","permissionName":"CNC(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2Inspection","permissionName":"CNC(二)全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning3","permissionName":"清洗(三)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.AOI","permissionName":"AOI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.SI","permissionName":"SI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial","permissionName":"供料上料","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial.AddMaterial","permissionName":"上料","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.ViewFlow","permissionName":"作业流程图","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob","permissionName":"包装作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CollectionCarton","permissionName":"包装采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo","permissionName":"箱号条码打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.CreateCartonInfo","permissionName":"生产箱号条码","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.Print","permissionName":"打印箱号条码","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.QueryCartonInfo","permissionName":"装箱明细","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.QueryCarton2RCard","permissionName":"装箱查询","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.OBARework","permissionName":"OBA返工","childPermissions":[{"permissionCode":"Pages.ProductionPlan.OBARework.ReworkUnPacking","permissionName":"返工拆箱","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.OBARework.ReworkBinding","permissionName":"返工单绑定","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.TSJob","permissionName":"返工作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.TSJob.TSComplete","permissionName":"返工返工","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.TSJob.TSRCardReflow","permissionName":"返工查询","childPermissions":null}]}]}]}
     * orgUnits : [{"id":2,"parentId":null,"code":"00000","displayName":"/  默认组织 "}]
     */

    private int userId;
    private String userName;
    private String fullName;
    private CurrentOrgUnitBean currentOrgUnit;
    private String token;
    private GrantPermissionBean grantPermission;
    private List<OrgUnitsBean> orgUnits;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CurrentOrgUnitBean getCurrentOrgUnit() {
        return currentOrgUnit;
    }

    public void setCurrentOrgUnit(CurrentOrgUnitBean currentOrgUnit) {
        this.currentOrgUnit = currentOrgUnit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GrantPermissionBean getGrantPermission() {
        return grantPermission;
    }

    public void setGrantPermission(GrantPermissionBean grantPermission) {
        this.grantPermission = grantPermission;
    }

    public List<OrgUnitsBean> getOrgUnits() {
        return orgUnits;
    }

    public void setOrgUnits(List<OrgUnitsBean> orgUnits) {
        this.orgUnits = orgUnits;
    }

    public static class CurrentOrgUnitBean {
        /**
         * id : 2
         * parentId : null
         * code : 00000
         * displayName : 默认组织
         */

        private int id;
        private Object parentId;
        private String code;
        private String displayName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }

    public static class GrantPermissionBean {
        /**
         * permissionCode : Pages
         * permissionName : Pages
         * childPermissions : [{"permissionCode":"Pages.QualityControl","permissionName":"质检管理","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC","permissionName":"IPQC质检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollectionSizeFunction","permissionName":"IPQC抽检尺寸功能","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.LQC","permissionName":"成品抽检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage","permissionName":"批抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCLotQA","permissionName":"栈板Lot绑定维护","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCCollection","permissionName":"批抽检采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCQuery","permissionName":"OQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.REL","permissionName":"REL实验室","childPermissions":[{"permissionCode":"Pages.QualityControl.REL.RELCollection","permissionName":"REL数据采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.REL.RELQuery","permissionName":"REL查询","childPermissions":null}]}]},{"permissionCode":"Pages.ProductionPlan","permissionName":"生产计划","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection","permissionName":"数据采集","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard","permissionName":"产品序列号打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.CreateRCard","permissionName":"生成序列号","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Export","permissionName":"导出","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Print","permissionName":"打印序列号","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.RMO2RCard","permissionName":"产品序列号重打印","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Molding","permissionName":"注塑过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Laser","permissionName":"镭雕过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC","permissionName":"CNC过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNCInspection","permissionName":"CNC全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Polish","permissionName":"抛光过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.PolishInspection","permissionName":"抛光全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning","permissionName":"清洗过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Anneoling","permissionName":"烘烤过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning2","permissionName":"清洗(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Paint","permissionName":"漆料上料","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Coating","permissionName":"喷漆过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CoatingInspection","permissionName":"喷漆全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2","permissionName":"CNC(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2Inspection","permissionName":"CNC(二)全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning3","permissionName":"清洗(三)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.AOI","permissionName":"AOI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.SI","permissionName":"SI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial","permissionName":"供料上料","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial.AddMaterial","permissionName":"上料","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.ViewFlow","permissionName":"作业流程图","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob","permissionName":"包装作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CollectionCarton","permissionName":"包装采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo","permissionName":"箱号条码打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.CreateCartonInfo","permissionName":"生产箱号条码","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.Print","permissionName":"打印箱号条码","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.QueryCartonInfo","permissionName":"装箱明细","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.QueryCarton2RCard","permissionName":"装箱查询","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.OBARework","permissionName":"OBA返工","childPermissions":[{"permissionCode":"Pages.ProductionPlan.OBARework.ReworkUnPacking","permissionName":"返工拆箱","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.OBARework.ReworkBinding","permissionName":"返工单绑定","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.TSJob","permissionName":"返工作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.TSJob.TSComplete","permissionName":"返工返工","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.TSJob.TSRCardReflow","permissionName":"返工查询","childPermissions":null}]}]}]
         */

        private String permissionCode;
        private String permissionName;
        private List<ChildPermissionsBeanXXX> childPermissions;

        public String getPermissionCode() {
            return permissionCode;
        }

        public void setPermissionCode(String permissionCode) {
            this.permissionCode = permissionCode;
        }

        public String getPermissionName() {
            return permissionName;
        }

        public void setPermissionName(String permissionName) {
            this.permissionName = permissionName;
        }

        public List<ChildPermissionsBeanXXX> getChildPermissions() {
            return childPermissions;
        }

        public void setChildPermissions(List<ChildPermissionsBeanXXX> childPermissions) {
            this.childPermissions = childPermissions;
        }

        public static class ChildPermissionsBeanXXX {
            /**
             * permissionCode : Pages.QualityControl
             * permissionName : 质检管理
             * childPermissions : [{"permissionCode":"Pages.QualityControl.IPQC","permissionName":"IPQC质检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollectionSizeFunction","permissionName":"IPQC抽检尺寸功能","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.LQC","permissionName":"成品抽检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage","permissionName":"批抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCLotQA","permissionName":"栈板Lot绑定维护","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCCollection","permissionName":"批抽检采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCQuery","permissionName":"OQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.REL","permissionName":"REL实验室","childPermissions":[{"permissionCode":"Pages.QualityControl.REL.RELCollection","permissionName":"REL数据采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.REL.RELQuery","permissionName":"REL查询","childPermissions":null}]}]
             */

            private String permissionCode;
            private String permissionName;
            private List<ChildPermissionsBeanXX> childPermissions;

            public String getPermissionCode() {
                return permissionCode;
            }

            public void setPermissionCode(String permissionCode) {
                this.permissionCode = permissionCode;
            }

            public String getPermissionName() {
                return permissionName;
            }

            public void setPermissionName(String permissionName) {
                this.permissionName = permissionName;
            }

            public List<ChildPermissionsBeanXX> getChildPermissions() {
                return childPermissions;
            }

            public void setChildPermissions(List<ChildPermissionsBeanXX> childPermissions) {
                this.childPermissions = childPermissions;
            }

            public static class ChildPermissionsBeanXX {
                /**
                 * permissionCode : Pages.QualityControl.IPQC
                 * permissionName : IPQC质检维护
                 * childPermissions : [{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollectionSizeFunction","permissionName":"IPQC抽检尺寸功能","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]
                 */

                private String permissionCode;
                private String permissionName;
                private List<ChildPermissionsBeanX> childPermissions;

                public String getPermissionCode() {
                    return permissionCode;
                }

                public void setPermissionCode(String permissionCode) {
                    this.permissionCode = permissionCode;
                }

                public String getPermissionName() {
                    return permissionName;
                }

                public void setPermissionName(String permissionName) {
                    this.permissionName = permissionName;
                }

                public List<ChildPermissionsBeanX> getChildPermissions() {
                    return childPermissions;
                }

                public void setChildPermissions(List<ChildPermissionsBeanX> childPermissions) {
                    this.childPermissions = childPermissions;
                }

                public static class ChildPermissionsBeanX {
                    /**
                     * permissionCode : Pages.QualityControl.IPQC.IPQCManage
                     * permissionName : IPQC抽检作业
                     * childPermissions : [{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollectionSizeFunction","permissionName":"IPQC抽检尺寸功能","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]
                     */

                    private String permissionCode;
                    private String permissionName;
                    private List<ChildPermissionsBean> childPermissions;

                    public String getPermissionCode() {
                        return permissionCode;
                    }

                    public void setPermissionCode(String permissionCode) {
                        this.permissionCode = permissionCode;
                    }

                    public String getPermissionName() {
                        return permissionName;
                    }

                    public void setPermissionName(String permissionName) {
                        this.permissionName = permissionName;
                    }

                    public List<ChildPermissionsBean> getChildPermissions() {
                        return childPermissions;
                    }

                    public void setChildPermissions(List<ChildPermissionsBean> childPermissions) {
                        this.childPermissions = childPermissions;
                    }

                    public static class ChildPermissionsBean {
                        /**
                         * permissionCode : Pages.QualityControl.IPQC.IPQCManage.IPQCCollection
                         * permissionName : IPQC抽检外观
                         * childPermissions : null
                         */

                        private String permissionCode;
                        private String permissionName;
                        private Object childPermissions;

                        public String getPermissionCode() {
                            return permissionCode;
                        }

                        public void setPermissionCode(String permissionCode) {
                            this.permissionCode = permissionCode;
                        }

                        public String getPermissionName() {
                            return permissionName;
                        }

                        public void setPermissionName(String permissionName) {
                            this.permissionName = permissionName;
                        }

                        public Object getChildPermissions() {
                            return childPermissions;
                        }

                        public void setChildPermissions(Object childPermissions) {
                            this.childPermissions = childPermissions;
                        }
                    }
                }
            }
        }
    }

    public static class OrgUnitsBean {
        /**
         * id : 2
         * parentId : null
         * code : 00000
         * displayName : /  默认组织
         */

        private int id;
        private Object parentId;
        private String code;
        private String displayName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }
}
