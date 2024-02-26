package com.zhidi.crm.manager.controller;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.manager.entity.Business;
import com.zhidi.crm.manager.service.IBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/manage/business")
public class BusinessController {
	
	@Autowired
	private IBusinessService businessService;

	@GetMapping("/BusinessAction_index")
	public String toBusiness(){
		log.info("++++++++++++++++跳转营销页面++++++++++++++++++++++");
		return "manage/business/list_business";
	}
	
	@ResponseBody
	@GetMapping("/BusinessAction_see")
	public ModelAndView toSee(String businessid,ModelAndView mv){
		log.info("++++++++++++++++++跳转查看页面++++++++++++++++++++");
		mv.addObject("busi", businessService.selectById(businessid));
		mv.setViewName("manage/business/see_business");
		return mv;
	}
	@GetMapping("/BusinessAction_edit")
	public ModelAndView toedit(String businessid,ModelAndView mv){
		log.info("+++++++++++++++++跳转修改页面+++++++++++++++++");
		mv.addObject("busi", businessService.selectById(businessid));
		mv.setViewName("manage/business/edit_business");
		return mv;
	}
	
	@ResponseBody
	@PostMapping("/BusinessAction_findByPage")
	public ResultData selectByPage(Pager<Business> pager, String name, String cname, String nsname, String oname){
		log.info("++++++++++++++++++++++加载数据+++++++++++++++++++++++++");
		return ResultData.buildSuccessResult("加载成功",businessService.selectByPage(pager, name, cname, nsname, oname));
		
	}
	
}
