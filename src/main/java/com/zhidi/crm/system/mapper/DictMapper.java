package com.zhidi.crm.system.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.Dict;

public interface DictMapper extends IBaseMapper<Dict> {
	
	List<Dict> findByDictTypeId(@Param("pager")Pager<Dict> pager,@Param("dictTypeId")String dictTypeId);
	
	/**
	 * 查询总数
	 * @param dictTypeId
	 * @return
	 */
	Integer getCount(@Param("dictTypeId")String dictTypeId);
	
	/**
	 * 把typeid改为null
	 * @param typeId
	 * @return
	 */
	int updateByTypeId(String typeId);
	
	/**
	 * 删除字典
	 * @param ids
	 * @return
	 */
	int deleteDict(@Param("ids")String[] ids);
}