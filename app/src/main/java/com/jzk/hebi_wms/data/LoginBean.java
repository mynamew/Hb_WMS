package com.jzk.hebi_wms.data;

import java.util.List;

/**
 * 登录的返回
 */

public class LoginBean {


    /**
     * userId : 2
     * fullName : ADMIN
     * currentOrgUnit : {"id":2,"parentId":null,"code":"00000","displayName":"默认组织"}
     * token : v59hdnoFzUiuoqj4YzSlJYtvkhDQuFEm7cbs-R-Tifm779X1eA7aaWMAedhW4GGUgLxMH0vUaXlCJ3A4qhrGlXPGbnCLFGZBuFj7qPmmLD5oS-6-5kQhpcErVpEEJRGx5DMAHHRG9zYHfrQCKQXWerYiFfnHmNqQ_XLXkdX4y8HDZsiTAj36h7DeFqPXnz__BeQmqIGnneOI_K272BQmkVpvynY9c-IXlwMDrFogbwhOgGCyxuVadnCXMvRdc1n4O-Q68jM9nH1cR65b0CcemBtzk-UAcCgnyAeSL5ODOlull-bt-RLhLo0mI1aUhcrN05VJHylnTcguFiuQ9w-j_CD_ltpw62Dti37AgokVfn_r-kh6cVj53UEzH4G-Vt6hqZbWj1ejyeyu-eCYe-WhW73Y_ENb8UUZ7NPcXodDNyVgmUcc18iAWoHy75_qsGMd44KjGCPmm1upUbEBSUbnVu4NdYH6drKPthwvMii-PbsgAQQ2RJEqPcV6pnpActaxG9JoyEHNeBFJi4tW30gALEaHcmtwGcwmpxHCuWezX5IE6kZNRopJh5qFoXWAJkbDnhMxNMbjpaWBbMLwSUgR5w
     * grantPermission : {"permissionCode":"Pages","permissionName":"Pages","childPermissions":[{"permissionCode":"Pages.QualityControl","permissionName":"质检管理","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC","permissionName":"IPQC质检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.LQC","permissionName":"成品抽检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage","permissionName":"批抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCLotQA","permissionName":"抽检批维护","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCCollection","permissionName":"批抽检采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCQuery","permissionName":"OQC查询","childPermissions":null}]}]}]},{"permissionCode":"Pages.ProductionPlan","permissionName":"生产计划","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection","permissionName":"数据采集","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard","permissionName":"产品序列号打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.CreateRCard","permissionName":"生成序列号","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Export","permissionName":"导出","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Print","permissionName":"打印序列号","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.RMO2RCard","permissionName":"产品序列号重打印","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Molding","permissionName":"注塑过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Laser","permissionName":"镭雕过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC","permissionName":"CNC过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNCInspection","permissionName":"CNC全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Polish","permissionName":"抛光过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.PolishInspection","permissionName":"抛光全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning","permissionName":"清洗过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning2","permissionName":"清洗(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Paint","permissionName":"漆料上料","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Coating","permissionName":"喷漆过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CoatingInspection","permissionName":"喷漆全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2","permissionName":"CNC(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2Inspection","permissionName":"CNC(二)全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning3","permissionName":"清洗(三)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.AOI","permissionName":"AOI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.SI","permissionName":"SI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial","permissionName":"供料上料","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial.AddMaterial","permissionName":"上料","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.ViewFlow","permissionName":"作业流程图","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob","permissionName":"包装作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CollectionCarton","permissionName":"包装采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo","permissionName":"箱号条码打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.CreateCartonInfo","permissionName":"生产箱号条码","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.Print","permissionName":"打印箱号条码","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.QueryCartonInfo","permissionName":"装箱查询","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.TSJob","permissionName":"返工作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.TSJob.TSComplete","permissionName":"返工返工","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.TSJob.TSRCardReflow","permissionName":"返工查询","childPermissions":null}]}]}]}
     * orgUnits : [{"id":2,"parentId":null,"code":"00000","displayName":"/  默认组织 "}]
     */

    private int userId;
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
         * childPermissions : [{"permissionCode":"Pages.QualityControl","permissionName":"质检管理","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC","permissionName":"IPQC质检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.LQC","permissionName":"成品抽检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage","permissionName":"批抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCLotQA","permissionName":"抽检批维护","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCCollection","permissionName":"批抽检采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCQuery","permissionName":"OQC查询","childPermissions":null}]}]}]},{"permissionCode":"Pages.ProductionPlan","permissionName":"生产计划","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection","permissionName":"数据采集","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard","permissionName":"产品序列号打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.CreateRCard","permissionName":"生成序列号","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Export","permissionName":"导出","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.MO2RCard.Print","permissionName":"打印序列号","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.RMO2RCard","permissionName":"产品序列号重打印","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Molding","permissionName":"注塑过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Laser","permissionName":"镭雕过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC","permissionName":"CNC过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNCInspection","permissionName":"CNC全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Polish","permissionName":"抛光过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.PolishInspection","permissionName":"抛光全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning","permissionName":"清洗过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning2","permissionName":"清洗(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Paint","permissionName":"漆料上料","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Coating","permissionName":"喷漆过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CoatingInspection","permissionName":"喷漆全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2","permissionName":"CNC(二)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.CNC2Inspection","permissionName":"CNC(二)全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.Cleaning3","permissionName":"清洗(三)过站采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.AOI","permissionName":"AOI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.SI","permissionName":"SI全检采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial","permissionName":"供料上料","childPermissions":[{"permissionCode":"Pages.ProductionPlan.DataCollection.OnWipMaterial.AddMaterial","permissionName":"上料","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.DataCollection.ViewFlow","permissionName":"作业流程图","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob","permissionName":"包装作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CollectionCarton","permissionName":"包装采集","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo","permissionName":"箱号条码打印","childPermissions":[{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.CreateCartonInfo","permissionName":"生产箱号条码","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.DeleteAll","permissionName":"批删除","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.Print","permissionName":"打印箱号条码","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.PackingJob.CartonInfo.QueryCartonInfo","permissionName":"装箱查询","childPermissions":null}]},{"permissionCode":"Pages.ProductionPlan.TSJob","permissionName":"返工作业","childPermissions":[{"permissionCode":"Pages.ProductionPlan.TSJob.TSComplete","permissionName":"返工返工","childPermissions":null},{"permissionCode":"Pages.ProductionPlan.TSJob.TSRCardReflow","permissionName":"返工查询","childPermissions":null}]}]}]
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
             * childPermissions : [{"permissionCode":"Pages.QualityControl.IPQC","permissionName":"IPQC质检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]},{"permissionCode":"Pages.QualityControl.LQC","permissionName":"成品抽检维护","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage","permissionName":"批抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCLotQA","permissionName":"抽检批维护","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCCollection","permissionName":"批抽检采集","childPermissions":null},{"permissionCode":"Pages.QualityControl.LQC.LQCManage.LQCQuery","permissionName":"OQC查询","childPermissions":null}]}]}]
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
                 * childPermissions : [{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage","permissionName":"IPQC抽检作业","childPermissions":[{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]}]
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
                     * childPermissions : [{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCCollection","permissionName":"IPQC抽检外观","childPermissions":null},{"permissionCode":"Pages.QualityControl.IPQC.IPQCManage.IPQCQuery","permissionName":"IPQC查询","childPermissions":null}]
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
