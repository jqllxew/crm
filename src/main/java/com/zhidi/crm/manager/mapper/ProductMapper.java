package com.zhidi.crm.manager.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Product;

public interface ProductMapper extends IBaseMapper<Product> {
	
	Integer getCount(@Param("product")Product product);
	
	/**
	 * 分页
	 * @param pager
	 * @return
	 */
	List<Product> findByPage(@Param("pager")Pager<Product> pager,@Param("product")Product product);
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	int deleteProduct(@Param("ids")String[] ids);
}