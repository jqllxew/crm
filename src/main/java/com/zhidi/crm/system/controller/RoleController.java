package com.zhidi.crm.system.controller;


import java.util.Date;
import java.util.List;

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
import com.zhidi.crm.system.entity.Role;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.RoleVo;
import com.zhidi.crm.system.service.IRoleService;
import com.zhidi.crm.system.service.IUserService;

@Slf4j
@Controller
@RequestMapping("/system/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	
	@ResponseBody
	@PostMapping("/RoleAction_findAll")
	public ResultData findAll(){
		return ResultData.buildSuccessResult("成功", roleService.queryfindAll());
	}
	
	@ResponseBody
	@GetMapping("/RoleAction_findAll")
	public List<String> selectDefault(String userid){
		return roleService.queryDefaultRole(userid);
	}
	@ResponseBody
	@PostMapping("/Role_userDist")
	public ResultData userDist(HttpSession session,String userid,String roleids){
		User user = userService.selectByPrimaryKey(userid);
		User admin = (User) session.getAttribute("user");
		
		if(roleService.updateRoleDist(userid, roleids)){
			user.setUpdateby(admin.getId());
			user.setUpdatetime(new Date());
			userService.updateByPrimaryKeySelective(user);
			return ResultData.buildSuccessResult("分配成功");
		}
		return ResultData.buildFailureResult("分配失败");
	}
	
	@GetMapping("/RoleAction_list")
	public String toRole(){
		return "system/list_role";
	}
	@ResponseBody
	@PostMapping("/RoleAction_findByPage")
	public ResultData ByPage(Pager<RoleVo> pager){
		return ResultData.buildSuccessResult("成功", roleService.findByPage(pager));
	}
	
	@GetMapping("/RoleAction_edit")
	public String toEdit(Model model,String id){
		RoleVo roleVo = roleService.queryDefault(id);
		model.addAttribute("roleVO", roleVo);
		return "system/edit_role";
	}
	
	@ResponseBody
	@PostMapping("/RoleAction_edit")
	public ResultData edit(Role role,HttpSession session){
		User admin = (User) session.getAttribute("user");
		String adminId = admin.getId();
		if(StringUtils.isEmpty(role.getId())){
			log.info("----------角色新增操作----------");
			role.setCreateby(adminId);
			role.setCreatetime(new Date());
			if(roleService.insertSelective(role) <= 0){
				return ResultData.buildFailureResult("添加失败");
			}
			return ResultData.buildSuccessResult("添加成功");
		}
		log.info("----------角色修改操作----------");
		role.setUpdateby(adminId);
		role.setUpdatetime(new Date());
		if(roleService.updateByPrimaryKeySelective(role) <= 0){
			return ResultData.buildFailureResult("修改失败");
		}
		return ResultData.buildSuccessResult("修改成功");
	}
	@ResponseBody
	@PostMapping("/RoleAction_remove")
	public ResultData removeRole(HttpSession session,String roleIds,String dRoleIds){
		User admin = (User) session.getAttribute("user");
		if(roleService.removeRole(roleIds,dRoleIds, admin)){
			return ResultData.buildSuccessResult("已删除");
		}
		return ResultData.buildFailureResult("删除失败");
	}
	@GetMapping("/RoleAction_assign")
	public String toAssign(Model model,String id){
		model.addAttribute("roleid", id);
		return "system/assign_role";
	}
	
	@ResponseBody
	@GetMapping("/RoleAction_validate")
	public ResultData validateRole(String rolename,String roleid){
		if(roleService.selectValidate(rolename)){//数据库中不存在,则验证通过
			return ResultData.buildSuccessResult();
		}
		if(!StringUtils.isEmpty(roleid)){//若存在,判断是否为修改操作
			String rolename2 = roleService.selectByPrimaryKey(roleid).getRolename();
			if(rolename2.equals(rolename)){//若未修改,则验证通过
				return ResultData.buildSuccessResult();
			}
		}
		return ResultData.buildFailureResult();//其余情况均不通过
	}
}
