package com.dangde.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dangde.dao.cache.RedisDao;
import com.dangde.domain.User;
import com.dangde.service.UserService;
import com.dangde.utils.Constant;
import com.dangde.utils.JwtUtil;
import com.dangde.utils.TokenModel;
import com.dangde.responseModle.ResultData;



@Component
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    private RedisDao redisDao;
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("finally")
	@PostMapping(value = "/register")//
    @ResponseBody
    public ResultData<Object> register(User user){
		
		ResultData<Object> resultData =new ResultData<Object>();
		
		if(this.userService.findUser_exitByTel(user.getTel())!=null){
			resultData.setStatus(Constant.USER_EXIT);//用户已存在	
			resultData.setMessage("用户已存在");
		    return resultData;						
			
		}
		
		user.setRegTime(new Date());	
		user.setUsername(user.getTel());
		
		try {
			this.userService.saveUser(user);
			resultData.setStatus(Constant.REGISTER_SUCCESS);//注册成功
			resultData.setMessage("注册成功");


		} catch (Exception e) {
			resultData.setStatus(Constant.REGISTER_FAIL);//注册失败
			resultData.setMessage("注册失败");

		} finally{
			return resultData;
		}
		

    }
	
    @PostMapping(value = "/login")
    @ResponseBody
    public ResultData<Object> login(String tel,String password){
    	
		ResultData<Object> resultData =new ResultData<Object>();
		Map<String,Object> map=new HashMap<String,Object>();

		User user = this.userService.findUserByTel_Psw(tel, password);
		
		if(user!=null){
			
			String newtoken="";
			try {
				newtoken = JwtUtil.createJWT(Constant.JWT_ID, "Dangde",user.getId().toString());
				TokenModel tokenModel=new TokenModel(user.getId(), newtoken);
				redisDao.putTokenModel(tokenModel);
			} catch (Exception e) {
            	resultData.setStatus(Constant.INNER_ERROR);
            	resultData.setMessage("服务器出错");
    			return resultData;

			}
			map.put("userId", user.getId());
			resultData.setData(map);
			resultData.setToken(newtoken);
			resultData.setStatus(Constant.LOGIN_SUCCESS);
			resultData.setMessage("登录成功");
			return resultData;

		}else{
			resultData.setStatus(Constant.LOGIN_FAIL);
			resultData.setMessage("登录失败,账号或密码错误");
			return resultData;
		}
		
    	 	
    }
	
    
    @GetMapping("/{id}/logout")
    @ResponseBody
	public ResultData<Object> logout(HttpServletRequest request){
		ResultData<Object> resultData =new ResultData<Object>();

        Long user_id=(Long) request.getAttribute("user_id");
    	try {
			userService.logout(user_id);
		} catch (Exception e) {
        	resultData.setStatus(Constant.INNER_ERROR);
        	resultData.setMessage("服务器出错");
			return resultData;
		}
		resultData.setStatus(Constant.LOGOUT_SUCCESS);
		resultData.setMessage("登出成功");
		return resultData;

	}
    
	

	@GetMapping(value="/{id}/info")
    @ResponseBody
	public ResultData<User> myselect(HttpServletRequest request){
		ResultData<User> resultData =new ResultData<User>();
		
		Long user_id=(Long) request.getAttribute("user_id");
		User user=userService.getUserById(user_id);
		
		if(user!=null){
			resultData.setData(user);
			resultData.setStatus(Constant.SUCCESS_DATA);
			resultData.setMessage("正常响应数据");
			return resultData;
		}
	
		return null;
		
	}
	
	@PutMapping(value="/{id}/info")
	@ResponseBody
	public ResultData<Object> myupdate_info(User user,HttpServletRequest request){
		//发过来的username,sex,birthday,job,company_type,company_name
				//剩余id,password,tel,email,iconurl
		ResultData<Object> resultData =new ResultData<Object>();
		
		Long user_id=(Long) request.getAttribute("user_id");				
		try{					
			user.setId(user_id);
			this.userService.updateUser_info(user);															
		}catch (Exception e){
			
			resultData.setStatus(Constant.USER_INFO_UPDATE_ERROR);
			resultData.setMessage("修改信息失败，服务器出错了");					
			return resultData;
			
		}			
		resultData.setStatus(Constant.USER_INFO_UPDATE_SUCCESS);
		resultData.setMessage("修改信息成功");
		return resultData;		
	}
	
    
    @GetMapping("/{id}/friends")
    @ResponseBody
	public ResultData<Object> getfriends(HttpServletRequest request){
		ResultData<Object> resultData =new ResultData<Object>();

        Long user_id=(Long) request.getAttribute("user_id");
		Map<String,Object> map=new HashMap<String,Object>();

		if(user_id!=null){
	    	List<User> friendList=userService.findFriends(user_id);
	    	map.put("friendList", friendList);
			resultData.setData(map);
			resultData.setStatus(Constant.SUCCESS_DATA);
			resultData.setMessage("正常响应数据");
			
		}
		
		return resultData;
	}

	
    
}
