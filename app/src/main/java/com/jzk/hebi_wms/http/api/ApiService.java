package com.jzk.hebi_wms.http.api;


import com.jzk.hebi_wms.data.LoginBean;
import com.jzk.hebi_wms.data.LoginRequest;
import com.jzk.hebi_wms.data.UserInfoBean;
import com.jzk.hebi_wms.data.VersionBean;
import com.jzk.hebi_wms.data.cnc.CncBean;
import com.jzk.hebi_wms.data.cnc.CncRequest;
import com.jzk.hebi_wms.data.inject.CheckRCardInfoRquest;
import com.jzk.hebi_wms.data.inject.EquipmentByTypeList;
import com.jzk.hebi_wms.data.inject.InjectMouldCommitRequest;
import com.jzk.hebi_wms.data.inject.InjectPassBean;
import com.jzk.hebi_wms.data.ipqc.CheckRecardInfoRequest;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcData;
import com.jzk.hebi_wms.data.ipqc.CollectionIpqcDataRequest;
import com.jzk.hebi_wms.data.ipqc.IpqcCommonResult;
import com.jzk.hebi_wms.data.ipqc.SaveCheckResultRequest;
import com.jzk.hebi_wms.data.polishing.PolishBiographyRequestBean;
import com.jzk.hebi_wms.data.polishing.PolishResultBean;
import com.jzk.hebi_wms.data.process.ProcessSelectBean;
import com.jzk.hebi_wms.data.set.ChangeOrgRequest;
import com.jzk.hebi_wms.data.set.ChangePasswordRequest;
import com.jzk.hebi_wms.data.station.AddMaterialBean;
import com.jzk.hebi_wms.data.station.AddMaterialRequest;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.data.station.NoneClass;
import com.jzk.hebi_wms.data.station.StationBean;
import com.jzk.hebi_wms.data.station.StationRequest;
import com.jzk.hebi_wms.data.station.SupplyMaterialBean;
import com.jzk.hebi_wms.data.station.ValIsInjectSameBatchRequest;
import com.jzk.hebi_wms.data.station.WorkerOrderBean;

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
     * 获取版本
     */
    @FormUrlEncoded
    @POST("api/services/app/clientVersion/GetCSVersion")
    Observable<CommonResult<VersionBean>> getAppVersion(@Field("TenantId") int tenantId, @Field("ClientCode") String clientCode);

    /**
     * 工序选择
     */
    @POST("api/services/productionplan/LoginStationCode/GetProcessList")
    Observable<CommonResult<List<ProcessSelectBean>>> getProcessList(@Body NoneClass noneClass);

    /**
     * 工位选择
     */
    @POST("api/services/productionplan/OnWipMaterial/GetStations")
    Observable<CommonResult<StationBean>> getStations(@Body StationRequest request);

    /**
     * 工单代码
     */

    @POST("api/services/productionplan/OnWipMaterial/GetMoCode")
    Observable<CommonResult<WorkerOrderBean>> getMoCode(@Body NoneClass noneClass);

    /**
     * 注塑机列表
     */
    @FormUrlEncoded
    @POST("api/services/productionplan/OnWipMaterial/GetInjectionMoldings")
    Observable<CommonResult<InjectMoldBean>> getInjectionMoldings(@Field("EqpTypeCode") String eqpTypeCode);
 /**
     * 注塑机列表
     */
    @FormUrlEncoded
    @POST("api/services/productionplan/OnWipMaterial/GetEquipmentByTypeList")
    Observable<CommonResult<EquipmentByTypeList>> getEquipmentByTypeList(@Field("EqpTypeCode") String eqpTypeCode);

    /**
     * 供料机列表
     */
    @FormUrlEncoded
    @POST("api/services/productionplan/OnWipMaterial/GetSuppliyEqps")
    Observable<CommonResult<SupplyMaterialBean>> getSuppliyEqps(@Field("EqpTypeCode") String eqpTypeCode);

    /**
     * 校验注入物料批号是否是同一批
     */
    @POST("api/services/productionplan/OnWipMaterial/ValIsInjectSameBatch")
    Observable<CommonResult<Object>> valIsInjectSameBatch(@Body ValIsInjectSameBatchRequest request);

    /**
     * 提交供料单号
     */
    @POST("api/services/productionplan/OnWipMaterial/CreateOrUpdateOnWipMaterial")
    Observable<CommonResult<AddMaterialBean>> createOrUpdateOnWipMaterial(@Body AddMaterialRequest request);

    /*******注塑过站****************************************************************************************************/
    /**
     * 注塑过站检验
     */

    @POST("api/services/productionplan/CollectionMolding/CheckRCardInfoAsync")
    Observable<CommonResult<InjectPassBean>> checkRCardInfoAsync(@Body CheckRCardInfoRquest request);

    /**
     * 注塑过站提交
     */
    @POST("api/services/productionplan/CollectionMolding/CollectionMoldingAsync")
    Observable<CommonResult<InjectPassBean>> collectionMoldingAsync(@Body InjectMouldCommitRequest request);

    /**
     * 不良代碼組
     */
    @FormUrlEncoded
    @POST("api/services/productionplan/CollectionMolding/GetErrorInfosAsync")
    Observable<CommonResult<InjectPassBean>> getErrorInfosAsync(@Field("CategoryId") int categoryId);

    /**
     * 不良代碼
     */
    @FormUrlEncoded
    @POST("api/services/productionplan/CollectionMolding/GetErrorInfoByGroupCodeAsync")
    Observable<CommonResult<InjectPassBean>> getErrorInfoByGroupCodeAsync(@Field("ErrorGroupCode") String errorGroupCode);

    /**
     * 不良代碼输入
     */
    @FormUrlEncoded
    @POST("api/services/productionplan/CollectionMolding/GetErrorInfoByErrorCodeAsync")
    Observable<CommonResult<InjectPassBean>> getErrorInfoByErrorCodeAsync(@Field("CategoryId") int categoryId, @Field("ErrorCode") String errorCode);
    /*******CNC****************************************************************************************************/
    /**
     * cnc提交
     */
    @POST("api/services/productionplan/CollectionCNC/CollectionCNCAsync")
    Observable<CommonResult<CncBean>> collectionCNCAsync(@Body CncRequest request);


    /**
     * 抛光
     */
    @POST("api/services/productionplan/CollectionPolish/CollectionPolishAsync")
    Observable<CommonResult<PolishResultBean>> collectionPolishAsync(@Body PolishBiographyRequestBean request);

    /********外观抽检******************************************************************************/
    /**
     * 获取批号信息
     *
     * @param lotNo
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/qualitycontrol/iPQCCollection/GetLotInfoAsync")
    Observable<CommonResult<IpqcCommonResult>> getLotInfoAsync(@Field("LotNo") String lotNo);

    /**
     * 生成批号
     *
     * @param ruleCode 固定字段 IPQC
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/qualitycontrol/iPQCCollection/CreateNewLotNoAsync")
    Observable<CommonResult<IpqcCommonResult>> createNewLotNoAsync(@Field("RuleCode") String ruleCode);

    /**
     * 获取质检名称
     *
     * @param noneClass
     * @return
     */
    @POST("api/services/qualitycontrol/iPQCCollection/GetIQPCNameAsync")
    Observable<CommonResult<IpqcCommonResult>> getIQPCNameAsync(@Body NoneClass noneClass);

    /**
     * 获取抽检时段
     *
     * @param noneClass
     * @return
     */
    @POST("api/services/qualitycontrol/iPQCCollection/GetTimePerodAsync")
    Observable<CommonResult<IpqcCommonResult>> getTimePerodAsync(@Body NoneClass noneClass);

    /**
     * 获取抽检工序
     *
     * @param typeCode
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/qualitycontrol/iPQCCollection/GetProcessAsync")
    Observable<CommonResult<IpqcCommonResult>> getProcessAsync(@Field("TypeCode") String typeCode);

    /**
     * 抽检校验
     *
     * @param recardInfoRequest
     * @return
     */
    @POST("api/services/qualitycontrol/iPQCCollection/CheckRCardInfoAsync")
    Observable<CommonResult<IpqcCommonResult>> checkRCardInfoAsync(@Body CheckRecardInfoRequest recardInfoRequest);
    /**
     * 批通过
     *
     * @param lotNo
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/qualitycontrol/iPQCCollection/IPQCLotPassAsync")
    Observable<CommonResult<IpqcCommonResult>> ipacLotPassAsync(@Field("LotNo") String lotNo);
    /**
     * 批退
     *
     * @param lotNo
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/qualitycontrol/iPQCCollection/IPQCLotRejectAsync")
    Observable<CommonResult<IpqcCommonResult>> ipqcLotRejectAsync(@Field("LotNo") String lotNo);
    /**
     * 获取采集数据
     *
     * @param collectionIpqcDataRequest
     * @return
     */
    @POST("api/services/qualitycontrol/collectionIPQCData/GetCollectionIPQCDataAsync")
    Observable<CommonResult<CollectionIpqcData>> getCollectionIPQCDataAsync(@Body CollectionIpqcDataRequest collectionIpqcDataRequest);
    /**
     * 保存采集数据
     *
     * @param request
     * @return
     */
    @POST("api/services/qualitycontrol/collectionIPQCData/CreateIPQCTemporaryDatasAsync")
    Observable<CommonResult<Object>> createIPQCTemporaryDatasAsync(@Body SaveCheckResultRequest request);
    /**
     * 抽检获取不良代码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/services/qualitycontrol/collectionIPQCData/GetErrorInfoByGroupCodeAsync")
    Observable<CommonResult<CollectionIpqcData>> getErrorInfoByGroupCodeAsyncByQuality(@Field("ErrorGroupCode") String errorGroupCode);


}