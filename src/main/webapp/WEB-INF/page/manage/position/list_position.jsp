<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="func" uri="http://www.mytaglib.com/taglib/func"%>
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
	<%@include file="../../script.html" %>

  </head>
  
  <body >
   	<table id="positionList" class="easyui-datagrid"></table>
    <div id="toolbar">
    	<span>岗位名称:</span>
		<input id="positionName" style="line-height:26px;border:1px solid #ccc">
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<func:func funcCode="posadd">
    	<a href="javascript:void(0);" onclick="return add('manage/position/PositionAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
    	</func:func>
    	<func:func funcCode="posdel">
    	<a href="javascript:void(0);" onclick="return del('manage/position/PositionAction_delete.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    	</func:func>
    	<func:func funcCode="posedit">
    	<a href="javascript:void(0);" onclick="return edit('manage/position/PositionAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>   	
		</func:func>
	</div>
    
    <script type="text/javascript">

	
 	
    	//加载用户数据
    	$(function(){
    		$("#positionList").datagrid({
    			url : "manage/position/PositionAction_findByCondition.action",
    			//queryParams:{"positionName":$("#positionName").val()},
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "positionid",
    			rownumbers : true,
    			singleSelect:true,
    			columns : [[
    				{field:"positionid",title:"选择岗位",checkbox:true},
    				{field:"pname",title:"岗位名称",width:10},
    				{field:"dname",title:"所属部门",width:10},
    				{field:"tpname",title:"岗位管理上级",width:10},
    				{field:"description",title:"岗位描述",sortable:true,width:10},
    				
    				
    			]],
    			loadFilter:function(data){
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.data.totalRows,"rows":data.data.data}
    			}
    			
        			
    		});
    		
    	})
    	
    	//条件查询
		function doSearch(){
			var val = $("#positionName").val();
			
			$("#positionList").datagrid('load',{
				
					"name":val
			})
		}
    	  //新建
    	function add(url){
    		parent.openTopWindow({
    			width:500,
    			height:350,
    			title:"添加岗位",
    			url:url,
    			close:function(){
    				$("#positionList").datagrid('reload')
    			}
    		})
    		}
    	//获取id的方法
    	function getIds(){
    		var ids = new Array();
    		$(":checked").each(function(){
    			if($(this).prop("name")=="positionid"){
    			ids.push($(this).val())
    			}
    		})
    		return ids;
    	}
		//修改
		function edit(url){
			var id = getIds();
			if(id.length != 1){
				$.messager.alert("提示","请选择一个目标")
				return;
			}
			parent.openTopWindow({
				width:500,
    			height:350,
    			title:"变更岗位",
    			url:url+"?id="+id,
    			close:function(){
    				$("#positionList").datagrid('reload')
    			}
			})
		}
		//删除
		function del(url){
			var id = getIds();
			if(id.length != 1){
				$.messager.alert("提示","请选择一个目标")
				return;
			}
			$.messager.confirm("警告","是否确认删除?",function(z){
				if(z){
					$.post(url,{"positionId":getIds().join(",")},function(result){
						if(result.success){
							$.messager.alert("提示",result.message)
							$("#positionList").datagrid('reload')
							return;
							}
						$messager.alert("提示",result.message)
					},"json")
				}
			})
		}
    </script>
  </body>
</html>
