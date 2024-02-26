package com.zhidi.crm.manager.service.impl;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.ProductMapper;
import com.zhidi.crm.manager.entity.Product;
import com.zhidi.crm.manager.service.IProductService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService {

	@Getter
	@Resource
	private ProductMapper mapper;

	@Override
	public Pager<Product> findByPage(Pager<Product> pager, Product product) {
		pager.setTotalRows(mapper.getCount(product));
		pager.setData(mapper.findByPage(pager,product));
		
		return pager;
	}
	
	@Override
	public int deleteByPrimaryKey(String id) {
		String[] ids = id.split(",");
		return mapper.deleteProduct(ids);
	}
}
