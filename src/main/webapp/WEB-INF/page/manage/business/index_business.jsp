<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'customer.jsp' starting page</title>
     
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  <%@include file="../../script.html" %>
  <body>
     <div id="tt" class="easyui-tabs" style="width:1500px">
	    <div title="商机" style="padding:20px;display:none;" data-options="iconCls:'icon-man',plain:true">
			<iframe src="manage/business/BusinessAction_list.action" width="100%" height="100%" frameBorder="0"></iframe>
	    </div>
	    <div title="统计" data-options="iconCls:'icon-man',plain:true" style="overflow:auto;padding:20px;display:none;">
			
	    </div>
	    <div title="帮助" data-options="iconCls:'icon-help',plain:true" style="padding:20px;display:none;">
			
	    </div>
	</div>
  </body>
</html>
