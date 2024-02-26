package com.zhidi.crm.manager.service.impl;

import java.util.List;

import lombok.Getter;
import org.springframework.stereotype.Service;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.ContractMapper;
import com.zhidi.crm.manager.entity.Contract;
import com.zhidi.crm.manager.service.IContractService;

import javax.annotation.Resource;

@Service
public class ContractServiceImpl extends BaseServiceImpl<Contract> implements IContractService {

	@Getter
	@Resource
	private ContractMapper mapper;

	@Override
	public Contract getByContractName(String contractid) {
		return mapper.getByContractName(contractid);
	}

	@Override
	public Pager<Contract> selectByPager(Pager<Contract> pager,String name) {
		//查询总数
		Integer contract = mapper.conut(name);
		pager.setTotalRows(contract);
		List<Contract> list = mapper.selectByPager(pager,name);
		pager.setData(list);
		return pager;
	}

	@Override
	public int deleteContract(String contractid) {
		String[] split = contractid.split(",");
		return mapper.deleteByPrimary(split);
	}
    
	@Override
	public int updateByPrimaryKeySelective(String contract) {
		return mapper.deleteByPrimaryKey(contract);
	}


}
