package com.wzw.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/*")
public class urlFilter implements Filter{
	
	// 不需要登录的 URI	不需要拦截的url
	private final String[] IGNORE_URI = {"/index","/account/validataAccount","/css/","/js/","/account/login","/images"};

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		String uri = request.getRequestURI();
		System.out.println("url:"+uri);
		
		Boolean pass = canPassIgnore(uri);
		
		/**
		 * 当前访问的URI是不是 在Ignore列表里  
		 * 登录页，注册页，首页 放行 
		 * 其他判断是否有登录标识 有 则放行
		 */
		if(pass) {
			// 在的话 放行 
			chain.doFilter(request, response);
			return;
		}
		// 是否已登录，从session里找 Account
		Object attribute = request.getSession().getAttribute("account");
		if(null==attribute) {
			// 没登录 跳转登录页面
			response.sendRedirect("/account/login");
			return;
		}else {
			chain.doFilter(request, response);
			return;
		}
	}

	private Boolean canPassIgnore(String uri) {
		// /index = uri
		// 判断 访问的URI 起始部分 是否包含 Ignore 
		// 下级目录资源也能访问
		for(String var : IGNORE_URI) {
			if(uri.startsWith(var)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 加载 Filter 启动之前需要的资源
		System.out.println("urlFilter start....");
		Filter.super.init(filterConfig);
	}
}
