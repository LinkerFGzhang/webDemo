<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user information</title>
</head>
<body>

<h1>添加组</h1>
<form:form action="/userAdd" method="POST" modelAttribute="group">
    username: <input type="text" name="name"/>
    password: <input type="password" name="password"/>
    姓名: <input type="text" name="generic_name"/>
    用户所属权限: <form:select path="group_id">
                    <form:options items="${requestScope.group}" itemValue="id" itemLabel="name" />
                </form:select>

    <button type="submit" name="submit"/>
</form:form>
</body>
</html>
