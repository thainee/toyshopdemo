<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="mobile-category">
    <ul class="mobile-category__list">
        <c:forEach var="category" items="${categories}">
            <li class="mobile-category__item">
                <a href="" class="mobile-category__link"><span>${category.name}</span></a>
            </li>
        </c:forEach>
    </ul>
</nav>