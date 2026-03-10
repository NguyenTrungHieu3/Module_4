<%--
  Created by IntelliJ IDEA.
  User: hieug
  Date: 3/9/2026
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ứng dụng từ điển đơn giản</title>
</head>
<body>
<form action="search" method="post">

  Nhập từ muốn tra:
  <input type="text" name="word"/>

  <br><br>

  <button type="submit">Chuyển đổi</button>

</form>

<h3>
  Nghĩa là: ${mean}
</h3>
</body>
</html>
