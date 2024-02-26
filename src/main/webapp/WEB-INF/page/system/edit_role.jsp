<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>添加角色</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="content-type" content="text/html;charset=UTF-8">
	<meta name="expires" content="0">    
	<%@ include file="../script.html" %>
	
  </head>
  
  <body style="width:100%;max-width:600px;padding:30px 60px;">
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">角色名称：</td>
					<td class="text-content">
						<input type="hidden" name="id" value="${roleVO.id }"> 
						<input type="text" name="rolename" value="${roleVO.rolename }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:['length[3,10]','rolename']">
					</td>
				</tr>
				<tr>
					<td class="text-title">排序编号：</td>
					<td class="text-content">
						<input type="text" name="sortnum" value="${roleVO.sortnum }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'sortnum'">
					</td>
				</tr>
				<tr>
					<td class="text-title">角色状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${roleVO.status==1?"selected":"" }>正常</option>
							<option value="0" ${roleVO.status==0?"selected":"" }>禁用</option>
							<option value="2" ${roleVO.status==2?"selected":"" }>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">描述信息：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="rolenote">${roleVO.rolenote }</textarea></td>
				</tr>

				<tr style="display: none;">
					<td colspan="2">
						<input type="hidden" name="funcIds" value="${roleVO.funcIds}"> 
						<input type="hidden" name="createbyId" value="${roleVO.createbyId}"> 
						<input type="hidden" name="createtime" value="${roleVO.createtime}">
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
		rolename:{
			validator:function(value, param){
				var status = false;
				$.ajax({
					url:"system/role/RoleAction_validate.do",
					type:"get",
					dataType:"json",
					data:{"rolename":value,"roleid":'${roleVO.id}'},
					async:false,
					success:function(result){
						if(result && result.success){
							status = result.success
						}
					}
				})
				return status;
			},
				message:'角色名已存在'
		},
		sortnum:{
			validator:function(value, param){
				return /^[0-9]{0,}$/.test(value);
			},
				message:'请输入数字'
		}
	})
		function save(){
			if(!$("#form").form('validate')){
				return;
			}
			$.post("system/role/RoleAction_edit.action",
				$("#form").serialize(),function(result){
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
			$("#form").form("clear")
		})
	</script>
</body>
</html>
				
