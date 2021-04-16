package com.yc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yc.bean.CartBean;
import com.yc.bean.UserBean;
import com.yc.dao.CartDao;
import com.yc.service.CartService;
import com.yc.util.Result;

@RestController
@RequestMapping("/cart")
public class CartServlet {
	
	@Autowired
	private CartService cs;
	
	@Autowired
	private CartDao cd;

	@RequestMapping("addCart")
	public Result addCart(CartBean cart,@SessionAttribute UserBean user){
		cart.setUid(user.getId());
		cs.addCart(cart);
		return Result.success("添加购物车成功",null);
	}
	
	@RequestMapping("findCart")
	public List<CartBean> findByUid(@SessionAttribute UserBean user){
		return cd.queryByUid(user.getId());
	}
	
	@RequestMapping("deleteById")
	public int deleteById(int id){
		int a= cd.deleteById(id);
		return a;
	}
	
	@RequestMapping("clear")
	public int clear(@SessionAttribute UserBean user){
		int a= cd.clear(user.getId());
		return a;
	}
}
