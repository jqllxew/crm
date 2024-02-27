package com.zhidi.crm.manager.service.impl;

import java.util.List;
import java.util.Map;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.DepartmentMapper;
import com.zhidi.crm.manager.entity.Department;
import com.zhidi.crm.manager.entity.vo.DepartmentVo;
import com.zhidi.crm.manager.service.IDepartmentService;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

	@Getter
	@Resource
	private DepartmentMapper mapper;

	@Override
	public Pager<DepartmentVo> selectByDeptPage(Pager<DepartmentVo> pager, Map<String, Object> params) {
		pager.setTotalRows(mapper.count(params));
		pager.setData(mapper.selectByDeptPage(pager,params));
		return pager;
	}
	@Override
	public List<Department> selectByData() {
		return mapper.selectByData();
	}
	@Override
	public int selectByname(String name) {
		if(StringUtils.isEmpty(mapper.selectByname(name))){
			return 0;
		}
		return 1;
	}

}
