<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="hello">hello</a><br/>
<a href="hello01?username=tom">原生传参数的方式</a><br/>
<a href="hello02?username=tom">@RequestParam传参数的方式</a><br/>
<a href="hello03?username=tom">获取请求头中的key</a><br/>
<a href="hello04?username=tom">获取Cookie</a><br/>
<form action="book" method="post">
	bookName:<input type="text" name="bname"><br/>
	bookID:<input type="text" name="bid"><br/>
	<!-- 为级联属性赋值 name="author.authorName" -->
	authorName:<input type="text" name="author.authorName"> <br/>
	stock:<input type="text" name="stock"> <br/>
	address:<input type="text" name="author.address"> <br/>
	age:<input type="text" name="author.age"> <br/>
	<input type="submit" value="提交"><br/>
</form>
<a href="hello05">传入原生API</a><br/>
</body>
</html>