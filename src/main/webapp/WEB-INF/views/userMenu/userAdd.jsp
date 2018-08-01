<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user information</title>
</head>
<body>
<h2>添加用户</h2>
<form:form action="user/add" method="POST" modelAttribute="user">
    <input type="hidden" name="id">
    账  号: <input type="text" name="name"/>
    <br/>
    密  码: <input type="password" name="password"/>
    <br/>
    姓  名: <input type="text" name="genericName"/>
    <br/>
    用户所属组: <form:select path="groupsByGroupId.id">
                    <form:options items="${requestScope.group}" itemValue="id" itemLabel="name"/>
                </form:select>
    <br/>
    <input type="submit" value="提交" />
</form:form>
</body>
</html>
