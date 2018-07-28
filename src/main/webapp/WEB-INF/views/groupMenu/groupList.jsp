<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户列表</title>
</head>
<body>

<h1>用户组列表</h1>

<!--将 post 请求转化为 delete-->
<form action="" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
</form>

<c:if test="${ empty userList}">
    数据库中没有用户信息...
</c:if>
<c:if test="${ !empty userList}">
    <table>
        <tr>
            <th>username</th>
            <th>password</th>
            <th>姓名</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${requestScope.userList}" var="temp">
            <tr>
                <td>${temp.name}</td>
                <td>${temp.password}</td>
                <td>${temp.generic_name}</td>
                <td><a href="/user/${temp.id}">Edit</a></td>
                <td><a class="delete" href="/user/${temp.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<footer class="blog-footer">
    <p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a
            href="https://twitter.com/mdo">@mdo</a>
        <a href="#">Back to top</a>
    </p>
</footer>

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
</body>
</html>
