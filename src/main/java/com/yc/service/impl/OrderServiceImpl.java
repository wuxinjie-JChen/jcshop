package com.yc.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.OrderBean;
import com.yc.bean.OrderItem;
import com.yc.dao.CartDao;
import com.yc.dao.GoodDao;
import com.yc.dao.OrderDao;
import com.yc.dao.OrderItemDao;
import com.yc.service.OrderService;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao od;
	
	@Autowired
	private OrderItemDao odm;
	
	@Autowired
	private CartDao cd;
	
	@Autowired
	private GoodDao gd;

	@Override
	public Long add(OrderBean order) {
		double money=0;
		for(OrderItem item:order.getItems()){
			double price=item.getGood().getPrice();
			int num=item.getNum();
			money+=price*num;
		}
		order.setMoney(money);
		Date date=new Date();
		Long id=date.getTime();
		order.setId(id);
		String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		Timestamp timestamp=Timestamp.valueOf(time);
        order.setCreateTime(timestamp);
		od.add(order);
		for(OrderItem item:order.getItems()){
			item.setOid(id);
			odm.insert(item);
			cd.delete(item.getGid(),order.getUid());
		}
		return order.getId();
	}
	
	@Override
	public void payOk(Long id) {
		List<OrderItem> orderItems=odm.findByOid(id);
		for(OrderItem oitem:orderItems){
			gd.update(oitem.getNum(), oitem.getGood().getId());
		}
		OrderBean ob=new OrderBean();
		ob.setId(id);
		ob.setState(1);
		od.update(ob);
	}


}
