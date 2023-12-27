package org.lizhankang.http.productLines.electrictReceipt;

import com.alibaba.fastjson.JSONObject;
import org.lizhankang.http.utils.API;


public class ReceiptPro {

    public static void main(String[] args) throws Exception {
        getUserToken();
    }

    /**
     * 获取 用户token
     */
    public static void getUserToken() throws Exception {
        JSONObject bizBody = new JSONObject();
        String endPoint = "/api/receipt/v1/brands/700001/wxuser/_get_user_token";
        // KA pro 电子小票小程序appID: wx96aee8ddb7f7e41c
        bizBody.put("appid", "wx96aee8ddb7f7e41c");
        // 李占康openid: oDfgJ5GLCXrvxkNHysnyvuYjHcpA
        bizBody.put("openid", "oDfgJ5GLCXrvxkNHysnyvuYjHcpA");
        API.VipGateWay.post(endPoint, bizBody);
    }
}
