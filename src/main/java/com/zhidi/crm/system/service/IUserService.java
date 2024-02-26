package com.zhidi.crm.system.service;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.UserVo;

public interface IUserService extends IBaseSerivce<User>{

	User selectByName(String username);
	
	/**
	 * 用户管理
	 * @param pager
	 * @return
	 */
	Pager<UserVo> findByPage(Pager<UserVo> pager);
	/**
	 * 查询默认值
	 * @param id
	 * @return
	 */
	UserVo queryDefault(String id);
	/**
	 * 删除用户
	 * @param userIds
	 * @return
	 */
	boolean removeUser(String userIds,String duserIds,User admin);
	
	/**
	 * 验证用户名 若存在返回false
	 * @param username
	 * @return
	 */
	boolean selectValidate(String username);
}
