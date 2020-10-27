<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EDIT Employee</title>
</head>
<body>

<%pageContext.setAttribute("ctp", request.getContextPath()); %>

<h1>EDIT Employee</h1>
<form:form action="${ctp }/emp/${employee.id }" method="POST" modelAttribute="employee">
	<input type="hidden" name="_method" value="put"/>
	<input type="hidden" name="id" value="${employee.id }"/>
	email:<form:input path="email"/><br/>
	gender:&nbsp;&nbsp;&nbsp;
		male:<form:radiobutton path="gender" value="1"/>&nbsp;&nbsp;&nbsp;
		female:<form:radiobutton path="gender" value="2"/><br/>
	dept:
		<form:select path="department.id" items="${depts }"
		itemLabel="departmentName" itemValue="id"></form:select><br/>
	<input type="submit" value="Update"/>
</form:form>
</body>
</html>