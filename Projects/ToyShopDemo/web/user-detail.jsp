<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Hồ sơ</title>

        <!-- Custom fonts for this template-->
        <link href="./assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="./assets/css/sb-admin-2.min.css" rel="stylesheet">
        <script>
            const imageFile = document.querySelector('#image').files[0];
            function validatePassword() {
                const retypePassword = document.getElementById('retypePassword');
                const password = document.getElementById('password');
                if (retypePassword.value) {
                    // Check if the password is valid. 6 character + include 1 upper, 1 lower, 1 num, 1 special char
                    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-+]).{6,}$/;
                    if (!passwordRegex.test(password.value)) {
                        alert('Mật khẩu phải có ít nhất 6 ký tự, 1 chữ thường, 1 chữ hoa, 1 chữ số và 1 ký tự đặc biệt.');
                        return false;
                    }

                    // Check if the retypePassword equals the password.
                    else if (retypePassword.value !== password.value) {
                        alert('Mật khẩu nhập lại không khớp.');
                        return false;
                    }

                    // If all of the checks pass, return true.
                    return true;
                }
            }

        </script>
    </head>
    <body>
        <div class="bg-gradient">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5" style="margin: 0 auto; box-shadow: 0 1px 2px 0 rgba(0,0,0,.30)">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Hồ sơ của tôi</h1>
                            </div>
                            <form onsubmit="return validatePassword();" class="user" action="userinfo" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="name">Tên</label>
                                    <input type="text" name="name" class="form-control form-control-user" id="name" value="${sessionScope.user.name}">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label> 
                                    <input type="text" name="email" class="form-control form-control-user" id="email" value="${sessionScope.user.email}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="phoneNumber">Số điện thoại</label> 
                                    <input type="text" name="phoneNumber" class="form-control form-control-user" id="phoneNumber" value="${sessionScope.user.phoneNumber}">
                                </div>
                                <div class="form-group">
                                    <label for="password">Mật khẩu</label> 
                                    <span class="password-error-tag" id="password-error-tag"></span>
                                    <input type="password" name="password" class="form-control form-control-user" id="password" value="${sessionScope.user.password}">
                                </div>
                                <div class="form-group">
                                    <label for="retypePassword">Nhập lại mật khẩu (Nếu bạn muốn thay đổi mật khẩu)</label> 
                                    <input type="password" name="retypePassword" class="form-control form-control-user" id="retypePassword" value="">
                                </div>
                                <img src="${sessionScope.user.avatar}" style="max-width: 500px">
                                <input type="hidden" name="defaultImage" value="${sessionScope.user.avatar}">
                                <input type="hidden" name="id" value="${sessionScope.user.id}">
                                <br></br>
                                <div class="form-group">
                                    <label>Cập nhật avatar mới?</label> 
                                    <input type="file" name="image" accept="image/*">
                                </div>

                                <hr>
                                <button type="submit" class="btn btn-facebook btn-user btn-block">Lưu</button>
                            </form>
                            <hr>
                            <hr>
                            <a href="home" class="btn btn-primary btn-user btn-block">Trở lại</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="./assets/vendor/jquery/jquery.min.js"></script>
        <script src="./assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="./assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="./assets/js/sb-admin-2.min.js"></script>
    </body>

</html>