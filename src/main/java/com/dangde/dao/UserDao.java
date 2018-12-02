package com.dangde.dao;




import org.apache.ibatis.annotations.Param;

import com.dangde.domain.User;


public interface UserDao {
	

	
	public void insertUser(User user);
	
	public void deleteUser(Long id);
	
	public void updateUser_info(User user);
	public void updateUser_email(User user);
	public void updateUser_password(User user);

	
	public String getUser_exist_ByTel(@Param("tel")String tel);

	public User getUserById(@Param("user_id")Long user_id);
	
	public User getUserByTel_Psw(@Param("tel")String tel,@Param("psw")String psw);

	public String getUserName(@Param("id")Long id);

}
