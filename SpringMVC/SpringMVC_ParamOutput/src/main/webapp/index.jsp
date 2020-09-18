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
<a href="handle01">将数据传给页面(Map)</a>
<a href="handle02">将数据传给页面(Model)</a>
<a href="handle03">将数据传给页面(ModelMap)</a>	<br/>
<a href="handle04">将数据传给页面(返回ModelAndView对象)</a><br/>

<form action="updateCar" method="post">
	Brand:Ferrari<br/>
	Color:<input type="text" name="color"><br/>
	Price:<input type="text" name="price"><br/>
	<input type="submit" value="提交"><br/>
</form>

<form action="handle05" method="post">
	Integer:<input type="text" name="string"><br/>
	<input type="submit" value="提交"><br/>
</form>
</body>
</html>