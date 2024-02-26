package com.zhidi.crm.manager.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Customer;

public interface CustomerMapper extends IBaseMapper<Customer> {

	Customer getByCustomerName(String Name);
	/**
	 * 
	 * @param pager 分页参数
	 * @param customer 查询条件
	 * @return 列表
	 */
	List<Customer> findByPager(@Param("pager")Pager<Customer> pager,
							   @Param("param")Customer customer);
	/**
	 * 
	 * @return
	 */
	Integer conut();
	
	Integer findfollow(@Param("customerId")String customerId,@Param("userId")String userId);
}