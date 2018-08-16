<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add user information</title>
    <link href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
</head>
<body>

<c:if test="${loginUser.groupsByGroupId.id > 2}">
    <p>您没有权限添加用户组</p>
</c:if>
<c:if test="${loginUser.groupsByGroupId.id <=2 && loginUser.groupsByGroupId.id >=1}">
    <h2 style="text-align: center">添加组</h2>
    <form:form class="form-horizontal" role="form" action="/group/add" method="post">

        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">name</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" placeholder="name">
            </div>
        </div>

        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">description</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="description" placeholder="description">
            </div>
        </div>

        <div class="form-group">
            <label for="owner">所属用户</label>
            <select id="owner" class="form-control">
                <c:forEach items="${owner}" var="o">
                    <option>${o.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>

    </form:form>
</c:if>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</body>
</html>
