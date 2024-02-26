package com.zhidi.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import com.zhidi.crm.system.entity.User;
/**
 * 登陆验证过滤器
 * @author jqllxew
 *
 */

@Slf4j
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("++++++++++++++++++++进入登陆拦截++++++++++++++++++++");
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		
		String uri = servletRequest.getRequestURI();
		String contextPath = servletRequest.getContextPath();
		log.info("++++++++++++++++++++请求的uri:"+uri+"++++++++++++++++++++");
		//获取会话中的对象
		User user =(User) session.getAttribute("user");
		if(user == null && !uri.equals(contextPath+"/login.do")){
			log.info("++++++++++++++++++++无用户拦截回登陆页面++++++++++++++++++++");
			servletResponse.sendRedirect(servletRequest.getContextPath()+"/login.html");
			return;
		}
		if(user !=null && uri.equals(contextPath+"/login.do")){
			log.info("++++++++++++++++++++有用户拦截回后台首页++++++++++++++++++++");
			servletResponse.sendRedirect(contextPath+"/index.do");
			return;
		}
		log.info("++++++++++++++++++++登录成功跳转++++++++++++++++++++");
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {}

}
