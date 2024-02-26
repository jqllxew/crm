<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
  	<form>
   	<table id="productList" class="easyui-datagrid"></table>
    <div id="toolbar"> 
      <!-- <select name="productid">
         <option value="任意字段">任意字段</option>
         <option value="产品名称">产品名称</option>
         <option value="研发时间">研发时间</option>
         <option value="详情连接">详情连接</option>
         <option value="开发团队">开发团队</option>
         <option value="成本价">成本价</option>
         <option value="建议零售价">建议零售价</option>
         <option value="创建人">创建人</option>
         <option value="创建时间">创建时间</option>
         <option value="修改时间">修改时间</option>  
      </select>
    -->
    
      产品名称:<input type="text" name="name" style="width:100px;height:35px;line-height:35px;" class="easyui-textbox theme-textbox-radius"/>
      产品零售价格:<input type="text" name="suggestedprice" style="width:100px;height:35px;line-height:35px;" class="easyui-textbox theme-textbox-radius"/>	
             <input type="reset"  id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload' ">  
    	
    	<a href="javascript:void(0);" onclick="return mysearch()" id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a> 
    	<a href="javascript:void(0);" onclick="return add('manage/product/ProductAction_edit.action')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加产品</a>
    	<a href="javascript:void(0);" onclick="return edit('manage/product/ProductAction_edit.action');" id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    	<a href="javascript:void(0);" onclick="return del('manage/product/ProductAction_remove.action')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    	<!-- <a href="javascript:void(0);" onclick="return assign('manage/user/UserAction_assign.action')" id="setBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true">产品工具</a> -->
    </div>
    </form>
    <script type="text/javascript">
    	//根据产品名称和产品价格相查询分业展示
    	function mysearch(){
    		$("#productList").datagrid('load',{
    			"name" : $("input[name='name']").val(),
    			"suggestedprice" : $("input[name='suggestedprice']").val()
    		})
    	}
    	function getIds(){
			var ids = new Array();
			$(":checked").each(function(){
				if($(this).prop("name")=="productid"){
					ids.push($(this).val())
				}
			})
			return ids;
		}
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
     				title:"编辑产品",
     				url:url+"?id="+ids,
     				close:function(){
     					$("#productList").datagrid('reload');
     					$("#productList").datagrid('unselectAll');
     				}
     			}		
     			);
    	}
    	function add(url){
    		parent.openTopWindow(
        			{
        				width:500,
        				height:550,
        				title:"新增产品",
        				url:url,
        				close:function(){
        					$("#productList").datagrid('reload');
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
            				$("#productList").datagrid('reload');
            				return;
            			}
            			$.messager.alert("提示",result.message)
            		},"json")
        		}
    		})
    	}
    	//加载用户数据
    	$(function(){
    		$("#productList").datagrid({
    			url : "manage/product/ProductAction_findByPage.action",
    			pagination : true,
    			toolbar : "#toolbar",
    			fitColumns : true,
    			idField : "productid",
    			rownumbers : true,
    			//singleSelect:true,
    			columns : [[
    				{field:"productid",title:"选择",checkbox:true}, 				
    				{field:"name",title:"产品名称",sortable:true,width:10},
    				{field:"link",title:"详情链接",width:10},
    				{field:"creatoruserid",title:"创建人",width:10},
    				{field:"createtime",title:"修改时间",width:10},
    				{field:"updatetime",title:"更新时间",width:10},
    				{field:"costprice",title:"成本价格",width:7},
    				{field:"suggestedprice",title:"建议价格",width:7},
    				//{field:"operation",title:"操作",formatter:function(value,rowData,index){
  			//return "<a href='javascript:void(0)' onclick='see("+rowData.id+")'>查看</a>"+
  			//" <a href='javascript:void(0)' onclick='editproduct("+rowData.id+")'>编辑</a>"
  			
  			
  		//}},	
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
