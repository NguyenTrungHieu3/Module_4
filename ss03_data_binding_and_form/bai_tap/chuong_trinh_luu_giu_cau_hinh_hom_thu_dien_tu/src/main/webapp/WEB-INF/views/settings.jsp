<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Email Settings</title>
</head>

<body>

<h2>Settings</h2>

<form:form action="/settings/update" method="post" modelAttribute="settings">

  Language:
  <form:select path="language" items="${languages}"/>

  <br><br>

  Page Size:
  Show <form:select path="pageSize" items="${pageSizes}"/> emails per page

  <br><br>

  Spam Filter:
  <form:checkbox path="spamFilter"/>

  <br><br>

  Signature:
  <br>
  <form:textarea path="signature" rows="4" cols="40"/>

  <br><br>

  <button type="submit">Update</button>

</form:form>

<h3>${message}</h3>

</body>
</html>