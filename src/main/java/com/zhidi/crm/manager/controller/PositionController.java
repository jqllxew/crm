package com.zhidi.crm.manager.controller;

import java.util.HashMap;
import java.util.Map;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.manager.entity.Department;
import com.zhidi.crm.manager.entity.Position;
import com.zhidi.crm.manager.entity.vo.DepartmentVo;
import com.zhidi.crm.manager.entity.vo.PositionVo;
import com.zhidi.crm.manager.service.IDepartmentService;
import com.zhidi.crm.manager.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/manage/position")
public class PositionController {

	@Autowired
	private PositionService positionService;
	@Autowired
	private IDepartmentService departmentService;

	@RequestMapping("/PositionAction_list")
	public String toPosition(){
		return "manage/position/list_position";
	}
	@GetMapping("/DepartmentAction_list")
	public String toDepartment(){
		return "manage/position/list_dept";
	}
	
	//部门加载数据
	@ResponseBody
	@PostMapping("/DepartmentAction_findByPage")
	public ResultData findbyPage(Pager<DepartmentVo> pager, String name, String pname){
		log.info("++++++++++++++++++++加载数据++++++++++++++++++++");
		Map<String, Object> params =new HashMap<>();
		params.put("name", name);
		params.put("pname", pname);
		return  ResultData.buildSuccessResult("加载成功",departmentService.selectByDeptPage(pager,params));
	}
	
	@GetMapping("/DepartmentAction_edit")
	public String toAddOrEdit(String id,Model model){
		model.addAttribute("data", departmentService.selectByPrimaryKey(id));
		model.addAttribute("listdata", departmentService.selectByData());
		
		return "manage/position/edit_dept";
	}
	@ResponseBody
	@PostMapping("/Department_addOrEdit")
	public ResultData addOrEdit(Department department){
		if(StringUtils.isEmpty(department.getDepartmentid())){
			log.info("++++++++++++++++++++添加++++++++++++++++++++");
			if(departmentService.insertSelective(department)>0){
				return ResultData.buildSuccessResult("添加成功");
			}
			return ResultData.buildFailureResult("添加失败");
		}
		
		log.info("++++++++++++++++++++修改++++++++++++++++++++");
		if(departmentService.updateByPrimaryKeySelective(department)>0){
			return ResultData.buildSuccessResult("修改成功");
		}
		return ResultData.buildFailureResult("修改失败！");
	}
	@ResponseBody
	@GetMapping("/DepartmentAction_delete")
	public ResultData deleteDept(String deptId){
		if(departmentService.deleteByPrimaryKey(deptId)>0){
			return ResultData.buildSuccessResult("删除成功！");
		};
		return ResultData.buildFailureResult("删除失败！");
	}
	//部门验证
	@ResponseBody
	@GetMapping("/deptValid")
	public ResultData toValid(String name){
		if(departmentService.selectByname(name)>0){
			return ResultData.buildSuccessResult();
		}
		return ResultData.buildFailureResult();
	}
	//岗位加载数据 
	@ResponseBody
	@PostMapping("/PositionAction_findByCondition")
	public ResultData findBypage(Pager<PositionVo> pager, String name){
		log.info("++++++++++++++++++++加载数据++++++++++++++++++++");
		return ResultData.buildSuccessResult("加载成功",positionService.selectBypage(pager, name));
	}
	@GetMapping("/PositionAction_edit")
	public String toAddPosition(String id,Model model){
		model.addAttribute("Vo", positionService.selectById(id));
		model.addAttribute("listDatad", positionService.selectByDatad());
		model.addAttribute("listDatap", positionService.selectByDatap());
		return "manage/position/edit_position";
	}
	@ResponseBody
	@PostMapping("/PositionAction_addOrEdit")
	//manage/position/PositiobAction_addOrEdit.do
	public ResultData addOrEdit(Position position){
		if(StringUtils.isEmpty(position.getPositionid())){
			log.info("++++++++++++++++++++添加++++++++++++++++++++");
			if(positionService.insertSelective(position)>0){
				return ResultData.buildSuccessResult("添加成功");
			}
			return ResultData.buildFailureResult("添加失败");
		}
		
		log.info("++++++++++++++++++++修改++++++++++++++++++++");
		if(positionService.updateByPrimaryKeySelective(position)>0){
			return ResultData.buildSuccessResult("修改成功");
		}
		return ResultData.buildFailureResult("修改失败");
		
	}
	@ResponseBody
	@PostMapping("/PositionAction_delete")
	public ResultData delete(String positionId){
		if(positionService.deleteByPrimaryKey(positionId)>0){
			return ResultData.buildSuccessResult("删除成功！");
		};
		return ResultData.buildFailureResult("删除失败！");
	}
	//岗位验证
	@GetMapping("/PositionValid")
	public ResultData toPositionValid(String name){
		if(positionService.selectByName(name) > 0){
			return ResultData.buildSuccessResult();
		}
		return ResultData.buildFailureResult();
	}
	
	
}
