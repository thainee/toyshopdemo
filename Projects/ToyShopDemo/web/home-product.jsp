
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!--<div class="home-filter hide-on-tablet-mobile">
    <span class="home-filter__label">
        Sắp xếp theo
    </span>
    <button class="btn btn--small">Phổ biến</button>
    <button class="btn btn--small btn--primary">Mới nhất</button>
    <button class="btn btn--small">Bán chạy</button>
    <div class="select-input">
        Giá
        <i class="fas fa-angle-down"></i>
        <div class="select-input__list">
            <a href="" class="select-input__item">
                Giá: Thấp đến Cao
            </a>
            <a href="" class="select-input__item">
                Giá: Cao đến Thấp
            </a>
        </div>
    </div>

    <div class="home-filter__page">
        <span class="home-filter__page-num">
            <span class="home-filter__page-current">1</span>/14
        </span>
        <div class="home-filter__page-control">
            <a href="" class="home-filter__page-btn home-filter__page-btn--disabled">
                <i class="home-filter__page-icon fas fa-angle-left"></i>
            </a>
            <a href="" class="home-filter__page-btn">
                <i class="home-filter__page-icon fas fa-angle-right"></i>
            </a>
        </div>
    </div>
</div>-->

<!--  Mobile Category  -->
<jsp:include page="mobile-category.jsp"/>

<div class="home-product">
    <div class="row sm-gutter">
        <!-- Product item  -->
        <!-- Row 1 and Row 2-->
        <c:forEach var="product" items="${products}">
            <div class="col l-3 m-4 c-6">
                <a class="product-item" href="homedetail?id=${product.id}">
                    <div class="product-item__img" style="background-image: url(${product.image})"></div>
                    <h4 class="product-item__name">
                        ${product.name}
                    </h4>
                    <div class="product-item__price">
                        <c:choose>
                            <c:when test="${product.discount > 0}">
                                <span class="product-item__price-old"><fmt:formatNumber value="${product.price}" type="currency"/></span> 
                                <span class="product-item__price-new"><fmt:formatNumber value="${product.price * ((100 - product.discount)/100)}" type="currency"/></span>
                            </c:when>
                            <c:otherwise>
                                <span class="product-item__price-new"><fmt:formatNumber value="${product.price}" type="currency"/></span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="product-item__info">
                        <!--  Liked: product-item__like--liked  -->
                        <span class="product-item__like">
                            <i class="product-item__like-icon-empty far fa-heart"></i>
                            <i class="product-item__like-icon-fill fas fa-heart"></i>
                        </span>
                        <div class="product-item__rating">
                            <i class="product-item__rating--gold fas fa-star"></i>
                            <i class="product-item__rating--gold fas fa-star"></i>
                            <i class="product-item__rating--gold fas fa-star"></i>
                            <i class="product-item__rating--gold fas fa-star"></i>
                            <i class="product-item__rating--gold fas fa-star"></i>
                        </div>
                        <div class="product-item__sold">

                        </div>
                    </div>
                    <div class="product-item__address">
                        TP. Hồ Chí Minh
                    </div>
                    <div class="product-item__favorite">
                        <span>Yêu thích+</span>
                    </div>
                    <c:if test="${product.discount > 0}">
                        <div class="product-item__sale-off">
                            <span class="product-item__sale-off-percent">${product.discount}%</span>
                            <span class="product-item__sale-off-label">GIẢM</span>
                        </div>
                    </c:if>

                </a>
            </div>
        </c:forEach>


    </div>

    <!-- Pagination  -->
    <div class="pagination home-product__pagination">
        <c:choose>
            <c:when test="${not empty sessionScope.productName}">
                <c:set var="href" value="search?product=${sessionScope.productName}"/>
            </c:when>
            <c:when test="${not empty sessionScope.categoryName}">
                <c:set var="href" value="home?category=${sessionScope.categoryName}"/>
            </c:when>
            <c:otherwise>
                <c:set var="href" value="home?"/>
            </c:otherwise>
        </c:choose>


        <c:if test="${currentPaginationPage > 1}">
            <a href="${href}&page=${currentPaginationPage - 1}" class="pagination__item">
                <i class="pagination__icon fas fa-chevron-left"></i>
            </a>
        </c:if>

        <c:if test="${startPage > 1}">
            <div class="pagination__item pagination__item--none">...</div>
        </c:if>

        <c:forEach begin="${startPage}" end="${endPage}" var="page">
            <c:choose>
                <c:when test="${page == currentPaginationPage}">
                    <a href="${href}&page=${page}" class="pagination__item pagination__item--active">${page}</a>
                </c:when>
                <c:otherwise>
                    <a href="${href}&page=${page}" class="pagination__item">${page}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${endPage < totalPaginationPage}">
            <div class="pagination__item pagination__item--none">...</div>
        </c:if>

        <c:if test="${currentPaginationPage < totalPaginationPage}">
            <a href="${href}&page=${currentPaginationPage + 1}" class="pagination__item">
                <i class="pagination__icon fas fa-chevron-right"></i>
            </a>
        </c:if>

    </div>