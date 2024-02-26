package com.zhidi.crm.system.service;

import java.util.List;

public interface IMenuVoService {
	
	List<String> queryByUserId(String userId);
}
