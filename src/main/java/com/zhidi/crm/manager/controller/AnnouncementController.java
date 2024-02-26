package com.zhidi.crm.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/announcement")
public class AnnouncementController {

	@RequestMapping("/AnnouncementAction_list")
	public String toAnnouncement(){
		
		return "manage/announcement/list_announcement";
	}
}
