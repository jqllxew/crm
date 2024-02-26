package com.zhidi.crm.manager.service;

import java.util.List;
import java.util.Map;

import com.zhidi.crm.manager.entity.vo.DepartmentVo;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Department;

public interface IDepartmentService extends IBaseSerivce<Department>{
	/**
	 * 分页查询
	 * @param pager
	 * @param val
	 * @return
	 */
	Pager<DepartmentVo> selectByDeptPage(Pager<DepartmentVo> pager, Map<String, Object> params);
	/**
	 * 查询下拉框数据
	 * @return
	 */
	List<Department> selectByData();
	
	/**
	 * 验证
	 * @param name
	 * @return
	 */
	int selectByname(String name);
}
