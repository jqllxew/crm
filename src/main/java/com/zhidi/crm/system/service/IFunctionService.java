package com.zhidi.crm.system.service;

import java.util.List;

import com.zhidi.crm.base.service.IBaseSerivce;
import com.zhidi.crm.system.entity.Function;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.FuncVo;
import com.zhidi.crm.system.entity.vo.MenuVo;
import com.zhidi.crm.system.entity.vo.TreeVo;

public interface IFunctionService extends IBaseSerivce<Function>{

	/**
	 * 验证权限名若存在返回false
	 * @param funcname
	 * @return
	 */
	boolean selectValidate(String funcname);
	
	/**
	 * 验证权限编码 若存在返回false
	 * @param funccode
	 * @return
	 */
	boolean selectValidateFunccode(String funccode);
	
	/**
	 * 根据角色id查询所对应的权限
	 * @param id
	 * @return
	 */
	List<MenuVo> getmyFunction(String id);
	
	/**
	 * 查询所有权限
	 * @return
	 */
	List<Function> queryfindAll();
	
	/**
	 * 所有权限展示
	 * @return
	 */
	List<FuncVo> queryfindAll2();
	
	/**
	 * 初始化角色权限
	 * @param roleid
	 * @return
	 */
	List<String> queryDefaultFunction(String roleid);
	
	/**
	 * 修改权限分配
	 * @param roleid
	 * @param funcids
	 * @return
	 */
	boolean updateFunctionDist(String roleid,String funcids);
	
	/**
	 * 查询combotree所需数据
	 * @param id
	 * @return
	 */
	List<TreeVo> findCombotree(String id);
	
	/**
	 * 删除权限
	 * @param admin
	 * @param funcids
	 * @return
	 */
	boolean removeFunction(User admin, String funcIds, String dfuncIds);
	
	/**
	 * 判断是否拥有该权限
	 * @param userId 用户id
	 * @param funcCode 函数代码
	 * @return
	 */
	Boolean authorFunc(String userId, String funcCode);
}
