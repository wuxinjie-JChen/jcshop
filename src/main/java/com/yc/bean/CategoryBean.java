package com.yc.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CategoryBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4963866003936732968L;
	private int id;
	private String name;
	private String classes;
	
	private List<GoodBean> goods;
}
