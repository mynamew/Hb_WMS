package com.jzk.hebi_wms.http.parser;

import okhttp3.HttpUrl;

/**
 * Created by jess on 17/07/2017 18:23
 * Contact with jess.yan.effort@gmail.com
 */
public class DefaultUrlParser implements UrlParser {
    @Override
    public HttpUrl parseUrl(HttpUrl domainUrl, HttpUrl url) {

        // 如果 HttpUrl.parse(url); 解析为 null 说明,url 格式不正确,正确的格式为 "https://github.com:443"
        // http 默认端口 80,https 默认端口 443 ,如果端口号是默认端口号就可以将 ":443" 去掉
        // 只支持 http 和 https

        if (null == domainUrl) return url;

        return url.newBuilder()
                .scheme(domainUrl.scheme())
                .host(domainUrl.host())
                .port(domainUrl.port())
                .build();
    }
}
