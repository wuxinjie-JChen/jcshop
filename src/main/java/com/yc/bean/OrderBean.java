package com.yc.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class OrderBean implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -8653242246671739192L;

	private Long id;
	private Integer uid;
	private Double money;
	private Timestamp createTime;
	private Integer state;
	private String remark;
	
	private List<OrderItem> items;
	private UserBean user;
}
