package com.zhidi.crm.listener;

import com.zhidi.crm.system.entity.Function;
import com.zhidi.crm.system.service.IFunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jqllxew
 */
@Slf4j
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String webName = sce.getServletContext().getContextPath();
		log.info("++++++++++++++++++++应用程序"+webName+"启动++++++++++++++++++++");
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		IFunctionService functionService = context.getBean(IFunctionService.class);
		List<Function> list = functionService.queryfindAll();
		List<String> urls = new ArrayList<>();
		log.info("++++++++++++++++++++加载全部权限+++++++++++++++++++++++");
		for (Function func : list) {
			log.info(webName+"/"+func.getFuncurl());
			urls.add(webName+"/"+func.getFuncurl());
		}
		log.info("++++++++++++++++++++权限加载完成++++++++++++++++++++");
		sce.getServletContext().setAttribute("urls", urls);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
