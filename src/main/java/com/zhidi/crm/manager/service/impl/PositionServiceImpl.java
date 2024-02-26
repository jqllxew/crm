package com.zhidi.crm.manager.service.impl;


import java.util.List;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.PositionMapper;
import com.zhidi.crm.manager.entity.Position;
import com.zhidi.crm.manager.entity.vo.PositionVo;
import com.zhidi.crm.manager.service.PositionService;

import javax.annotation.Resource;

@Service
public class PositionServiceImpl extends BaseServiceImpl<Position> implements PositionService{

	@Getter
	@Resource
	private PositionMapper mapper;

	@Override
	public Pager<PositionVo> selectBypage(Pager<PositionVo> pager, String val) {
		pager.setTotalRows(mapper.count(val));
		pager.setData(mapper.selectByPage(pager, val));
		return pager;
	}
	@Override
	public PositionVo selectById(String id) {
		return mapper.selectById(id);
	}
	@Override
	public List<PositionVo> selectByDatad() {
		return mapper.selectByDatad();
	}
	@Override
	public List<PositionVo> selectByDatap() {
		return mapper.selectByDatap();
	}
	@Override
	public int selectByName(String name) {
		if(StringUtils.isEmpty(mapper.selectByName(name))){
			return 0;
		}
		return 1;
	}
	
	

}
