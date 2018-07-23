<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录界面</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
</head>
<body>

    <div class="container" id="test">

        <form class="form-signin" action="/login" method="post">
            <h2 class="form-signin-heading">登 录 界 面</h2>

            <input type="text" id="inputUsername" class="form-control" placeholder="username" name="name" required autofocus>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>

            <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>

        </form>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
