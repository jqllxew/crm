package com.zhidi.crm.system.service.impl;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.system.mapper.LoginHistoryMapper;
import com.zhidi.crm.system.entity.LoginHistory;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.service.ILoginHistoryService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LoginHistorySreviceImpl extends BaseServiceImpl<LoginHistory> implements ILoginHistoryService{

	@Getter
	@Resource
	private LoginHistoryMapper mapper;

	@Override
	public int insertLoginHistory(User user, String ip) {
		String str = "";
		//判断是不是第一次登陆
		if(mapper.selectByUserid(user.getId()) != 0){
			//判断两次登陆的IP地址是否相同  
			LoginHistory history = mapper.selectByForeignKey(user.getId());
			if(ip.equals(history.getLoginip())){
				str = "正常";
			}
			str="异常";
		}
		str="正常";
		LoginHistory loginHistory = new LoginHistory();
		//设置LoginHistory参数
		loginHistory.setUserid(user.getId());
		loginHistory.setLoginstatus(str);
		loginHistory.setLogintime(new Date());
		loginHistory.setLoginip(ip);
		return mapper.insertSelective(loginHistory);
	}

	
	
}
