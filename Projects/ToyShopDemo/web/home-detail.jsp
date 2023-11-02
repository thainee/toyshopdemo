
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Toy Kingdom</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/grid.css">
        <link rel="stylesheet" href="./assets/css/main.css">
        <link rel="stylesheet" href="./assets/css/responsive.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
            jQuery(document).ready(($) => {
                $('.quantity').on('click', '.plus', function (e) {
                    let $input = $(this).prev('input.qty');
                    let val = parseInt($input.val());
                    $input.val(val + 1).change();
                });

                $('.quantity').on('click', '.minus', function (e) {
                    let $input = $(this).next('input.qty');
                    var val = parseInt($input.val());
                    if (val > 1) {
                        $input.val(val - 1).change();
                    }
                });
            });
        </script>
    </head>
    <body>
        <div class="app">
            <!-- Header --> 
            <jsp:include page="header.jsp"/>

            <div class="app__container">
                <div class="grid wide">
                    <div style="background: white">
                        <div class="row sm-gutter">
                            <div class="col l-5 m-5 c-12">
                                <div class="product-item__img" style="background-image: url(${product.image})"></div>
                            </div>

                            <div class="col l-7 m-7 c-12">
                                <div class="product-right">
                                    <span class="product-right__name">${product.name}</span>
                                    <div class="product-right__price">
                                        <c:choose>
                                            <c:when test="${product.discount > 0}">
                                                <span class="product-item__price-old"><fmt:formatNumber value="${product.price}" type="currency"/></span> 
                                                <span class="product-item__price-new"><fmt:formatNumber value="${product.price * ((100 - product.discount)/100)}" type="currency"/></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="product-item__price-new"><fmt:formatNumber value="${product.price}" type="currency"/></span>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:if test="${product.discount > 0}">
                                            <div class="product-right__sale-off">
                                                <span class="product-right__sale-off-percent">${product.discount}%</span>
                                                <span class="product-right__sale-off-label">GIẢM</span>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="product-right__description">
                                        <span class="product-right__description-heading">${product.description}</span>
                                    </div>
                                    <form class="product-right__quantity" action="order" method="post">
                                        <div class='quantity'>
                                            <input type='button' value='-' class='qtyminus minus' field='quantity' />
                                            <input type='text' name='quantity' value='1' class='qty' />
                                            <input type='button' value='+' class='qtyplus plus' field='quantity' />
                                        </div>
                                        <button type="submit" class="product-right__quantity-btn btn btn--primary">Thêm vào giỏ hàng</button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <!-- Footer -->       
            <jsp:include page="footer.jsp"/>
    </body>
</html>