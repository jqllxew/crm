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
    
    <title>用户列表</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">
	<%@include file="../script.html" %>

  </head>
  
  <body >
   	<table id="userList" class="easyui-datagrid" style="height: 468px"></table>
    <div id="toolbar">
    	<func:func funcCode="useradd">
    		<a href="javascript:void(0);" onclick="return add('system/user/UserAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
    	</func:func>
    	<func:func funcCode="userdel">
    	<a href="javascript:void(0);" onclick="return del('system/user/UserAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    	</func:func>
    	<func:func funcCode="useredit">
    	<a href="javascript:void(0);" onclick="return edit('system/user/UserAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    	</func:func>
    	<func:func funcCode="userassign">
    	<a href="javascript:void(0);" onclick="return assign('system/user/UserAction_assign.action')" id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true">分配角色</a>
    	</func:func>
    </div>
    <script type="text/javascript">
    
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
    	//分配角色按钮事件处理函数
    	function assign(url){
    		
    		var ids = getIds();
    		if(ids.length != 1){
    			$.messager.alert("提示","请选择一个目标分配")
    			return;
    		}
    		parent.openTopWindow(
       			{
       				width:500,
       				height:600,
       				title:"角色分配",
       				isScrolling:'yes',
       				url:url+"?id="+ids,
       				close:function(){
       					$("#userList").datagrid('reload');
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
        				title:"编辑用户",
        				url:url+"?id="+ids,
        				close:function(){
        					$("#userList").datagrid('reload');
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
        				title:"新建用户",
        				url:url,
        				close:function(){
        					$("#userList").datagrid('reload');
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
        		var users = $("#userList").datagrid("getSelections")
             	var userIds = new Array()//逻辑删除的id
             	var duserIds = new Array()//物理删除的id
             	$.each(users,function(index,user){
             		if(user.status == 2){
             			duserIds.push(user.id)
             		}else{
             			userIds.push(user.id)
             		}
             	})
        		 if(b){
		    		$.post(url,{"userIds":userIds.join(","),"duserIds":duserIds.join(",")},function(result){
		    			if(result.ex){
		    				$.messager.alert("警告",result.ex)
		    				return;
		    			}
		    			if(result.success){
		    				$.messager.alert("提示",result.message)
		    				$("#userList").datagrid('reload')
		    				$("#userList").datagrid('unselectAll')
		    				return;
		    			}
		    			$.messager.alert("警告",result.message)
		    		},"json")
        		 }
        	 })
    			
        }
    	//加载用户数据
    	function query(){
    		$("#userList").datagrid('load')
    	}
    	$(function(){
    		$("#userList").datagrid({
    			url : "system/user/UserAction_findByPage.action",
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "id",
    			rownumbers : true,
    			//singleSelect:true,
    			columns : [[
    				{field:"id",title:"选择",checkbox:true},
    				{field:"username",title:"用户名",sortable:true,width:10},
    				{field:"phone",title:"联系电话",width:15},
    				{field:"email",title:"电子邮箱",width:20},
    				{field:"rolenames",title:"用户角色",width:15},
    				{field:"createby",title:"创建者",width:10},
    				{field:"createtime",title:"创建时间",width:15},
    				{field:"updateby",title:"修改者",width:10},
    				{field:"updatetime",title:"修改时间",width:15},
    				{field:"status",title:"用户状态",formatter:function(value,rowData,index){
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
