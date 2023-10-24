package org.lizhankang.http.utils.apiRequestUtils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import org.lizhankang.http.utils.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HuToolHTTPRequest {
    private static final Logger log = LoggerFactory.getLogger(HuToolHTTPRequest.class);
    private static final String CHARSET_UTF8 = "utf8";
    private static final String VAPI_APPID = "28lpm0000002";
    private static final String VAPI_DOMAIN = "https://vapi.shouqianba.com/";
    private static final String VAPI_PRIVATE_KEY = ApiUtils.readPrivateKey("/Users/sqb/all-shouqianba-projects/python-projects/ka-request/common/privateKey.pem");
    private String url;
    private final JSONObject body = new JSONObject();
    private final JSONObject body_request = new JSONObject();
    private final JSONObject body_request_head = new JSONObject();


    public HuToolHTTPRequest() {

    }

    /**
     * huToolHTTP 发送请求
     */
    public String vapiGateWay(String endPoint, JSONObject bizBody){

        body_request_head.put("version", "1.0.0");
        body_request_head.put("appid", VAPI_APPID);
        body_request_head.put("request_time", ApiUtils.getNowDateTime());
        body_request_head.put("reserve", "{}");
        body_request_head.put("sign_type", "SHA1");
        body_request.put("head", body_request_head);
        body_request.put("body", bizBody);
        body.put("request", body_request);

//        String private_key = ApiUtils.readPrivateKey("/Users/sqb/all-shouqianba-projects/python-projects/ka-request/common/privateKey.pem");
        String signature = RSASignature.sign(body_request.toString(), VAPI_PRIVATE_KEY, CHARSET_UTF8);
        body.put("signature", signature);

        String requestMessage = String.format("%s API 请求: %s", endPoint, body);
        System.out.println(requestMessage);

        url = VAPI_DOMAIN + endPoint;

        // 设置请求头
        HttpRequest request = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "utf8")
                .body(body.toString());

        // 发送请求并获取响应
        HttpResponse response = request.execute();

        int statusCode = response.getStatus();
        String responseBody = response.body();
        System.out.println("HTTP 状态码: " + statusCode);
        String responseMessage = String.format("%s API 响应: %s", endPoint, responseBody);
        System.out.println(responseMessage);

        return responseBody;
    }

    public String vipGateWay(String endPoint, JSONObject bizBody){
        return "";
    }
}
