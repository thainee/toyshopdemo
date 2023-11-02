<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Order Detail</title>

        <!-- Custom fonts for this template-->
        <link href="./assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="./assets/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="bg-gradient">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5" style="margin: 0 auto; box-shadow: 0 1px 2px 0 rgba(0,0,0,.30)">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Order Detail</h1>
                            </div>
                            <form class="user" action="update" method="post">
                                <div class="form-group">
                                    <label for="name">Order ID</label>
                                    <input type="text" class="form-control form-control-user" id="id" value="${order.id}" readonly>
                                </div>
                                <c:forEach var="orderItem" items="${orderItems}">
                                    <div class="form-group">
                                        <label for="${orderItem}">Items</label> 
                                        <input type="text" class="form-control form-control-user" id="${orderItem}" value="Product ID: ${orderItem.productId}, Quantity: ${orderItem.quantity}" readonly>
                                    </div>
                                </c:forEach>
                                <div class="form-group">
                                    <label for="total">Total</label> 
                                    <input type="text" class="form-control form-control-user" id="total" value="<fmt:formatNumber value="${order.total}"/>" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="customerName">Customer name</label> 
                                    <input type="text" class="form-control form-control-user" id="customerName" value="${shippingAddress.name}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="phoneNumber">Phone number</label> 
                                    <input type="text" class="form-control form-control-user" id="phoneNumber" value="${shippingAddress.phoneNumber}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="address">Address</label> 
                                    <input type="text" class="form-control form-control-user" id="address" value="${shippingAddress.commune}, ${shippingAddress.district}, ${shippingAddress.city}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="addressLine">Address line note</label> 
                                    <input type="text" class="form-control form-control-user" id="addressLine" value="${shippingAddress.addressLine}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="status">Order Status</label>
                                    <input type="text" class="form-control form-control-user" id="status" value="${order.orderStatus}" readonly>
                                </div>
                                <div class="form-group">
                                    <label>Change status to?</label>
                                    <select name="orderStatus">
                                        <option value="${order.orderStatus}"></option>
                                        <option value="Processing">Processing</option>
                                        <option value="Success">Success</option>
                                    </select>
                                </div>
                                <br></br>

                                <hr>
                                <input type="hidden" name="id" value="${order.id}">
                                <button type="submit" class="btn btn-facebook btn-user btn-block">Update</button>
                            </form>
                            <hr>
                            <hr>
                            <a href="admin?table=${sessionScope.currentTable}" class="btn btn-primary btn-user btn-block">Back</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="./assets/vendor/jquery/jquery.min.js"></script>
        <script src="./assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="./assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="./assets/js/sb-admin-2.min.js"></script>
    </body>

</html>