package com.yc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.bean.CartBean;
import com.yc.dao.CartDao;
import com.yc.service.CartService;
@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao dao;
	
	@Override
	public void addCart(CartBean cart) {
		// 添加购物车，如果已经有该商品，那么加数量
		if(dao.update(cart)==0){
			dao.add(cart);
		}
	}

}
