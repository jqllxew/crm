<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>message.html</title>
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./static/css/common.css">
	<script type="text/javascript" src="./static/jquery/jquery.min.js"></script>
</head>
<body>
  <div class="demo">
    <ul id="list">  
   <li><label><input type="checkbox" value="${Message}">全部选择</label></li>
   <li><label><input type="checkbox" value="${Message.userByTousername}">adminCEO</label></li>
	</ul>
	<input type="checkbox" id="all">  
	</div>
	<label>内容：</label>
	<input type="text"name="MessageVo.content" style="width:300px;height:300px"></br>
	<input type="submit" name="submit" value="发送" style="width:50px;height:50px;color:red">
	<input type="button" value="取消" style="width:50px; height:50px;color: blue ">
  </body>
  <script type="text/javascript">
  	$(function(){
		$("#all").click(function(){
			if(this.checked){
				$("#list input[type=checkbox]").prop("checked",true)
			}else{
				$("#list input[type=checkbox]").prop("checked",false)
			}
		})
  	})
  </script>
</html>