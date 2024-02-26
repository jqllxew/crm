package com.zhidi.crm.system.service;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.Dict;
import com.zhidi.crm.system.entity.DictType;

public interface IDictService extends IBaseSerivce<Dict>{

	/**
	 * 分页
	 * @param pager
	 * @param dictType
	 * @return
	 */
	Pager<Dict> findByDictTypeId(Pager<Dict> pager, DictType dictType);
	
	/**
	 * 把typeid改为null
	 * @param typeId
	 * @return
	 */
	int updateByTypeId(String typeId);
}
