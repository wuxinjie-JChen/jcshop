package com.yc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yc.bean.CartBean;
import com.yc.bean.GoodBean;
import com.yc.bean.OrderBean;
import com.yc.bean.OrderItem;
import com.yc.bean.UserBean;
import com.yc.dao.OrderDao;
import com.yc.dao.OrderItemDao;
import com.yc.service.CartService;
import com.yc.service.OrderService;
import com.yc.util.Result;

@RestController
@RequestMapping("/order")
public class OrderServlet {
	
	
	@Autowired
	private OrderDao od;
	
	@Autowired
	private OrderItemDao oid;
	
	@Autowired
	private OrderService os;

	@RequestMapping("add")
	public Result add(OrderBean order,@SessionAttribute UserBean user){
		System.out.println(order);
		try {
			order.setUid(user.getId());
			Long oid=os.add(order);
			return Result.success("订单提交成功",oid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.failure("订单提交失败",null);
		}
		
	}
	
	@RequestMapping("queryById")
	public List<OrderItem> queryByOid(String id){
		Long oId=Long.parseLong(id);
		return oid.findByOid(oId);
	}
	
	@RequestMapping("queryById2")
	public OrderBean queryByOid2(String id,HttpSession session){
		Long oId=Long.parseLong(id);
		TreeMap<Integer,Object> a=new TreeMap<Integer, Object>();
		a.put(1,oId);
		a.put(2,od.selectById(oId).getMoney());
		a.put(3,"杰辰购物");
		session.setAttribute("ppp",a);
		return od.selectById(oId);
	}
	
	@RequestMapping("queryByUser")
	public Map<String, Object> queryByUser(@SessionAttribute UserBean user,@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size){
		Map<String, Object> map = new HashMap<>();
		// 分页查询
		// count 参数： 表示是否查询总行数
		boolean count = true;
		if (page<2) {
			page = 1;
		}
		Page<GoodBean> p=PageHelper.startPage(page, size, count);
		od.selectByUid(user.getId());
		map.put("list", p);
		//总页数
		map.put("pages", p.getPages());
		//当前页
		map.put("page",p.getPageNum());
		return map;
	}
	
	@RequestMapping("payOk")
	public void payOk(String id){
		Long oId=Long.parseLong(id);
		os.payOk(oId);
	}
	
}
