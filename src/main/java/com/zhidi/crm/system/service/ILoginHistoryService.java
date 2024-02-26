package com.zhidi.crm.system.service;


import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.system.entity.LoginHistory;
import com.zhidi.crm.system.entity.User;

public interface ILoginHistoryService extends IBaseSerivce<LoginHistory>{
	/**
	 * 存入登陆历史
	 * @param user
	 * @param ip
	 * @return
	 */
	int insertLoginHistory(User user, String ip);
	
}
