package com.zhidi.crm.manager.controller;

import javax.servlet.http.HttpSession;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.manager.entity.Customer;
import com.zhidi.crm.manager.service.ICustomerService;
import com.zhidi.crm.manager.service.IProductService;
import com.zhidi.crm.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客户管理页面的处理器
 * @author jqllxew
 *
 */
@Controller
@RequestMapping("/manage/customer")
public class CustomerController {
	 
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/CustomerAction_customer")
	public String toCustomer(){
		return "manage/customer/list";
	}
	//分页查询
	@ResponseBody
	@PostMapping("/CustomerAction_pagination")
	public ResultData customer(Pager<Customer> pager, Customer customer){
		ResultData data = ResultData.buildSuccessResult("", customerService.findByPager(pager, customer));
		return data;
	}
	
	@ResponseBody
	@PostMapping("/CustomerAction_isfollowed")
	public ResultData isfollowed(String customerId,HttpSession session){
		User user =  (User) session.getAttribute("user");
		Boolean b = customerService.findfollow(customerId, user.getId());
		if(b){
			return ResultData.buildSuccessResult();
		}
		return ResultData.buildFailureResult();
	}
}
