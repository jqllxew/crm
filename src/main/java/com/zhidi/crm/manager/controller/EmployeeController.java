package com.zhidi.crm.manager.controller;


import java.util.HashMap;
import java.util.Map;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.manager.entity.Employee;
import com.zhidi.crm.manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 员工管理页面的处理器
 * @author jqllxew
 *
 */
@Slf4j
@Controller
@RequestMapping("/manage/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/EmployeeAction_list")
	public String toEmployee(){
		log.info("++++++++++++++++++++跳转到employee页面++++++++++++++++++++");
		return "manage/employee/list_employee";
	}
	@ResponseBody
	@PostMapping("/EmployeeAction_findByPage")
	public ResultData loadData(Pager<Employee> pager , String name){
		Map<String, Object> params =new HashMap<>();
		params.put("name", name);
		log.info("++++++++++++++++++++页面加载+++++++++++++++++++");
		Pager<Employee> byPager = employeeService.selectByPager(pager,params);
		return ResultData.buildSuccessResult("",byPager);
	}
	
	@GetMapping("/EmployeeAction_edit")
	public String toEdit(String id,Model model){
		log.info("++++++++++++++++++++跳转修改页面+++++++++++++++++++");
		Employee emp = employeeService.selectByPrimaryKey(id);
		model.addAttribute("emp", emp);
		return "manage/employee/edit_employee";
	}
	
	@GetMapping("/EmployeeAction_add")
	public String toadd(){
		log.info("++++++++++++++++++++跳转新增页面+++++++++++++++++++");
		return "manage/employee/add_employee";
	}
	
	
	@ResponseBody
	@PostMapping("/EmployeeAction_addOrEdit")
	public ResultData addOrEdit(Employee emp){
		 System.out.println(emp.getEmpid());
		
		if(StringUtils.isEmpty(emp.getEmpid())){
			log.info("++++++++++++++++++++添加+++++++++++++++++++");
			int i = employeeService.insertSelective(emp);
			if(i > 0){
				 return ResultData.buildSuccessResult("添加成功！");
			}
			return ResultData.buildFailureResult("添加失败！");
		}
		
		log.info("++++++++++++++++++++修改+++++++++++++++++++");
			int i = employeeService.updateByPrimaryKeySelective(emp);
			if(i > 0){
				return ResultData.buildSuccessResult("修改成功");
			}
			return ResultData.buildFailureResult("修改失败！");
	}
	@ResponseBody
	@PostMapping("/EmployeeAction_remove")
	public ResultData romove(String empIds){
		
		log.info("++++++++++++++++++++删除+++++++++++++++++++");
		if(employeeService.removeEmpByIds(empIds)){
			return ResultData.buildSuccessResult("删除成功！");
		}
		return ResultData.buildFailureResult("删除失败！");
	}
	//员工验证
	@GetMapping("/EmpValid")
	public ResultData toEmpValid(String name){
		if(employeeService.selectByName(name)>0){
			return ResultData.buildSuccessResult();
		}
		return ResultData.buildFailureResult();
	}
	
}
