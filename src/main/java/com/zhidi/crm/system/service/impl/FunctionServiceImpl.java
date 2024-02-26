package com.zhidi.crm.system.service.impl;

import com.zhidi.crm.base.service.impl.BaseServiceImpl;
import com.zhidi.crm.system.mapper.FunctionMapper;
import com.zhidi.crm.system.entity.Function;
import com.zhidi.crm.system.entity.User;
import com.zhidi.crm.system.entity.vo.FuncVo;
import com.zhidi.crm.system.entity.vo.MenuVo;
import com.zhidi.crm.system.entity.vo.TreeVo;
import com.zhidi.crm.system.service.IFunctionService;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements IFunctionService{

	@Getter
	@Resource
	private FunctionMapper mapper;

	private final List<String> child = new ArrayList<>();
	
	@Override
	public List<MenuVo> getmyFunction(String id) {
		List<Function> list = mapper.getmyFunction(id);
		List<MenuVo> menus = new ArrayList<>();
		
		for (Function function : list) {
			MenuVo menuVo = new  MenuVo();
			menuVo.setName(function.getFuncname());
			menuVo.setUrl(function.getFuncurl());
			menuVo.setpId(function.getParentid());
			menuVo.setId(function.getId());
			menus.add(menuVo);
		}
		return menus;
	}

	@Override
	public List<Function> queryfindAll() {
		
		return mapper.queryfindAll();
	}

	@Override
	public List<String> queryDefaultFunction(String roleid) {
		List<String> funcids = mapper.queryDefaultFunction(roleid);
		List<String> funcIds = new ArrayList<>();
		for (String funcid : funcids) {
			if(mapper.findTreecount(funcid) <= 0)
				funcIds.add(funcid);
		}
		return funcIds;
	}

	@Override
	public boolean updateFunctionDist(String roleid, String funcids) {
		mapper.deleteFunctionDist(roleid);
		if(StringUtils.isEmpty(funcids))
			return true;
		String[] split = funcids.split(",");
		for (String funcid : split) {
			if(mapper.insertFunctionDist(roleid, funcid) <= 0)
				return false;
		}
		return true;
	}

	@Override
	public List<FuncVo> queryfindAll2() {
		return mapper.queryfindAll2();
	}
	
	@Override
	public List<TreeVo> findCombotree(String id) {
		List<TreeVo> combotree = mapper.findCombotree(id);
		for (TreeVo tree : combotree) {
			if(mapper.findTreecount(tree.getId()) > 0){
				tree.setState("closed");continue;
			}
			tree.setState("open");
		}
		return combotree;
	}

	@Override
	public int updateByPrimaryKeySelective(Function func) {
		if(func.getId().equals(func.getParentid())){
			return -7;
		}
		List<String> funcid = new ArrayList<>();
		funcid.add(func.getId());
		selectChild(funcid);
		//判断父节点是否为子记录
		if(child.contains(func.getParentid())){
			//若是子记录 则先解除自联接
			mapper.deleteParentid(func.getId());
		}
		child.clear();//清空集合
		return super.updateByPrimaryKeySelective(func);
	}

	/**
	 * 得出所有子记录
	 * @param funcids
	 * @return
	 */
	public void selectChild(List<String> funcids){
		List<String> ids = mapper.selectChild(funcids);
		if(ids.size() > 0){
			child.addAll(ids);
			selectChild(ids);
		}
	}
	@Override
	public boolean removeFunction(User admin, String funcIds , String dfuncIds) {
		boolean a = false, b = false;
		if(!StringUtils.isEmpty(funcIds)){
			String[] funcids = funcIds.split(",");
			mapper.removeRoleFunction(funcids);
			for (String funcid : funcids) {
				mapper.deleteParentid(funcid);
			}
			a = mapper.removeFunction(funcids, admin.getId(), new Date()) > 0;
		}
		if(!StringUtils.isEmpty(dfuncIds)){
			String[] funcids = dfuncIds.split(",");
			b = mapper.deleteFunction(funcids) > 0;
		}
		
		return a | b;
	}

	@Override
	public boolean selectValidate(String funcname) {
		Function function = mapper.selectByName(funcname);
		return function == null;
	}
	
	@Override
	public boolean selectValidateFunccode(String funccode) {
		Function function = mapper.selectByFunccode(funccode);
		return function == null;
	}

	
	@Override
	public Function selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey2(id);
	}

	@Override
	public Boolean authorFunc(String userId, String funcCode) {
		return mapper.getFuncByFuncCode(userId, funcCode) != null;
	}
}
