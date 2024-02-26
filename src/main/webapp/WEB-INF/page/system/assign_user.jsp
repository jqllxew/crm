<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>为用户分配角色</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<%@include file="../script.html" %>

  </head>
  
  <body>
    <!-- 把所有的角色查询出来展示到这个位置 -->
    <input type="hidden" name="userId" value="${user.id }" >
    <table id="dataList"></table>
    <div style="text-align: center;margin-top:10px;">
    	<a  class="easyui-linkbutton" id="assignBtn" data-options="iconCls:'icon-man'">分配</a>
    </div>
    <script type="text/javascript">
    	$(function(){
    		//获取id
    		function getIds(){
    			var ids = new Array();
    			$(":checked").each(function(){
    				if($(this).prop("name")=="id"){
    					ids.push($(this).val())
    				}
    			})
    			
    			return ids;
    		}
    		
    		$("#assignBtn").on("click",function(){
    			
    			$.post("system/role/Role_userDist.action",
    			{"userid":'${userid}',"roleids":getIds().join(',')},
    			function(result){
    				if(result.ex){
    					$.messager.alert("警告",result.ex)
    					return;
    				}
    				if(result.success){
    					parent.$.messager.alert("提示",result.message)
    					parent.closeTopWindow()
    					return;
    				}
    				$.messager.alert("警告",result.message)
    			},"json")
    			
    		});
    	
    		//加载所有角色
    		$("#dataList").datagrid({
    			url : "system/role/RoleAction_findAll.action",
    			fitColumns : true,
    			idField : "id",
    			rownumbers : true,
    			columns : [[
    				{field:"id",title:"选择",checkbox:true},
    				{field:"rolename",title:"角色名",sortable:true,width:10},
    				{field:"rolenote",title:"角色描述",width:10},
    				{field:"status",title:"角色状态",formatter:function(value,rowData,index){
    					if(value == 1){
    						return "可用";
    					}else if(value == 0){
    						return "禁用";
    					}else if(value == 2){
    						return "已删除";
    					}else{
    						return "";
    					}
    				}}
    				
    			]],
    			loadFilter:function(data){
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				console.log(data)
    				return {"rows":data.data};
    			},
    			//当前表格中所有的数据加载完毕之后
    			onLoadSuccess : function(){
    				//根据用户的id获取这个用户对应的角色信息
    				 var userid = '${userid}';
    				$.get("system/role/RoleAction_findAll.action", {"userid":userid}, function(result){
    					
    					$.each(result,function(index,obj){
		    				$("#dataList").datagrid('selectRecord',obj)
        				})
    				}, "json");
    			
    			}
    			
    		});
    	});
    </script>
  </body>
</html>
