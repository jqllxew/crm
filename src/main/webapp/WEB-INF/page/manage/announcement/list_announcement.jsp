<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公告表</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<%@include file="../../script.html" %>

  </head>
  
  <body >
  	<form>
   	<table id="annouceList" class="easyui-datagrid"></table>
    <div id="toolbar"> 
     
      公告标题:<input type="text" name="title" style="width:100px;height:35px;line-height:35px;" class="easyui-textbox theme-textbox-radius"/>
      部门:<input type="text" name="departmentid" style="width:100px;height:35px;line-height:35px;" class="easyui-textbox theme-textbox-radius"/>

          <input type="reset"  id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload' ">  
    	
    	<a href="javascript:void(0);" onclick="return mysearch('manage/announcement/AnnouncementAction_mysearch.action')" id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a> 
    	<a href="javascript:void(0);" onclick="return add('manage/announcement/AnnouncementAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    	<a href="javascript:void(0);" onclick="return edit('manage/announcement/AnnouncementAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    	<a href="javascript:void(0);" onclick="return del('manage/announcement/AnnouncementAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    
    </div>
    </form>
    <script type="text/javascript">
    	//加载用户数据
    	$(function(){
    		$("#annouceList").datagrid({
    			url : "manage/announcement/AnnouncementAction_findByPage.action",
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "announcementid",
    			rownumbers : true,
    			//singleSelect:true,
    			columns : [[  
    				{field:"announcementid",title:"文章编号",checkbox:true}, 				
    				{field:"department",title:"通知部门",sortable:true,width:5},
    				{field:"user",title:"发表人",width:5},
    				{field:"title",title:"标题",width:10},
    				{field:"content",title:"内容",width:20},
    				{field:"updatetime",title:"更新时间",width:10},
    				{field:"status",title:"登录状态",width:3},
    	//			{field:"operation",title:"操作",formatter:function(value,rowData,index){
  		//	return "<a href='javascript:void(0)' onclick='myedit("+index+")'>修改</a>"+"&nbsp;&nbsp;"+
  		//	" <a href='javascript:void(0)' onclick='editproduct("+index+")'>删除</a>"
  			
  			
       //}},	
    			]],
    			loadFilter:function(data){
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.data.totalRows,"rows":data.data.result};
    			}
    		
    		});
    	});
    	
    		//function modify(index){
				//$("#annouceList").datagrid("selectRow",index)
				//var row=$("#annouceList").datagrid("getSelected")
				//var id=row.taskid;
			//	parent.openTopWindow(
				//{
				//	width:500,
				//	height:700,
				//	title:"修改",
				//	url:"manage/task/TaskAction_modify.action"+"?taskVo.taskid="+id,
				//	close:function(){
				//		$("#taskList").datagrid("reload")
				//	}
				//}
				//)
			//}
  
    </script>
  </body>
</html>
