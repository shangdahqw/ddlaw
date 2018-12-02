package com.dangde.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

//tomcat8以后默认编码格式是utf-8;   tomcat7之前的都是iso8859-1,   如果默认情况下，tomcat使用的的编码方式：iso8859-1
// tomcat7真正解决全站乱码
public class CharacterEncodingFilter implements Filter {

	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		request.setCharacterEncoding("UTF-8");  //post  
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		
		//chain.doFilter(request, response);
		
		chain.doFilter(new MyRequest(request), response);   
	}
	
	/*包装
	1.写一个类，实现与被增强对象相同的接口
	2.定义一个变量，记住被增强对象
	3.定义一个构造方法，接收被增强对象
	4.覆盖想增强的方法
	5.对于不想增强的方法，直接调用被增强对象（目标对象）的方法
	 */
	
	class MyRequest extends HttpServletRequestWrapper{

		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		@Override
		public String getParameter(String name) {
			
			String value = this.request.getParameter(name);
			if(!request.getMethod().equalsIgnoreCase("get")){ //是post，就放行
				return value;
			}
			
			if(value==null){
				return null;
			}
			
			try {//如果是get方法
				return value = new String(value.getBytes("ISO-8859-1"),request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		
		
		
	}

	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
