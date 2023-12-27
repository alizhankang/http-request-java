package org.lizhankang.http.productLines;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.lizhankang.http.utils.ApiUtils;
import org.lizhankang.http.utils.ApiGateWay;
import java.util.HashMap;
import java.util.Map;
public class LitePOSTest {

    /**
     * purchase openAPI
     */
    @Test
    public void purchaseApi() {
        String endPoint = "/api/lite-pos/v1/sales/purchase";

        JSONObject bizBody = new JSONObject();
//        bizBody.put("brand_code", "999888");  // {999777:PANDORA-SIT2}
//        bizBody.put("store_sn", "LPK001");
        bizBody.put("brand_code", "100067");
        bizBody.put("store_sn", "0568");
//        bizBody.put("brand_code", "700001");
//        bizBody.put("store_sn", "yuetou01");

//        bizBody.put("brand_code", "1024");
//        bizBody.put("store_sn", "litepos");

        bizBody.put("workstation_sn", "16");
        // 1: 智能终端 2: h5 4: pc 5: 微信小程序 12: 顾客出码
        bizBody.put("scene", 10);
        bizBody.put("amount", 40000);
        bizBody.put("disposable1", "301");
        bizBody.put("enable_sub_tender_types1", "301");
        bizBody.put("disable_sub_tender_types1", "301");
        // scene = 12 时，必传
        bizBody.put("dynamic_id", "43780422335187867543");
        bizBody.put("expire_time", 200);
        bizBody.put("expired_at1", ApiUtils.getSomeDaysLaterDateTime(20));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());

        bizBody.put("subject", "bufen指定");
        bizBody.put("currency", "156");
        bizBody.put("industry_code", "156");
        bizBody.put("pos_info", "156");
        bizBody.put("customer", "lisi");
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("notify_url", "https://wwww.baidu.com");
        bizBody.put("return_url1", "https://wwww.baidu.com");
        bizBody.put("description", "Description of the order created by Li Si");
        bizBody.put("check_sn", "check_sn: " + ApiUtils.getNumberString(10));

        // crm信息
        JSONObject crmAccountOption = new JSONObject();
        // 1:金融权益; 3: 礼品卡; 4: 会员; 5: 礼品卡+储值账户
        crmAccountOption.put("app_type", 1);
        crmAccountOption.put("mobile7", "17621712181");
        crmAccountOption.put("mobile0", "18621514943");
        crmAccountOption.put("mobile2", "18616349992");
        crmAccountOption.put("mobile9", "13167066630");
        crmAccountOption.put("mobile1", "15618422696");
        crmAccountOption.put("member_id", "CIAM9308-90c5-4ccf-bb15-ec6e4e675885");
        crmAccountOption.put("wx_union_id", "owGQX5vAjGkV2h3gfCQz2uBRIKLA");
        bizBody.put("crm_account_option", crmAccountOption);

        // specified_payment指定支付方式使用规则
        JSONObject specified_payment = new JSONObject();
        // 优先礼品卡弹框
        specified_payment.put("selected_giftcard", 2);
        // 直接进入分笔收银台
        specified_payment.put("partial_payment", "1");
        JSONArray installment_tenders = new JSONArray();
        JSONObject installment_tender = new JSONObject();
        installment_tender.put("sub_tender_type", 901);
        JSONArray installment_options = new JSONArray();
        JSONObject installment_option = new JSONObject();
        installment_option.put("installment_number", 3);
        installment_option.put("installment_fee_merchant_percent", 100);
        installment_options.add(installment_option);
        installment_tender.put("installment_options", installment_options);
        installment_tenders.add(installment_tender);
        specified_payment.put("installment_tenders", installment_tenders);
        bizBody.put("specified_payment1", specified_payment);

        // tenders信息
        JSONArray tenders = new JSONArray();
        JSONObject tender1 = new JSONObject();
        tender1.put("amount","22");
        tender1.put("create_time",ApiUtils.getNowDateTime());
        tender1.put("transaction_sn", ApiUtils.getNumberString(12));
        tender1.put("tender_type", 8);
        tender1.put("sub_tender_type","803");
        tender1.put("installment_number1","3");
        tender1.put("installment_fee_merchant_percent1","100");
        tenders.add(tender1);
        bizBody.put("tenders1", tenders);

        // 生产环境
//        API.VapiGateWay.post(endPoint, bizBody);
        // 测试环境
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    @Test
    public void ipayApi() {
        String endPoint = "/api/lite-pos/v1/sales/ipay";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "1024");
        bizBody.put("store_sn", "litepos");
        bizBody.put("store_name", "litepos");
        bizBody.put("workstation_sn", "01");
        bizBody.put("amount", "1000");

        bizBody.put("scene_type", 1);
        bizBody.put("tender_type", "1");
        // 106: 钱包预授权完成
        bizBody.put("sub_tender_type", "106");
        // 预授权订单交易的轻pos流水号
        bizBody.put("dynamic_id", "");

        bizBody.put("currency", "CNY");
        bizBody.put("subject", "立即付接口订单");
        bizBody.put("description", "立即付订单");
        bizBody.put("operator", "lisi");
        bizBody.put("customer", "lisi");
        bizBody.put("industry_code", 0);
        bizBody.put("notify_url", "");
        bizBody.put("return_url", "");
        bizBody.put("pos_info", "{'terminal_id':'208812486129412'}");
        bizBody.put("check_sn", "check_sn: " + ApiUtils.getNumberString(10));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());
        bizBody.put("sales_sn", "sales_sn" + ApiUtils.getNumberString(12));
        ApiGateWay.VipGateWay.post(endPoint, bizBody);

    }

    /**
     * refund openAPI
     */
    @Test
    public void refundApi() {
        String endPoint = "/api/lite-pos/v1/sales/refund";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "999888");
        bizBody.put("store_sn", "LPK001");
        bizBody.put("store_name", "litepos");
        bizBody.put("workstation_sn", "litepos");
        bizBody.put("amount", "-1000");
        bizBody.put("scene1", "");
        bizBody.put("check_sn", "check_sn: " + ApiUtils.getNumberString(10));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());
        bizBody.put("sales_sn", "sales_sn" + ApiUtils.getNumberString(12));
        bizBody.put("currency", "CNY");
        bizBody.put("subject", "退款订单");
        bizBody.put("description", "退款订单");
        bizBody.put("operator", "lisi");
        bizBody.put("customer", "lisi");
        bizBody.put("industry_code", 0);
        bizBody.put("pos_info", "{'terminal_id':'208812486129412'}");
        bizBody.put("extended", "extended");
        bizBody.put("reflect", "DK0UXhLaLKuWxqEnMq三pwECOJ");
        bizBody.put("notify_url", "https://www.baidu.com");

        JSONArray tenders = new JSONArray();
        JSONObject tender1 = new JSONObject();
        tender1.put("amount","-1000");
        // 9: 退款
        tender1.put("operation","9");
        tender1.put("original_tender_sn","623122600100780420");
        tender1.put("pay_status","0");
        tender1.put("create_time",ApiUtils.getNowDateTime());
        tender1.put("transaction_sn", ApiUtils.getNumberString(12));
        tenders.add(tender1);
        bizBody.put("tenders", tenders);

//        KA.VipGateWay.post(endPoint, bizBody);
        ApiGateWay.VapiGateWay.post(endPoint, bizBody);
    }

    /**
     * 销售类订单查询openAPI
     */
    @Test
    public void querySaleOrderApi() {
        String endPoint = "/api/lite-pos/v1/sales/query";
        JSONObject bizBody = new JSONObject();
        bizBody.put("brand_code", "1024");
        bizBody.put("order_sn", "7902247732228366");
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    /**
     * 收银台订单查询
     */
    @Test
    public void cashierQueryOrderApi() {
        String endPoint = "/api/lpos/cashier/v2/cashier";
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("order_token", "edfc7ef89bceb52959393155365d7efa");
        ApiGateWay.VipGateWay.get(endPoint, paramsMap);
    }

    /**
     * 销售类订单取消订单openAPI
     * 只能取消订单状态为 0 和 5 状态的订单
     */
    @Test
    public void voidApi() {
        String endPoint = "/api/lite-pos/v1/sales/void";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "1024");
        bizBody.put("original_order_sn", "");
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    /**
     * 销售类订单终止接口
     * 只能终止订单状态为 0 和 5 状态的订单
     */
    @Test
    public void terminate() {
        String endPoint = "/api/lite-pos/v1/sales/terminate";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "1024");
        bizBody.put("order_sn", "");
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    /**
     * 预授权openAPI
     */
    @Test
    public void authorizationInitial() {
        String endPoint = "/api/lite-pos/v1/authorization/initial";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "1024");
        bizBody.put("store_sn", "litepos");
        bizBody.put("store_name", "测试门店");
        bizBody.put("workstation_sn", "");
        bizBody.put("scene", 2);
        bizBody.put("amount", 10);

        bizBody.put("channel_fixed", "1");
        // 6: 钱包(国际信用卡)
        bizBody.put("auth_channel", "6");
        // 当指定钱包预授权时，传 钱包信用卡的auth_code
        bizBody.put("contract_sn", "");

        bizBody.put("expire_time1", "");
        bizBody.put("return_url1", "");
        bizBody.put("sales_sn", ApiUtils.getNumberString(10));
        bizBody.put("notify_url", "");
        bizBody.put("description", "订单描述");
        bizBody.put("extension_1", "");
        bizBody.put("extension_2", "");
        bizBody.put("good_name", "");
        bizBody.put("extended", "");
        bizBody.put("reflect", "");

        bizBody.put("check_sn", ApiUtils.getNumberString(10));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());
        bizBody.put("credit_template_sn", "");

        bizBody.put("currency", 156);
        bizBody.put("partial_auth_flag", "");
        bizBody.put("subject", "预授权订单");
        bizBody.put("operator", "李四");
        bizBody.put("customer", "李四");
        bizBody.put("industry_code", "");
        bizBody.put("pos_info", "");
        // 指定 国际信用卡预授权时 传入钱包信用卡的auth_code
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    /**
     * 预授权类订单取消openAPI
     */
    @Test
    public void authorizationVoid() {
        String endPoint = "/api/lite-pos/v1/authorization/release";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "1024");
        // 原预授权订单号
        bizBody.put("original_order_sn", "");

        bizBody.put("expire_time1", 200);
        bizBody.put("expired_at1", ApiUtils.getSomeDaysLaterDateTime(20));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());
        bizBody.put("subject", "预授权完成 by purchase");
        bizBody.put("currency", "156");
        bizBody.put("industry_code", "156");
        bizBody.put("pos_info", "156");
        bizBody.put("customer", "lisi");
        bizBody.put("notify_url", "https://wwww.baidu.com");
        bizBody.put("return_url1", "https://wwww.baidu.com");
        bizBody.put("description", "Description of the order created by Li Si");
        bizBody.put("sales_sn", "sales_sn" + ApiUtils.getNumberString(12));
        bizBody.put("check_sn", "check_sn: " + ApiUtils.getNumberString(10));
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    /**
     * 预授权完成 by purchase
     */
    @Test
    public void authorizationConpleteByPurchase() {
        String endPoint = "/api/lite-pos/v1/sales/purchase";

        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
//        bizBody.put("brand_code", "999888");  // {999777:PANDORA-SIT2}
//        bizBody.put("store_sn", "LPK001");
//        bizBody.put("brand_code", "700001");
//        bizBody.put("store_sn", "yuetou01");

        bizBody.put("brand_code", "1024");
        bizBody.put("store_sn", "litepos");
        bizBody.put("workstation_sn", "16");
        // 1: 智能终端 2: h5 4: pc 5: 微信小程序 12: 顾客出码
        bizBody.put("scene", 10);
        bizBody.put("amount", 40000);
        // tenders信息
        JSONArray tenders = new JSONArray();
        JSONObject tender1 = new JSONObject();
        tender1.put("amount","22");
        tender1.put("tender_type", 1);
        tender1.put("sub_tender_type","101");
        tender1.put("original_tender_sn","623113000062655563");
        tender1.put("pay_status","0");
        tender1.put("create_time",ApiUtils.getNowDateTime());
        tender1.put("transaction_sn", ApiUtils.getNumberString(12));
        tenders.add(tender1);
        bizBody.put("tenders", tenders);

        bizBody.put("expire_time1", 200);
        bizBody.put("expired_at1", ApiUtils.getSomeDaysLaterDateTime(20));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());
        bizBody.put("subject", "预授权完成 by purchase");
        bizBody.put("currency", "156");
        bizBody.put("industry_code", "156");
        bizBody.put("pos_info", "156");
        bizBody.put("customer", "lisi");
        bizBody.put("notify_url", "https://wwww.baidu.com");
        bizBody.put("return_url1", "https://wwww.baidu.com");
        bizBody.put("description", "Description of the order created by Li Si");
        bizBody.put("sales_sn", "sales_sn" + ApiUtils.getNumberString(12));
        bizBody.put("check_sn", "check_sn: " + ApiUtils.getNumberString(10));

        // 生产环境
//        API.VapiGateWay.post(endPoint, bizBody);
        // 测试环境
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    /**
     * 预授权完成 by ipay
     */
    @Test
    public void authorizationConpleteByIpay() {
        String endPoint = "/api/lite-pos/v1/sales/ipay";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "1024");
        bizBody.put("store_sn", "litepos");
        bizBody.put("store_name", "litepos");
        bizBody.put("workstation_sn", "01");
        bizBody.put("amount", "1000");
        // 1: 付款码、协议支付
        bizBody.put("scene_type", 1);
        bizBody.put("tender_type", "1");
        // 106: 钱包预授权完成
        bizBody.put("sub_tender_type", "106");
        // 预授权订单交易的轻pos流水号
        bizBody.put("dynamic_id", "");

        bizBody.put("currency", "CNY");
        bizBody.put("subject", "立即付接口订单");
        bizBody.put("description", "立即付订单");
        bizBody.put("operator", "lisi");
        bizBody.put("customer", "lisi");
        bizBody.put("industry_code", 0);
        bizBody.put("notify_url", "");
        bizBody.put("return_url", "");
        bizBody.put("pos_info", "{'terminal_id':'208812486129412'}");
        bizBody.put("check_sn", "check_sn: " + ApiUtils.getNumberString(10));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());
        bizBody.put("sales_sn", "sales_sn" + ApiUtils.getNumberString(12));
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }

    /**
     * 预授权类订单查询openAPI
     */
    @Test
    public void authorizationOrderQuery() {
        String endPoint = "/api/lite-pos/v1/authorization/query";
        JSONObject bizBody = new JSONObject();
        bizBody.put("request_id", ApiUtils.getReuestId());
        bizBody.put("brand_code", "1024");
        // 原预授权订单号
        bizBody.put("original_order_sn", "");
        ApiGateWay.VipGateWay.post(endPoint, bizBody);
    }


}