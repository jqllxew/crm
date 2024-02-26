package com.zhidi.crm.manager.service.impl;

import java.util.List;


import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.BusinessMapper;
import com.zhidi.crm.manager.entity.Business;
import com.zhidi.crm.manager.service.IBusinessService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessServiceImpl extends BaseServiceImpl<Business> implements IBusinessService {

	@Getter
	@Resource
	private BusinessMapper mapper;
	
	@Override
	public List<Business> queryAll() {
		List<Business> list = mapper.queryAll();
		return list;
	}

	@Override
	public Pager<Business> selectByPage(Pager<Business> pager, String val, String cval, String nsval, String oval) {
		pager.setTotalRows(mapper.count(val, cval, nsval, oval));
		pager.setData(mapper.selectByPage(pager, val, cval, nsval, oval));;
		return pager;
	}

	@Override
	public Business selectById(String id) {
		return mapper.selectById(id);
	}

}
