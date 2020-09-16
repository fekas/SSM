<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
 * REST风格：Representational State Transfer	资源表现层状态转化
 * 系统希望以非常简洁的URL来发送请求
 * 用GET，POST，DELETE，PUT等提交请求的方式来表示对资源的增删改查
 * 问题：
 * 	从页面只能发GET和POST请求，没有其他方式的请求
 -->

<a href="book/1">查询图书</a><br>
<form action="book" method="post">
	<input type="submit" value="添加图书">
</form>
<!-- Spring提供对Rest风格的支持 -->
<!-- 利用Spring中的filter把普通请求转化为规定形式的请求 -->
<!-- 利用带_method的参数，该参数的值就是要转换请求名 的post类型的表单-->
<form action="book/1" method="post">
	<input type="hidden" name="_method" value="delete">
	<input type="submit" value="删除1号图书">
</form><br>
<form action="book/1" method="post">
	<input type="hidden" name="_method" value="put">
	<input type="submit" value="更新1号图书">
</form><br>
<!-- 高版本出现405错误，在顶部添加isErrorPage=true -->
</body>
</html>