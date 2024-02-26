package com.zhidi.crm.system.entity.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FuncVo {

	private String id;
	private String funcname;
	private String functype;
	private String funcurl;
	private String funcnote;
	private String createby;
	private String createbyId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createtime;
	private String updateby;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updatetime;
	private Long status;
	private String funccode;
	private String parentid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFuncname() {
		return funcname;
	}
	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}
	public String getFunctype() {
		return functype;
	}
	public void setFunctype(String functype) {
		this.functype = functype;
	}
	public String getFuncurl() {
		return funcurl;
	}
	public void setFuncurl(String funcurl) {
		this.funcurl = funcurl;
	}
	public String getFuncnote() {
		return funcnote;
	}
	public void setFuncnote(String funcnote) {
		this.funcnote = funcnote;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getCreatebyId() {
		return createbyId;
	}
	public void setCreatebyId(String createbyId) {
		this.createbyId = createbyId;
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
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getFunccode() {
		return funccode;
	}
	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	
}
