package com.zhidi.crm.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.system.entity.Message;
import com.zhidi.crm.system.service.IMessageService;

@Controller
@RequestMapping("/system/message")
public class MessageController {

	@Autowired
	private IMessageService messageService;
	
	@GetMapping("/MessageAction_list")
	public String toMessage(){
		return "system/message/list_message";
	}
	
	@ResponseBody
	@PostMapping("/MessageAction_findByPage")
	public ResultData findByPage(Pager<Message> pager){
		return ResultData.buildSuccessResult("", messageService.findByPage(pager));
	}
	
	@GetMapping("/MessageAction_write")
	public String write(){
		return "system/message/write_message";
	}
}
