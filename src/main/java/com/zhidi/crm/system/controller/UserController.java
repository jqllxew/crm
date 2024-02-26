package com.zhidi.crm.system.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.UserVo;
import com.zhidi.crm.system.service.IUserService;

@Slf4j
@Controller
@RequestMapping("/system/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("/UserAction_list")
	public String toUser(){
		return "system/list_user";
	}
	
	
	@ResponseBody
	@PostMapping("/UserAction_findByPage")
	public ResultData ByPage(Pager<UserVo> pager){
		return ResultData.buildSuccessResult("成功", userService.findByPage(pager));
	}
	
	@GetMapping("/UserAction_edit")
	public String toEdit(Model model,String id){
		if(!StringUtils.isEmpty(id)){
			UserVo userVo = userService.queryDefault(id);
			model.addAttribute("userVO", userVo);
		}
		return "system/edit_user";
	}
	@ResponseBody
	@PostMapping("/UserAction_edit")
	public ResultData edit(UserVo uservo,User user,HttpSession session){
		User admin = (User) session.getAttribute("user");
		String adminId = admin.getId();
		
		if(StringUtils.isEmpty(uservo.getId())){
			log.info("----------用户新增操作----------");
			user.setCreateby(adminId);//设置添加人
			user.setCreatetime(new Date());//设置添加时间
			int i = userService.insertSelective(user);
			if(i <= 0){
				return ResultData.buildFailureResult("添加失败");
			}
			return ResultData.buildSuccessResult("添加成功");
		}
		log.info("----------用户修改操作----------");
		user.setUpdateby(adminId);//设置修改人
		user.setUpdatetime(new Date());//设置修改时间
		int i = userService.updateByPrimaryKeySelective(user);
		if(i <= 0){
			return ResultData.buildFailureResult("修改失败");
		}
		return ResultData.buildSuccessResult("修改成功");
	}
	@ResponseBody
	@PostMapping("/UserAction_remove")
	public ResultData removeUser(String userIds,String duserIds,HttpSession session){
		User admin = (User) session.getAttribute("user");
		if(userService.removeUser(userIds,duserIds,admin)){
			return ResultData.buildSuccessResult("已删除");
		}
		return ResultData.buildFailureResult("删除失败");
	}
	
	@GetMapping("/UserAction_assign")
	public String toAssign(Model model,String id){
		model.addAttribute("userid", id);
		return "system/assign_user";
	}
	
	@ResponseBody
	@GetMapping("/UserAction_validate")
	public ResultData validate(String username ,String userid){
		if(userService.selectValidate(username)){//数据库中不存在,则验证通过
			return ResultData.buildSuccessResult();
		}
		if(!StringUtils.isEmpty(userid)){//若存在,判断是否为修改操作
			String username2 = userService.selectByPrimaryKey(userid).getUsername();
			if(username2.equals(username)){//若未修改,则验证通过
				return ResultData.buildSuccessResult();
			}
		}
		return ResultData.buildFailureResult();//其余情况均不通过
	}
}
