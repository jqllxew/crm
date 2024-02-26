<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.mytaglib.com/taglib/func" prefix="func" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色列表</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<%@include file="../script.html" %>

  </head>
  
  <body >
   	<table id="roleList" class="easyui-datagrid"></table>
    <div id="toolbar">
    	<func:func funcCode="roleinsert">
    	<a href="javascript:void(0);" onclick="return add('system/role/RoleAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
    	</func:func>
    	<func:func funcCode="roleremove">
    	<a href="javascript:void(0);" onclick="return del('system/role/RoleAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    	</func:func>
    	<func:func funcCode="roleedit">
    	<a href="javascript:void(0);" onclick="return edit('system/role/RoleAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    	</func:func>
    	<func:func funcCode="roleassign">
    	<a href="javascript:void(0);" onclick="return assign('system/role/RoleAction_assign.action')" id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true">分配权限</a>
    	</func:func>
    </div>
    <script type="text/javascript">
	    function getIds(){
			var ids = new Array();
			$(":checked").each(function(){
				if($(this).prop("name")=="id"){
					ids.push($(this).val())
				}
			})
			return ids;
		}
    	//分配权限按钮事件处理函数
    	function assign(url){
    		//获取到选中的那个角色的id
    		var ids = getIds();
    		if(ids.length != 1){
    			$.messager.alert("提示","请选择一个目标")
    			return;
    		}
    		parent.openTopWindow(
       			{
       				width:500,
       				height:600,
       				title:"权限分配",
       				url:url+"?id="+ids,
       				isScrolling:"yes",
       				close:function(){
       					$("#roleList").datagrid('reload');
       				}
       			}		
       			);
    	}
    	//修改按钮事件处理函数
    	function edit(url){
    		var ids = getIds();
    		if(ids.length != 1){
    			$.messager.alert("提示","请选择一个目标修改")
    			return;
    		}
    		parent.openTopWindow(
     			{
     				width:500,
     				height:550,
     				title:"编辑角色",
     				url:url+"?id="+ids,
     				close:function(){
     					$("#roleList").datagrid('reload');
     				}
     			}		
     			);
    	}
    	
    	//添加按钮事件处理函数
        function add(url){
        	parent.openTopWindow(
      			{
      				width:500,
      				height:550,
      				title:"编辑角色",
      				url:url,
      				close:function(){
      					$("#roleList").datagrid('reload');
      				}
      			}		
      			);
        }
        //删除按钮事件处理函数
        function del(url){
        	//获取到选中的一行数据
    		var ids = getIds()
    		if(ids.length < 1){
    			$.messager.alert("提示","请至少选择一个目标")
    			return;
    		}
        	
        	$.messager.confirm("警告","是否确认删除"+ids.length+"条记录?",function(b){
       		 if(b){
	        	var roles = $("#roleList").datagrid("getSelections")
	        	console.log(roles)
	        	var roleIds = new Array()
	        	var dRoleIds = new Array()
	        	$.each(roles,function(index,role){
	        		if(role.status == 2){
	        			dRoleIds.push(role.id)
	        		}else{
	        			roleIds.push(role.id)
	        		}
	        	})
		    		$.post(url,
		    			{
			    			"roleIds":roleIds.join(","),
			    			"dRoleIds":dRoleIds.join(",")
		    			},
		    			function(result){
		    			if(result.ex){
		    				$.messager.alert("警告",result.ex)
		    				return;
		    			}
		    			if(result.success){
		    				$.messager.alert("提示",result.message)
		    				$("#roleList").datagrid('reload')
		    				$("#roleList").datagrid('uncheckAll')
		    				return;
		    			}
		    			$.messager.alert("警告",result.message)
		    		},"json")
       		 }
      		 })
       		
        }
    	//加载角色数据
    	$(function(){
    		$("#roleList").datagrid({
    			url : "system/role/RoleAction_findByPage.action",
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "id",
    			rownumbers : true,
    			//singleSelect:true,
    			columns : [[
    				{field:"id",title:"选择",checkbox:true},
    				{field:"rolename",title:"角色名称",sortable:true,width:10},
    				{field:"rolenote",title:"角色描述",width:15},
    				{field:"funcnames",title:"权限",width:10},
    				{field:"createby",title:"创建者",width:15},
    				{field:"createtime",title:"创建时间",width:10},
    				{field:"updateby",title:"修改者",width:15},
    				{field:"updatetime",title:"修改时间",width:10},
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
    				return {"total":data.data.totalRows,"rows":data.data.data};
    			}
    		
    		});
    	});
    </script>
  </body>
</html>
