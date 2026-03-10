<%--
  Created by IntelliJ IDEA.
  User: hieug
  Date: 3/9/2026
  Time: 9:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Máy tính cá nhân</title>
</head>
<body>
<h2>Máy tính cá nhân</h2>
<form action="calculate" method="post">
<input type="number" name="num1">
  <input type="number" name="num2">
  <br>
  <button type="submit" name="operator" value="+">Addition(+)</button>
  <button type="submit" name="operator" value="-">Subtraction(-)</button>
  <button type="submit" name="operator" value="*">Multiplication(X)</button>
  <button type="submit" name="operator" value="/">Division(/)</button>
</form>
<h2>Result</h2>
</body>
</html>
