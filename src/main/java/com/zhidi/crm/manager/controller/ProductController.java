package com.zhidi.crm.manager.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.zhidi.crm.common.Pager;
import com.zhidi.crm.common.ResultData;
import com.zhidi.crm.manager.entity.Product;
import com.zhidi.crm.manager.service.IProductService;
import com.zhidi.crm.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 产品管理页面的处理器
 * @author jqllxew
 *
 */
@Controller
@RequestMapping("/manage/product")
public class ProductController {
	@Autowired
	private IProductService productService;

	@GetMapping("/ProductAction_list")
	public String toProduct(){
		return "manage/product/list_product";
	}
	@ResponseBody
	@PostMapping("/ProductAction_findByPage")
	public ResultData findByPage(Pager<Product> pager, Product product){
		return ResultData.buildSuccessResult("", productService.findByPage(pager,product));
	}
	
	@GetMapping("/ProductAction_edit")
	public String toEdit(Model model,String id){
		if(!StringUtils.isEmpty(id)){
			Product product = productService.selectByPrimaryKey(id);
			model.addAttribute("product", product);
		}
		return "manage/product/edit_product";
	}
	@ResponseBody
	@PostMapping("/ProductAction_edit")
	public ResultData edit(HttpSession session,Product product){
		User admin = (User) session.getAttribute("user");
		 Date date = new Date();
		if(StringUtils.isEmpty(product.getProductid())){
			product.setCreatoruserid(admin.getId());
			product.setCreatetime(date);
			product.setUpdatetime(date);
			if(productService.insertSelective(product) > 0){
				return ResultData.buildSuccessResult("添加成功");
			}
			return ResultData.buildFailureResult("添加失败");
		}
		product.setUpdatetime(date);
		if(productService.updateByPrimaryKeySelective(product) > 0){
			return ResultData.buildSuccessResult("修改成功");
		}
		return ResultData.buildFailureResult("修改失败");
	}
	@ResponseBody
	@PostMapping("/ProductAction_remove")
	public ResultData remove(String ids){
		if(productService.deleteByPrimaryKey(ids) > 0){
			return ResultData.buildSuccessResult("已删除");
		}
		return ResultData.buildFailureResult("删除失败");
	}
}
