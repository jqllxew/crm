package com.zhidi.crm.manager.entity.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContractVo {

	private String contractid;
	
	private String contractnumber;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date duetime;
	
	private String businessConnectionName;
	
	private String businessCustomerName;
	
	private String ownerUserId;
	
	private double price;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startdate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date enddate;
	
	private String status;
	
	private String contractcontent;
	
	private String description;

	public String getContractid() {
		return contractid;
	}

	public void setContractid(String contractid) {
		this.contractid = contractid;
	}

	public String getContractnumber() {
		return contractnumber;
	}

	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}

	public Date getDuetime() {
		return duetime;
	}

	public void setDuetime(Date duetime) {
		this.duetime = duetime;
	}

	public String getBusinessConnectionName() {
		return businessConnectionName;
	}

	public void setBusinessConnectionName(String businessConnectionName) {
		this.businessConnectionName = businessConnectionName;
	}

	public String getBusinessCustomerName() {
		return businessCustomerName;
	}

	public void setBusinessCustomerName(String businessCustomerName) {
		this.businessCustomerName = businessCustomerName;
	}

	public String getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContractcontent() {
		return contractcontent;
	}

	public void setContractcontent(String contractcontent) {
		this.contractcontent = contractcontent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
