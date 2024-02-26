package com.zhidi.crm.system.service;

import java.util.List;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.system.entity.DictType;

public interface IDictTypeService extends IBaseSerivce<DictType>{

	/**
	 * 查询所有字典类型
	 * @return
	 */
	List<DictType> queryfindAll();
	
	boolean selectByName(String typename);
	boolean selectByCode(String typecode);
}
