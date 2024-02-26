package com.zhidi.crm.system.service.impl;


import com.zhidi.crm.system.mapper.DictMapper;
import com.zhidi.crm.system.entity.Dict;
import com.zhidi.crm.system.entity.DictType;
import lombok.Getter;
import org.springframework.stereotype.Service;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.service.IDictService;

import javax.annotation.Resource;

@Service
public class DictServiceImpl extends BaseServiceImpl<Dict> implements IDictService{

	@Getter
	@Resource
	private DictMapper mapper;

	@Override
	public Pager<Dict> findByDictTypeId(Pager<Dict> pager, DictType dictType) {
		String dictTypeId = dictType.getId();
		pager.setTotalRows(mapper.getCount(dictTypeId));
		pager.setData(mapper.findByDictTypeId(pager, dictTypeId));
		return pager;
	}

	@Override
	public int updateByTypeId(String typeId) {
		return mapper.updateByTypeId(typeId);
	}
	
	@Override
	public int deleteByPrimaryKey(String ids) {
		return mapper.deleteDict(ids.split(","));
	}

}
