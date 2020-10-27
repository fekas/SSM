<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!-- 导入SpringMVC的表单标签库 -->
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Employee</title>
</head>
<body>
<h1>Add Employee</h1>

<h2>原生表单标签</h2>
<form action="">
	lastname:<input type="text" name="lastname"/><br/>
	email:<input type="text" name="email"/><br/>
	gender:<br/>
		male:<input type="radio" name="gender" value="1"/><br/>
		female:<input type="radio" name="gender" value="0"/><br/>
	Department:
		<select name = "department.id">
			<c:forEach items="${depts }" var="dept">
				<option value="${dept.id }">${dept.departmentName }</option>
			</c:forEach>	
		</select>
	<input type="submit" value="submit"/>
</form><br/>

<!-- 使用SpringMVC的表单标签代替原生的form表单 
	通过SpringMVC的表单标签可以实现将模型中的属性和HTML表单元素绑定，以实现表单数据更便捷的编辑和表单值的回显
	
	SpringMVC认为：表单数据中的每一项最终都是要回显的：
		path指定一个属性，这个属性是从隐含模型(请求域中取出的某个对象的属性)
		path指定的每一个属性，请求域中必须有一个对象拥有这个属性
			这个属对象名字为command
		modelAttribute
		以前表单标签从请求域中获取一个command对象，然后把这个对象回显，可以用modelAttribute指定一个新的名代替command
-->

<h2>SpringMVC提供的表单标签</h2>
<%
	pageContext.setAttribute("ctp",request.getContextPath());
%>
<form:form action="${ctp }/emp" modelAttribute="employee" method="POST">
	<!-- path就是原来html-input的name项 
		作用：作为原生的那么项、自动回显隐含模型中对应的属性的值-->
	lastName:<form:input path="lastName"/>
	email:<form:input path="email"/>
	gender：<br/>
		male:<form:radiobutton path="gender" value="1"/><br/>
		female:<form:radiobutton path="gender" value="0"/><br/>
		
		<!-- items指定要遍历的集合 
			itemLabel指定遍历的出这个对象哪个属性作为option标签体的值
			itemValue指定遍历出的对象的哪个属性是提交的值
			-->
	dept:<form:select path="department.id" 
		items="${depts }" itemLabel="departmentName" itemValue="id"></form:select>
		<input type="submit" value="submit"/>
</form:form>
</body>
</html>