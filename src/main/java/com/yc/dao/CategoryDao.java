package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.bean.CategoryBean;

public interface CategoryDao {

	@Insert("insert into category values(null,#{name},#{classes})")
	public void add(CategoryBean bean);
	
	@Select("select * from category")
	public List<CategoryBean> find();
	
	@Select("select * from category where id=#{id}")
	public CategoryBean findById(int id);
	
	@Select("select * from category where classes like concat('%',#{name},'%')")
	@Results(@Result(column="id",property="goods",
			many=@Many(select="com.yc.dao.GoodDao.findByClassId")))
	public CategoryBean findByClassName(String name);
}
