<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/js/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
</head>
<body style="height: 100%">
<h2 style="text-align: center">摄像头列表</h2>

<c:if test="${ empty requestScope.cameraList}">
    您当前的权限不足
</c:if>

<c:if test="${!empty requestScope.cameraList}">
    <table class="table table-striped table-hover" style="border: 1px;cellpadding:5px;mso-cellspacing: 10px">
        <thead>
        <tr>
            <th>Id</th>
            <th>name</th>
            <th>description</th>
            <th>url</th>
            <th>address</th>
            <th>height</th>
            <th>width</th>
            <th>longitude</th>
            <th>latitude</th>
            <th>managerUser</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.cameraList}" var="c">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>${c.description}</td>
                <td>${c.url}</td>
                <td>${c.address}</td>
                <td>${c.height}</td>
                <td>${c.width}</td>
                <td>${c.longitude}</td>
                <td>${c.latitude}</td>
                <td>${c.usersByUserId.genericName}</td>
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
                            <input type="text" class="form-control" id="editId" placeholder="Id"
                                   readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editName" class="col-sm-2 control-label">name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editName" placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editType" class="col-sm-2 control-label">type</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editType" placeholder="type"
                                   readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editOwner" class="col-sm-2 control-label">owner</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editOwner" placeholder="owner"
                                   readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editFsName" class="col-sm-2 control-label">fsName</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editFsName" placeholder="fsName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editSuffix" class="col-sm-2 control-label">suffix</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editSuffix" placeholder="suffix">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editTime" class="col-sm-2 control-label">createTime</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editTime" placeholder="createTime"
                                   readonly="readonly">
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
    <%--<input type="hidden" name="_method" value="DELETE">--%>
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

<!-- id name type owner fsName suffix createTime -->
<script type="text/javascript">

    // 编辑模态框值的显示
    function editModal(obj) {
        var fId = $(obj).closest("tr").find("td:eq(0)").text();
        var fName = $(obj).closest("tr").find("td:eq(1)").text();
        var fType = $(obj).closest("tr").find("td:eq(2)").text();
        var fOwner = $(obj).closest("tr").find("td:eq(3)").text();
        var fFsname = $(obj).closest("tr").find("td:eq(4)").text();
        var fSuffix = $(obj).closest("tr").find("td:eq(5)").text();
        var fCreateTime = $(obj).closest("tr").find("td:eq(6)").text();
        //alert(gId + " " + gName + "  " + gDescription + "  " + gOwner);
        $('#editId').val(fId);
        $('#editName').val(fName);
        $('#editType').val(fType);
        $('#editOwner').val(fOwner);
        $('#editFsName').val(fFsname);
        $('#editSuffix').val(fSuffix);
        $('#editTime').val(fCreateTime);
        $('#groupModify').modal("toggle")
        return false;
    }

    // 编辑模态框 提交
    function editSubmit(obj) {
        //表单序列化
        //var form_data = $('#form_data').serialize();
        var newId = $.trim($('#editId').val());
        var newName = $.trim($('#editName').val());
        var newType = $.trim($('#editType').val());
        var newFsName = $.trim($('#editFsName').val());
        var newSuffix = $.trim($('#editSuffix').val());

        var file_data = {
            "id": newId,
            "type":newType,
            "name":newName,
            "fsName":newFsName,
            "suffix":newSuffix
        }

        $.ajax({
            type: "put",
            url: "/file/update",
            data: file_data,
            datatype: JSON,
            contextTypes: "application/json,charset=utf-8",
            success: function (data) {
                if (data > 0) {
                    alert("编辑成功...")
                }
            },
            error: function (data) {
                alert("请求出错")
            }
        });
        return false;
    }

    function deleteModal(obj) {
        var tempId = $(obj).closest("tr").find("td:eq(0)").text();
        $('#tempId').val(tempId);
        $('#deleteForm').modal("toggle");
        return false;
    }

    function deleteSubmit() {
        var id = $('#tempId').val();
        $.ajax({
            type: "delete",
            url: "/file/delete/" + id,
            data: {},
            datatype: JSON,
            contextTypes: "application/json,charset=utf-8",
            success: function (data) {
                if (data > 0) {
                    alert("删除成功...")
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

