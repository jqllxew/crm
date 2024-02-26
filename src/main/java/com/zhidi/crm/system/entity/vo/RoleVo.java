package com.zhidi.crm.system.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RoleVo {

	private String id;
	private String rolename;
	private String rolenote;
	private Long sortnum;
	private String funcIds;
	private String funcnames;
	private String createbyId;
	private String createby;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createtime;
	private String updateby;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updatetime;
	private BigDecimal status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRolenote() {
		return rolenote;
	}
	public void setRolenote(String rolenote) {
		this.rolenote = rolenote;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
	public Long getSortnum() {
		return sortnum;
	}
	public void setSortnum(Long sortnum) {
		this.sortnum = sortnum;
	}
	public String getFuncIds() {
		return funcIds;
	}
	public void setFuncIds(String funcIds) {
		this.funcIds = funcIds;
	}
	public String getCreatebyId() {
		return createbyId;
	}
	public void setCreatebyId(String createbyId) {
		this.createbyId = createbyId;
	}
	public String getFuncnames() {
		return funcnames;
	}
	public void setFuncnames(String funcnames) {
		this.funcnames = funcnames;
	}
	
	
	
}
