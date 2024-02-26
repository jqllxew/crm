package com.zhidi.crm.system.service.impl;

import java.util.List;

import com.zhidi.crm.system.mapper.FunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhidi.crm.system.service.IMenuVoService;
@Service
public class MenuVoServiceImpl implements IMenuVoService{

	@Autowired
	private FunctionMapper functionMapper;
	@Override
	public List<String> queryByUserId(String userId) {
		
		return functionMapper.getAllurlByUserId(userId);
	}
	
	
}
