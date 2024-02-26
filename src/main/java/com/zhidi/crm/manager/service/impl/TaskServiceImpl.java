package com.zhidi.crm.manager.service.impl;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.TaskMapper;
import com.zhidi.crm.manager.entity.Task;
import com.zhidi.crm.manager.service.ITaskService;
import com.zhidi.crm.system.mapper.UserMapper;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements ITaskService {

	@Getter
	@Resource
	private TaskMapper mapper;

	@Resource
	private UserMapper userMapper;

	@Override
	public Pager<Task> findByPage(Pager<Task> pager, Task task) {
		pager.setTotalRows(mapper.getCount(task));
		pager.setData(mapper.findByPage(pager,task,(pager.getPage()-1)*pager.getRows(),pager.getRows()));
		return pager;
	}

	@Override
	public List<Object> getColumn(String column) {
		return mapper.getColumn(column);
	}
	
	@Override
	public Task selectByPrimaryKey(String id) {
		Task task = mapper.selectByPrimaryKey(id);
		if(!StringUtils.isEmpty(task.getCreatoruserid()))
			task.setCreatoruserid(userMapper.selectByPrimaryKey(task.getCreatoruserid()).getUsername());
		if(!StringUtils.isEmpty(task.getOwneruserid()))
			task.setOwneruserid(userMapper.selectByPrimaryKey(task.getOwneruserid()).getUsername());
		if(!StringUtils.isEmpty(task.getDeleteuserid()))
			task.setDeleteuserid(userMapper.selectByPrimaryKey(task.getDeleteuserid()).getUsername());
		return task;
	}

	@Override
	public int insertSelective(Task task) {
		task.setCreatoruserid(userMapper.selectByName(task.getCreatoruserid()).getId());
		task.setOwneruserid(userMapper.selectByName(task.getOwneruserid()).getId());
		task.setDeleteuserid(userMapper.selectByName(task.getDeleteuserid()).getId());
		return super.insertSelective(task);
	}
	
	@Override
	public int updateByPrimaryKeySelective(Task task) {
		task.setCreatoruserid(userMapper.selectByName(task.getCreatoruserid()).getId());
		task.setOwneruserid(userMapper.selectByName(task.getOwneruserid()).getId());
		return super.updateByPrimaryKeySelective(task);
	}
	
	@Override
	public int deleteByPrimaryKey(String ids) {
		int i = 0;
		for (String id : ids.split(",")) {
			i = mapper.deleteByPrimaryKey(id);
			if(i <= 0)
				return 0;
		}
		return i;
	}
}
