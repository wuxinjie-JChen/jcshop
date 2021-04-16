package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yc.bean.OrderBean;
import com.yc.bean.OrderItem;

public interface OrderDao {

	@Insert("insert into `order` values(#{id},#{uid},#{createTime},#{money},'111',0)")
	void add(OrderBean order);
	
	@Select("select * from `order` where id=#{id}")
	@Results(@Result(column="uid",property="user",one=@One(select="com.yc.dao.UserDao.findById")))
	OrderBean selectById(Long id);

	@Select("select * from `order` where uid=#{uid}")
	List<OrderBean> selectByUid(int id);

	@Update("update `order` set state=#{state} where id=#{id}")
	void update(OrderBean order);
}
