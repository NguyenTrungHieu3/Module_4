<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Result</title>
</head>

<body>

<h2>Gia vị bạn đã chọn:</h2>

<c:if test="${empty condiments}">
  <p>${message}</p>
</c:if>

<c:forEach items="${condiments}" var="item">
  <p>${item}</p>
</c:forEach>

<a href="/home">Back</a>

</body>
</html>