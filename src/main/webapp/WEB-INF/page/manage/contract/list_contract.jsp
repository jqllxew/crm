<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合同列表</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	<%@include file="../../script.html" %>

  </head>
  
  <body >
  	<div>
  		<select class="easyui-combobox theme-textbox-radius" style="width:120px" id="contractOne">
  			<option value="2">客户</option>
  			<option value="3">联系人</option>
  			<option value="4">负责人</option>
  		</select>
  		<select class="easyui-combobox theme-textbox-radius" style="width:100px" id="contractTwo">
  			<option value="1">包含字段</option>
  			<option value="2">精确查询</option>
  		</select>
  		<span>查询:</span>
		<input id="contract" style="line-height:26px;border:1px solid #ccc">
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
  	</div>
   	<table id="contractList" class="easyui-datagrid"></table>
    <div id="toolbar">
    	<a href="javascript:void(0);" onclick="return add('manage/position/ContractAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
    	<a href="javascript:void(0);" onclick="return del('manage/position/ContractAction_delete.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    	<a href="javascript:void(0);" onclick="return edit('manage/position/ContractAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>   	
	</div>
    </div>
    <script type="text/javascript">
    	//条件查询
 		function getIds(){
			var ids = new Array();
			$(":checked").each(function(){
				if($(this).prop("name")=="contractid"){
					ids.push($(this).val())
				}
			})
			return ids;
		}
    	//添加合同
    	function add(url){  
        	parent.openTopWindow({        		
        		width : 800,
        		height : 600,
        		title : "新建合同",
        		url : url,
        		close:function(){
        			$("#contractList").datagrid('reload')
        		}
        	})
        }
        //修改部门
    	function edit(url){
    		var id = getIds();
    		if(id.length != 1){
    			$.messager.alert("提示","请选择一个目标修改")
    			return;
    		}
    		parent.openTopWindow({
    				width:800,
    				height:600,
    				title:"修改用户",
    				url:url+"?id="+id,
    				close:function(){
    					$("#contractList").datagrid('reload');
    				}		
    			});
        }
        //查询
        
        
     	//删除部门
     	function del(url){
     	    //获取到选中的一行数据
     	    if(getIds().length < 1){
     	    	$.messager.alert("提示","至少选择一个目标")
     	    	return;
     	    }
     	    $.messager.confirm("警告","是否确认删除?",function(b){
     	    	if(b){
     	    		$.post(url,{"contractid":getIds().join(",")},function(result){
     	    			if(result.ex){
     	    				$.messager.alert("警告",result.ex)
     	    				return;
     	    			}
     	    			if(result.success){
     	    				$.messager.alert("提示",result.message)
		    				$("#contractid").datagrid('reload')
		    				return;
     	    			}
     	    			$.messager.alert("警告",result.message)
     	    		},"join")    	    		
     	    	}	    	
     	    })
        }
    
    	//加载用户数据
    	$(function(){
    		$("#contractList").datagrid({
    			url : "manage/position/ContractAction_findByPage.action",
    			//queryParams:{"positionName":$("#positionName").val()},
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "contractid",
    			rownumbers : true,
    			//singleSelect:true,
    			showHeader:true,//定义是否显示头部
    			columns : [[
    				{field:"contractid",title:"选择合同",checkbox:true},
    				{field:"contractnumber",title:"合同编号"},
    				{field:"businesscustomername",title:"客户"},
    				{field:"ownerUsername",title:"负责人"},//ownerUserName
    				{field:"businessconnectionname",title:"联系人"},
    				{field:"duetime",title:"签约日期"},
    				{field:"price",title:"合同金额"},
    				{field:"status",title:"状态"},
    				{field:"endTime",title:"距离到期时间"},
    				{field:"operation",title:"操作",formatter:function(value,row,index){ 
		  			return "<a href='manage/position/ContractAction_edit.action?ids='"+row.index+">查看</a>"+
		  			"&nbsp;&nbsp<a href='javascript:void(0)' onclick='return modify("+index+")'>编辑</a>";
  				}}
    				
    				
    			]],
    			loadFilter:function(data){
    				console.log(data.data.data)
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.data.totalRows,"rows":data.data.data};
    			}
    		
    		});
    	});
    </script>
  </body>
</html>
