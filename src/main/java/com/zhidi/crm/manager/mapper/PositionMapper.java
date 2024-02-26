package com.zhidi.crm.manager.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.manager.entity.vo.PositionVo;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Position;

public interface PositionMapper extends IBaseMapper<Position> {
	/**
	 * 查询总数
	 * @param val
	 * @return
	 */
	Integer count(@Param("val")String val);
	
	List<PositionVo> selectByPage(@Param("pager")Pager<PositionVo> pager, @Param("val")String val);
	/**
	 * 查询数据
	 * @param id
	 * @return
	 */
	PositionVo selectById(String id);
	/**
	 *上级管理
	 * @return
	 */
	List<PositionVo> selectByDatap();
	/**
	 * 所属部门
	 * @return
	 */
	List<PositionVo> selectByDatad();
	
	int insertSomeAttri(PositionVo vo);
	int updateSomeAttri(PositionVo vo);
	/**
	 * 验证
	 * @param name
	 * @return
	 */
	Position selectByName(String name);
}