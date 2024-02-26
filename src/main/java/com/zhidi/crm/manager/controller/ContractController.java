package com.zhidi.crm.manager.controller;

import java.util.List;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.manager.entity.Business;
import com.zhidi.crm.manager.entity.Contract;
import com.zhidi.crm.manager.service.IBusinessService;
import com.zhidi.crm.manager.service.IContractService;
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
 * 合同管理页面的处理器
 * @author jqllxew
 *
 */
@Slf4j
@Controller
@RequestMapping("/manage/position")
public class ContractController {
    
	@Autowired
	private IContractService contractService;
	
	@Autowired
	private IBusinessService businessService;
	
	@GetMapping("/ContractAction_list")
	public String toContract(){
		return "manage/contract/list_contract";
	}
	
	//分页查询
	@ResponseBody
	@PostMapping("/ContractAction_findByPage")
    public ResultData contract(Pager<Contract> pager , String name){
		log.info("----------查询----------");
		pager = contractService.selectByPager(pager,name);		
		return ResultData.buildSuccessResult("加载成功", pager);
	}
	
	@GetMapping("/ContractAction_edit")
	public String add(Model model){
		List<Business> list = businessService.queryAll();
		model.addAttribute("businessData", list);
	    return "manage/contract/edit_contract";
	}
	//新增操作
	@ResponseBody
	@PostMapping("/ContractAction_saveOrUpdate")
	public ResultData addContract(Contract contract){
		if(StringUtils.isEmpty(contract.getContractid())){
			log.info("----------新增操作----------");
			int i = contractService.insertSelective(contract);
			if(i <= 0){
				return ResultData.buildFailureResult("添加失败");				
			}
			return ResultData.buildSuccessResult("添加成功");
		}
		log.info("----------修改操作----------");
		int i = contractService.updateByPrimaryKeySelective(contract);
		if(i <= 0){
			return ResultData.buildFailureResult("修改失败");
		}
		return ResultData.buildSuccessResult("添加成功");
	}
	//删除操作
	@ResponseBody
	@PostMapping("/ContractAction_delete")
	public ResultData deleteContract(String contractid){
		//多个删除
		int j = contractService.deleteContract(contractid);
		if(j <= 0){
			return ResultData.buildFailureResult("删除失败");
		}
		return ResultData.buildSuccessResult("删除成功");
		//单个删除
		/*int i = contractService.deleteByPrimaryKey(contractid);
		if(i <= 0){
			return ResultData.buildFailureResult("已删除");
		}
		return ResultData.buildSuccessResult("删除失败");*/
	}
}
