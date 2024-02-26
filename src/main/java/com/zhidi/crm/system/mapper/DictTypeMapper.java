package com.zhidi.crm.system.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.system.entity.DictType;

public interface DictTypeMapper extends IBaseMapper<DictType> {
	
	/**
	 * 查询所有字典类型
	 * @return
	 */
	List<DictType> queryfindAll();
	
	DictType selectByName(String typename);
	DictType selectByCode(String typecode);
}