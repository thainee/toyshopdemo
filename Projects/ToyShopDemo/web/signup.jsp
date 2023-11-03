
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/grid.css">
        <link rel="stylesheet" href="./assets/css/main.css">
        <link rel="stylesheet" href="./assets/css/responsive.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <title>Sign up</title>
        <script>
            // Get all the input elements with the required attribute.
            const requiredInputs = document.querySelectorAll('input[required]');

// Check if any of the required inputs are empty.
            const isAnyInputEmpty = requiredInputs.some((input) => input.value === '');

// If any of the required inputs are empty, prevent the form from submitting.
            if (isAnyInputEmpty) {
                event.preventDefault();
            }

            function validatePassword() {
                const retypePassword = document.getElementById('retypePassword');
                const password = document.getElementById('password');

                // Check if the password is valid. 6 character + include 1 upper, 1 lower, 1 num, 1 special char
                const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-+]).{6,}$/;
                if (!passwordRegex.test(password.value)) {
                    alert('Mật khẩu phải có ít nhất 6 ký tự, 1 chữ thường, 1 chữ hoa, 1 chữ số và 1 ký tự đặc biệt.');
                    event.preventDefault();
                    return false;
                }

                // Check if the retypePassword equals the password.
                if (retypePassword.value !== password.value) {
                    alert('Mật khẩu nhập lại không khớp.');
                    event.preventDefault();
                    return false;
                }

                // If all of the checks pass, return true.
                if (passwordRegex.test(password.value) && retypePassword.value === password.value){
                    return true;
                }
            }
        </script>

    </head>
    <body>
        <c:if test="${not empty sessionScope.user}">
            <c:redirect url="/home"/>
        </c:if>
        <div class="modal">
            <div class="">

            </div>

            <div class="modal__body">
                <!-- Auth form Sign up -->
                <form action="signup" method="post" class="auth-form" onsubmit="validatePassword()">
                    <div class="auth-form__container">
                        <div class="auth-form__header">
                            <h3 class="auth-form__heading">Đăng ký</h3>
                            <a href="/toyshop/login" class="auth-form__swtich-btn">Đăng nhập</a>
                        </div>

                        <div class="auth-form__form">
                            <div class="auth-form__group">
                                <input type="text" name="name" class="auth-form__input" placeholder="Tên của bạn" required>
                            </div>
                            <div class="auth-form__group">
                                <input type="text" name="phoneNumber" class="auth-form__input" placeholder="Số điện thoại của bạn" required>
                            </div>
                            <div class="auth-form__group">
                                <input type="email" name="email" class="auth-form__input" placeholder="Email của bạn" required>
                                <p id="passwordError" style="color: red; padding: 0 10px; font-size: 13px">${msg}</p>
                            </div>
                            <div class="auth-form__group">
                                <input id="password" type="password" name="password" class="auth-form__input" placeholder="Mật khẩu của bạn" required>
                            </div>

                            <div class="auth-form__group">
                                <input id="retypePassword"type="password" name="retypePassword" class="auth-form__input" placeholder="Nhập lại mật khẩu" required>
                            </div>
                        </div>

                        <div class="auth-form__aside">
                            <p class="auth-form__policy-text">Bằng việc đăng kí, bạn đã đồng ý với Toy Kingdom về
                                <a href="" class="auth-form__text-link">Điều khoản dịch vụ</a> &
                                <a href="" class="auth-form__text-link">Chính sách bảo mật</a>
                            </p>
                        </div>

                        <div class="auth-form__controls">
                            <a href="/toyshop/home" class="btn btn--normal">TRỞ LẠI</a>
                            <button class="btn btn--primary" type="submit">ĐĂNG KÝ</button>
                        </div>
                    </div>

                    <div class="auth-form__social">
                        <a href="" class="btn btn--small auth-form--fb-btn">
                            <i class="auth-form__social-icon fab fa-facebook-square"></i>
                            Kết nối với Facebook
                        </a>
                        <a href="" class="btn btn--small auth-form--gg-btn">
                            <i class="auth-form__social-icon fab fa-google"></i>
                            Kết nối với Google
                        </a>
                    </div>
                </form>
            </div> 
        </div> 

        <!-- Footer -->       
        <jsp:include page="footer.jsp"/>
    </body>
</html>
