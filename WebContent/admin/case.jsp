<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>case</title>
<link href="../css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="leftmenu">
  <jsp:include page="adminMenu.html"  />

</div>
<div id="rightcon" >
	<form action="upload" method="post" enctype="multipart/form-data">
	               案例名：<input type="text" name="casename"/>
		<br> prePicture1:<input type="file" name="pic" />
		<br> prePicture2:<input type="file" name="pic1"/>
		<br> aftPicture1:<input type="file" name="pic2" />
		<br> aftPicture2:<input type="file" name="pic3"/>
	    <br/>案例介绍：<textarea cols="50" rows="6" name="intro"></textarea>
		<br> <input	type="submit" value="提交"></input>
	</form>
	登陆状态：${login}</div>
</body>
</html>