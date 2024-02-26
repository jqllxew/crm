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

import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.system.entity.Function;
import com.zhidi.crm.system.entity.Role;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.service.IFunctionService;
import com.zhidi.crm.system.service.IRoleService;

@Slf4j
@Controller
@RequestMapping("/system/function")
public class FunctionController {
	
	@Autowired
	private IFunctionService functionService;
	@Autowired
	private IRoleService roleService;
	
	@ResponseBody
	@PostMapping("/FunctionAction_findAll")
	public ResultData findAll(){
		return 	ResultData.buildSuccessResult("",functionService.queryfindAll());
	}
	@ResponseBody
	@PostMapping("/FunctionAction_findAll2")
	public ResultData findAll2(){
		return ResultData.buildSuccessResult("", functionService.queryfindAll2());
	}
	@ResponseBody
	@GetMapping("/FunctionAction_findAll")
	public List<String> selectDefault(String roleid){
		return functionService.queryDefaultFunction(roleid);
	}
	@ResponseBody
	@PostMapping("/Function_RoleDist")
	public ResultData roleDist(String roleid,String funcids,HttpSession session){
		Role role = roleService.selectByPrimaryKey(roleid);
		User user = (User) session.getAttribute("user");
		if(functionService.updateFunctionDist(roleid, funcids)){
			role.setUpdateby(user.getId());
			role.setUpdatetime(new Date());
			roleService.updateByPrimaryKeySelective(role);
			return ResultData.buildSuccessResult("分配成功");
		}
		return ResultData.buildFailureResult("分配失败");
	}
	
	@GetMapping("/FunctionAction_list")
	public String toFunction(){
		return "system/list_function";
	}
	
	@GetMapping("/FunctionAction_edit")
	public String toEdit(Model model,String id){
		if(!StringUtils.isEmpty(id)){
			Function func = functionService.selectByPrimaryKey(id);
			model.addAttribute("func", func); 
		}
		return "system/edit_function";
	}
	@ResponseBody
	@PostMapping("/FunctionAction_edit")
	public ResultData edit(HttpSession session,Function func){
		User admin = (User) session.getAttribute("user");
		
		if(StringUtils.isEmpty(func.getId())){
			log.info("----------权限新增操作----------");
			func.setCreateby(admin.getId());
			func.setCreatetime(new Date());
			if(functionService.insertSelective(func) > 0){
				return ResultData.buildSuccessResult("添加成功");
			}
			return ResultData.buildFailureResult("失败");
		}
		log.info("----------权限修改操作----------");
		func.setUpdateby(admin.getId());
		func.setUpdatetime(new Date());
		int i = 0;
		if((i = functionService.updateByPrimaryKeySelective(func)) > 0)
			return ResultData.buildSuccessResult("修改成功");
		if(i == -7)
			return ResultData.buildFailureResult("修改失败,上级权限不能是自己");
		return ResultData.buildFailureResult("修改失败");
	}
	
	@ResponseBody
	@GetMapping("/FunctionAction_findCombotree")
	public ResultData findCombotree(String id){
		return ResultData.buildSuccessResult("combotree数据", functionService.findCombotree(id));
	}
	
	@ResponseBody
	@PostMapping("/FunctionAction_remove")
	public ResultData removeFunction(HttpSession session,String funcids,String dfuncIds){
		User admin = (User) session.getAttribute("user");
		if(functionService.removeFunction(admin, funcids, dfuncIds)){
			return ResultData.buildSuccessResult("已删除");
		}
		return ResultData.buildFailureResult("删除失败");
	}
	
	@ResponseBody
	@GetMapping("/FunctionAction_validate")
	public ResultData validateFunction(String funcname,String funcid){
		if(functionService.selectValidate(funcname)){//数据库中不存在,则验证通过
			return ResultData.buildSuccessResult();
		}
		if(!StringUtils.isEmpty(funcid)){//若存在,判断是否为修改操作
			String funcname2 = functionService.selectByPrimaryKey(funcid).getFuncname();
			if(funcname2.equals(funcname)){//若未修改,则验证通过
				return ResultData.buildSuccessResult();
			}
		}
		return ResultData.buildFailureResult();//其余情况均不通过
	}
	@ResponseBody
	@PostMapping("/FunctionAction_validate")
	public ResultData validateFunccode(String funccode,String funcid){
		if(functionService.selectValidateFunccode(funccode)){
			return ResultData.buildSuccessResult();
		}
		if(!StringUtils.isEmpty(funcid)){
			String funccode2 = functionService.selectByPrimaryKey(funcid).getFunccode();
			if(funccode2.equals(funccode)){
				return ResultData.buildSuccessResult();
			}
		}
		return ResultData.buildFailureResult();
	}
}
