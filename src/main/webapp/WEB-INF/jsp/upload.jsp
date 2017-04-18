<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>上传图片</title>
</head>
<body>

<form method="post" action="importQualify" enctype="multipart/form-data">
	<input type="text" name="type"/><br/>
	<input type="file" name="file1"/>
	<input type="submit"/>
</form>

</body>
</html>