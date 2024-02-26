<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加任务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../../script.html" %>
  </head>
  
  <body style="width:100%;max-width:600px;padding:30px 60px;">
  	<section class="info-section">
    	<form id="form" method="post">
    		<table>
				<tr>
					<td class="text-title">创建者名：</td>
					<td class="text-content">
						<input type="hidden" name="taskid" value="${task.taskid }"> 
						<input type="text" name="creatoruserid" value="${task.creatoruserid }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:['length[2,10]','username']">
					</td>
				</tr>

				<tr>
					<td class="text-title">负责人：</td>
					<td class="text-content">
						<input type="text" name="owneruserid" value="${task.owneruserid }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:['length[2,10]','username']">
					</td>
				</tr>
				<c:if test="${empty task }">
				<tr >
					<td class="text-title">删除人：</td>
					<td class="text-content">
						<input type="text" name="deleteuserid" value="${task.deleteuserid }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:['length[2,10]','username']">
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="text-title">相关人：</td>
					<td class="text-content">
						<input type="text" name="aboutUser" value="${task.aboutUser }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">主题：</td>
					<td class="text-content">
						<input type="text" name="subject" value="${task.subject }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="text-title">优先级：</td>
					<td class="text-content">
						<input type="text" name="priority" value="${task.priority }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="text-title">邮件通知：</td>
					<td class="text-content">
						<input type="text" name="sendemail" value="${task.sendemail }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'email'"></td>
				</tr>
				
				<tr>
					<td class="text-title">截止日期：</td>
					<td class="text-content">
						<input type="text" name="duedate" value="<fmt:formatDate value="${task.duedate }"  pattern='yyyy-MM-dd'/>"
							class="easyui-datebox theme-datebox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">更新日期：</td>
					<td class="text-content">
						<input type="text" name="updtadate" value='<fmt:formatDate value="${task.updtadate }" pattern='yyyy-MM-dd'/>'
							class="easyui-datebox theme-datebox-radius" data-options="required:true">
					</td>
				</tr>
				
				<tr>
					<td class="text-title">任务状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${task.status==1?"selected":"" }>正常</option>
							<option value="0" ${task.status==0?"selected":"" }>禁用</option>
							<option value="2" ${task.status==2?"selected":"" }>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">描述信息：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="description">${task.description }</textarea></td>
				</tr>
				<!-- 因为用户关联信息不需要修改，但是如果没有把这些信息传递给服务器端，hibernate对数据进行更新时就会把对应条目设置为null值 -->

				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
				</tr>
			</table>	
    	</form>
    </section>	
  </body>
  	<script type="text/javascript">
  	$.extend($.fn.validatebox.defaults.rules, {
		username:{
			validator:function(value, param){
				var status = false;
				$.ajax({
					url:"system/user/UserAction_validate.do",
					type:"get",
					dataType:"json",
					data:{"username":value},
					async:false,
					success:function(result){
						if(result){
							status = !result.success
						}
					}
				})
				return status;
			},
				message:'用户名不存在'
		}
	})	
  	
  			$("#saveBtn").on("click" , function(){  			
  				if(!$("#form").form('validate')){
  					return;
  				}
  				$.post("manage/task/TaskAction_edit.action",$("#form").serialize(),function(result){
  					if(result.ex){
  						$.messager.alert("警告",result.ex)
  						return;
  					}
  					if(result.success){
  						parent.$.messager.alert("提示",result.message);
  						parent.closeTopWindow()
  						return;
  					}
  					$.massager.alert("提示",result.message)
  				},"json")
  			})
  		
  		
  		
  			$("#resetBtn").on("click",function(){
  				$("#form").form("reset")
  			});	
  		
  	
  	</script>
</html>
