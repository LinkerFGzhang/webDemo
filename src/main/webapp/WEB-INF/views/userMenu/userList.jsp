<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>user list</title>
    <link href="/js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">

    <style type="text/css" scoped>
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

<c:if test="${empty requestScope.allUser}">
    <c:if test="${empty requestScope.allGroup}">
        <p>您没有权限查看</p>
    </c:if>
    <c:if test="${! empty requestScope.allGroup}">
        数据库中没有用户记录
    </c:if>
</c:if>

<c:if test="${!empty allUser}">
    <nav id="navbar-example2" class="navbar navbar-light bg-faded nav nav-tabs nav-justified">
        <h3 class="navbar-brand">Project Name</h3>
        <ul class="nav nav-pills">
            <c:forEach items="${allGroup}" var="g">
                <li class="nav-item"><a class="nav-link" href="#${g.description}">${g.description}</a></li>
            </c:forEach>
        </ul>
    </nav>
    <c:forEach items="${allGroup}" var="g">
        <div id="${g.description}" data-spy="scroll" data-target="#navbar-example2" data-offset="0"
             class="scrollspy-example tabs-content">
            <table class="table table-striped table-hover">
                <c:if test="${user.id == 1}">
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
                <c:if test="${user.id != 1}">
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
                <c:forEach items="${allUser}" var="u">
                    <c:if test="${g.description == u.description}"></c:if>
                    <th>${u.id}</th>
                    <th>${u.name}</th>
                    <th>${u.genericName}</th>
                    <th>null</th>
                    <th>${u.createTime}</th>
                    <th>${u.description}</th>
                    <th><<a href="/user/${u.id}">Edit</a></th>
                    <th><<a class="delete" href="/user/${u.id}">Delete</a></th>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
</c:if>

<!--jQuery 实现将 action 修改为 delete-->
<script type="text/javascript">
    $(".delete").click(function () {
        var href = $(this).attr("href");
        $("form").attr("action", href).sumbit();
        return false;
    })
</script>

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>
