package com.zhidi.crm.system.service.impl;

import java.util.List;

import com.zhidi.crm.system.mapper.DictTypeMapper;
import com.zhidi.crm.system.entity.DictType;
import lombok.Getter;
import org.springframework.stereotype.Service;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.system.service.IDictTypeService;

import javax.annotation.Resource;

@Service
public class DictTypeServiceImpl extends BaseServiceImpl<DictType> implements IDictTypeService{

	@Getter
	@Resource
	private DictTypeMapper mapper;

	@Override
	public List<DictType> queryfindAll() {
		return mapper.queryfindAll();
	}
	
	@Override
	public boolean selectByName(String typename) {
		return mapper.selectByName(typename) == null;
	}
	@Override
	public boolean selectByCode(String typecode) {
		return mapper.selectByCode(typecode) == null;
	}
}
