package com.zhidi.crm.manager.service;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Customer;

public interface ICustomerService extends IBaseSerivce<Customer>{

	/**
     * 根据用户名查用户信息
     * @param Name
     * @return
     */
	Customer getByCustomerName(String Name);
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	Pager<Customer> findByPager(Pager<Customer> pager,Customer customer);
	
	/**
	 * 获取关注
	 * @param customerId
	 * @param userId
	 * @return
	 */
	Boolean findfollow(String customerId,String userId);
}
