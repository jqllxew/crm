package com.zhidi.crm.system.mapper;

import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.system.entity.Message;

public interface MessageMapper extends IBaseMapper<Message> {

    int updateByPrimaryKeyWithBLOBs(Message record);
    
    Integer getCount();
    
    List<Message> findByPage(@Param("pager")Pager<Message> pager);

}