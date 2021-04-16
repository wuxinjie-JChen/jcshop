package com.yc.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int gid;
	private int uid;
	private int num;
	private GoodBean good;
	
}
