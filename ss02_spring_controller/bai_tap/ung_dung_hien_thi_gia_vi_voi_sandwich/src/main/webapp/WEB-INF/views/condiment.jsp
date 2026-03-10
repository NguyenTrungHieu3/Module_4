<%--
  Created by IntelliJ IDEA.
  User: hieug
  Date: 3/9/2026
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hãy chọn gia vị cho món sanwich</h2>
<form action="save" method="post">
  <input type="checkbox" name="condiment" value="Lettuce"> Lettuce <br>
  <input type="checkbox" name="condiment" value="Tomato"> Tomato <br>
  <input type="checkbox" name="condiment" value="Mustard"> Mustard <br>
  <input type="checkbox" name="condiment" value="Sprouts"> Sprouts <br>

  <br>
  <button type="submit">Save</button>
</form>
</body>
</html>
