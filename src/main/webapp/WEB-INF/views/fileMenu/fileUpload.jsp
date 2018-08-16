<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.css">
</head>
<body>
<h2 style="text-align: center">文件上传</h2>

${requestScope.loginUser.id}-----${loginUser.name}<br>

<form action="${pageContext.request.contextPath}/file/upload" enctype="multipart/form-data" method="post">
    <input type="hidden" name="user" value="${requestScope.loginUser}">
    <table>
        <tr>
            <td>请选择所要上传的文件:</td>
            <td><input type="file" name="file"></td>
        </tr>
        <%--<tr>--%>
            <%--<td>文件所属用户:</td>--%>
            <%--<td>--%>
                <%--<select name="user">--%>
                    <%--<c:forEach items="${requestScope.user}" var="u">--%>
                        <%--<option name="" value=""></option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td><input type="submit" value="上传"></td>
        </tr>
    </table>
</form>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>
