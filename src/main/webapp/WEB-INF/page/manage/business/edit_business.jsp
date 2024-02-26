<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>编辑权限</title>

<meta name="pragma" content="no-cache">
<meta name="cache-control" content="no-cache">
<meta name="expires" content="0">
<%@include file="../../script.html"%>
</head>
<style type="text/css">
.myselect{
	height:30px;
}
</style>

<body style="width:100%;max-width:100%;padding:10px 30px;">
<form id="form" method="post">
<strong>主要信息</strong>
	<section class="info-section">
		
			<table id="tb">
				<tr>
					<td class="text-title">负责人</td>
					<td class="text-content">
						<input type="hidden" name="busi.businessid" value="${busi.businessid }">
						<input type="hidden" name="busi.createtime" value="${busi.createtime }">
						<input type="hidden" name="busi.creatoruserid" value="${busi.creatoruserid }">
						<select class="myselect" name="busi.ownerId">
							<option value="-1">请选择负责人</option>
						</select>
					</td>
					<td class="text-title">客户</td>
					<td class="text-content">
						<select class="myselect" name="busi.customerId">
							<option value="-1">请选择客户</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">商机金额</td>
					<td class="text-content">
						<input type="text" name="busi.salesprice" value="${busi.salesprice }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:false">
					</td>
					<td class="text-title">商机名</td>
					<td class="text-content">
						<input type="text" name="busi.name" value="${busi.name }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:false">
					</td>
				</tr>
				<tr>
					<td class="text-title">联系人</td>
					<td class="text-content">
						<select class="myselect" name="busiVO.contactsId">
						</select>
							<a href="javascript:void(0)" class="text-title" style="color:blue;">新建</a>
					</td>
					
				</tr>
				<tr>
					<td class="text-title" >合同签订地址</td>
					<td class="text-content" colspan="3">
						<input type="text" style="width:550px;" name="busi.contractaddress" value="${busi.contractaddress }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:false">
					</td>
				</tr>
				<tr>
					<td class="text-title">商机类型</td>
					<td class="text-content">
						<select class="myselect" name="busi.type">
						</select>
					</td>
					<td class="text-title">状态</td>
					<td class="text-content">
						<select class="myselect" name="busiVO.businessStatusId">
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">商机来源</td>
					<td class="text-content">
						<select class="myselect" name="busiVO.origin">
						</select>
					</td>
					<td class="text-title">赢单率</td>
					<td class="text-content">
						<input type="text" name="busi.gainrate" value="${busi.gainrate }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:false">
					</td>
				</tr>
				<tr>
					<td class="text-title">预定成交价</td>
					<td class="text-content">
						<input type="text" name="busi.estimateprice" value="${busi.estimateprice }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
					<td class="text-title">下次联系时间</td>
					<td class="text-content">
						<input class="easyui-datetimebox" name="busi.nextsteptime" value="${busi.nextsteptime }"
	  					  data-options="required:false,showSeconds:false" style="width:150px">
					</td>
				</tr>
				<tr>
					<td class="text-title">下次联系内容</td>
					<td class="text-content">
						<input type="text" name="busi.nextstep" value="${busi.nextstep }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:false">
					</td>
					
				</tr>
				
				
				</table>
		
	</section>
	<strong>附加信息</strong>
	<section class="info-section">
			<table>
				<tr>
					<td class="text-title">备注</td>
					<td class="text-content">
						<textarea name="busi.description" rows="5" style="width:80%;">${busi.description }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary" style="margin-left:-70px;">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger" style="margin-left:30px;">重置</a>
					</td>
				</tr>
				</table>
	</section>
	</form>
	
	<script type="text/javascript">
$(function(){
	//给负责人下拉框填充选项
	
	//给客户下拉框填充选项
	
	  //给商机类型下拉框填充选项
	
	 //给商机状态下拉框填充选项
	
	 //给商机来源下拉框填充选项
	
	  		
	  		
	  		
	  		
	  		
	  		
})
//监听客户下拉框的事件，若更改客户则重新给联系人下拉框填充选项


 //给联系人下拉框填充选项的方法

	
	
	
	
			//当页面加载完毕初始化树状下拉列表
			
		
			
	</script>
</body>
</html>
				