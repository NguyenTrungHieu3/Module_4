<%--
  Created by IntelliJ IDEA.
  User: hieug
  Date: 3/16/2026
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Student Management</title>
    <c:import url="${pageContext.request.contextPath}/WEB-INF/views/layout/library.jsp"/>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/WEB-INF/views/layout/navbar.jsp"/>
<div class="container mt-4">
    <h2>Student List</h2>
    <h2 class="bg-success" style="color: #fff">${message}</h2>
    <a class="btn btn-primary mb-3" href="/student/add">
        Add Student
    </a>

    <table class="table table-bordered">

        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Language</th>
            <th>Class</th>
            <th>Action</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${studentList}" varStatus="status" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>
                    <c:if test="${student.gender}">
                        Nam
                    </c:if>
                    <c:if test="${!student.gender}">
                        Nữ
                    </c:if>
                </td>
                <td>
                    <c:forEach items="${classCGList}" varStatus="classStatus" var="classCG.id">
                        <c:if test="${classCG.id == student.classCG.id}">
                            ${classCG.name}
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <a class="btn btn-warning" href="/student/edit/${student.id}">Edit</a>
                    <button onclick="getInfoDelete('${student.id}', '${student.name}')" type="button" class="btn btn-danger"
                            data-bs-toggle="modal" data-bs-target="#deleteModal">Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/student/delete" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Delete Confirm</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="studentId" id="studentId">
                    Bạn có có muốn xóa sinh viên <span id="studentName" class="text-danger"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function getInfoDelete(studentId, studentName) {
        document.getElementById("studentId").value = studentId;
        document.getElementById("studentName").innerText = studentName;
    }
</script>
</body>
</html>
