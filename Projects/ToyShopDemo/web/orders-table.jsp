<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="orders" class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Orders</h6>
    </div>

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Update at</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Function</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>User ID</th>
                        <th>Update at</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Function</th>
                    </tr>
                </tfoot>
                <c:set var="currentTable" value="orders" scope="session"/>
                <tbody>
                    <c:forEach var="order" items="${orders}">     
                        <c:if test="${order.orderStatus != 'Opening'}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.userId}</td>
                                <td>${order.updatedAt}</td>
                                <td><fmt:formatNumber value="${order.total}"/></td>
                                <c:choose>
                                    <c:when test="${order.orderStatus == 'Processing'}">
                                        <td style="color: red">${order.orderStatus}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="color: green">${order.orderStatus}</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <form action="detail" method="get">
                                        <input type="hidden" name="id" value="${order.id}">
                                        <input type="hidden" name="table" value="${sessionScope.currentTable}">
                                        <button type=submit" class="btn btn-primary">Detail</button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
