package com.zhidi.crm.manager.service;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Product;

public interface IProductService extends IBaseSerivce<Product>{

	/**
	 * 分页
	 * @param pager
	 * @return
	 */
	Pager<Product> findByPage(Pager<Product> pager,Product product);
}
