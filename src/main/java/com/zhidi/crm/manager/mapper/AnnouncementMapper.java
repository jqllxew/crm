package com.zhidi.crm.manager.mapper;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.manager.entity.Announcement;

public interface AnnouncementMapper extends IBaseMapper<Announcement> {

	int updateByPrimaryKeyWithBLOBs(Announcement record);

}