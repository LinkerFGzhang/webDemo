<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.css">
</head>
<body>
<h2 style="text-align: center">添加摄像头</h2>

<form action="/file/upload" method="post">
    <input type="hidden" name="id">
    name:<input type="text" name="name"><br>
    description:<input type="text" name="description"><br>
    url:<input type="url" name="url"><br>
    address:<input type="text" name="address"><br>
    height:<input type="number" name="height"><br>
    width:<input type="number" name="width"><br>
    longitude:<input type="number" name="longitude"><br>
    latitude:<input type="number" name="latitude"><br>
    该摄像头管理用户:
    <select name="usersByUserId">
        <c:forEach items="${requestScope.managerUser}" var="u">
            <option name="${u.genericName}" value="${u.id}"></option>
        </c:forEach>
    </select>

    <<input type="submit" value="submit">

</form>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>
