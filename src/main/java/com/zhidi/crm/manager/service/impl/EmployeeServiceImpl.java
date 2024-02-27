package com.zhidi.crm.manager.service.impl;


import java.util.Map;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.EmployeeMapper;
import com.zhidi.crm.manager.entity.Employee;
import com.zhidi.crm.manager.service.EmployeeService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Slf4j
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

	@Getter
	@Resource
	private EmployeeMapper mapper;

	@Override
	public Pager<Employee> selectByPager(Pager<Employee> pager, Map<String, Object> params) {
		log.info("++++++++++++++++++++分页查询++++++++++++++++++++");
		pager.setTotalRows(mapper.count(params));
		pager.setData(mapper.selectByPager(pager, params));
		return pager;
	}
	@Override
	public boolean removeEmpByIds(String empIds) {
		log.info("++++++++++++++++++++删除操作++++++++++++++++++++");
		String[] empids = empIds.split(",");
		return mapper.removeEmpByIds(empids) > 0;
	}
	@Override
	public int selectByName(String name) {
		if(StringUtils.isEmpty(mapper.selectByName(name))){
			return 0;
		}
		return 1;
	}



}
