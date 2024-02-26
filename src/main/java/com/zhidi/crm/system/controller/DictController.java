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
import com.zhidi.crm.system.entity.Dict;
import com.zhidi.crm.system.entity.DictType;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.service.IDictService;
import com.zhidi.crm.system.service.IDictTypeService;

@Slf4j
@Controller
@RequestMapping("/system/dict")
public class DictController {
	
	@Autowired
	private IDictTypeService dictTypeService;
	@Autowired
	private IDictService dictService;
	
	@GetMapping("/DictAction_index")
	public String toDict(){
		return "system/dict/index_dict";
	}
	
	@ResponseBody
	@PostMapping("/DictTypeAction_findAll")
	public ResultData dtfindAll(){
		return ResultData.buildSuccessResult("字典类型", dictTypeService.queryfindAll());
	}
	
	@ResponseBody
	@PostMapping("/DictAction_findByDictTypeId")
	public ResultData findByDictTypeId(DictType dictType, Pager<Dict> pager){
		return ResultData.buildSuccessResult("", dictService.findByDictTypeId(pager, dictType));
	}
	
	@GetMapping("/DictTypeAction_edit")
	public String todtEdit(Model model,String id){
		if(!StringUtils.isEmpty(id)){
			DictType dictType = dictTypeService.selectByPrimaryKey(id);
			model.addAttribute("dictType", dictType);
		}
		return "system/dict/edit_dictType";
	}
	@ResponseBody
	@PostMapping("/DictTypeAction_edit")
	public ResultData dtEdit(HttpSession session,DictType dictType){
		User admin = (User) session.getAttribute("user");
		if(StringUtils.isEmpty(dictType.getId())){
			log.info("----------字典类型新增操作----------");
			dictType.setCreateby(admin.getId());
			dictType.setCreatetime(new Date());
			if(dictTypeService.insertSelective(dictType) > 0){
				return ResultData.buildSuccessResult("新增成功");
			}
			return ResultData.buildFailureResult("新增失败");
		}
		log.info("----------字典类型修改操作----------");
		dictType.setUpdateby(admin.getId());
		dictType.setUpdatetime(new Date());
		if(dictTypeService.updateByPrimaryKeySelective(dictType) > 0){
			return ResultData.buildSuccessResult("修改成功");
		}
		return ResultData.buildFailureResult("修改失败");
	}
	
	@ResponseBody
	@PostMapping("/DictTypeAction_remove")
	public ResultData removeDt(HttpSession session,String id){
		User admin = (User) session.getAttribute("user");
		if(dictService.updateByTypeId(id) > 0){
			DictType dictType = dictTypeService.selectByPrimaryKey(id);
			dictType.setUpdateby(admin.getId());
			dictType.setUpdatetime(new Date());
			dictType.setStatus(2L);
			dictTypeService.updateByPrimaryKeySelective(dictType);
			return ResultData.buildSuccessResult("已解除该类型");
		}
		if(dictTypeService.deleteByPrimaryKey(id) > 0){
			return ResultData.buildSuccessResult("已删除");
		}
		return ResultData.buildFailureResult("删除失败");
	}
	
	@GetMapping("/DictAction_edit")
	public String toEdit(Model model,String id){
		if(!StringUtils.isEmpty(id)){
			Dict dict = dictService.selectByPrimaryKey(id);
			model.addAttribute("dict", dict);
		}
		List<DictType> list = dictTypeService.queryfindAll();
		model.addAttribute("types", list);
		return "system/dict/edit_dict";
	}
	@ResponseBody
	@PostMapping("/DictAction_edit")
	public ResultData edit(HttpSession session,Dict dict){
		
		if(StringUtils.isEmpty(dict.getId())){
			log.info("----------字典新增操作----------");
			if(dictService.insertSelective(dict) > 0){
				return ResultData.buildSuccessResult("新增成功");
			}
			return ResultData.buildFailureResult("新增失败");
		}
		log.info("----------字典修改操作----------");
		if(dictService.updateByPrimaryKeySelective(dict) > 0){
			return ResultData.buildSuccessResult("修改成功");
		}
		return ResultData.buildFailureResult("修改失败");
	}
	
	@ResponseBody
	@PostMapping("/DictAction_remove")
	public ResultData remove(String ids){
		if(dictService.deleteByPrimaryKey(ids) > 0){
			return ResultData.buildSuccessResult("已删除");
		}
		return ResultData.buildFailureResult("删除失败");
	}
	@ResponseBody
	@GetMapping("/DictTypeAction_validate")
	public ResultData validateTypeName(String typename,String typeid){
		if(dictTypeService.selectByName(typename)){
			return ResultData.buildSuccessResult();
		}
		if(!StringUtils.isEmpty(typeid)){
			String typename2 = dictTypeService.selectByPrimaryKey(typeid).getTypename();
			if(typename2.equals(typename)){
				return ResultData.buildSuccessResult();
			}
		}
		return ResultData.buildFailureResult();
	}
	
	@ResponseBody
	@PostMapping("/DictTypeAction_validate")
	public ResultData validateTypeCode(String typecode,String typeid){
		if(dictTypeService.selectByCode(typecode)){
			return ResultData.buildSuccessResult();
		}
		if(!StringUtils.isEmpty(typeid)){
			String typecode2 = dictTypeService.selectByPrimaryKey(typeid).getTypecode();
			if(typecode2.equals(typecode)){
				return ResultData.buildSuccessResult();
			}
		}
		return ResultData.buildFailureResult();
	}
}
