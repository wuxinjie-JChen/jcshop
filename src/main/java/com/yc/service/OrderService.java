package com.yc.service;

import com.yc.bean.OrderBean;

public interface OrderService {

	public Long add(OrderBean order);

	void payOk(Long id);

}
