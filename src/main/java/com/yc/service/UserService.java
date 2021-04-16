package com.yc.service;

import org.springframework.stereotype.Service;

import com.yc.bean.UserBean;


public interface UserService {

	public int register(UserBean bean,String repwd) throws ServiceException;
	
	public UserBean login(UserBean bean) throws ServiceException;
}
