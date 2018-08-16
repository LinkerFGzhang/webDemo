<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<h2 style="text-align: center">用户组列表</h2>
<br/>

<c:if test="${ empty requestScope.groupLists}">
    您当前的权限不足
</c:if>

<c:if test="${! empty requestScope.groupLists}">
    <table class="table table-striped table-hover" style="border: 1px;cellpadding:5px;mso-cellspacing: 10px">
        <thead>
        <tr>
            <th>Id</th>
            <th>name</th>
            <th>description</th>
            <th>owner</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.groupLists}" var="g">
            <tr>
                <td>${g[0].id}</td>
                <td>${g[0].name}</td>
                <td>${g[0].description}</td>
                <td>${g[1].genericName}</td>
                <td>
                    <button class="testEdit btn btn-primary btn-sm" onclick="editModal(this)">Edit
                    </button>
                </td>
                <td>
                    <button class="btn btn-primary btn-sm btn-danger" onclick="deleteModal(this)">Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<!-- 编辑模态框（Modal） -->
<form id="editForm" action="" method="post" class="form-horizontal" role="form" style="margin: 20px;">
    <input type="hidden" name="_method" value="PUT">
    <div class="modal fade" id="groupModify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel" style="text-align: center">
                        编&nbsp;&nbsp;&nbsp;辑
                    </h4>
                </div>
                <!-- body -->
                <div class="modal-body">
                    <!-- 表单回显 -->
                    <div class="form-group">
                        <label for="editId" class="col-sm-2 control-label">ID</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editId" placeholder="Id" disabled="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editName" class="col-sm-2 control-label">name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editName" placeholder="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDescription" class="col-sm-2 control-label">description</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editDescription" placeholder="description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editOwner" class="col-sm-2 control-label">所属用户</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editOwner" placeholder="description"
                                   disabled="true">
                        </div>
                    </div>
                </div>
                <!-- footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default" onclick="editSubmit(this)">提交</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                    <%--<span id="editTip"></span>--%>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form>

<!-- 删除模态框（Modal） -->
<form id="deleteForm" action="" method="post">
    <input type="hidden" name="_method" value="DELETE">
    <input type="hidden" id="tempId">
    <div class="modal fade" id="groupDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- header -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">
                        删&nbsp;&nbsp;&nbsp;除
                    </h4>
                </div>
                <!-- body -->
                <div class="modal-body">
                    <p>您确定要删除这些信息吗?</p>
                </div>
                <!-- footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default" onclick="deleteSubmit()">确定</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                    <span id="deleteTip"></span>
                </div>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">

    // 编辑模态框的值显示
    function editModal(obj) {
        var gId = $(obj).closest("tr").find("td:eq(0)").text();
        var gName = $(obj).closest("tr").find("td:eq(1)").text();
        var gDescription = $(obj).closest("tr").find("td:eq(2)").text();
        var gOwner = $(obj).closest("tr").find("td:eq(3)").text();
        //alert(gId + " " + gName + "  " + gDescription + "  " + gOwner);
        $('#editId').val(gId);
        $('#editName').val(gName);
        $('#editDescription').val(gDescription);
        $('#editOwner').val(gOwner);
        $('#groupModify').modal("toggle")
    }

    // 编辑模态框 提交
    function editSubmit(obj) {
        var newId = $('#editId').val();
        var newName = $('#editName').val();
        var newDescription = $('#editDescription').val();
        //var newOwner = $('#editOwner').val();
        if (newName == '') {
            alert('请输入组名');
            return
        }
        if (newDescription == '') {
            alert('请输入组描述');
            return
        }
        $.ajax({
            type: "put",
            url: "/group/update",
            data: {"id": newId, "name": newName, "description": newDescription},
            datatype: JSON,
            contextTypes: "application/json,charset=utf-8",
            success: function (data) {
                //json数据转化为对象
                alert(data)
                var obj = eval("(" + data + ")");
                alert(obj)
                if (data > 0) {
                    location.reload()
                }
                else {
                    alert("失败，请重新尝试")
                }
            },
            error: function (data) {
                alert("请求出错")
            }
        });
        return false;
    }

    // 删除模态框
    function deleteModal(obj) {
        var gId = $(obj).closest("tr").find("td:eq(0)").text();
        $("#tempId").val(gId);
        $('#groupDelete').modal("toggle");
        return false;
    }

    // 删除 确定
    function deleteSubmit() {
        var gId = $('#tempId').val();
        var href = "${pageContext.request.contextPath}/group/delete/" + gId;
        //JSON.stringify()
        $.ajax({
            type: "delete",
            url: "/group/delete/" + gId,
            data: {"gId": gId},
            datatype: JSON,
            contextTypes: "application/json,charset=utf-8",
            success: function (data) {
                alert(data)
                if (data > 0) {
                    $("#deleteTip").html("<span style='color:#3ee249'>恭喜，删除成功！</span>");
                    location.reload();
                }
                else {
                    $("#deleteTip").html("<span style='color:#e23215'>失败，请重新尝试!</span>");
                }
            },
            error: function () {
                alert("请求出错...")
            }
        });
        return false;
    }
</script>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</body>
</html>
