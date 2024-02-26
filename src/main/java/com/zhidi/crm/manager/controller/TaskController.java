package com.zhidi.crm.manager.controller;

import java.util.Date;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.manager.entity.Task;
import com.zhidi.crm.manager.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 任务管理页面的处理器
 * @author jqllxew
 *
 */
@Controller
@RequestMapping("/manage/task")
public class TaskController {
	@Autowired
	private ITaskService taskService;
	
	//跳转到任务管理页面
	@GetMapping("/TaskAction_list")
	public String toTask(){
		return "manage/task/list_task";
	}
	@ResponseBody
	@PostMapping("/TaskAction_findByPage")
	public ResultData findByPage(Pager<Task> pager, Task task){
		return ResultData.buildSuccessResult("", taskService.findByPage(pager, task));
	}
	@ResponseBody
	@GetMapping("TaskAction_getColumn")
	public ResultData getColumn(String column){
		if("1".equals(column)){
			return null;
		}
		return ResultData.buildSuccessResult("", taskService.getColumn(column));
	}
	
	@GetMapping("/TaskAction_edit")
	public String toEdit(Model model,String taskId){
		if(!StringUtils.isEmpty(taskId)){
			Task task = taskService.selectByPrimaryKey(taskId);
			model.addAttribute("task", task);
		}
		return "manage/task/edit_task";
	}
	
	@ResponseBody
	@PostMapping("/TaskAction_edit")
	public ResultData edit(Task task){
		Date date = new Date();
		if(StringUtils.isEmpty(task.getTaskid())){
			task.setCreatedate(date);
			if(taskService.insertSelective(task) > 0){
				return ResultData.buildSuccessResult("新增成功");
			}
			return ResultData.buildFailureResult("新增失败");
		}
		if(taskService.updateByPrimaryKeySelective(task) > 0){
			return ResultData.buildSuccessResult("修改成功");
		}
		return ResultData.buildFailureResult("修改失败");
	}
	@ResponseBody
	@PostMapping("/TaskAction_del")
	public ResultData del(String ids){
		if(taskService.deleteByPrimaryKey(ids) > 0){
			return ResultData.buildSuccessResult("已删除");
		}
		return ResultData.buildFailureResult("删除失败");
	}
	
	@GetMapping("/TaskAction_see")
	public String see(Model model,String taskId){
 		Task task = taskService.selectByPrimaryKey(taskId);
		model.addAttribute("task", task);
		return "manage/task/see_task";
	}
}
