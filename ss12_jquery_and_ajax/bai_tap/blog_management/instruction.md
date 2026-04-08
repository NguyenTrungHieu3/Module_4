[Bài tập] Tích hợp AJAX cho ứng dụng Blog
Mục tiêu
Luyện tập sử dụng AJAX trong một ứng dụng Web.

Mô tả
Trong phần này, chúng sẽ tính hợp AJAX vào trong ứng dụng Blog thông qua 2 tính năng
mới:
- Tìm kiếm: Người dùng nhập từ vào ô tìm kiếm, ứng dụng sẽ gửi request thông qua AJAX để
  hiển thị danh sách các bài viết phù hợp
- Tải thêm: Là tính năng thay thế cho tính năng phân trang. Khi hiển thị danh sách bài viết, chỉ
  hiện thị 20 bài viết, sau đó, người dùng sẽ nhấn nút “Tải thêm” để xem các bài viết cũ hơn.

Hướng dẫn
Bước 1: Thêm dependencies AJAX trong file build.gradle
1. Chức năng tìm kiếm
   Bước 1: Trong trang danh sách các bài viết, tạo form tìm kiếm gồm 1 textbox và 1 nút search
   Bước 2: Viết Jquery AJAX cho sự kiện submit form gửi dữ liệu cho controller
   Bước 3: Sửa lại controller nhận và trả dữ liệu lại cho AJAX hiển thị kết quả tìm kiếm
   Bước 4: Chạy ứng dụng

2. Tải thêm
   Bước 1: Trong trang danh sách thêm nút “Tải thêm”
   Bước 2: Viết Jquery Ajax cho sự kiện click nút “Tải thêm”
   Bước 3: Viết controller trả về danh sách bài viết cho view
   Bước 4: Chạy ứng dụng
Tôi có một bài tập như trên, tôi muốn bạn thực hiện giúp tôi. Cách sử dụng ajax như sau:
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="jquery-3.6.0.js"></script>

</head>
<body>
<div>
    <h1>Thêm mơi</h1>
    Name: <input id="name"><br>
    Gender: <input type="radio" value="true" name="gender"> Nam
    <input type="radio" value="false" name="gender"> Nữ<br>
    Class name: <select id="classId">
    <option value="0">---------Chọn-------</option>
    <option value="1">C07</option>
    <option value="2">C08</option>
    <option value="3">C09</option>
</select><br>
    <button  id="btn-add">Thêm mới</button>
</div>

<table class="table table-dark table-stripped" border="1" style="border-collapse: collapse;">
    <thead>
    <tr>
        <td>STT</td>
        <td>Id</td>
        <td>Name</td>
        <td>Gender</td>
        <td>Class name</td>
    </tr>
    </thead>
    <tbody id="content">

    </tbody>

</table>
<script>
    $(document).ready(function () {
        display();

        $("#btn-add").click(function (){
            let name = $("#name").val();
            let gender = $("input[name='gender']:checked").val();
            let classId = $("#classId").val();
            const student = {
                name: name,
                gender: gender,
                classes: {
                    id: classId
                }
            }
            $.ajax({
                url: "http://localhost:8080/api/v1/students",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(student),
                success: function (data) {
                  display();
                },
                error: function (data){
                    console.log("-------thất bại--------")
                    console.log(data)
                }
            })
        })

    })



    function display() {
        $.ajax({
            url: "http://localhost:8080/api/v1/students",
            method: "GET",
            dataType: "json",
            success: function (data) {
                // hiển thị lên bảng;
                let content = "";
                for (let i = 0; i < data.length; i++) {
                    content += `
                     <tr>
                     <td>${i + 1}</td>
                     <td>${data[i].id}</td>
                     <td>${data[i].name}</td>
                     <td>${data[i].gender}</td>
                     <td>${data[i].classes?.name}</td>
                     </tr>
                    `
                }
                $("#content").html(content);
            }
        })
    }
</script>
<h2>Footer</h2>
</body>
</html>