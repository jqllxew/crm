package com.zhidi.crm.manager.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.entity.Task;

public interface TaskMapper extends IBaseMapper<Task> {

    int updateByPrimaryKeyWithBLOBs(Task record);

    
    List<Task> findByPage(@Param("pager")Pager<Task> pager,@Param("task")Task task,
		@Param("firstResult") Integer firstResult,
		@Param("maxResult") Integer maxResult);
    
    
    Integer getCount(@Param("task")Task task);
    
    /**
     * 获取字段
     * @param column
     * @return
     */
    List<Object> getColumn(@Param("column")String column);
}