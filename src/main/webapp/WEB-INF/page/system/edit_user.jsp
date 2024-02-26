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
<%@include file="../script.html"%>
</head>

<body >
	<section class="info-section">
		<form id="form" >
			<table>
				<tr>
					<td class="text-title">用户名：</td>
					<td class="text-content">
						<input type="hidden" name="id" value="${userVO.id }"> 
						<input type="text" name="username" ${empty userVO ? '':'readonly="readonly"'} value="${userVO.username }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:['length[6,20]','username']">
					</td>
				</tr>

				<tr style="display:${empty userVO ? 'table-row' : 'none'};">
					<td class="text-title">密码：</td>
					<td class="text-content">
						<input type="password" name="password" value="${userVO.password }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[6,20]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">联系电话：</td>
					<td class="text-content">
						<input type="text" name="phone" value="${userVO.phone }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'phone'">
					</td>
				</tr>
				<tr>
					<td class="text-title">电子邮箱：</td>
					<td class="text-content">
						<input type="text" name="email" value="${userVO.email }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'email'"></td>
				</tr>
				<tr>
					<td class="text-title">排序编号：</td>
					<td class="text-content">
						<input type="text" name="sortnum" value="${userVO.sortnum }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'sortnum'">
					</td>
				</tr>
				<tr>
					<td class="text-title">用户状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${userVO.status==1?"selected":"" }>正常</option>
							<option value="0" ${userVO.status==0?"selected":"" }>禁用</option>
							<option value="2" ${userVO.status==2?"selected":"" }>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">描述信息：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="usernote">${userVO.usernote }</textarea></td>
				</tr>
				<!-- 因为用户关联信息不需要修改，但是如果没有把这些信息传递给服务器端，hibernate对数据进行更新时就会把对应条目设置为null值 -->

				<tr style="display: none;">
					<td colspan="2">
						<input type="hidden" name="roleIds" value="${userVO.roleIds}"> 
						<input type="hidden" name="createbyId" value="${userVO.createbyId}"> 
						<input type="hidden" name="createtime" value="${userVO.createtime}">
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
		username:{
			validator:function(value, param){
				var status = false;
				$.ajax({
					url:"system/user/UserAction_validate.do",
					type:"get",
					dataType:"json",
					data:{"username":value,"userid":'${userVO.id}'},
					async:false,
					success:function(result){
						if(result && result.success){
							status = result.success
						}
					}
				})
				return status;
			},
				message:'用户名已存在'
		},
		phone: {
			validator:function(value, param){
			return /^1[3|4|5|8][0-9]\d{4,8}$/.test(value);
		},
			message:'号码格式不正确'
		},
		email:{
			validator:function(value, param){
				var emailvali = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)
			return emailvali;     
			},
			message:'邮箱格式不正确'
		},
		sortnum:{
			validator:function(value, param){
				return /^[0-9]{0,}$/.test(value);     
			},
				message:'请输入数字'
		}
	})
	
		function save(){
		 var b = $("#form").form('validate')
			if(!b){
				return;
			}
			$.post("system/user/UserAction_edit.action",
				$("#form").serialize(),function(result){
				if(result.ex){
					parent.$.messager.alert("警告",result.ex)
					return;
				}
				if(result.success){
					parent.$.messager.alert("提示",result.message)
					parent.closeTopWindow()
					return;
				}
				$.messager.alert("警告",result.message)
			},"json")
		}
		
		$("#saveBtn").on("click",function(){
				save();
		})
		$("#resetBtn").on("click",function(){
			var id = $("input[name='id']").val()
			if(!id){
				$("input[textboxname='username']").textbox("setValue","")
				$("input[textboxname='password']").textbox("setValue","")
			}
			$("input[textboxname='phone']").textbox("setValue","")
			$("input[textboxname='email']").textbox("setValue","")
			$("input[textboxname='sortnum']").textbox("setValue","")
			$("select[comboname='status']").combobox("setValue","")
			$("textarea[name='usernote']").val("")
		})
		
	</script>
</body>
</html>
				