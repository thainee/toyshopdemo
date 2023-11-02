<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="products" class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Categories</h6>
    </div>

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Function</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Function</th>
                    </tr>
                </tfoot>
                <c:set var="currentTable" value="categories" scope="session"/>
                <tbody>
                    <c:forEach var="category" items="${categories}">
                        <tr>
                            <td>${category.id}</td>
                            <td>${category.name}</td>
                            <td>${category.description}</td>
                            <td>
                                <form action="detail" method="get">
                                    <input type="hidden" name="id" value="${category.id}">
                                    <input type="hidden" name="table" value="${sessionScope.currentTable}">
                                    <button type=submit" class="btn btn-primary">Detail</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <form action="insert" method="get">
            <input type="hidden" name="table" value="${sessionScope.currentTable}">
            <button type=submit" class="btn btn-success">Insert new category</button>
        </form>

    </div>

</div>
