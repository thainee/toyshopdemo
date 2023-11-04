<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>
            .gradient-custom {
                background: #f5f5f5;
            }

            .btn-primary {
                background-color: #f05d40 !important;
                color: white;
                border: none;
            }
            .btn-primary:hover {
                background-color: #f05d40 !important;
            }

            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                /* display: none; <- Crashes Chrome on hover */
                -webkit-appearance: none;
                margin: 0; /* <-- Apparently some margin are still there even though it's hidden */
            }

            input[type=number] {
                -moz-appearance:textfield; /* Firefox */
            }

        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>
    <body>
        <section class="h-100 gradient-custom">
            <div class="container py-5">
                <div class="row d-flex justify-content-center my-4">
                    <div class="col-md-8">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Giỏ hàng</h5>
                            </div>
                            <div class="card-body">
                                <c:if test="${not empty sessionScope.orderItems}">
                                    <c:set var="totalPrice" value="${0}" scope="session"/>
                                    <c:forEach var="orderItem" items="${sessionScope.orderItems}">
                                        <!-- Single item -->
                                        <div class="row">
                                            <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
                                                <!-- Image -->
                                                <div class="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">
                                                    <img src="${orderItem.product.image}"
                                                         class="w-100" alt="Blue Jeans Jacket" />
                                                    <a href="#!">
                                                        <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
                                                    </a>
                                                </div>
                                                <!-- Image -->
                                            </div>

                                            <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                                                <!-- Data -->
                                                <p><strong>${orderItem.product.name}</strong></p>
                                                <form action="orderitem" method="post">
                                                    <input type="hidden" name="orderId" value="${sessionScope.currentOrder.id}">
                                                    <input type="hidden" name="productId" value="${orderItem.product.id}">
                                                    <button type="submit" class="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip"
                                                            title="Remove item">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
                                                        <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
                                                        </svg>
                                                    </button>
                                                </form>
                                                <button type="button" class="btn btn-danger btn-sm mb-2" data-mdb-toggle="tooltip"
                                                        title="Move to the wish list">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                                                    <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                                    </svg>
                                                </button>
                                                <!-- Data -->
                                            </div>

                                            <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                                                <!-- Quantity -->
                                                <form class="d-flex mb-4" action="orderitemquantity" method="post"style="max-width: 300px">
                                                    <input type="hidden" name="orderId" value="${sessionScope.currentOrder.id}">
                                                    <input type="hidden" name="productId" value="${orderItem.product.id}">
                                                    <input type="hidden" name="quantity" value="${orderItem.quantity}">
                                                    <button type="submit" name="button" value="minus" class="btn btn-primary px-3 me-1 btn-sm d-flex justify-content-center align-items-center"
                                                            onclick="if (this.parentNode.querySelector('input[type=number]').value > 1)
                                                                        this.parentNode.querySelector('input[type=number]').stepDown()">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash" viewBox="0 0 16 16">
                                                        <path d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z"/>
                                                        </svg>
                                                    </button>

                                                    <div class="form-outline">
                                                        <input min="1" name="quantity" value="${orderItem.quantity}" type="number" class="form-control text-center" readonly/>
                                                        <c:set var="totalPrice" value="${totalPrice + (orderItem.quantity * orderItem.product.price)}" scope="session"/>
                                                    </div>

                                                    <button type="submit" name="button" value="plus" class="btn btn-primary px-3 ms-1 btn-sm d-flex justify-content-center align-items-center"
                                                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                                                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                                        </svg>
                                                    </button>
                                                </form>
                                                <!-- Quantity -->

                                                <!-- Price -->
                                                <p class="text-start text-md-center">
                                                    <strong class="price"><fmt:formatNumber value="${orderItem.product.price *((100 - orderItem.product.discount)/100)}" type="currency"/></strong>
                                                </p>
                                                <!-- Price -->
                                            </div>
                                            <hr class="my-4" />
                                        </div>
                                        <!-- Single item -->
                                    </c:forEach>
                                </c:if>

                            </div>
                        </div>

                        <form class="card mb-4" action="shippingaddress" method="get">
                            <div class="card-body">
                                <p><strong>Địa chỉ nhận hàng</strong></p>
                                <c:if test="${empty sessionScope.currentShippingAddress}">
                                    <p class="mb-0">${sessionScope.shippingAddresses[0].name} | ${sessionScope.shippingAddresses[0].phoneNumber}</p>
                                    <p class="mb-0">${sessionScope.shippingAddresses[0].addressLine}</p>
                                    <p class="mb-0">${sessionScope.shippingAddresses[0].commune}, ${sessionScope.shippingAddresses[0].district}, ${sessionScope.shippingAddresses[0].city}</p>
                                </c:if>
                                <c:if test="${not empty sessionScope.currentShippingAddress}">
                                    <p class="mb-0">${sessionScope.currentShippingAddress.name} | ${sessionScope.currentShippingAddress.phoneNumber}</p>
                                    <p class="mb-0">${sessionScope.currentShippingAddress.addressLine}</p>
                                    <p class="mb-0">${sessionScope.currentShippingAddress.commune}, ${sessionScope.currentShippingAddress.district}, ${sessionScope.currentShippingAddress.city}</p>
                                </c:if>  
                            </div>
                            <button type="submit" class="btn btn-primary ms-3 mb-3" style="max-width: 150px">Thay đổi</button>
                        </form>

                        <div class="card mb-4">
                            <div class="card-body">
                                <p><strong>Thời gian nhận hàng dự kiến</strong></p>
                                <p class="mb-0"><fmt:formatDate pattern="dd/MM/yyyy" value="${deliveryTime1}"/> - <fmt:formatDate pattern="dd/MM/yyyy" value="${deliveryTime2}"/></p>
                            </div>
                        </div>
                        <div class="card mb-4 mb-lg-0">
                            <div class="card-body">
                                <p><strong>Chúng tôi chấp nhận các phương thức thanh toán</strong></p>
                                <img class="me-2" width="45px"
                                     src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                                     alt="Visa" />
                                <img class="me-2" width="45px"
                                     src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
                                     alt="American Express" />
                                <img class="me-2" width="45px"
                                     src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
                                     alt="Mastercard" />
                                <img class="me-2" width="45px"
                                     src="https://upload.wikimedia.org/wikipedia/commons/b/b5/PayPal.svg"
                                     alt="PayPal acceptance mark" />
                            </div>
                        </div>

                        <div class="card mb-4 mb-lg-0 mt-4">
                            <div class="card-body">
                                <a href="home" class="btn btn-primary btn-lg btn-block">Quay lại</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-header py-3">
                                <h5 class="mb-0">Thanh toán</h5>
                            </div>
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                        Giá sản phẩm
                                        <span class="total"><fmt:formatNumber value="${sessionScope.totalPrice}" type="currency"/></span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                        Phí vận chuyển
                                        <span>Miễn phí</span>
                                    </li>
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                        <div>
                                            <strong>Tổng thanh toán</strong>
                                            <strong>
                                                <p class="mb-0">(Đã bao gồm thuế VAT)</p>
                                            </strong>
                                        </div>
                                        <span class="total"><fmt:formatNumber value="${sessionScope.totalPrice}" type="currency"/><strong></strong></span>
                                    </li>
                                </ul>

                                <a href="payment" class="btn btn-primary btn-lg btn-block">
                                    Chọn phương thức thanh toán
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>


</html>
