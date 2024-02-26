package com.zhidi.crm.system.mapper;

import java.util.Date;
import java.util.List;

import com.zhidi.crm.base.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import com.zhidi.crm.system.entity.Function;
import com.zhidi.crm.system.entity.vo.FuncVo;
import com.zhidi.crm.system.entity.vo.TreeVo;

public interface FunctionMapper extends IBaseMapper<Function> {
	
	List<Function> getmyFunction(String id);
	
	Function selectByName(String funcname);
	Function selectByFunccode(String funccode);
	/**
	 * 借用了一下别的字段
	 * @param id
	 * @return
	 */
	Function selectByPrimaryKey2(String id);
	
	/**
	 * 查询所有权限
	 * @return
	 */
	List<Function> queryfindAll();
	
	/**
	 * 总数
	 * @return
	 */
	Integer getCount();
	
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
	 * 删除角色所有权限
	 * @param roleid
	 * @return
	 */
	int deleteFunctionDist(String roleid);
	
	/**
	 * 添加角色权限
	 * @param roleid
	 * @param funcid
	 * @return
	 */
	int insertFunctionDist(@Param("roleid")String roleid,@Param("funcid")String funcid);
	
	/**
	 * 查询combotree所需数据 (下一级)
	 * @param id
	 * @return
	 */
	List<TreeVo> findCombotree(String id);
	
	/**
	 * 查询下一级个数
	 * @param id
	 * @return
	 */
	Integer findTreecount(String id);
	
	/**
	 * 解除自联接(用于修改权限)
	 * @param id
	 * @return
	 */
	Integer deleteParentid(String id);
	
	/**
	 * 删除权限所有角色
	 * @param funcids
	 * @return
	 */
	int removeRoleFunction(@Param("funcids")String[] funcids);
	
	/**
	 * 逻辑删除权限
	 * @param funcids
	 * @param adminId
	 * @param updateTime
	 * @return
	 */
	int removeFunction(@Param("funcids")String[] funcids,
						@Param("adminId")String adminId,
						@Param("updateTime")Date updateTime);
	
	/**
	 * 物理删除权限
	 * @param roleids
	 * @return
	 */
	int deleteFunction(@Param("funcids")String[] funcids);
	
	/**
	 * 递归 查询子记录
	 * @param funcids
	 * @return
	 */
	List<String> selectChild(@Param("funcids")List<String> funcids);
	
	/**
	 * 
	 * @param userId
	 * @param funcCode
	 * @return
	 */
	Function getFuncByFuncCode(@Param("userId")String userId, @Param("funcCode")String funcCode);
	
	/**
	 * 查询登陆用户的所有权限url
	 * @param userid
	 * @return
	 */
	List<String> getAllurlByUserId(String userid);
}