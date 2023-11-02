
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="category">
    <h3 class="category__heading">
        <i class="category__heading-icon fas fa-list"></i>
        Danh mục shop
    </h3>

    <div class="category__list">         
        <c:forEach var="category" items="${categories}">
            <c:choose>
                <c:when test="${category.name == sessionScope.categoryName}">
                    <a href="home?category=${category.name}" class="category__item category__item--active">${category.name}</a>
                </c:when>
                <c:otherwise>
                    <a href="home?category=${category.name}" class="category__item">${category.name}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</nav>

