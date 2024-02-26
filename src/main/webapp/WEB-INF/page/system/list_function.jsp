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
    
    <title>权限列表</title>
    
	<meta name="pragma" content="no-cache">
	<meta name="cache-control" content="no-cache">
	<meta name="expires" content="0">    
	
	<%@include file="../script.html" %>

  </head>
  
  <body>
    <!-- 表格中展示所有的权限 -->
    <table id="funcList" style="height:70%"></table>
    <div id="toolbar">
    	<func:func funcCode="funcadd">
    	<a href="javascript:void(0);" onclick="return add('system/function/FunctionAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新建</a>
    	</func:func>
    	<func:func funcCode="funcdel">
    	<a href="javascript:void(0);" onclick="return del('system/function/FunctionAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    	</func:func>
    	<func:func funcCode="funcedit">
    	<a href="javascript:void(0);" onclick="return edit('system/function/FunctionAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    	</func:func>
    </div>
  
    <script type="text/javascript">
    	function add(url){
   		parent.openTopWindow(
       			{
       				width:500,
       				height:550,
       				title:"新建权限",
       				url:url,
       				close:function(){
       					$("#funcList").treegrid('reload');
       				}
       			}
       			);
    	}
    	
    	function edit(url){
    		
    	var rows = $("#funcList").treegrid("getCheckedNodes");
    	if(!rows || rows.length != 1){
    		$.messager.alert("提示","请只选择1个目标")
    		return;
    	}
   		parent.openTopWindow(
       			{
       				width:500,
       				height:550,
       				title:"编辑权限",
       				url:url+"?id="+rows[0].id,
       				close:function(){
       					$("#funcList").treegrid('reload');
       					$("#funcList").treegrid('uncheckNode',rows[0].id)
       				}
       			}
       			);
    	}
    //删除按钮事件处理函数
        function del(url){
        	//获取到选中的一行数据   
        	var rows = $("#funcList").treegrid("getCheckedNodes");
        	if(rows.length < 1){
        		$.messager.alert("提示","至少选择一个目标")
        		return;
        	}
        	$.messager.confirm("警告","是否确认删除"+rows.length+"条记录?",function(b){
        		if(b){
        			var funcids = new Array()//用来存储逻辑删除的id
                	var dfuncIds = new Array()//用来存储物理删除的id
                	$.each(rows,function(index,func){
                		if(func.status == 2){
                			dfuncIds.push(func.id)
                		}else{
                			funcids.push(func.id)
                		}
                	})
                	$.post(url,{
                		"funcids":funcids.join(","),
                		"dfuncIds":dfuncIds.join(",")
                	},function(result){
                		if(result.success){
                			$.messager.alert("提示",result.message)
                			$("#funcList").treegrid('reload')
                			$.each(funcids,function(index,obj){
                				$("#funcList").treegrid('uncheckNode',obj)
                			})
                			$.each(dfuncIds,function(index,obj){
                				$("#funcList").treegrid('uncheckNode',obj)
                			})
                			return;
                		}
                		
                	},"json")
        		}
        	})
        }
        
        
    	$(function(){
    		//加载所有权限
    		$("#funcList").treegrid({
    			url : "system/function/FunctionAction_findAll2.action",
    			fitColumns : true,
    			idField : "id",
    			treeField: "funcname",
    			rownumbers : true,
    			pagination : false,
    			toolbar : "#toolbar",
    			checkbox : true,
    			singleSelect : false,
    			cascadeCheck : false,
    			pageSize:20, 
    			columns : [[
    				{field:"funcname",title:"权限名称",sortable:true,width:10},
    				{field:"functype",title:"权限类型",width:5,formatter:function(value,rowData,index){
    					if(value == 1){
    						return "菜单";
    					}else if(value == 0){
    						return "按钮";
    					}else{
    						return "其他";
    					}
    				}},
    				{field:"funcurl",title:"权限URL",width:10},
    				{field:"funcnote",title:"权限描述",width:10},
    				{field:"createby",title:"创建者",width:5},
    				{field:"createtime",title:"创建时间",width:10},
    				{field:"updateby",title:"修改者",width:5},
    				{field:"updatetime",title:"修改时间",width:10},
    				{field:"status",title:"权限状态",formatter:function(value,rowData,index){
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
    			loadFilter:function(data){//"_parentId"
    				if(data && data.data){
    					$.each(data.data ,function(index,obj){
    						if(obj.parentid){
    							data.data[index]._parentId = obj.parentid; 
    						}
    					});
    					return {"rows":data.data};
    				}
    				return null;
    			},
    			
    		});
				/* var p = $('#funcList').datagrid('getPager');
				$(p).pagination({
		            pageSize: 40,//每页显示的记录条数，默认为20 
		            pageList: [20, 25, 30 ,35 ,40],//可以设置每页记录条数的列表
		            beforePageText: '第',//页数文本框前显示的汉字
		            afterPageText: '页    共 {pages} 页',
		            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	        	}); */
    	});
    </script>
  </body>
</html>
