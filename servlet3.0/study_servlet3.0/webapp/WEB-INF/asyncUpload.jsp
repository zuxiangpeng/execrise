<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'upload.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="asyncUpload" method="POST"
		enctype="multipart/form-data">
		<table align="center" border="1" width="50%">
			<tr>
				<td>Select A Image to Upload:</td>
				<td><input type="file" name="image"  multiple="multiple" ></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="上传"></td>
			</tr>
		</table>
	</form>
</body>
</html>
