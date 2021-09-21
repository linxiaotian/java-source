package com.lxt.learnsource.jdbc;

import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class InsertJob implements Runnable {

    private int type;

    private Connection conn;

    public InsertJob(int type, Connection conn) {
        this.type = type;
        this.conn = conn;
    }

    @SneakyThrows
    @Override
    public void run() {
        int start = 1;
        if (type != 1) {
            start = (type - 1) * 10000 + 1;
        } else {
            start = 1;
        }
        int end = type * 10000;

        for (int i=0;i<10000;i++) {
            insertJdbcPtmt(conn,start);
            ++start;
        }

    }

    private void insertJdbc(Connection conn, int orderId) {

        OrderInfoEntity order = new OrderInfoEntity();
        order.setOrderId(String.valueOf(orderId));
        order.setOrderSn(new Date().getTime());
        order.setUserId("789234");
        order.setReceiveUser("huanzhang");
        order.setProvinve("010");
        order.setCity("010");
        order.setDistrict("124");
        order.setAddress("二道港2幢");
        order.setPaymentMethod("1");
        order.setOrderMoney("100.2");
        order.setDistrictMoney("10.2");
        order.setExpressMoney("5.0");
        order.setPaymentMoney("200.3");

        order.setExpressName("申通");
        order.setExpressSn("1000234234");
        order.setOrderStatus("1");
        order.setOrderPoint("100");
        JdbcService.insertOrder(conn,order);
    }

    private void insertJdbcPtmt(Connection conn, int orderId) {

        OrderInfoEntity order = new OrderInfoEntity();
        order.setOrderId(String.valueOf(orderId));
        order.setOrderSn(new Date().getTime());
        order.setUserId("789234");
        order.setReceiveUser("huanzhang");
        order.setProvinve("010");
        order.setCity("010");
        order.setDistrict("124");
        order.setAddress("二道港2幢");
        order.setPaymentMethod("1");
        order.setOrderMoney("100.2");
        order.setDistrictMoney("10.2");
        order.setExpressMoney("5.0");
        order.setPaymentMoney("200.3");

        order.setExpressName("申通");
        order.setExpressSn("1000234234");
        order.setOrderStatus("1");
        order.setOrderPoint("100");
        JdbcService.insertOrderPtmt(conn,order);
    }


}
