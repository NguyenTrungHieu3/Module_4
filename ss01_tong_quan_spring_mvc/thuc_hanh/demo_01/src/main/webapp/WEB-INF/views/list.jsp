<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>List Student</title>
  <jsp:include page="layout/library.jsp"/>
</head>
<body>
<jsp:include page="layout/my-navbar.jsp"/>
<h2>Quản lý sản sinh viên</h2>
<%--<div class="d-flex justify-content-between px-3">--%>
<%--  <form action="/student" method="get">--%>
<%--    <input name="action" value="search" hidden="hidden">--%>
<%--    <input name="studentName" placeholder="Tên hàng hóa" value="${studentName}">--%>
<%--    <select name="classId">--%>
<%--      <option value="0">-- Chọn lớp --</option>--%>
<%--      <c:forEach items="${classList}" var="cls">--%>
<%--        <option value="${cls.id}" ${cls.id == classId ? 'selected' : ''}>${cls.name}</option>--%>
<%--      </c:forEach>--%>
<%--    </select>--%>
<%--    <input type="number" name="score" placeholder="Điểm" value="${score}">--%>
<%--    <button type="submit">Tìm kiếm</button>--%>
<%--  </form>--%>
<%--  <a href="/student?action=add" class="btn btn-success">Thêm mới sinh viên</a>--%>
<%--</div>--%>

<div class="mt-3 px-3">
  <table class="table table-bordered table-hover table-striped">
    <thead>
    <tr>
      <td>STT</td>
      <td>Tên sinh viên</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="student" varStatus="status">
      <tr>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>
            <%--          <a href="/product?action=update&id=${product.id}" class="btn btn-sm btn-warning">Chỉnh sửa</a>--%>
          <a class="btn btn-sm btn-warning">Chỉnh sửa</a>
        </td>
        <td>
            <%--          <button onclick="getInfoDelete('${product.id}', '${product.productName}')" type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteProductModal">Xóa</button>--%>
          <button onclick="getInfoDelete('${student.id}', '${student.name}')" type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteProductModal">Xóa</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>


<!-- Modal delete -->
<div class="modal fade" id="deleteProductModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form action="/student?action=delete" method="post">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Xác nhận xóa</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <input hidden="hidden" name="deletedId" id="deleteId">
          <span>Bạn có muốn xoá sinh viên </span> <span class="text-danger" id="deleteName"></span>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-danger">Save changes</button>
        </div>
      </div>
    </form>
  </div>
</div>

<%--Modal notify--%>
<div class="modal fade" id="notifyModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Thông báo</h5>
      </div>
      <div class="modal-body text-center">
        <span id="notifyMessage"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="window.location='/student'">OK</button>
      </div>
    </div>
  </div>
</div>
<script>
  <c:if test="${not empty param.msg}">
  openModalNotify('${param.msg}');
  </c:if>

  function openModalNotify(msg) {
    const message = {
      delete_success: { text: "Xóa thành công!", cls: "text-success fw-bold" },
      delete_fail:    { text: "Xóa thất bại!",   cls: "text-danger fw-bold" },
      delete_error:   { text: "Xóa thất bại. Lỗi hệ thống!",   cls: "text-warning fw-bold" },
      add_success: { text: "Thêm thành công!", cls: "text-success fw-bold" },
      add_fail:    { text: "Thêm thất bại!",   cls: "text-danger fw-bold" },
      add_error:   { text: "Thêm thất bại. Lỗi hệ thống!",   cls: "text-warning fw-bold" },
      update_success: { text: "Chỉnh sửa thành công!", cls: "text-success fw-bold" },
      update_fail:    { text: "Chỉnh sửa thất bại!",   cls: "text-danger fw-bold" },
      update_error:   { text: "Chỉnh sửa thất bại. Lỗi hệ thống!",   cls: "text-warning fw-bold" }
    }
    const config = message[msg];
    if (!config) {
      return;
    }
    const notifyMessage = document.getElementById('notifyMessage');
    notifyMessage.className = config.cls;
    notifyMessage.innerText = config.text;

    new bootstrap.Modal(document.getElementById('notifyModal')).show();
  }
  function getInfoDelete(id, name) {
    document.getElementById('deleteId').value = id;
    document.getElementById('deleteName').innerText = name;
  }
</script>
</body>
</html>
