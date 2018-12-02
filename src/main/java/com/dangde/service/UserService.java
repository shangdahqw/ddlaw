package com.dangde.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dangde.domain.User;




public interface UserService {

	
	public void saveUser(User user);
	
	public void deleteUser(Long id);

	public void updateUser_info(User user);
	public void updateUser_email(User user);
	public void updateUser_password(User user);

	
	public String findUser_exitByTel(String tel);
	public User getUserById(Long user_id);
	
	public User findUserByTel_Psw(String tel,String psw);

	public List<User> findFriends(Long userid);
	public void logout(Long user_id);


}
