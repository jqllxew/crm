package com.zhidi.crm.manager.service;


import java.util.List;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Position;
import com.zhidi.crm.manager.entity.vo.PositionVo;

public interface PositionService extends IBaseSerivce<Position>{
	
	
	/**
	 * 分页查询
	 * @param pager
	 * @param val
	 * @return
	 */
	Pager<PositionVo> selectBypage(Pager<PositionVo> pager, String val);
	
	PositionVo selectById(String id);
	
	/**
	 * 查询下拉框数据
	 */
	List<PositionVo> selectByDatap();

	List<PositionVo> selectByDatad();
	/**
	 * 验证
	 * @param name
	 * @return
	 */
	int selectByName(String name);
	
	
	
}
