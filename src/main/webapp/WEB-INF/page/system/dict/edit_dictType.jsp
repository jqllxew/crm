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
					<td class="text-title">字典类型名：</td>
					<td class="text-content">
						<input type="hidden" name="id" value="${dictType.id }"> 
						<input type="hidden" name="createby" value="${dictType.createby }"> 
						<input type="text" name="typename" value="${dictType.typename }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'typename'">
					</td>
				</tr>

				<tr>
					<td class="text-title">字典类型编码：</td>
					<td class="text-content">
						<input type="text" name="typecode" value="${dictType.typecode }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'typecode'">
					</td>
				</tr>
				<tr>
					<td class="text-title">备注：</td>
					<td class="text-content">
						<input type="text" name="typenote" value="${dictType.typenote }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
				
				<tr>
					<td class="text-title">排序编号：</td>
					<td class="text-content">
						<input type="text" name="sortnum" value="${dictType.sortnum }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'sortnum'">
					</td>
				</tr>
				<tr>
					<td class="text-title">状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${dictType.status==1?"selected":"" }>正常</option>
							<option value="0" ${dictType.status==0?"selected":"" }>禁用</option>
							<option value="2" ${dictType.status==2?"selected":"" }>已删除</option>
						</select>
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
	$.extend($.fn.validatebox.defaults.rules, {
		typename:{
			validator:function(value, param){
				var status = false;
				$.ajax({
					url:"system/dict/DictTypeAction_validate.do",
					type:"get",
					dataType:"json",
					data:{"typename":value,"typeid":'${dictType.id}'},
					async:false,
					success:function(result){
						if(result && result.success){
							status = result.success
						}
					}
				})
				return status;
			},
				message:'该类型已存在'
		},
		typecode:{
			validator:function(value, param){
				var status = false;
				$.ajax({
					url:"system/dict/DictTypeAction_validate.do",
					type:"post",
					dataType:"json",
					data:{"typecode":value,"typeid":'${dictType.id}'},
					async:false,
					success:function(result){
						if(result && result.success){
							status = result.success
						}
					}
				})
				return status;
			},
				message:'类型编码重复!!'
		},
		sortnum:{
			validator:function(value, param){
				return /^[0-9]{0,}$/.test(value);
			},
				message:'请输入数字'
		}
	})
		$(function() {
			//重置按钮
			$("#resetBtn").on("click",function(event){
				$("#form").form("reset");
			});
			//保存按钮
			$("#saveBtn").on("click", function(event) {
				if(!$("#form").form('validate')){
					return;
				}
				$.post("system/dict/DictTypeAction_edit.action",$("form").serialize(),function(result){
					if(result.ex){
						$.messager.alert("警告",result.ex)
						return;
					}
					if(result.success){
						parent.$.messager.alert("提示",result.message)
						parent.closeTopWindow()
						return;
					}
					$.messager.alert("提示",result.message)
				},"json")
			});
		});
	</script>
</body>
</html>
				