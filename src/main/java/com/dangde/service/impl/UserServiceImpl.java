package com.dangde.service.impl;

import com.dangde.dao.UserDao;
import com.dangde.dao.cache.RedisDao;
import com.dangde.dao.third.User_Friends_Dao;
import com.dangde.domain.User;
import com.dangde.service.UserService;
import com.dangde.utils.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

  // 日志对象
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  // 注入Service依赖
  @Autowired // @Resource
  private UserDao userDao;

  @Autowired // @Resource
  private User_Friends_Dao user_Friends_Dao;

  @Autowired private RedisDao redisDao;

  @Transactional
  @Override
  public void saveUser(User user) {

    try {
      user.setPassword(ServiceUtils.md5(user.getPassword()));
      this.userDao.insertUser(user);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      logger.error("saveUser inner error :" + e.getMessage(), e);
      // 所以编译期异常转化为运行期异常
      throw new RuntimeException("saveUser inner error :" + e.getMessage());
    }
  }

  @Override
  public void deleteUser(Long id) {
    // TODO Auto-generated method stub

  }

  @Transactional
  @Override
  public void updateUser_info(User user) {
    // TODO Auto-generated method stub
    try {
      this.userDao.updateUsernfo(user);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      logger.error("updateUser_info inner error :" + e.getMessage(), e);
      // 所以编译期异常转化为运行期异常
      throw new RuntimeException("updateUser_info inner error :" + e.getMessage());
    }
  }

  @Transactional
  @Override
  public void updateUser_email(User user) {
    // TODO Auto-generated method stub
    try {
      this.userDao.updateUserEmail(user);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      logger.error("updateUser_email inner error :" + e.getMessage(), e);
      // 所以编译期异常转化为运行期异常
      throw new RuntimeException("updateUser_email inner error :" + e.getMessage());
    }
  }

  @Transactional
  @Override
  public void updateUser_password(User user) {
    // TODO Auto-generated method stub
    try {
      this.userDao.updateUserPassword(user);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      logger.error("updateUser_password inner error :" + e.getMessage(), e);
      // 所以编译期异常转化为运行期异常
      throw new RuntimeException("updateUser_password inner error :" + e.getMessage());
    }
  }

  @Override
  public String findUser_exitByTel(String tel) {

    String exit = this.userDao.getUserExistByTel(tel);
    if (exit != null) return exit;

    return null;
  }

  @Override
  public User findUserByTel_Psw(String tel, String psw) {
    // TODO Auto-generated method stub

    return this.userDao.getUserByTelPsw(tel, ServiceUtils.md5(psw));
  }

  @Override
  public List<User> findFriends(Long userid) {

    return user_Friends_Dao.findFriends(userid);
  }

  @Override
  public User getUserById(Long user_id) {

    return this.userDao.getUserById(user_id);
  }

  @Override
  public void logout(Long user_id) {

    try {
      // todo注销
    } catch (Exception e) {
      throw new RuntimeException("logout redis error :" + e.getMessage());
    }
  }
}
