
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
        <title>Login</title>

    </head>
    <body>
        <c:if test="${not empty sessionScope.user}">
            <c:redirect url="/toyshop/home"/>
        </c:if>
        <div class="modal">
            <div class="">

            </div>

            <div class="modal__body">

                <!-- Auth form Login -->
                <form action="login" method="post" class="auth-form">
                    <div class="auth-form__container">
                        <div class="auth-form__header">
                            <h3 class="auth-form__heading">Đăng nhập</h3>
                            <a href="/toyshop/signup" class="auth-form__swtich-btn">Đăng ký</a>
                        </div>

                        <div class="auth-form__form">
                            <div class="auth-form__group">
                                <input type="email" name="email" class="auth-form__input" placeholder="Email của bạn">
                            </div>
                            <div class="auth-form__group">
                                <input type="password" name="password" class="auth-form__input" placeholder="Mật khẩu của bạn">
                            </div>
                        </div>

                        <div class="auth-form__aside">
                            <div class="auth-form__help">
                                <a href="" class="auth-form__help-link auth-form--forgot">Quên mật khẩu</a>
                                <span class="auth-form__separate"></span>
                                <a href="" class="auth-form__help-link">Cần trợ giúp?</a>
                            </div>
                        </div>

                        <c:if test="${not empty msg}">
                            <h2 style="color: red">${msg}</h2>
                        </c:if>

                        <div class="auth-form__controls">
                            <a href="/toyshop/home" class="btn btn--normal">TRỞ LẠI</a>
                            <button class="btn btn--primary" type="submit">ĐĂNG NHẬP</button>
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
