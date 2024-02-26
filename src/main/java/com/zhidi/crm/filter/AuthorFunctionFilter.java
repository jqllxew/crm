package com.zhidi.crm.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
public class AuthorFunctionFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("++++++++++++++++++++进入权限验证++++++++++++++++++++");
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		ServletContext context = servletRequest.getServletContext();
		
		//获取用户的权限
		List<String> userUrls = (List<String>) session.getAttribute("userUrls");
		//获取所有权限
		List<String> urls = (List<String>) context.getAttribute("urls");
		//获取请求的uri
		String uri = servletRequest.getRequestURI();
		//截取？之前的uri
		int index = uri.indexOf("?");
		if(index != -1){
			uri = uri.substring(0, index);
		}
		//对请求的uri进行权限验证
		for (String url : urls) {
			if(uri.equals(url)){
				for (String userurls : userUrls) {
					if(uri.equals(userurls)){
						chain.doFilter(servletRequest, servletResponse);
						log.info("++++++++++++++++++++对请求的"+uri+"的权限验证通过++++++++++++++++++++++");
						return;
					}
				}
				log.info("++++++++++++++++++++用户没有访问该权限的权限++++++++++++++++++++++");
				servletResponse.sendRedirect(servletRequest.getContextPath()+"/nofunc.html");
				return;
			}
		}
		//如果不相同就执行
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig chain) throws ServletException {}
}
