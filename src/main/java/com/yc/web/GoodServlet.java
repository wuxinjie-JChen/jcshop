package com.yc.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yc.bean.GoodBean;
import com.yc.dao.GoodDao;

@RestController
@RequestMapping("/good")
public class GoodServlet {

	@Autowired
	private GoodDao dao;
	
	@RequestMapping("/find")
	public List<GoodBean>  find(GoodBean bean){
		List<GoodBean> list=new ArrayList<GoodBean>();
		if(bean.getId()==0){
			list= dao.find();
			System.out.println(list.size());
			return list;
		}else{
			GoodBean b=dao.findById(bean);
			System.out.println(b);
			list.add(b);
			return list;
		}
		
		
	}
	
	@RequestMapping("/findById")
	public Map<String, Object> findById(int id,@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "12") int size){
		Map<String, Object> map = new HashMap<>();
		// 分页查询
		// count 参数： 表示是否查询总行数
		boolean count = true;
		if (page<2) {
			page = 1;
		}
		Page<GoodBean> p=PageHelper.startPage(page, size, count);
		dao.findByClassId(id);
		map.put("list", p);
		//总页数
		map.put("pages", p.getPages());
		//当前页
		map.put("page",p.getPageNum());
		return map;
	}
	
	@RequestMapping("/findByName")
	public Map<String, Object> findByName(String name,@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "12") int size){
		Map<String, Object> map = new HashMap<>();
		// 分页查询
		// count 参数： 表示是否查询总行数
		boolean count = true;
		if (page<2) {
			page = 1;
		}
		Page<GoodBean> p=PageHelper.startPage(page, size, count);
		dao.findByName(name);
		map.put("list", p);
		//总页数
		map.put("pages", p.getPages());
		//当前页
		map.put("page",p.getPageNum());
		return map;
	}
}
