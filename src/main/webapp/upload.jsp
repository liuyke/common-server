<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="http://localhost:8080/common-server/test/importQualify" method="post" enctype="multipart/form-data">
    <input type="text" name="type"/><br/>
    <input type="text" name="idCard"/><br/>
    <input type="file" name="files"/><br/>
    <input type="submit"/>
</form>
</body>
</html>