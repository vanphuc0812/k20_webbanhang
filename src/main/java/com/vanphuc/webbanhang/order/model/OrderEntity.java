package com.vanphuc.webbanhang.order.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderEntity {
    public final static String TABLE_NAME = "WBH_ORDER";

    @UtilityClass
    public class OrderUser {
        public final static String TABLE_NAME = "WBH_ORDER_USER";
        public final static String USERID = "USER_ID";
        public final static String ORDERID = "ORDER_ID";
        public final static String ORDER_MAPPED_USER = "user";
    }

    @UtilityClass
    public class OrderProduct {
        public final static String TABLE_NAME = "WBH_ORDER_PRODUCT";
        public final static String PRODUCTID = "PRODUCT_ID";
        public final static String ORDERID = "ORDER_ID";
        public final static String ORDER_MAPPED_PRODUCT = "products";
    }
}
