package com.dangde.dao.third;

import com.dangde.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface User_Friends_Dao {

  public void insertUser_Friend(@Param("user_id") Long user_id, @Param("friend_id") Long friend_id);

  public List<User> findFriends(@Param("userid") Long userid);
}
