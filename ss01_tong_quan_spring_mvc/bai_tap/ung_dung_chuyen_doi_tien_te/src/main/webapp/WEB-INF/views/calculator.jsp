<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Currency Converter</title>
</head>

<body>

<h2>Ứng dụng chuyển đổi tiền tệ</h2>

<form action="convert" method="post">

    Tỉ giá usd sang :
    <input type="text" name="rate"/>

    <br><br>

    USD:
    <input type="text" name="usd"/>

    <br><br>

    <button type="submit">Chuyển đổi</button>

</form>

<h3>
    VND: ${vnd} VND
</h3>

</body>
</html>