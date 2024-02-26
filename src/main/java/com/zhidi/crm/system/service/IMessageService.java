package com.zhidi.crm.system.service;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.Message;

public interface IMessageService extends IBaseSerivce<Message>{

	Pager<Message> findByPage(Pager<Message> pager);
}
