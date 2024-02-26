<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>岗位管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../../script.html" %>
	<style type="text/css">
		table{
			margin:auto;
		}
		input[type="text"]{
			width:85%;
			height:30px;
			border:0.5px solid gray;
		}
		select{
			width:85%;
			height:30px;
			border:0.5px solid gray;
		}
	
		td:only-child{
			text-align:center;
			
		}
	</style>

  </head>
  
  <body> 
    <div>
    	<form  method="post" id="form">
    		<table>
    			<tr>
    				<td>岗位名称:</td>
    				<td>
    					<input type="hidden" name="positionid" value="${Vo.positionid }">
    					<input type="text" name="name" ${empty Vo ? '':'readonly="readonly"' } value="${Vo.pname }" class="easyui-textbox theme-textbox-radius" data-options="required:true, validType:['length[2,8]','positionname']">
    				</td>
    			</tr>
    			<tr>
    				<td>所属部门:</td>
    				<td>
    					<select name="departmentid" id="select2">
    						<option value="-1">请选择所属部门</option>
    						<c:forEach items="${listDatad }" var="Vod">
    							<option value="${Vod.departmentid }">${Vod.dname }</option>
    						</c:forEach>			
    					</select>
    				</td>
    			</tr>
    			<tr>
    				<td>上级岗位:</td>
    				<td>
    					<select id="select1" name="parentid">
    						<option value="-1">请选择岗位上级</option>				
    						<c:forEach items="${listDatap }" var="Vop">
    							<option value="${Vop.positionid }">${Vop.tpname }</option>
    						</c:forEach>
    					</select>    					
    				</td>
    			</tr>
    			<tr>
    				<td>岗位描述:</td>
    				<td><textarea rows="6" cols="50" name="description">${Vo.description}</textarea></td>
    			</tr>
    			<tr>
    				<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
    			</tr>    			
    		</table>
    	</form>
    </div>
  </body>
  <script type="text/javascript">
  	$(function() {
  		$("#select1").val('${Vo.parentid}')
  		$("#select2").val('${Vo.departmentid}')
  		//保存
	  		$("#saveBtn").on("click",function(){
					$.post("manage/position/PositionAction_addOrEdit.do",
						$("#form").serialize(),
					function(data){
						if(data){
							parent.$.messager.alert("提示",data.message)
							parent.closeTopWindow()
							return;
						}
						parent.$.messager.alert("提示",data.message)
					},"json")
	  	  		})
	  	  		//验证
  	  		$.extend($.fn.validatebox.defaults.rules, {
					positionname:{
						validator:function(value,param){
							var status = false;
							$.ajax({
								url:"manage/position/PositionValid.do?name="+name,
								type:"get",
								dateType:"json",
								asyuc:false,
								success:function(result){
									status = result.success
									}
								})
								return status
							}, 
						
						message:'岗位已存在'
					}
  	  	  		})
	  	  		
		});
	//重置
	$("#resetBtn").on("click",function(){
		$("#from").from("clear")
	})
	
	  </script>
</html>
