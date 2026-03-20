<%--
  Created by IntelliJ IDEA.
  User: hieug
  Date: 3/16/2026
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Add Student</title>
    <c:import url="${pageContext.request.contextPath}/WEB-INF/views/layout/library.jsp"/>
</head>
<body>
    <c:import url="${pageContext.request.contextPath}/WEB-INF/views/layout/navbar.jsp"/>
    <form:form action="/student/add" method="post" modelAttribute="student" cssClass="w-25 mx-auto mt-3 p-3 border rounded shadow">
        <div class="mb-3">
            <label class="form-label" for="id">ID</label>
            <form:input path="id" cssClass="form-control"></form:input>
        </div>

        <div class="mb-3">
            <label class="form-label" for="name">Name</label>
            <form:input path="name" cssClass="form-control"></form:input>
        </div>
        <div class="mb-3">
            <label class="form-label" for="gender">Gender</label>
            <form:radiobutton path="gender" value="true" /> Nam
            <form:radiobutton path="gender" value="false" /> Nữ
        </div>
        <div class="mb-3">
            <label class="form-label" for="classCG">Class</label>
            <form:select path="classCG.id">
                <form:option value="0">Chọn lớp</form:option>
                <c:forEach items="${classCGList}" varStatus="classCGStatus" var="classCG">
                    <form:option value="${classCG.id}">${classCG.name}</form:option>
                </c:forEach>
            </form:select>
        </div>
        <form:button type="submit" class="btn btn-primary">Add</form:button>
    </form:form>
</body>
</html>
