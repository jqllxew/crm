<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="func" uri="http://www.mytaglib.com/taglib/func" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工信息表</title>
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<%@include file="../../script.html" %>

  </head>
  <style type="text/css">
  body{
  	font-size:14px
  }
  </style>
  <body >
    <div id="toolbar" style="padding:0 30px;">
       	<b>员工姓名: </b><input class="easyui-textbox" id="name" type="text" name="name" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true" style="background-color:#00aa00;color:white">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload" style="background-color:#00aa00;color:white;margin-left:10px">重置</a>
        <func:func funcCode="empadd">
        <a href="javascript:void(0);" onclick="return add('manage/employee/EmployeeAction_add.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" style="background-color:#00aa00;color:white;margin-left:10px">添加</a>
    	</func:func>
    	<func:func funcCode="empremove">
    	<a href="javascript:void(0);" onclick="return remove('manage/employee/EmployeeAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" style="background-color:#f00;color:white;margin-left:10px">删除</a>
    	</func:func>
    	<func:func funcCode="empedit">
    	<a href="javascript:void(0);" onclick="return edit('manage/employee/EmployeeAction_edit.action')" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" style="background-color:#00aa00;color:white;margin-left:10px">修改</a>
    	</func:func>
    </div>
    <table id="empList" class="easyui-datagrid" style="font-size:14px"></table>
    <script type="text/javascript">
    	
    	//加载用户数据
    	$(function(){
    		$("#empList").datagrid({
    			url : "manage/employee/EmployeeAction_findByPage.action",
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "empid",
    			rownumbers : true,
    			//singleSelect:true,
    			columns : [[
    				{field:"empid",title:"选择",checkbox:true},
    				{field:"name",title:"员工姓名",width:10},
    				{field:"telephone",title:"联系电话",sortable:true,width:10},
    				{field:"email",title:"邮箱",sortable:true,width:10},
    				{field:"address",title:"地址",width:10},			
    			]],
    			loadFilter:function(data){
        			console.log(data.data.data);
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.data.totalRows,"rows":data.data.data};
    			}
    		
    		})

    	});


    	
    	//获取id的方法
    	function getIds(){
    		var ids = new Array();
    		$(":checked").each(function(){
    			if($(this).prop("name")=="empid"){
    			ids.push($(this).val())
    			}
    		})
    		return ids;
    	}
    	//新增
		function add(url){
			parent.openTopWindow({
				width:520,
				height:400,
				title:"添加员工",
				url:url,
				close:function(){
					$("#empList").datagrid('reload')
				}
			})
		}
		//删除
		function remove(url){
				if(getIds().length < 1){
					$.messager.alert("提示","请选择要删除的对象")
					}
					$.messager.confirm("警告","是否确认删除?",function(z){
						if(z){
							$.post(url,{"positionId":getIds().join(",")},function(result){
								if(result.success){
									$.messager.alert("提示",result.message)
									$("#empList").datagrid('reload')
									return;
									}
								$.messager.alert("提示",result.message)
							},"json")
						}
					})
				
			}
    	//修改
    		function edit(url){
				var id = getIds();
				if(id.length != 1){
					$.messager.alert("提示","请选择一个目标")
					return;
				}
        		
				parent.openTopWindow({
					width:520,
					height:400,
					title:"修改员工",
					url:url+"?id="+id,
					close:function(){
						$("#empList").datagrid('reload')
					}
				})
    		}
    		//查询
    		$("#searchBtn").on("click",function(){
				var val = $("input[type='hidden']").val();
				
								$("#empList").datagrid('load',{
									
										"name":val
									});
						
        		})
        		
        	//重置
        	$("#setBtn").on("click",function(){
        		$("#name").textbox("setValue","")
				$("#empList").datagrid('load',{
					"name":""
					})
            	})
    </script>
  </body>
</html>
