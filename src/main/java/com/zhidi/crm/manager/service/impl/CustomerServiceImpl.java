package com.zhidi.crm.manager.service.impl;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.common.Pager;
import com.zhidi.crm.manager.mapper.CustomerMapper;
import com.zhidi.crm.manager.entity.Customer;
import com.zhidi.crm.manager.service.ICustomerService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements ICustomerService {

	@Getter
	@Resource
	private CustomerMapper mapper;

	@Override
	public Customer getByCustomerName(String customerid) {
		return mapper.getByCustomerName(customerid);
	}

	@Override
	public Pager<Customer> findByPager(Pager<Customer> pager, Customer customer) {
		//查询总数
		pager.setTotalRows(mapper.conut());
		pager.setData(mapper.findByPager(pager, customer));
		return pager;
	}

	@Override
	public Boolean findfollow(String customerId, String userId) {
		Integer flag = mapper.findfollow(customerId, userId);
		return flag > 0;
	}
}
