package com.zhidi.crm.manager.service;

import java.util.List;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Business;

public interface IBusinessService extends IBaseSerivce<Business> {

	List<Business> queryAll();
	/**
	 * 分页查询
	 * @param pager
	 * @param val
	 * @param cval
	 * @param nsval
	 * @param oval
	 * @return
	 */
	Pager<Business> selectByPage(Pager<Business> pager,String val,String cval,String nsval,String oval); 
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	Business selectById(String id);
		
}
