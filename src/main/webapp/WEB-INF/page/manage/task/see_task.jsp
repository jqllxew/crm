<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'lsee_task.jsp' starting page</title>
    
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
						<input type="hidden" name="taskVo.taskid" value="${task.taskid }"> 
						<input type="text" name="taskVo.creatorUsername" value="${task.creatoruserid }" 
							class="easyui-textbox theme-textbox-radius"  readonly="readonly">
					</td>
				</tr>

				<tr>
					<td class="text-title">负责人：</td>
					<td class="text-content">
						<input type="text" name="taskVo.ownerUsername" value="${task.owneruserid }"
							class="easyui-textbox theme-textbox-radius"  readonly="readonly">
					</td>
				</tr>
				<tr style="display:${empty taskVo ? 'table-row' : 'none'};">
					<td class="text-title">删除人：</td>
					<td class="text-content">
						<input type="text" name="taskVo.DeleteUsername" value="${task.deleteuserid }"
							class="easyui-textbox theme-textbox-radius"  readonly="readonly">
					</td>
				</tr>

				<tr>
					<td class="text-title">相关人：</td>
					<td class="text-content">
						<input type="text" name="taskVo.aboutUserId" value="${task.aboutUser }"
							class="easyui-textbox theme-textbox-radius"  readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="text-title">主题：</td>
					<td class="text-content">
						<input type="text" name="taskVo.subject" value="${task.subject }"
							class="easyui-textbox theme-textbox-radius"  readonly="readonly"></td>
				</tr>
				<tr>
					<td class="text-title">优先级：</td>
					<td class="text-content">
						<input type="text" name="taskVo.priority" value="${task.priority }"
							class="easyui-textbox theme-textbox-radius" readonly="readonly" ></td>
				</tr>
				<tr>
					<td class="text-title">邮件通知：</td>
					<td class="text-content">
						<input type="text" name="taskVo.sendemail" value="${task.sendemail }"
							class="easyui-textbox theme-textbox-radius" readonly="readonly" ></td>
				</tr>
				
				<tr>
					<td class="text-title">截止日期：</td>
					<td class="text-content">
						<input type="text" name="taskVo.duedate" value='<fmt:formatDate value="${task.duedate }" pattern='yyyy-MM-dd'/>'
							class="easyui-textbox theme-textbox-radius" readonly="readonly" >
					</td>
				</tr>
				<tr>
					<td class="text-title">更新日期：</td>
					<td class="text-content">
						<input type="text" name="taskVo.updtadate" value='<fmt:formatDate value="${task.updtadate }" pattern='yyyy-MM-dd'/>'
							class="easyui-textbox theme-textbox-radius" readonly="readonly" >
					</td>
				</tr>
				
				<tr>
					<td class="text-title">任务状态：</td>
					<td class="text-content">
						<select name="taskVo.status" class="easyui-combobox theme-textbox-radius"  readonly="readonly">
							<option value="1" ${task.status==1?"selected":"" }>正常</option>
							<option value="0" ${task.status==0?"selected":"" }>禁用</option>
							<option value="2" ${task.status==2?"selected":"" }>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">描述信息：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="taskVo.description">${task.description }</textarea></td>
				</tr>
				<!-- 因为用户关联信息不需要修改，但是如果没有把这些信息传递给服务器端，hibernate对数据进行更新时就会把对应条目设置为null值 -->
				
			</table>	
    	</form>
    </section>
  </body>
</html>
