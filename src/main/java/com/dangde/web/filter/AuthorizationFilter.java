package com.dangde.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dangde.dao.cache.RedisDao;
import com.dangde.utils.CheckResult;
import com.dangde.utils.Constant;
import com.dangde.utils.JwtUtil;
import com.dangde.utils.TokenModel;
import com.google.gson.Gson;
import com.dangde.responseModle.ResultData;
import io.jsonwebtoken.Claims;
public class AuthorizationFilter implements Filter {
	
	private RedisDao redisDao;
	public RedisDao getRedisDao() {
		return redisDao;
	}


	public void setRedisDao(RedisDao redisDao) {
		this.redisDao = redisDao;
	}


	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String url=request.getRequestURI();
		
		
		if(url.startsWith("/ddlaw/user/login")||url.startsWith("/ddlaw/user/register")){
			chain.doFilter(request, response);
			return;
		}
			
		
		//认证用户
		// 取得token
		String token = request.getHeader("Authorization");

        if (token == null || token.equals("")) {
    		ResultData<Object> resultData =new ResultData<Object>();
    		resultData.setStatus(Constant.NOLOGIN);
    		resultData.setMessage("用户未登入!");
    		response.getWriter().write(new Gson().toJson(resultData)); ;
    		return;
        	
        }
        
        CheckResult checkResult=JwtUtil.validateJWT(token);//验证token
        
		ResultData<Object> resultData =new ResultData<Object>();
		
        if(checkResult.isSuccess()){ 
        	
        	Claims c = checkResult.getClaims();    
        	long user_id=Long.parseLong(c.getSubject());
        	
        	TokenModel tokenModel=redisDao.getTokenModel(user_id);
        	
        	if(tokenModel!=null&&token.equals(tokenModel.getToken())){

        		request.setAttribute ("user_id", user_id);
                chain.doFilter(req, resp);
    			return;

        	}else{
            	resultData.setStatus(Constant.TOKEN_EXPIRE);
            	resultData.setMessage("Token过期");
        		response.getWriter().write(new Gson().toJson(resultData));
        		return;
        	}
  
        }else{ //验证不成功
        	resultData.setStatus(Constant.TOKEN_VALITADE_FAIL);
        	resultData.setMessage("Token验证不通过");
    		response.getWriter().write(new Gson().toJson(resultData));
    		return;
  
        }
            
    }		


	public void destroy() {
		// TODO Auto-generated method stub

	}

	

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
