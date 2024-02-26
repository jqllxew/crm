package com.zhidi.crm.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.service.ILoginHistoryService;
import com.zhidi.crm.system.service.IMenuVoService;
import com.zhidi.crm.system.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMenuVoService menuService;
	
	@Autowired
	private ILoginHistoryService loginHistoryService;
	
	@GetMapping("/login")
	public String tologin(){
		return "redirect:login.html";
	}
	
	@ResponseBody
	@PostMapping("/login")
	public ResultData Login(HttpSession session,String username,String password,HttpServletRequest request){
		
		if(StringUtils.isEmpty(username)){
			return ResultData.buildFailureResult("请输入用户名！");
		}
		if(StringUtils.isEmpty(password)){
			return ResultData.buildFailureResult("请输入密码！");
		}
		//如果都有值就通过用户名查询
		User user = userService.selectByName(username);
		
		if(user == null){
			return ResultData.buildFailureResult("用户名不存在！");	
		}
		
		if(!password.equals(user.getPassword())){
			return ResultData.buildFailureResult("密码错误！");
		}
		if(user.getStatus() == 0){
			return ResultData.buildFailureResult("账户以禁用！");
		}
		if(user.getStatus() == 2){
			return ResultData.buildFailureResult("账号已删除！");
		}
		//都正确
		//存入登陆历史纪录,并与上次的ip地址比较
		String ip = request.getRemoteAddr();
		loginHistoryService.insertLoginHistory(user,ip);
		session.setAttribute("user", user);
		//将用户对应权限放入Session中

		List<String> urls = menuService.queryByUserId(user.getId());
		List<String> userUrls = new ArrayList<String>();
		for (String url : urls) {
			userUrls.add(request.getContextPath() + "/" +url);
		}
		session.setAttribute("userUrls", userUrls);
		
		return ResultData.buildSuccessResult();
		
	}
	@RequestMapping("/index")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:login.do";
	}
}
