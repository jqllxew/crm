package com.zhidi.crm.system.service.impl;

import java.util.Date;
import java.util.List;

import com.zhidi.crm.system.mapper.RoleMapper;
import com.zhidi.crm.system.entity.Role;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.RoleVo;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.service.IRoleService;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService{

	@Getter
	@Resource
	private RoleMapper mapper;

	@Override
	public List<Role> queryfindAll() {
		return mapper.queryfindAll();
	}
	@Override
	public List<String> queryDefaultRole(String id) {
		
		return mapper.queryDefaultRole(id);
	}
	@Override
	public boolean updateRoleDist(String userid, String roleids) {
		mapper.deleteRoleDist(userid);
			
		if(StringUtils.isEmpty(roleids))
			return true;
		String[] split = roleids.split(",");
		for (String roleid : split) {
			if(mapper.insertRoleDist(userid, roleid) <= 0)
				return false;
		}
		return true;
	}
	@Override
	public Pager<RoleVo> findByPage(Pager<RoleVo> pager) {
		pager.setTotalRows(mapper.getCount());
		pager.setData(mapper.findByPage(pager));
		return pager;
	}
	@Override
	public RoleVo queryDefault(String id) {
		return mapper.queryDefault(id);
	}
	@Override
	public boolean removeRole(String roleIds,String dRoleIds, User admin) {
		boolean a = false,b=false;
		if(!StringUtils.isEmpty(roleIds)){
			String[] roleids = roleIds.split(",");
			removeDependence(roleids);
			a = mapper.removeRole(roleids, admin.getId(), new Date()) > 0;
		}
		if(!StringUtils.isEmpty(dRoleIds)){
			String[] dRoleids = dRoleIds.split(",");
			b = mapper.deleteRole(dRoleids) > 0;
		}
		return a | b;
	}
	
	/**
	 * 解除user和function表依赖
	 *
	 */
	private void removeDependence(String[] roleids){
		mapper.removeFunctionRole(roleids);
		mapper.removeUserRole(roleids);
	}
	@Override
	public boolean selectValidate(String rolename) {
		Role role = mapper.selectByName(rolename);
		return role == null;
	}

	
}
