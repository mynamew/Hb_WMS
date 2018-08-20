package com.jzk.hebi_wms.http;

/**
 * author: timi
 * create at: 2017-08-21 16:17
 */

import com.jzk.hebi_wms.base.Constants;
import com.jzk.hebi_wms.utils.SpUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装公共参数（Key和密码）
 * <p>
 * @author jzk
 */
public class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        /**
         * 请求中的Url
         */
        HttpUrl oldUrl = oldRequest.url();
        /**
         * 应用配置的服务器Url
         */
        String spBaseUrl = SpUtils.getInstance().getBaseUrl();
        /**
         * Request的url
         */
        HttpUrl parseUrl = null;
        // 为了测试下载
        if (oldUrl.toString().contains("ApkVersion")) {
            parseUrl = oldUrl;
        } else {
            /**
             * 对url 进行处理 当本地sp 存储的是和Constants不同的url 的时候进行替换BaseUrl的操作
             */
            if (!spBaseUrl.equals(oldUrl.toString())) {
                try {
                    //生成转换的url
                    parseUrl = HttpUrl.parse(spBaseUrl + oldUrl.toString().replace(Constants.BASE_URL, ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {//相同的则直接转换
                parseUrl = oldUrl;
            }
        }
        /**
         * Request 对象
         */
        Request newRequest = null;
        /**
         * 拦截器  登录的请求的时候不进行header 的设置
         */
        if (!oldUrl.toString().contains("ClientLogin")) {
            // 普通请求
            newRequest = oldRequest.newBuilder()
                    //类型
                    .addHeader("Content-Type", "application/json")
                    //请求的token
                    .addHeader(Constants.AUTHORIZATION, SpUtils.getInstance().getAuthorization())
                    //请求的语言
                    .addHeader(Constants.LOCALE_LAUGUAGE, SpUtils.getInstance().getLocaleLanguage())
                    .method(oldRequest.method(), oldRequest.body())
                    .url(parseUrl)
                    .build();
        } else {
            // 登录请求
            newRequest = oldRequest.newBuilder()
                    //类型
                    .addHeader("Content-Type", "application/json")
                    //请求的语言
                    .addHeader(Constants.LOCALE_LAUGUAGE, SpUtils.getInstance().getLocaleLanguage())
                    .method(oldRequest.method(), oldRequest.body())
                    .url(parseUrl)
                    .build();
        }
        return chain.proceed(newRequest);
    }
}
