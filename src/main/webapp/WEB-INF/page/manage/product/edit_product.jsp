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
	<%@ include file="../../script.html" %>
	
  </head>
  
  <body style="width:100%;max-width:600px;padding:30px 60px;">
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">产品名称：</td>
					<td class="text-content">
						<input type="hidden" name="productid" value="${product.productid }"> 
						<input type="text" name="name" value="${product.name }" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[3,10]'">
					</td>
				</tr>
				<tr>
					<td class="text-title">详情链接：</td>
					<td class="text-content">
						<input type="text" name="link" value="${product.link }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
							<input type="hidden" name="productVO.user" value="${product.creatoruserid }">
					</td>
				</tr>
				
				<!--  <tr>
					<td class="text-title">创建人：</td>
					<td class="text-content">
						<input type="hidden" name="productVO.user" value="${productVO.user }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				
				</tr>
				-->
				<tr>
					
					<td class="text-content">
					    <input type="hidden" name="updatetime"  value="${product.updatetime }" >
						<input type="hidden" name="createtime"  value="${product.createtime }" >	 
					</td>
				</tr>
				<!-- <tr>
					<td class="text-title">更新时间：</td>
					<td class="text-content">
						<input type="text" name="productVO.updatetime" value="${productVO.updatetime }" class="easyui-textbox theme-textbox-radius"data-options="required:true">	 
					</td>
				</tr>
				 -->
				<tr>
					<td class="text-title">成本价格：</td>
					<td class="text-content">
						<input type="text" name="costprice" value="${product.costprice }"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>	
				<tr>
					<td class="text-title">建议价格：</td>
					<td class="text-content">
						<input type="text" name="suggestedprice" value="${product.suggestedprice }"
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
		$("#resetBtn").on("click",function(){
			$("#form").form("clear")
		})
		$("#saveBtn").on("click",function(){
			if(!$("#form").form("validate")){
				return;
			}
			$.post("manage/product/ProductAction_edit.action",$("#form").serialize(),function(result){
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
		})
			
	</script>
</body>
</html>
				
