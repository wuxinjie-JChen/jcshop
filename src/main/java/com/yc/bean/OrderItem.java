package com.yc.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -725563047393784030L;

	private Integer id;
    private Long oid;
    private Integer gid;
    private Integer num;
    //private OrderBean order;
    private GoodBean good;
}
