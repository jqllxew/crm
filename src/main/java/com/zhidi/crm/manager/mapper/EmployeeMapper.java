package com.zhidi.crm.manager.mapper;

import java.util.List;
import java.util.Map;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Employee;

public interface EmployeeMapper extends IBaseMapper<Employee> {
	/**
	 * 查询总条数
	 */
	Integer count(@Param("params")Map<String, Object> params);
	/**
	 * 分页查询
	 * @param pager 分页查询条件
	 * @return 分页数据
	 */
	List<Employee> selectByPager(@Param("pager")Pager pager,
			@Param("firstResult")Integer firstResult,
			@Param("maxResult")Integer maxResult,
			@Param("params") Map<String, Object> params);
	/**
	 * 删除emp
	 * @param empids
	 * @return
	 */
	int removeEmpByIds(@Param("empids")String[] empids);
	/**
	 * 验证
	 * @param name
	 * @return
	 */
	Employee selectByName(String name);
}