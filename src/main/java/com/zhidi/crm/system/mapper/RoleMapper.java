package com.zhidi.crm.system.mapper;


import java.util.Date;
import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.Role;
import com.zhidi.crm.system.entity.vo.RoleVo;

public interface RoleMapper extends IBaseMapper<Role> {
	
	Role selectByName(String rolename);
	
	/**
	 * 查询所有role
	 * @param pager
	 * @return
	 */
	List<Role> queryfindAll();
	
	/**
	 * 查询总数
	 * @return
	 */
	Integer getCount();
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	List<RoleVo> findByPage(@Param("pager")Pager<RoleVo> pager);
	
	/**
	 * 查询角色默认值
	 * @param id
	 * @return
	 */
	RoleVo queryDefault(String id);
	
	/**
	 * 逻辑删除角色
	 * @param userids
	 * @return
	 */
	int removeRole(@Param("roleids")String[] roleids,
					@Param("adminId")String adminId,
					@Param("updateTime")Date updateTime);
	
	/**
	 * 解除角色所有用户
	 * @param roleids
	 * @return
	 */
	int removeUserRole(@Param("roleids")String[] roleids);
	
	/**
	 * 解除角色所有权限
	 * @param roleids
	 * @return
	 */
	int removeFunctionRole(@Param("roleids")String[] roleids);
	
	/**
	 * 物理删除
	 * @param roleids
	 * @return
	 */
	int deleteRole(@Param("roleids")String[] roleids);
	
	/**
	 * 查询默认角色
	 * @return
	 */
	List<String> queryDefaultRole(String id);
	
	/**
	 * 删除用户所有的角色
	 * @param userid
	 * @return
	 */
	int deleteRoleDist(String userid);
	
	/**
	 * 添加用户角色
	 * @param userid
	 * @param roleid
	 * @return
	 */
	int insertRoleDist(@Param("userid")String userid,@Param("roleid")String roleid);
}