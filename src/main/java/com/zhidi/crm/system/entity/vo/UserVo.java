package com.zhidi.crm.system.entity.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserVo {

	private String id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String usernote;
	private String rolenames;
	private String roleIds;
	private String createby;
	private String createbyId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createtime;
	private String updateby;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updatetime;
	private Long sortnum;
	private Long status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsernote() {
		return usernote;
	}
	public void setUsernote(String usernote) {
		this.usernote = usernote;
	}
	public String getRolenames() {
		return rolenames;
	}
	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
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
	public Long getSortnum() {
		return sortnum;
	}
	public void setSortnum(Long sortnum) {
		this.sortnum = sortnum;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	
	
}
