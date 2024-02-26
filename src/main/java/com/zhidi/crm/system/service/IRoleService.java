package com.zhidi.crm.system.service;


import java.util.List;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.Role;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.RoleVo;

public interface IRoleService extends IBaseSerivce<Role>{
	
	boolean selectValidate(String rolename);
	
	/**
	 * 查询所有角色
	 * @param pager
	 * @return
	 */
	List<Role> queryfindAll();
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	Pager<RoleVo> findByPage(Pager<RoleVo> pager);
	
	/**
	 * 查询角色默认值
	 * @param id
	 * @return
	 */
	RoleVo queryDefault(String id);
	
	/**
	 * 删除角色
	 * @param userIds
	 * @return
	 */
	boolean removeRole(String roleIds, String dRoleIds, User admin);
	
	/**
	 * 查询默认角色
	 * @return
	 */
	List<String> queryDefaultRole(String id);
	
	/**
	 * 修改角色分配
	 * @param userid
	 * @param roleids
	 * @return
	 */
	boolean updateRoleDist(String userid,String roleids);
	
	
}
