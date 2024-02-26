package com.zhidi.crm.system.service.impl;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.mapper.MessageMapper;
import com.zhidi.crm.system.entity.Message;
import com.zhidi.crm.system.service.IMessageService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements IMessageService{

	@Getter
	@Resource
	private MessageMapper mapper;

	@Override
	public Pager<Message> findByPage(Pager<Message> pager) {
		pager.setTotalRows(mapper.getCount());
		pager.setData(mapper.findByPage(pager));
		return pager;
	}
}
