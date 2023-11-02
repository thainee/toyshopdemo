<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="products" class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Products_Categories</h6>
    </div>

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Category ID</th>
                        <th>Function</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Product ID</th>
                        <th>Category ID</th>
                        <th>Function</th>
                    </tr>
                </tfoot>
                <c:set var="currentTable" value="product_categories" scope="session"/>
                <tbody>
                    <c:forEach var="productCategory" items="${productCategories}">
                        <tr>
                            <td>${productCategory.productId}</td>
                            <td>${productCategory.categoryId}</td>
                            <td>
                                <form action="delete" method="post">
                                    <input type="hidden" name="productId" value="${productCategory.productId}">
                                    <input type="hidden" name="categoryId" value="${productCategory.categoryId}">
                                    <input type="hidden" name="table" value="${currentTable}">
                                    <button type=submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <form action="insert" method="get">
            <input type="hidden" name="table" value="${currentTable}">
            <button type=submit" class="btn btn-success">Insert new Product_Category</button>
        </form>
    </div>

</div>
