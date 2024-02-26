package com.zhidi.crm.manager.service;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Contract;

public interface IContractService extends IBaseSerivce<Contract> {

	/**
     * 根据用户名查用户信息
     * @param Name
     * @return
     */
	Contract getByContractName(String Name);
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	Pager<Contract> selectByPager(Pager<Contract> pager,String name);
	
	/**
	 * 删除操作
	 * @param contractid
	 * @return
	 */
	int deleteContract(String contractid);
	/**
	 * 修改
	 * @param contract
	 * @return
	 */
	int updateByPrimaryKeySelective(String contract);
	
}
    
