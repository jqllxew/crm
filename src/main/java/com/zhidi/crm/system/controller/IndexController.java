package com.zhidi.crm.system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.MenuVo;
import com.zhidi.crm.system.service.IFunctionService;
@Controller
public class IndexController {
	@Autowired
	private IFunctionService functionService;
	
	@ResponseBody
	@GetMapping("/getFunctions")
	public ResultData test3(HttpSession session){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return null;
		List<MenuVo> data = functionService.getmyFunction(user.getId());
		
		return ResultData.buildSuccessResult("", data);
	}
}
