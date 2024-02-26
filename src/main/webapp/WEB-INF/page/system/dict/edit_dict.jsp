<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>编辑用户</title>

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

<body >
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">字典名：</td>
					<td class="text-content">
						<input type="hidden" name="id" value="${dict.id }"> 
						<input type="text" name="dictname" value="${dict.dictname }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>

				<tr>
					<td class="text-title">字典编码：</td>
					<td class="text-content">
						<input type="text" name="dictcode" value="${dict.dictcode }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">字典值：</td>
					<td class="text-content">
						<input type="text" name="dictvalue" value="${dict.dictvalue }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="text-title">字典类型：</td>
					<td class="text-content">
						<select class="myselect" name="typeid" data-options="required:true,validType:'notNull' ">
							<option value="">请选择字典类型</option>
							<c:forEach items="${types }" var="type">
								<option value="${type.id }">${type.typename }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">字典备注：</td>
					<td class="text-content">
						<input type="text" name="dictnote" value="${dict.dictnote }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
				</tr>
				</table>
		</form>
	</section>
	<script type="text/javascript">
		$(function() {
			//初始化字典类型
			if('${dict.typeid}'){
				$("select[name='typeid']").val('${dict.typeid}')
			}
			//重置按钮
			$("#resetBtn").on("click",function(event){
				$("#form").form("reset");
			});
			//保存按钮
			$("#saveBtn").on("click", function(event) {
				if(!$("#form").form('validate')){
					return;
				}
				$.post("system/dict/DictAction_edit.action",$("#form").serialize(),function(result){
					if(result.ex){
						$.messager.alert("警告",result.ex)
						return;
					}
					if(result.success){
						parent.$.messager.alert("提示",result.message)
						parent.closeTopWindow();
						return;
					}
					$.messager.alert("提示",result.message)
				},"json")
			});
			$.extend($.fn.validatebox.defaults.rules, {
			    notNull: {
					validator: function(value, param){
						if(value==""){
							return false;
						}
						return true;
					},
					message: '请在下拉框中选择一个词典类型'
			    }
			});


	});
		
	</script>
</body>
</html>
				