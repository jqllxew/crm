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
    
    <title>My JSP 'add_dept.jsp' starting page</title>
    
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
		#select{
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
    <div id="div1">
    	<form  method="post" id="form">
    		<table>
    			<tr>
    				<td>部门名称:</td>
    				<td>
    					<input type="text" name="name" ${empty data ? '':'readonly="readonly"' }  value="${data.name}" class="easyui-textbox theme-textbox-radius" data-options="required:true, validType:['length[2,8]','deptname']">
    					<input type="hidden" value="${data.departmentid}" name="departmentid">
    				</td>
    			</tr>
    			<tr>
    				<td>上级部门:</td>
    				<td>
    					<select  name="parentid" id="select" data-option="required:true">
    						<option value="-1">请选择上级部门</option>
    						<c:forEach items="${listdata }" var="da">
    							<option value="${da.departmentid }">${da.name }</option>
    						</c:forEach>				
    					</select>    					
    				</td>
    			</tr>
    			<tr>
    				<td>部门描述:</td>
    				<td><textarea data-option="required:true" cols="50" rows="5" name="description">${data.description}</textarea></td>
    			</tr>
    			<tr>
    				<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-save">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-reset">重置</a>
					</td>
    			</tr>    			
    		</table>
    	</form>
    </div>
  </body>
  <script type="text/javascript">
  		//添加
  		$("#saveBtn").on("click",function(){
				$.post("manage/position/Department_addOrEdit.do",$("#form").serialize(),function(data){
					if(data){
						parent.$.messager.alert("提示",data.message)
						parent.closeTopWindow()
						return;
					}
					parent.$.messager.alert("提示",data.message)
					},"json")
  	  		})
  	  		//重置
  	  		$("#resetBtn").on("click",function(){
        		$("#form").form("clear")		
            	})
  	  		
  	  		$(function(){
  	  	  		$("#select").val('${data.parentid }')
  	  		//验证
  	  		$.extend($.fn.validatebox.defaults.rules, {
					deptname:{
						validator:function(value,param){
							var status = false;
							$.ajax({
								url:"manage/position/deptValid.do",
								type:"get",
								dateType:"json",
								asyuc:false,
								success:function(result){
									status = result.success
									console.log(result.success)
									}
								})
								return status
							}, 
						
						message:'部门已存在'
					}
  	  	  		})

  	  	  	})
  </script>
</html>
