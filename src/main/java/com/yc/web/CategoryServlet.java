package com.yc.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yc.bean.CategoryBean;
import com.yc.bean.GoodBean;
import com.yc.dao.CategoryDao;

@RestController
@RequestMapping("category")
public class CategoryServlet {

	@Autowired
	private CategoryDao dao;
	
	@RequestMapping("/find")
	public Map find(){
		List<CategoryBean> list= dao.find();
		List<String> names=new ArrayList();
		for(CategoryBean bean:list){
			if(names==null||!names.contains(bean.getName())){
				names.add(bean.getName());
			}
		}
		Map m=new HashMap<>();
		m.put("categorys", list);
		m.put("names", names);
		return m;
	}
	
	
}
