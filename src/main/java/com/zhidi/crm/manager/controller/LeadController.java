package com.zhidi.crm.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 线索管理页面的处理器
 * @author jqllxew
 *
 */
@Controller
@RequestMapping("/manage/leads")
public class LeadController {

	@GetMapping("/LeadAction_list")
	public String toLead(){
		return "manage/leads/list_lead";
	}
}
