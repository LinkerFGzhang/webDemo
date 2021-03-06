<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>user list</title>
    <link href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">

    <style type="text/css">
        .scrollspy-example {
            position: relative;
            height: 200px;
            margin-top: .5rem;
            overflow: auto;
        }
    </style>
</head>
<body>
<!--设置隐藏域属性 name为 _method 值为 DELETE-->
<form action="" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
</form>

<!-- test -->
<%-------${requestScope.allUser}<br/>--%>
<%------>>>>>>${requestScope.allGroup}<br/>--%>
<%--<br/>--%>

<%--<c:forEach items="${requestScope.allGroup}" var="g">--%>
<%--${g[0].id}-----${g[0].description}-----${g[1].name}<br>--%>
<%--</c:forEach>--%>
<%--<br/><br/>--%>

<%--<c:forEach items="${requestScope.allUser}" var="u">--%>
<%--${u[0].id}---${u[0].name}---${u[1].name}--%>
<%--<br>--%>
<%--</c:forEach>--%>

<!-- 正文 -->
<c:if test="${empty requestScope.allUser}">
    <c:if test="${empty requestScope.allGroup}">
        <p>您没有权限查看</p>
    </c:if>
    <c:if test="${! empty requestScope.allGroup}">
        <p>数据库中没有用户记录</p>
    </c:if>
</c:if>

<!--
    g[0]为 Groups列表，g[1]为与其相关联的 Users表
    u[0]为 Users列表，u[1]为与其相关联的 Groups表
-->
<c:if test="${!empty requestScope.allUser}">
    <nav id="navbar-example2" class="navbar navbar-light bg-faded nav nav-tabs nav-justified">
        <h3 class="navbar-brand">用户列表</h3><br/>
        <ul class="nav nav-pills nav-tabs" role="tablist">
            <c:forEach items="${requestScope.allGroup}" var="g">
                <li><a href="#${g[0].name}" role="tab" data-toggle="pill">${g[0].description}</a></li>
            </c:forEach>
        </ul>
    </nav>
    <div class="tab-content">
        <c:forEach items="${requestScope.allGroup}" var="g">
            <div id="${g[0].name}" data-spy="scroll" data-target="#navbar-example2" data-offset="0"
                 class="scrollspy-example tabs-content">
                <table class="table table-striped table-hover" style="border: 1px">
                    <c:if test="${loginUser.id == 1}">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>username</th>
                            <th>password</th>
                            <th>姓名</th>
                            <th>头像/head</th>
                            <th>创建时间</th>
                            <th>所属组/group</th>
                            <th>状态/token</th>
                            <th>编辑</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                    </c:if>
                    <c:if test="${loginUser.id != 1}">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>username</th>
                            <th>姓名</th>
                            <th>头像/head</th>
                            <th>创建时间</th>
                            <th>所属组/group</th>
                            <th>编辑</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                    </c:if>
                    <tbody>
                    <!-- 各分组下的用户 -->
                    <c:forEach items="${requestScope.allUser}" var="u">
                        <c:if test="${g[0].description == u[1].description}">
                            <tr>
                                <th>${u[0].id}</th>
                                <th>${u[0].name}</th>
                                <th>${u[0].genericName}</th>
                                <th>null</th>
                                <th>${u[0].createTime}</th>
                                <th>${u[1].description}</th>
                                <th><<a href="/user/${u[0].id}">Edit</a></th>
                                <th><<a class="delete" href="/user/${u[0].id}">Delete</a></th>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</c:if>

<!--jQuery 实现将 action 修改为 delete-->
<script type="text/javascript">
    $(function () {
        $(".delete").click(function () {
            var href = $(this).attr("href");
            $("form").attr("action", href).sumbit();
            return false;
        })
    })
</script>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>
