package com.zhidi.crm.manager.mapper;

import com.zhidi.crm.base.mapper.IBaseMapper;
import com.zhidi.crm.manager.entity.EmailRecord;

public interface EmailRecordMapper extends IBaseMapper<EmailRecord> {




    int updateByPrimaryKeyWithBLOBs(EmailRecord record);

}