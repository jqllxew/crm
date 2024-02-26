package com.zhidi.crm.manager.service;


import java.util.Map;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Employee;

public interface EmployeeService extends IBaseSerivce<Employee>{
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	Pager<Employee> selectByPager(Pager<Employee> pager,Map<String, Object> params);

	/**
	 * 删除员工
	 */
	boolean removeEmpByIds(String empIds);
	/**
	 * 验证
	 * @param name
	 * @return
	 */
	int selectByName(String name);
	
}
