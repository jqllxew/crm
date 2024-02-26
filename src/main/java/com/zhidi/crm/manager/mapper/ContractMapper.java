package com.zhidi.crm.manager.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Contract;

public interface ContractMapper extends IBaseMapper<Contract> {

	Contract getByContractName(String Name);
	/**
	 * 用户管理
	 * @param pager
	 * @return
	 */
	List<Contract> selectByPager(@Param("pager")Pager<Contract> pager,@Param("name")String name);
	/**
	 * 查询总数
	 * @return
	 */
	Integer conut(String name);
	/**
	 * 查询用户默认值
	 * @param id
	 * @return
	 */
	Contract queryDefault(String id);
	
	/**
	 * 删除操作
	 * @param s
	 * @return
	 */
	int deleteByPrimary(@Param("s")String[] s);
	
}