package com.yc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yc.bean.UserBean;


public interface UserDao {

	@Insert("insert into user values(null,#{account},#{name},#{pwd},#{email},#{phone},null)")
	public int add(UserBean user);
	
	@Select("select * from user where account=#{account} and pwd=#{pwd}")
	public UserBean find(UserBean user);
	
	@Select("select * from user where id=#{id}")
	public UserBean findById(int id);
	
	@Update("update user set addr=#{addr} where id=#{id}")
	public void updateAddr(@Param("id") int id,@Param("addr") String addr);
}
