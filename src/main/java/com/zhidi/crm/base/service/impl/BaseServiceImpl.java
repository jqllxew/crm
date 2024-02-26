package com.zhidi.crm.base.service.impl;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.base.service.IBaseSerivce;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseServiceImpl<T> implements IBaseSerivce<T> {

	protected abstract IBaseMapper<T> getMapper();

	@Override
	public int deleteByPrimaryKey(String id) {
		return getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return getMapper().insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return getMapper().insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(String id) {
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return getMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return getMapper().updateByPrimaryKey(record);
	}

}
