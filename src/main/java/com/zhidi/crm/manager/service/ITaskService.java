package com.zhidi.crm.manager.service;


import java.util.List;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Task;

public interface ITaskService extends IBaseSerivce<Task>{

	Pager<Task> findByPage(Pager<Task> pager,Task task);
	
	/**
	 * 获取字段
	 * @param column
	 * @return
	 */
	List<Object> getColumn(String column); 
}
