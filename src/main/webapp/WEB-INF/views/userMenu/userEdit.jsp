<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User information</title>
</head>
<body>

<form:form action="" method="post" modelAttribute="user">
    <c:if test="${ !empty user.id}">
        <input type="hidden" name="_method" value="PUT">
    </c:if>
    username: <input type="text" name="name">
    password: <input type="text" name="password">
    姓名：<input type="text" name="generic_name">
    所属权限: <form:select path="">
            <form:options items="" itemValue=""></form:options>
</form:select>

    <input type="submit" value="sumbit">

</form:form>

<script src="js/jquery.min.js"/>
</body>
</html>