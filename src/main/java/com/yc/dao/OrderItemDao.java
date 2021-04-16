package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.bean.OrderBean;
import com.yc.bean.OrderItem;

public interface OrderItemDao {

	@Insert("insert into orderItem values(#{id},#{oid},#{gid},#{num})")
	void insert(OrderItem item);

	@Select("select * from orderItem where oid=#{id}")
	@Results(id="rmOitem",value={
			@Result(column="gid",property="good",
			one=@One(select="com.yc.dao.GoodDao.findById"))})
	List<OrderItem> findByOid(Long id);

}
