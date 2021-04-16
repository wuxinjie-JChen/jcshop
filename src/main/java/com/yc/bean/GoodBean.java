package com.yc.bean;

import java.io.Serializable;

import lombok.Data;
@Data
public class GoodBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6659687767508789752L;

	private int id;
	private String name;
	private double price;
	private String img;
	private int classId;
	private String detail;
	private Integer num;//购买次数
	
	private CategoryBean category;
	
}
