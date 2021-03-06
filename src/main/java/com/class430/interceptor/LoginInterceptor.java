package com.class430.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String[] interceptUri = new String[]{
				"insert","delete","modify","getInformation","getMyThing","getAll","write","say"
		};
		String uri = request.getRequestURI();
		
		for(String str: interceptUri){
			if(uri.contains(str)){
				if(request.getSession().getAttribute("userId")==null){
					response.sendRedirect("/login");
					System.out.println("拦截到了(已处理)"+uri);
					return false;
				}
			}
		}
		
		return true;
	}

	
}
