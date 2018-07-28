<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <link href="js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link href="/css/menu.css" rel="stylesheet">
</head>
<body>
<!--菜单栏-->
<div class="navbar navbar-duomi navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <ul class="nav pull-left">
                <li><a class="navbar-brand" href="#" id="logo">XXXX后台管理系统</a></li>
            </ul>
            <ul class="nav pull-right nvabar-nav">
                <li><span><img src="${pageContext.request.contextPath}/images/user.png"
                               width="20px" height="28px"/></span><a href="user/toUpdate?id=${user.id}"><span
                        style="padding-top:5px">${user.genericName}</span></a></li>
                <li><a href="/logout"><span><img src="/images/logout.png" width="20px"
                                                 height="28px"/></span></a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <!--左侧内容-->
        <div class="col-md-2">
            <ul id="main-nav" class="main-nav nav nav-tabs nav-stacked" style="">
                <!--首页-->
                <li>
                    <a href="/home" target="right-content">
                        <i class="glyphicon glyphicon-home"></i>
                        首页
                    </a>
                </li>
                <!--组管理-->
                <li>
                    <a href="#groupMenu"
                       class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-th-large"></i>
                        组管理
                        <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                    </a>
                    <ul id="groupMenu" class="nav nav-list secondmenu collapse" style="height: 0px;">
                        <li><a href="#" target="right-content"><i
                                class="glyphicon glyphicon-th-list"></i>&nbsp;组表</a></li>
                        <li><a href="#" target="right-content"><i
                                class="glyphicon glyphicon-plus"></i>&nbsp;添加组</a></li>
                    </ul>
                </li>
                <!--用户管理-->
                <li>
                    <a href="#userMenu"
                       class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-user"></i>
                        用户管理
                        <span class="pull-right glyphicon  glyphicon-chevron-toggle"></span>
                    </a>
                    <ul id="userMenu" class="nav nav-list secondmenu collapse" style="height: 0px;">
                        <li><a href="/user/list/${user.id}" target="right-content"><i
                                class="glyphicon glyphicon-th-list"></i>&nbsp;用户列表</a></li>
                        <li><a href="/user/add" target="right-content"><i
                                class="glyphicon glyphicon-plus"></i>&nbsp;添加用户</a></li>
                    </ul>
                </li>
                <!--文件上传管理-->
                <li>
                    <a href="#fileMenu"
                       class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-folder-open"></i>
                        文件上传
                        <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                    </a>
                    <ul id="fileMenu" class="nav nav-list secondmenu collapse">
                        <li><a href="#" target="right-content"><i
                                class="glyphicon glyphicon-th-list"></i>&nbsp;文件列表</a></li>
                        <li><a href="#" target="right-content"><i
                                class="glyphicon glyphicon-file"></i>&nbsp;文件上传</a></li>
                    </ul>
                </li>
                <!--摄像头管理-->
                <li>
                    <a href="#camerasMenu"
                       class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-camera"></i>
                        摄像头管理
                        <span class="pull-right glyphicon glyphicon-chevron-toggle"></span>
                    </a>
                    <ul id="camerasMenu" class="nav nav-list secondmenu collapse">
                        <li><a href="#" target="right-content"><i
                                class="glyphicon glyphicon-facetime-video"></i>&nbsp;摄像头配置</a></li>
                    </ul>
                </li>
            </ul>
        </div>

        <!--右侧内容-->
        <div class="col-md-10">
            <div>
                <iframe id="right-content" name="right-content" src=""
                        width="100%" frameborder="no"
                        border="0" marginwidth="0"
                        marginheight=" 0" scrolling="no" allowtransparency="yes">
                </iframe>
            </div>
        </div>
    </div>
</div>
</div>

<script type="text/javascript">
    /**
     * iframe 的高度自适应
     */
    function reinitIframe() {
        var iframe = document.getElementById("right-content");
        try {
            var bHeight = iframe.contentWindow.document.body.scrollHeight;
            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            var height = Math.max(bHeight, dHeight);
            iframe.height = height;
            console.log(height);
        } catch (ex) {
        }
    }
    window.setInterval("reinitIframe()", 200);
</script>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</body>
</html>