<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    <link href="js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
</head>
<body>

<div class="container" id="test">

    <form action="/login" class="form-signin" method="post" style="border: 1px">
        <h2 class="form-signin-heading" style="text-align: center">登 录 界 面</h2>
        <br/>
        <input type="text" id="inputUsername" class="form-control" placeholder="username" name="name" required
               autofocus>
        <br/>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">登 录</button>

    </form>
</div>

<%--<a href="/user/test">test hibernate hql</a>--%>
<%--<div class="container" id="test">--%>
    <%--<form class="form-signin" method="post" style="border: 1px">--%>
        <%--<h2 class="form-signin-heading" style="text-align: center">登 录 界 面</h2>--%>
        <%--<br/>--%>
        <%--<input type="text" id="inputUsername" class="form-control" placeholder="username" name="name" required--%>
               <%--autofocus>--%>
        <%--<br/>--%>
        <%--<input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>--%>

        <%--<button class="btn btn-lg btn-primary btn-block" type="submit" onclick="login()">登 录</button>--%>
    <%--</form>--%>
<%--</div>--%>

<%--<script type="text/javascript">--%>
    <%--function login() {--%>
        <%--var userData = {--%>
            <%--"name" : $('#inputUsername').val(),--%>
            <%--"password": $('#inputPassword').val()--%>
        <%--}--%>
        <%--$.ajax({--%>
            <%--type: "POST",--%>
            <%--url: "login",--%>
            <%--data: userData,--%>
            <%--datatype:JSON,--%>
            <%--contextTypes:"application/json,charset=utf-8",--%>
            <%--success: function (data) {--%>
                <%--if (data.id != null) {--%>
                    <%--window.location.replace("/menu");--%>
                <%--}--%>
                <%--else {--%>
                    <%--alert("用户名或密码错误");--%>
                <%--}--%>
            <%--},--%>
        <%--})--%>
    <%--}--%>
<%--</script>--%>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>
