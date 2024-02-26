package com.zhidi.crm.system.service.impl;


import java.util.Date;

import com.zhidi.crm.system.mapper.UserMapper;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.UserVo;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.service.IUserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService{

	@Getter
	@Resource
	private UserMapper mapper;

	@Override
	public User selectByName(String username) {
		return mapper.selectByName(username);
	}

	@Override
	public Pager<UserVo> findByPage(Pager<UserVo> pager) {
		Integer count = mapper.getCount();
		pager.setTotalRows(count);
		pager.setData(mapper.findByPage(pager));
		return pager;
	}

	@Override
	public UserVo queryDefault(String id) {
		return mapper.queryDefault(id);
	}

	@Override
	public boolean removeUser(String userIds,String duserIds,User admin) {
		boolean a = false, b= false;
		if(!StringUtils.isEmpty(userIds)){
			String[] userids = userIds.split(",");
			mapper.removeRoleUser(userids);
			a = mapper.removeUser(userids,admin.getId(),new Date()) > 0;
		}
		if(!StringUtils.isEmpty(duserIds)){
			String[] userids = duserIds.split(",");
			b = mapper.deleteUser(userids) > 0;
		}
		return a | b;
	}

	@Override
	public boolean selectValidate(String username) {
		User user = mapper.selectByName(username);
		return user == null;
	}
}
