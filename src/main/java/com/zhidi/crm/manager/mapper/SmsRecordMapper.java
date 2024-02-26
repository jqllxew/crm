package com.zhidi.crm.manager.mapper;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.manager.entity.SmsRecord;

public interface SmsRecordMapper extends IBaseMapper<SmsRecord> {

    int updateByPrimaryKeyWithBLOBs(SmsRecord record);

}