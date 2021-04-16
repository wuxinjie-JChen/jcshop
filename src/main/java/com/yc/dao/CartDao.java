package com.yc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yc.bean.CartBean;

public interface CartDao {

	@Insert("insert into cart values(null,#{gid},#{uid},#{num})")
	public int add(CartBean cart);
	
	@Update("update cart set num=num+#{num} where gid=#{gid} and uid=#{uid}")
	public int update(CartBean cart);
	
	@Select("select * from cart where uid=#{uid}")
	@Results(id="rmcart",value={@Result(column="gid",property="good",
				one=@One(select="com.yc.dao.GoodDao.findById"))})
	public List<CartBean> queryByUid(int uid);

	@Delete("delete from cart where id=#{id}")
	public int deleteById(int id);

	@Delete("delete from cart where uid=#{id}")
	public int clear(int id);

	@Delete("delete from cart where gid = #{gid} and uid = #{uid}")
	public void delete(@Param("gid")int gid, @Param("uid")int uid);
}
