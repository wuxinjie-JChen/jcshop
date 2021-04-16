package com.yc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.UserBean;
import com.yc.dao.UserDao;
import com.yc.service.ServiceException;
import com.yc.service.UserService;
import com.yc.util.CheckNullUtil;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;

	@Override
	public int register(UserBean user,String repwd) throws ServiceException {
		if(!user.getPwd().equals(repwd)){
			throw new ServiceException("输入的两次密码不一致！！！");
		}
		if(dao.find(user)!=null){
			throw new ServiceException("该用户名已被注册！！！");
		}
		int a=dao.add(user);
		return a;
	}

	@Override
	public UserBean login(UserBean bean) throws ServiceException {
		CheckNullUtil.checkNull(bean.getAccount(), "用户名不能为空");
		CheckNullUtil.checkNull(bean.getPwd(), "密码不能为空");
		return dao.find(bean);
	}

}
