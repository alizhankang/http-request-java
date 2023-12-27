package org.lizhankang.http.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public enum API {
    VapiGateWay,
    VipGateWay;
    private static final Logger log = LoggerFactory.getLogger(API.class);
    private static String DOMAIN;
    private static String APPID;
    private static String PRIVATE_KEY;
    private static String CHARSET_UTF8 = "utf8";
    private static final JSONObject body = new JSONObject();
    private static final JSONObject body_request = new JSONObject();
    private static final JSONObject body_request_head = new JSONObject();

    /**
     * 无参数的get请求
     * @param endPoint
     * @return
     */
    public String get(String endPoint){
        return get( endPoint, null, null);
    }

    /**
     * 有query参数的get请求
     * @param endPoint
     * @param paramsMap
     * @return
     */
    public String get(String endPoint, Map<String, String> paramsMap){
        return get( endPoint, paramsMap, null);
    }

    /**
     * 既有query参数又有headers参数的get请求
     * @param endPoint
     * @param paramsMap
     * @param headersMap
     * @return
     */
    public String get(String endPoint, Map<String, String> paramsMap, Map<String, String> headersMap){
        switch (this){
            case VapiGateWay:
                DOMAIN = "https://vapi.shouqianba.com/";
                APPID = "28lpm0000002";
                PRIVATE_KEY = ApiUtils.getPrivateKey("/Users/lizhankang/Documents/shouqianba/privateKey.pem");
                break;
            case VipGateWay:
                DOMAIN = "https://vip-apigateway.iwosai.com";
                APPID = "28lpm3781001";
                PRIVATE_KEY = ApiUtils.getPrivateKey("/Users/lizhankang/Documents/shouqianba/privateKey.pem");
                break;
        }
        String url = DOMAIN + endPoint;
        UrlBuilder urlBuilder = UrlBuilder.of(url);

        if (MapUtil.isNotEmpty(paramsMap)){
            UrlQuery urlQuery = UrlQuery.of(paramsMap);
            urlBuilder.setQuery(urlQuery);
        }

        String requestMessage = String.format("%s API 请求: %s", url, paramsMap);
        System.out.println(requestMessage);

        HttpRequest httpRequest = HttpRequest.of(urlBuilder);
        if (MapUtil.isNotEmpty(headersMap)) {
            httpRequest = httpRequest.addHeaders(headersMap);
        }

        // 发送请求并获取响应
        HttpResponse httpResponse = httpRequest.execute();
        int statusCode = httpResponse.getStatus();
        System.out.println("HTTP 状态码: " + statusCode);
        String responseBody = httpResponse.body();
        System.out.println(String.format("%s API 响应: %s", url, responseBody));

        return responseBody;
    }


    /**
     * 只有body参数的post请求
     * @param endPoint
     * @param bizBody
     * @return
     */
    public String post(String endPoint, JSONObject bizBody){
        return post(endPoint, bizBody, null);
    }

    /**
     * 既有body参数又有headers参数的post请求
     * @param endPoint
     * @param bizBody
     * @param headersMap
     * @return
     */
    public String post(String endPoint, JSONObject bizBody, Map<String, String> headersMap){
        switch (this){
            case VapiGateWay:
                DOMAIN = "https://vapi.shouqianba.com";
                APPID = "28lpm0000002";
                PRIVATE_KEY = ApiUtils.getPrivateKey("/Users/lizhankang/Documents/shouqianba/privateKey.pem");
                break;
            case VipGateWay:
                DOMAIN = "https://vip-apigateway.iwosai.com";
                APPID = "28lpm3781001";
                PRIVATE_KEY = ApiUtils.getPrivateKey("/Users/lizhankang/Documents/shouqianba/privateKey.pem");
                break;
        }
        body_request_head.put("version", "1.0.0");
        body_request_head.put("appid", APPID);
        body_request_head.put("request_time", ApiUtils.getNowDateTime());
        body_request_head.put("sign_type", "SHA1");
        body_request.put("head", body_request_head);
        body_request.put("body", bizBody);

        String signature = RSAB.sign(body_request.toString(), PRIVATE_KEY, CHARSET_UTF8);
        body.put("request", body_request);
        body.put("signature", signature);

        String url = DOMAIN + endPoint;
        String requestMessage = String.format("%s API 请求: %s", url, body);
        System.out.println(requestMessage);

        // 设置请求头
        HttpRequest httpRequest = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", CHARSET_UTF8)
                .body(body.toString());

        if (MapUtil.isNotEmpty(headersMap)){
            httpRequest = httpRequest.addHeaders(headersMap);
        }

        // 发送请求并获取响应
        HttpResponse httpResponse = httpRequest.execute();

        int statusCode = httpResponse.getStatus();
        System.out.println("HTTP 状态码: " + statusCode);
        String responseBody = httpResponse.body();
        System.out.println(String.format("%s API 响应: %s", url, responseBody));

        return responseBody;
    }
}
