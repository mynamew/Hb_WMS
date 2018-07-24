package com.jzk.hebi_wms.base;

/**
 * 需要的静态变量
 */

public class Constants {
    /**
     * base url
     */
    public static final String BASE_URL = "http://szjuqent.imwork.net:91/";
    //    public static final String BASE_URL = "http://192.168.0.39:8066/";
    //    public static final String BASE_URL = "http://192.168.18.117:8066/";
    //超时时间   2分钟
    public static int DEFAULT_TIMEOUT = 2;

    /***********
     * PDA  配置
     *********************************************************************************************/
    //是否有备品
    public static final String IS_GIVE_GOOD = "is_give_goods";
    //是否有附加属性
    public static final String IS_MATERAIL_ATTR = "is_material_attr";
    //是否无纸化作业（即无条码，直接通过单据列表找到单号等数据）
    public static final String IS_BILL_LIST = "is_bill_list";
    /**********
     * 状态栏颜色
     *********************************************************************************************/
    public static final String StatusColorStr = "#c6ae75";
    /**********
     * SharePerference　存储用户信息 相关
     *********************************************************************************************/
    //记录密码
    public static final String REMENBER_PSW = "REMENBER_PSW";
    //是否是第一次登录
    public static final String IS_FIRST_LOG = "IS_FIRST_LOG";
    //用户名
    public static final String USER_NAME = "USER_NAME";
    //用户编号
    public static final String USER_NUM = "USER_NUM";
    //用户密码
    public static final String USER_PSW = "USER_PSW";
    //用户部门
    public static final String USER_DEPART = "USER_DEPART";
    //用户性别
    public static final String USER_SEX = "USER_SEX";
    //用户手机
    public static final String USER_TEL = "USER_TEL";
    //用户所属组织
    public static final String USER_FROM = "USER_FROM";
    //组织id
    public static final String ORGANAZATION_ID = "ORGANAZATION_ID";
    //用户权限组织
    public static final String USER_ROOT = "USER_ROOT";
    //登录的id
    public static final String CUSER_ID = "USER_ID";
    //租户信息
    public static final String TENANCY_NAME = "TENANCY_NAME";
    //用户所有的信息
    public static final String USER_INFO = "USER_INFO";
    /***********
     * 扫码相关
     *********************************************************************************************/
    public static final int REQUEST_CODE = 1001;//主页跳转到扫码
    public static final int REQUEST_SCAN_CODE_MATERIIAL = 1002;//物料条码 扫码
    public static final int REQUEST_SCAN_CODE_LIB_LOATION = 1003;//库位码  扫码
    public static final int REQUEST_SCAN_CODE_RETURN_MATERIAL = 1004;//退料单号  扫码
    public static final int REQUEST_SCAN_CODE_BARCODE = 1005;//条码
    public static final int REQUEST_SCAN_CODE_CONTAINER = 1006;//容器
    public static final int REQUEST_SCAN_CODE_ORDERNO = 1007;//单号
    /***********
     * 蓝牙相关
     *********************************************************************************************/

    public static final int REQUEST_CODE_BLUETOOTH_ON = 1008;//打开蓝牙
    public static final int REQUEST_CODE_OPEN_GPS = 1009;//打开GPS
    public static final int REQUEST_CODE_PERMISSION_LOCATION = 1010;//定位
    /***********
     * SharePerference存储token   key /  value的前缀
     *********************************************************************************************/
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_VALUE = "Bearer ";
    /***********
     * SharePerference存储工序  key /  value的前缀
     *********************************************************************************************/
    public static final String PROCESS_SELECT = "PROCESS_SELECT";
    public static final String PROCESS_SELECT_CODE = "PROCESS_SELECT_CODE";
    /***********
     *SharePerference　存储 应用语言设置 中文简体（zh-CN）、中文繁体（zh-TW）、English（en）
     *********************************************************************************************/
    public static final String LOCALE_LAUGUAGE = "Abp.Localization.CultureName";
    /***********
     * SharePerference存储用户选的baseurl
     *********************************************************************************************/
    public static final String SP_BASE_URL = "SP_BASE_URL";
    /***********
     * 跳转 传递的相关字段
     *********************************************************************************************/
    /***********
     * 跳转到登录界面 是否显示服务配置的弹框（ 可能是来自于服务配置按钮的点击）
     *********************************************************************************************/
    public static final String IS_NEED_SHOW_SHOW_SERVER_SET = "isNeedShowServerSet";
    /***********
     * 设备类型
     * 1、INJECT_MOLD 注塑机
     * 2、MOULD 模具
     * 3、FEED  供料机
     *********************************************************************************************/
    public enum  DeviceType{
        /**
         * 注塑机
         */
        MOLDING,
        /**
         * 模具
         */
        MOULD,
        /**
         * 上料机
         */
        FEED,
        /**
         * CNC
         */
        CNC,
        /**
         * 抛光设备
         */
        POLISH,
    }

}
