package com.zhidi.crm.exception.resolver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhidi.crm.exception.MyException;

public class MyExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String xr = request.getHeader("X-Requested-With");
		//判断是否为ajax请求
		if("XMLHttpRequest".equals(xr)){
			response.setContentType("application/json;charset=UTF-8");
			
			System.out.println(ex instanceof MyException);
			try {
				PrintWriter writer = response.getWriter();
				Map<String, Object> map = new HashMap<>();
				map.put("ex", ex.getMessage());
				
				ObjectMapper om = new ObjectMapper();
				om.writeValue(writer, map);
				writer.flush();
				writer.close();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("ex", ex);
		mv.setViewName("myex");
		return mv;
	}

}
