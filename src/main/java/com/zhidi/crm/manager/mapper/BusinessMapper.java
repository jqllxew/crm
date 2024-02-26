package com.zhidi.crm.manager.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Business;

public interface BusinessMapper extends IBaseMapper<Business> {
	/**
	 * 查询所有
	 * @return
	 */
	List<Business> queryAll();
	/**
	 * 
	 * @param businessid
	 * @return
	 */
	Business queryDefault(String businessid);
	/**
	 * 查询总数
	 * @return
	 */
	Integer count(@Param("val")String val,@Param("cval")String cval,@Param("nsval")String nsval,@Param("oval")String oval);
	/**
	 * 分页
	 * @return
	 */
	List<Business> selectByPage(@Param("pager")Pager<Business> pager,@Param("val")String val,@Param("cval")String cval,@Param("nsval")String nsval,@Param("oval")String oval);
	
	Business selectById(String id);
}