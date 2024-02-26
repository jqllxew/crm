package com.zhidi.crm.system.mapper;

import java.util.Date;
import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.UserVo;

public interface UserMapper extends IBaseMapper<User> {
    
	User selectByName(String username);
	
	/**
	 * 用户管理
	 * @param pager
	 * @return
	 */
	List<UserVo> findByPage(@Param("pager")Pager<UserVo> pager);
	/**
	 * 返回总数
	 * @return
	 */
	Integer getCount();
	/**
	 * 查询用户默认值
	 * @param id
	 * @return
	 */
	UserVo queryDefault(String id);
	/**
	 * 逻辑删除用户
	 * @param userids
	 * @return
	 */
	int removeUser(@Param("userids")String[] userids,
					@Param("adminId")String adminId,
					@Param("updateTime")Date updateTime);
	/**
	 * 解除用户所有角色
	 * @param userids
	 * @return
	 */
	int removeRoleUser(@Param("userids")String[] userids);
	
	/**
	 * 物理删除
	 * @param userids
	 * @return
	 */
	int deleteUser(@Param("userids")String[] userids);
}