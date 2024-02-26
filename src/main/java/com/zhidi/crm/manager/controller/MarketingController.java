package com.zhidi.crm.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/marketing")
public class MarketingController {

	@GetMapping("/MarketingAction_email")
	public String toMarketing(){
		
		return "manage/marketing/email_marketing";
	}
	
	@GetMapping("/MarketingAction_sendemail")
	public String sendemail(){
		return "manage/marketing/sendemail_marketing";
	}
	
	@GetMapping("/MarketingAction_sendmsg")
	public String sendmsg(){
		return "manage/marketing/sendmsg_marketing";
	}
}
