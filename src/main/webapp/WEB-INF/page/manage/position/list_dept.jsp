<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="func" uri="http://www.mytaglib.com/taglib/func" %>
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
   	<table id="deptList" class="easyui-datagrid"></table>
    <div id="toolbar">
    	<span> 部门名称:</span>
		<input id="departmentName" style="line-height:26px;border:1px solid #ccc">
		<span> 上级部门:</span>
		<input id="departmentParentName" style="line-height:26px;border:1px solid #ccc">
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		<func:func funcCode="deptadd">
    	<a href="javascript:void(0);" onclick="return add('manage/position/DepartmentAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
		</func:func>
		<func:func funcCode="deptdel">
    	<a href="javascript:void(0);" onclick="return del('manage/position/DepartmentAction_delete.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</func:func>
		<func:func funcCode="deptedit">
    	<a href="javascript:void(0);" onclick="return edit('manage/position/DepartmentAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		</func:func>
	</div>
    </div>
    <script type="text/javascript">

    	//加载用户数据
    	$(function(){
    		$("#deptList").datagrid({
    			url : "manage/position/DepartmentAction_findByPage.action",
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,//设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
    			idField : "departmentid",
    			rownumbers : true,
    			singleSelect:true,
    			columns : [[
    				{field:"departmentid",title:"选择",checkbox:true,width:10},
    				{field:"dname",title:"部门名称",width:10},
    				{field:"parentname",title:"上级部门",width:10},
    				{field:"description",title:"部门描述",width:10},	
    			]],
    			loadFilter:function(data){
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.data.totalRows,"rows":data.data.data};
    			}
    		
    		});
    		
    	});
    	//条件查询
    	function doSearch(){
    		var val = $("#departmentName").val()
    	    var pval = $("#departmentParentName").val()
    		           
			$("#deptList").datagrid('load',{
				
				"name":val,
				"pname":pval
			})
        }
        //新增
        function add(url){
        	parent.openTopWindow({
    			width:1000,
    			height:400,
    			title:"添加部门",
    			url:url,
    			close:function(){
    				$("#deptList").datagrid('reload')
    			}
    		})
        }
      //获取id的方法
    	function getIds(){
    		var ids = new Array();
    		$(":checked").each(function(){
    			if($(this).prop("name")=="departmentid"){
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
				width:1000,
    			height:400,
    			title:"修改部门",
    			url:url+"?id="+id,
    			close:function(){
    				$("#deptList").datagrid('reload')
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
					$.get(url,{"deptId":getIds().join(",")},function(result){
						if(result.success){
							$.messager.alert("提示",result.message)
							$("#deptList").datagrid('reload')
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
