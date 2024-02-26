package com.zhidi.crm.manager.mapper;

import java.util.List;
import java.util.Map;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.manager.entity.vo.DepartmentVo;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Department;

public interface DepartmentMapper extends IBaseMapper<Department> {

	/**
	 * 查询总数
	 * @param val
	 * @return
	 */
	int count(@Param("params")Map<String, Object> params);
	/**
	 * 分页
	 * @param pager
	 * @param val
	 * @return
	 */
	List<DepartmentVo> selectByDeptPage(@Param("pager")Pager<DepartmentVo> pager,
                                        @Param("firstResult")Integer firstResult,
                                        @Param("maxResult")Integer maxResult,
                                        @Param("params")Map<String, Object> params);
	
	/**
	 * 下拉框
	 * @return
	 */
	List<Department> selectByData();
	/**
	 * 验证
	 * @param name
	 * @return
	 */
	Department selectByname(String name);
}