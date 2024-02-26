<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="func"  uri="http://www.mytaglib.com/taglib/func"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>权限列表</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	
	<%@include file="../../script.html" %>

  </head>
  <style type="text/css">
  	#container{
  		width:100%;
  		text-align:center;
  	}
  	#left{
  		float:left;
  		width:30%;
  		border:1px solid blue;
  	}
  	#right{
  		float:left;
  		width:69%;
  		border:1px solid blue;
  	}
  	h3{
  		color:green;
  	}
  </style>
  <body>
    <!-- 表格中展示所有的权限 -->
    <div id="container">
    	<div id="left">
    		<h3>字典类型</h3>
	    	<table id="dtList"  style="height:600px"></table>
	    	<div id="toolbarDt">
	    		<func:func funcCode="dictadd">
		    	<a href="javascript:void(0);" onclick="return addDt('system/dict/DictTypeAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
	    		</func:func>
	    		<func:func funcCode="dictdel">
		    	<a href="javascript:void(0);" onclick="return delDt('system/dict/DictTypeAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	    		</func:func>
	    		<func:func funcCode="dictedit">
		    	<a href="javascript:void(0);" onclick="return editDt('system/dict/DictTypeAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
	   			</func:func>
	   		</div>
   		 </div>
    	<div id="right">
    		<h3 id="info">所有字典</h3>
    		<div id="toolbar" >
	    		<func:func funcCode="dictadd">
		    	<a href="javascript:void(0);" onclick="return add('system/dict/DictAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
	    		</func:func>
	    		<func:func funcCode="dictdel">
		    	<a href="javascript:void(0);" onclick="return del('system/dict/DictAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	    		</func:func>
	    		<func:func funcCode="dictedit">
		    	<a href="javascript:void(0);" onclick="return edit('system/dict/DictAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
		    	</func:func>
		    </div>
    		<table id="dictList" class="easyui-datagrid" style="height:500px"></table>
    	</div>
    </div>
    
    
  
    <script type="text/javascript">
    var currentDt={
       	id:"",
       	typename:""
    }
    
    function addDt(url){
    	parent.openTopWindow(
    			{
    				width:500,
    				height:550,
    				title:"新增字典类型",
    				url:url,
    				close:function(){
    					$("#dtList").treegrid('reload');
    					
    				}
    			}		
    			);
    }
    function delDt(url){
    	var dt = $("#dtList").treegrid("getSelected")
    	if(!dt){
    		$.messager.alert("提示","请选择一个目标")
    		return;
    	}
    	$.messager.confirm("警告","是否确认删除",function(b){
    		if(b){
    			$.post(url,{"id":dt.id},function(result){
    				if(result.ex){
    					$.messager.alert("警告",result.ex)
    					return;
    				}
    				if(result.success){
    					$.messager.alert("提示",result.message)
    					$("#dtList").treegrid('reload')
    					$("#dtList").treegrid('unselect',dt.id)
    					return;
    				}
    				$.messager.alert("提示",result.message)
    			},"json")
    		}
    	})
    }
    function editDt(url){
    	var dt = $("#dtList").treegrid("getSelected")
    	if(!dt){
    		$.messager.alert("提示","请选择一个目标")
    		return;
    	}
    	parent.openTopWindow(
   			{
   				width:500,
   				height:550,
   				title:"编辑字典类型",
   				url:url+"?id="+dt.id,
   				close:function(){
   					$("#dtList").treegrid('reload');
   					$("#dtList").treegrid('unselect',dt.id)
   				}
   			}		
   			);
    }
    function add(url){
    	parent.openTopWindow(
   			{
   				width:500,
   				height:550,
   				title:"新增字典",
   				url:url,
   				close:function(){
   					$("#dictList").datagrid('reload');
   				}
   			}		
   			);
    }
    function getIds(){
		var ids = new Array();
		$(":checked").each(function(){
			if($(this).prop("name")=="id"){
				ids.push($(this).val())
			}
		})
		return ids;
	}
    function del(url){
    	var ids = getIds();
    	if(ids.length < 1){
    		$.messager.alert("提示","至少选择一个目标")
    		return;
    	}
    	$.messager.confirm("警告","是否确认删除"+ids.length+"条记录",function(b){
    		if(b){
    			$.post(url,{"ids":ids.join(",")},function(result){
    				if(result.ex){
    					$.messager.alert("警告",result.ex)
    					return;
    				}
    				if(result.success){
    					$.messager.alert("提示",result.message)
    					$("#dictList").datagrid('reload')
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
    		$.messager.alert("提示","请只选择1条记录")
    		return;
    	}
    	parent.openTopWindow(
      			{
      				width:500,
      				height:550,
      				title:"编辑字典",
      				url:url+"?id="+ids,
      				close:function(){
      					$("#dictList").datagrid('reload');
      					$("#dictList").datagrid('unselectAll')
      				}
      			}		
      			);
    }
    
    		//加载字典数据
    function loadDictList(dictTypeId,typecode){
    		$("#dictList").datagrid({
    			url : "system/dict/DictAction_findByDictTypeId.action?id="+dictTypeId+"&typecode="+typecode,
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "id",
    			rownumbers : true,
    			//singleSelect:true,
    			columns : [[
    				{field:"id",title:"选择",checkbox:true},
    				{field:"dictname",title:"词条名",sortable:true,width:10},
    				{field:"dictcode",title:"编码",width:15},
    				{field:"dictvalue",title:"值",width:20},
    				{field:"dictnote",title:"备注",width:15},
    				{field:"typeid",title:"词条类型",width:15}
    			]],
    			loadFilter:function(data){
    				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
    				return {"total":data.data.totalRows,"rows":data.data.data};
    			}
    		
    		});
    	}
    
    	$("#dtList").treegrid({
    		url : "system/dict/DictTypeAction_findAll.action",
    		fitColumns : true,
    		idField : "id",
    		treeField: "typename",
    		rownumbers : false,
    		toolbar : "#toolbarDt",
    		//checkbox : true,
    		singleSelect : true,
    		cascadeCheck : false,
    		columns : [[
    			{field:"typename",title:"字典类型名称",sortable:true,width:10},
    			{field:"typecode",title:"编码",sortable:true,width:10,formatter:function(value,rowData,index){
    				if(value == "allType"){
    					return "";
    				}else{
    					return value;
    				}
    				}},
    			{field:"status",title:"状态",formatter:function(value,rowData,index){
    				if("allType"==rowData.typecode){
    					return "";
    				}else if(value == 1){
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
    		loadFilter:function(data){//"_parentId"
    			if(data && data.data){
    				$.each(data.data ,function(index,obj){
    					if(obj.id!="ACEAF0031E2C4F92B16EAB5F5D923DE7"){
    						data.data[index]._parentId ="ACEAF0031E2C4F92B16EAB5F5D923DE7";
    					}
    				});
    				return {"rows":data.data};
    			}
    			return null;
    		},
    		onClickRow:function(row){
    			loadDictList(row.id,row.typecode);
    			if("allType"==row.typecode){
    				currentDt.id="";
    				currentDt.typename="";
    				$("#info").text("所有字典");
    			}else{
    				currentDt.id=row.id;
    				currentDt.typename=row.typename;
    				$("#info").text("字典 类型为["+currentDt.typename+"]");
    			}
    		}
    	});
    	loadDictList("ACEAF0031E2C4F92B16EAB5F5D923DE7","allType");
    
    </script>
  </body>
</html>
