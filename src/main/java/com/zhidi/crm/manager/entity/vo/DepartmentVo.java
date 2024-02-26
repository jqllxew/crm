package com.zhidi.crm.manager.entity.vo;

public class DepartmentVo {
	private String departmentid;
	private String dname;
	private String parentname;
	private String description;
	private String Parentid;

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getparentname() {
		return parentname;
	}

	public void setparentname(String parentname) {
		this.parentname = parentname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentid() {
		return Parentid;
	}

	public void setParentid(String parentid) {
		Parentid = parentid;
	}

}
