package org.lizhankang.http.productLines.litePOS;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.lizhankang.http.utils.ApiUtils;
import org.lizhankang.http.utils.apiRequestUtils.HuToolHTTPRequest;

public class LitePosPro {
    private final static HuToolHTTPRequest KA_API = new HuToolHTTPRequest();
    public static void main(String[] args) throws Exception {
//        purchaseApi();
//        purchasrBetaApi();
        querySaleOrder();
    }

    /**
     * purchase Api
     */
    public static void purchaseApi() throws Exception {
        String endPoint = "api/lite-pos/v1/sales/purchase";
        //body
        JSONObject bizBody = new JSONObject();
        bizBody.put("brand_code", "999888");  // {999777:PANDORA-SIT2}
        bizBody.put("store_sn", "LPK001");
        bizBody.put("workstation_sn", "0");
        bizBody.put("scene", 4);
        bizBody.put("amount", 10000);
        bizBody.put("expire_time", 20);
        bizBody.put("expired_at1", ApiUtils.getSomeDaysLaterDateTime(20));
        bizBody.put("sales_time", ApiUtils.getNowDateTime());
        bizBody.put("dynamic_id", "43780864669439820283");
        bizBody.put("currency", "156");
        bizBody.put("industry_code", "156");
        bizBody.put("pos_info", "156");
        bizBody.put("customer", "lisi");
        bizBody.put("enable_sub_tender_types1", "301");
        bizBody.put("request_id", "request_id 700001");
        bizBody.put("subject", "bufen指定");
        bizBody.put("notify_url", "https://wwww.baidu.com");
        bizBody.put("return_url1", "https://wwww.baidu.com");
        bizBody.put("description", "Description of the order created by Li Si");
        bizBody.put("check_sn", "check_sn: " + ApiUtils.getNumberString(10));

        // crm信息
        JSONObject crmAccountOption = new JSONObject();
        crmAccountOption.put("app_type", 3);
        crmAccountOption.put("mobile7", "17621712181");
        crmAccountOption.put("mobile0", "18621514943");
        crmAccountOption.put("mobile2", "18616349992");
        crmAccountOption.put("mobile9", "13167066630");
        crmAccountOption.put("mobile1", "15618422696");
        crmAccountOption.put("wx_union_id", "owGQX5vAjGkV2h3gfCQz2uBRIKLA");
        bizBody.put("crm_account_option", crmAccountOption);

        // specified_payment指定支付方式使用规则
        JSONObject specified_payment = new JSONObject();
        specified_payment.put("selected_giftcard", 2);
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
        bizBody.put("tenders1",tenders);

        KA_API.vapiGateWay(endPoint, bizBody);
    }

    /**
     * 销售类订单查询
     */
    public static void querySaleOrder(){
        String endPoint = "api/lite-pos/v1/sales/query";
        JSONObject bizBody = new JSONObject();
        bizBody.put("brand_code", "999888");
        bizBody.put("order_sn", "7903247793899526");
        KA_API.vapiGateWay(endPoint, bizBody);
    }

}
