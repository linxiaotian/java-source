package com.lxt.learnsource.jdbc;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderInfoEntity implements Serializable {

    private static final long serialVersionUID = 3905569103329001812L;

    private String orderId;

    private Long orderSn;

    private String userId;

    private String receiveUser;

    private String provinve;

    private String city;

    private String district;

    private String address;

    private String paymentMethod;

    private String orderMoney;

    private String districtMoney;

    private String expressMoney;

    private String paymentMoney;

    private String expressName;

    private String expressSn;

    private Date createTime;

    private Date deliverTime;

    private Date payTime;

    private Date receiveTime;

    private String orderStatus;

    private String orderPoint;

    private Date modified_time;

}
