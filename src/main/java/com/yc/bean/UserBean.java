package com.yc.bean;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5156182150062361691L;

	private int id;
	@NotEmpty(message = "用户名不能为空")
	@Pattern(regexp = "[0-9]*", message = "用户名只能用数字")
	private String account;
	
	@NotEmpty(message = "昵称不能为空")
	private String name;
	
	@NotEmpty(message = "密码不能为空")
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]{5,15}", message = "密码长度（6-16），必须以字母开头")
	private String pwd;
	
	@Email(message = "邮箱地址格式错误")
	@NotEmpty(message = "邮箱不能为空")
	private String email;
	
	@Pattern(regexp = "1\\d{10}", message = "电话号码必须是1开头的11位数字")
	private String phone;
	
}
