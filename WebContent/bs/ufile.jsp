<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件上传</title>
</head>
<body>
	
	<!-- 以二进制文件的方式进行上传 -->
	<form action="${pageContext.request.contextPath}/bsuploadfile" method="post" enctype="multipart/form-data">
	
			<!-- 非兼容性的文件夹上传功能！ -->
			选择上传的文件:<input type="file" name="upfile" >
			<input type="submit" value="上传">
	</form>
</body>
</html>