package com.zhidi.crm.system.mapper;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.system.entity.LoginHistory;

public interface LoginHistoryMapper extends IBaseMapper<LoginHistory> {
	/**
	 * 查询记录里上次登陆的地址
	 * @param userid
	 * @return
	 */
	LoginHistory selectByForeignKey(String userid);
	/**
	 * 查询该用户是否是第一次登陆
	 * @param userid
	 * @return
	 */
	int selectByUserid(String userid); 
	
}