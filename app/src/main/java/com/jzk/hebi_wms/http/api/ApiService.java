package com.jzk.hebi_wms.http.api;


import com.jzk.hebi_wms.data.LoginBean;
import com.jzk.hebi_wms.data.LoginRequest;
import com.jzk.hebi_wms.data.UserInfoBean;
import com.jzk.hebi_wms.data.process.ProcessSelectBean;
import com.jzk.hebi_wms.data.set.ChangeOrgRequest;
import com.jzk.hebi_wms.data.set.ChangePasswordRequest;
import com.jzk.hebi_wms.data.set.GetPDA_ParameterResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * retrofit 的网络请求api
 * author: timi
 * create at: 2017-08-15 09:58
 */

/**
 * 关于Api Servaice  注解的解释：
 * 1、@Field 单个表单数据提交
 * 2、@FieldMap 用map的形式提交一系列表单数据
 * 3、@Body     用于提交实体转换成的json 对象的提交（为了处理类似链表形式的提交,
 * 4、如果没有参数 则直接去除 @FormUrlEncoded 只加@Post注解即可
 * 链表形式的提交用@FieldMap是实现不了的"），
 */
public interface ApiService {
    /**
     * 登录
     *
     * @return
     */
    @POST("api/Account/ClientLogin")
    Observable<CommonResult<LoginBean>> login(@Body LoginRequest bean);
    /**
     * 获取用户信息
     */
    @FormUrlEncoded
    @POST("api/authority/GetUserInfo")
    Observable<CommonResult<UserInfoBean>> getUserInfo(@Field("userId") int userid, @Field("deviceType") int deviceType, @Field("mac") String mac);
    /**
     * 设置PDA编号
     */
    @FormUrlEncoded
    @POST("api/services/wpda/AMain/SetPDACode")
    Observable<CommonResult<Object>> setPDACode(@FieldMap Map<String, Object> params);
    /**
     * 更改密码
     */
    @POST("api/services/app/profile/ChangePassword")
    Observable<CommonResult<Object>> changePassword(@Body ChangePasswordRequest params);

    /**
     * 获取PDA编号
     */
    @FormUrlEncoded
    @POST("api/services/wpda/AMain/GetPDACode")
    Observable<CommonResult<String>> getPDACode(@FieldMap Map<String, Object> params);
    /**
     * 切换组织
     */
    @FormUrlEncoded
    @POST("api/Account/ClientChangeOrgainzation")
    Observable<CommonResult<LoginBean>> changeOrgainzation(@Body ChangeOrgRequest params);

    /**
     * 工序选择
     */
    @POST("api/services/productionplan/LoginStationCode/GetProcessList")
    Observable<CommonResult<List<ProcessSelectBean>>> getProcessList();

}
