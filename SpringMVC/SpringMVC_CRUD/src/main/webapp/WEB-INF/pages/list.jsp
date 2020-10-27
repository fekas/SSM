<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%pageContext.setAttribute("ctp", request.getContextPath());%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee List</title>
<script type="text/javascript" src="${ctp }/scripts/jquery-1.9.1.min.js"></script>
</head>
<body>
<h1>Employee List</h1>

<table border="1" cellpadding="5px" cellspacing="0">
	<tr>
		<th>ID</th>
		<th>lastName</th>
		<th>email</th>
		<th>gender</th>
		<th>departmentName</th>
		<th>EDIT</th>
		<th>DELETE</th>
	</tr>
	<c:forEach items="${emps }" var="emp">
		<tr>
			<td>${emp.id }</td>
			<td>${emp.lastName }</td>
			<td>${emp.email }</td>
			<td>${emp.gender==0?"female":"male" }</td>
			<td>${emp.department.departmentName }</td>
			<td>
				<a href="${ctp }/emp/${emp.id }">EDIT</a>
			</td>
			<td>
				<a href="${ctp }/emp/${emp.id }" class="delBtn">DELETE</a>
			</td>
		</tr>
	</c:forEach>
</table>
<a href="${ctp }/toaddpage">Add Employee</a>
<form id="deleteForm" action="${ctp }/emp/${emp.id }" method="post">
	<input type="hidden" name="_method" value="DELETE"/>
</form>
<script type="text/javascript">
	$(function(){
		$(".delBtn").click(function(){
			//确认删除
			
			//改变表单的action指向
			$("#deleteForm").attr("action",this.href)
			//提交表单
			$("#deleteForm").submit();
			return false;
		});
	});
</script>
</body>
</html>