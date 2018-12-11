package com.dangde.dao;

import com.dangde.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

  public void insertUser(User user);

  public void deleteUser(Long id);

  public void updateUsernfo(User user);

  public void updateUserEmail(User user);

  public void updateUserPassword(User user);

  public String getUserExistByTel(@Param("tel") String tel);

  public User getUserById(@Param("user_id") Long userId);

  public User getUserByTelPsw(@Param("tel") String tel, @Param("psw") String psw);

  public String getUserName(@Param("id") Long id);
}
