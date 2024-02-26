<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="func" uri="http://www.mytaglib.com/taglib/func" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list_task.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../../script.html" %>
  </head>
  	
  <body>
  	<div>
  		<select  name="task" class="easyui-combobox theme-textbox-radius" style="width:120px">
  			<option value="1">任意字段</option>
  			<option value="subject" >主题</option>
  			<option value="creatoruserid">创建者</option>
  			<option value="owneruserid">负责人</option>
  			<option value="status">任务状态</option>
  			<option value="priority">优先级</option>
  			<option value="duedate">截止日期</option>
  		</select>
  		<select name="creatorUsername" class="easyui-combobox theme-textbox-radius" style="width:100px" >
  		</select>
  		<input name="subject" type="text" value="${taskVo.subject}" class="easyui-textbox theme-textbox-radius">
  		<a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
  	</div>
 
    <table id="taskList"class="easyui-datagrid">
    	
    </table>
  	  <div id="toolbar">
		<func:func funcCode="taskadd">
    	<a href="javascript:void(0);" onclick="return add('manage/task/TaskAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
		</func:func>
		<func:func funcCode="taskdel">
    	<a href="javascript:void(0);" onclick="return del('manage/task/TaskAction_del.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</func:func>
		<func:func funcCode="taskedit">
    	<a href="javascript:void(0);" onclick="return edit('manage/task/TaskAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    	</func:func>
    </div>
    
  </body>
  <script type="text/javascript">
  function search(col,val){
		if(col == "subject")
			$("#taskList").datagrid('load',{"subject":val})
		if(col == "creatoruserid")
			$("#taskList").datagrid('load',{"creatoruserid":val})
		if(col == "owneruserid")
			$("#taskList").datagrid('load',{"owneruserid":val})
		if(col == "status")
			$("#taskList").datagrid('load',{"status":val})
		if(col == "priority")
			$("#taskList").datagrid('load',{"priority":val})
		if(col == "duedate")
			$("#taskList").datagrid('load',{"duedate":val})
	}
  		var col = "";
	  	$("select[name='task']").combobox({
			onSelect:function(param){
				$.get("manage/task/TaskAction_getColumn.action",{"column":param.value},
				function(result){
					col = param.value
					var sel = $("select[comboname='creatorUsername']")
					if(result.success){
						sel.combobox("loadData", []);
						sel.combobox('clear')
						var data= sel.combobox('getData');
						if(col=="status"){
							data[0]={value:1,text:"可用"}
							data[1]={value:0,text:"禁用"}
						}else{
							$.each(result.data,function(index,obj){
								data[index]={value:obj,text:obj}
							})
						}
						sel.combobox('loadData',data)
					}
				},"json")
			} 
		})
		$("#searchBtn").on("click",function(){
			search(col,$("input[name='subject']").val())
		})
  		$("select[name='creatorUsername']").combobox({
			onSelect: function(param){
				search(col,param.value)
			}
		})
		function getIds(){
			var ids = new Array();
			$(":checked").each(function(){
				if($(this).prop("name")=="taskid"){
					ids.push($(this).val())
				}
			})
			return ids;
		}
  		function add(url){
  			parent.openTopWindow(
       			{
       				width:550,
       				height:630,
       				title:"新增任务",
       				url:url,
       				close:function(){
       					$("#taskList").datagrid('reload');
       				}
       			}
       			);
  		}
  		function del(url){
  			var ids = getIds();		
  			if(ids.length < 1){
  				$.messager.alert("提示","请至少选择一个目标")
  				return;
  			}
  			$.messager.confirm("警告","是否确认删除"+ids.length+"条记录?",function(b){
  				if(b){
	  				$.post(url,{"ids":ids.join(",")},function(result){
	  					if(result.ex){
	  						$.messager.alert("警告",result.ex)
	  						return;
	  					}
	  					if(result.success){
	  						$.messager.alert("提示",result.message)
	  						$("#taskList").datagrid('reload')
	  						$("#taskList").datagrid('unselectAll')
	  						return;
	  					}
	  					$.messager.alert("提示",result.message)
	  				},"json")
  				}
  			})
  		}
  		function edit(url){
  			var ids = getIds();		
  			if(ids.length != 1){
  				$.messager.alert("提示","请只选择1个目标修改")
  				return;
  			}
  			parent.openTopWindow(
       			{
       				width:550,
       				height:600,
       				title:"修改任务",
       				url:url+"?taskId="+ids,
       				close:function(){
       					$("#taskList").datagrid('reload');
       				}
       			}
       			);
  		}
		$(function(){
			$("#taskList").datagrid({
				url:"manage/task/TaskAction_findByPage.action",
				pagination:true,
				toolbar:"#toolbar",
				fitColumns:true,
				idField:"taskid",
				rownumbers:true,
				columns :[[
					{field:"taskid",title:"选择",checkbox:true},
					{field:"subject" ,title:"主题",width:10},
					{field:"aboutUser",title:"相关信息",width:10},
					{field:"owneruserid",title:"负责人",width:15},
					{field:"status",title:"状态",width:10,formatter:function(value,rowData,index){
						if(value == 1){
    						return "可用";
    					}else if(value == 0){
    						return "禁用";
    					}else if(value == 2){
    						return "已删除";
    					}else{
    						return "";
    					}
					}},
					{field:"priority",title:"优先级",width:15},
					{field:"creatoruserid",title:"创建者",width:15},
					{field:"duedate" ,title:"截止日期",width:15},
					{field:"operation",title:"操作",formatter:function(value,row,index){
						
					return "<a href='javascript:void(0);' onclick=\"return see(\'manage/task/TaskAction_see.do\',\'"+row.taskid+"\')\">查看</a>"+
		  			" <a href='javascript:void(0);' onclick=\"return modify(\'manage/task/TaskAction_edit.action\',\'"+row.taskid+"\')\">编辑</a>"		  
					}},
				]],
				loadFilter:function(data){
					return {"total":data.data.totalRows,"rows":data.data.data}
				}
				
			})
			
		}) 
			
			function see(url,id){
				parent.openTopWindow(
		       			{
		       				width:550,
		       				height:600,
		       				title:"查看任务",
		       				url:url+"?taskId="+id,
		       				close:function(){
		       					$("#taskList").datagrid('unselectAll');
		       				}
		       			}
		       			);
					
				}
				function modify(url,id){
					parent.openTopWindow(
		       			{
		       				width:550,
		       				height:600,
		       				title:"修改任务",
		       				url:url+"?taskId="+id,
		       				close:function(){
		       					$("#taskList").datagrid('reload');
		       					$("#taskList").datagrid('unselectAll');
		       				}
		       			}
		       			);
				}
			
			
			
  
  </script>
</html>
