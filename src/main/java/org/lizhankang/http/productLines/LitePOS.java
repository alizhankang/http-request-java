package org.lizhankang.http.productLines;


public interface LitePOS {

    /**
     * purchase Api
     */
    public static void purchaseApi(){

    }

    /**
     * 立即付openAPI
     */
    public static void ipayApi(){

    }

    /**
     * 退款订单
     */
    public static void refundApi(){

    }


    /**
     * 销售类订单查询
     */
    public static void querySaleOrderApi(){

    }


    /**
     * 收银台订单查询接口
     */
    public static void cashierQueryOrderApi(){

    }

    /**
     * 取消订单openAPI
     */
    public static void voidApi(){
    }

    /**
     * 预授权openAPI
     */
    public static void authorizationInitial(){

    }

    /**
     * 预授权取消
     */
    public static void authorizationVoid(){

    }

    /**
     * 销售类订单终止openAPI
     */
    public static void terminate(){

    }
    /**
     * 预授权完成 by purchase
     */
    public static void authorizationConpleteByPurchase(){

    }

    /**
     * 预授权完成 by ipay
     */
    public static void authorizationConpleteByIpay(){

    }

    /**
     * 预授权订单查询
     */
    public static void authorizationOrderQuery(){

    }




}
