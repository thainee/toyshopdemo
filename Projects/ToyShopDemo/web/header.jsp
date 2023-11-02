<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class="header">
    <div class="grid wide">
        <nav class="navbar">
            <!-- Navbar list  -->
            <div class="navbar__container hide-on-tablet-mobile">
                <div class="navbar__list">
                    <!-- <a href="" class="navbar__item navbar--border">Trở thành Người bán Shopee</a> -->
                    <a class="navbar__qr navbar--border" href="https://shopee.vn/web" target="_blank">
                        <div class="navbar__item navbar--pointer">Tải ứng dụng</div>
                        <div class="qr navbar--pointer">
                            <div><img class="qr__img" src="./assets/img/header_qr/header_qr.png" alt=""></div>
                            <div class="row qr__more">
                                <img class="qr__link" src="./assets/img/header_qr/header_qr_appstore.png" alt="">
                                <img class="qr__link" src="./assets/img/header_qr/header_qr_ggplay.png" alt="">
                                <img class="qr__link" src="./assets/img/header_qr/header_qr_gallery.png" alt="">
                            </div>
                        </div>
                    </a>
                    <div class="row">
                        <div class="navbar__text">
                            Kết nối
                        </div>
                        <a href="https://www.facebook.com/ShopeeVN" target="_blank" class="navbar__icon navbar__item">
                            <i class="fab fa-facebook"></i>
                        </a>
                        <a href="https://www.instagram.com/Shopee_VN" target="_blank" class="navbar__icon navbar__item">
                            <i class="fab fa-instagram-square"></i>
                        </a>
                    </div>
                </div>
                <div class="navbar__list">
                    <div class="navbar__notify">
                        <div class="navbar__item">
                            <i class="navbar__icon far fa-bell"></i>
                            Thông báo
                        </div>
                        <!-- Notify  -->
                        <div class="notify">
                            <header class="notify__header">
                                Thông báo mới nhận
                            </header>
                            <div class="notify__body">
                                <a href="#" class="notify__item notify--seen">
                                    <img class="notify__img" src="https://cf.shopee.vn/file/afd61876eccbba55e5fd44dc5e361ac2_tn" alt="">
                                    <div class="notify__content">
                                        <p class="notify__title">Voucher nóng hổi đừng vội bỏ qua!</p>
                                        <p class="notify__note">💥 Mã Hoàn đến 100K Xu đơn 150K
                                            🌈 Đã có sẵn trong ví voucher của bạn
                                            🔔 Hiệu lực: 0H 19/09/2021 - 23H59 25/09/2021 
                                            👉 Số lượng có hạn - Dùng ngay bạn nhé!</p>
                                    </div>
                                </a>

                                <a href="#" class="notify__item">
                                    <img class="notify__img" src="https://cf.shopee.vn/file/565277eec233892490bc426672ab6165_tn" alt="">
                                    <div class="notify__content">
                                        <p class="notify__title">Ting Ting</p>
                                        <p class="notify__note">✨Vũ trụ Shopee đang mách bảo bạn điều gì? Xem ngay TẠI ĐÂY👉</p>
                                    </div>
                                </a>

                                <a href="#" class="notify__item">
                                    <img class="notify__img" src="https://cf.shopee.vn/file/ea575d282e9b554e2192343f3bcee3b9_tn" alt="">
                                    <div class="notify__content">
                                        <p class="notify__title">Rầm rộ loạt deal x9K từ hàng Quốc Tế</p>
                                        <p class="notify__note">🔥 Hàng hot từ Hàn Quốc, Indonesia
                                            💫 Mua nhiều giảm đến 50%
                                            ✨ Thương hiệu ưu đãi giảm đến 50%
                                            🛒 Hấp dẫn quá nè - Bỏ giỏ liền tay!</p>
                                    </div>
                                </a>
                            </div>
                            <footer class="notify__footer">
                                <a href="#" class="notify__more">
                                    Xem tất cả
                                </a>
                            </footer>
                        </div>
                    </div>
                    <a class="navbar__item"  href="https://help.shopee.vn/vn/s/" target="_blank">
                        <i class="navbar__icon far fa-question-circle"></i>
                        Hỗ trợ
                    </a>

                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <!--  Navbar Login / Register  -->
                            <a href="/toyshop/signup" class="navbar__item navbar--border">Đăng ký</a>
                            <a href="/toyshop/login" class="navbar__item">Đăng nhập</a> 
                        </c:when>

                        <c:otherwise>
                            <!--  User menu -->
                            <div class="navbar__user">
                                <a href="" class="navbar__user-link">
                                    <img class="navbar__user-img" src="${sessionScope.user.avatar}" alt="">
                                    <span class="navbar__user-name">${sessionScope.user.name}</span>
                                </a> 
                                <div class="user-menu">
                                    <a href="#" class="user-menu__item">Tài khoản của tôi</a>
                                    <a href="#" class="user-menu__item">Đơn mua</a>
                                    <form action="logout" class="">
                                        <button type="submit" class="user-menu__item user-menu-item--separate btn btn--default">Đăng xuất</button>
                                    </form>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </nav>
        <!-- Header search  -->
        <div class="header-w-search">
            <label for="mobile-search-checkbox" class="header-w-search__mobile-search">
                <i class="fas fa-search"></i>
            </label>

            <a href="/toyshop/home" class="header-w-search__logo hide-on-tablet">
                <img src="./assets/img/toykingdom_logo.png" class="header-w-search__logo-img">

            </a>

            <input type="checkbox" hidden id="mobile-search-checkbox" class="header-w-search__search-checkbox">

            <form class="header-w-search__search" action="search">
                <div class="search-bar">
                    <div class="search-bar__input-wrap">
                        <input type="text" name="product" class="search-bar__input" placeholder="Tìm trong shop này">
                        <!-- Search history  -->
                        <!--                        <div class="search-bar__history">
                                                    <header class="search-bar__history-heading">
                                                        Lịch sử tìm kiếm 
                                                    </header>
                                                    <div class="search-bar__history-list">
                                                        <a href="#" class="search-bar__history-items">
                                                            Siêu xe
                                                        </a>
                                                        <a href="#" class="search-bar__history-items">
                                                            Mô hình
                                                        </a>
                                                    </div>
                                                </div>-->
                    </div>

                    <button type="submit" class="btn btn--small btn--primary">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </form>

            <div class="header-w-search__cart">
                <!-- Cart list  -->
                <div class="cart-drawer">
                    <i class="header-w-search__cart-img fas fa-shopping-cart"></i>

                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <div class="cart-drawer__list cart-drawer--no-cart">
                                <!-- No cart: cart-drawer--no-cart  -->
                                <img src="./assets/img/header_cart/no__cart.png" alt="" class="cart-drawer-no-cart__img">
                                <span class="cart-drawer-no-cart__msg">Chưa có sản phẩm, vui lòng đăng nhập để chọn sản phẩm</span>
                            </c:when>

                            <c:otherwise>
                                <div class="cart-drawer__list">
                                    <!-- Cart list  -->
                                    <span class="cart-drawer__quantity">3</span>
                                    <div class="cart-drawer__list-header">
                                        Sản phẩm mới thêm
                                    </div>

                                    <div class="cart-drawer__items">
                                        <img src="https://cf.shopee.vn/file/6799d0e1227876dad94b185f51d18d04_tn" alt="" class="cart-drawer__items-img">
                                        <div class="cart-drawer__items-info">
                                            <div class="cart-drawer__items-intro">
                                                <div class="cart-drawer__items-header">Nước rửa mắt Eyebon</div>
                                                <div class="cart-drawer__items-wrap">
                                                    <div class="cart-drawer__items-price">350.000đ </div>
                                                    <div class="cart-drawer__items-multiply">x</div>
                                                    <div class="cart-drawer__items-quantity">2</div>
                                                </div>
                                            </div>
                                            <div class="cart-drawer__items-wrap">
                                                <div class="cart-drawer__items-classify">Phân loại hàng: Hồng mới</div>
                                                <div class="cart-drawer__items-remove">Xóa</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cart-drawer__items">
                                        <img src="https://cf.shopee.vn/file/f91ed2d18d9089cf4f97ebac77b4b83f_tn" alt="" class="cart-drawer__items-img">
                                        <div class="cart-drawer__items-info">
                                            <div class="cart-drawer__items-intro">
                                                <div class="cart-drawer__items-header">Khẩu trang chống sương mù</div>
                                                <div class="cart-drawer__items-wrap">
                                                    <div class="cart-drawer__items-price">79.000đ</div>
                                                    <div class="cart-drawer__items-multiply">x</div>
                                                    <div class="cart-drawer__items-quantity">1</div>
                                                </div>
                                            </div>
                                            <div class="cart-drawer__items-wrap">
                                                <div class="cart-drawer__items-classify">Phân loại hàng: Đen</div>
                                                <div class="cart-drawer__items-remove">Xóa</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cart-drawer__items">
                                        <img src="https://cf.shopee.vn/file/9a3e039acfe997d18ea31dfedaab0df8_tn" alt="" class="cart-drawer__items-img">
                                        <div class="cart-drawer__items-info">
                                            <div class="cart-drawer__items-intro">
                                                <div class="cart-drawer__items-header">Khẩu trang Xiaomi KN95</div>
                                                <div class="cart-drawer__items-wrap">
                                                    <div class="cart-drawer__items-price">72.000đ</div>
                                                    <div class="cart-drawer__items-multiply">x</div>
                                                    <div class="cart-drawer__items-quantity">1</div>
                                                </div>
                                            </div>
                                            <div class="cart-drawer__items-wrap">
                                                <div class="cart-drawer__items-classify">Phân loại hàng: Đen</div>
                                                <div class="cart-drawer__items-remove">Xóa</div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#" class="cart-drawer__list-view btn btn--primary">Xem giỏ hàng</a>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
        <div class="sort-bar">
            <a href="" class="sort-bar__item sort-bar--active">Liên quan</a>
            <a href="" class="sort-bar__item">Mới nhất</a>
            <a href="" class="sort-bar__item">Bán chạy</a>
            <a href="" class="sort-bar__item">Giá</a>
        </div>
</header>
