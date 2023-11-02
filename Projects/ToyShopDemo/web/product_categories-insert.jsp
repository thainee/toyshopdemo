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

        <title>Product_Category Insert</title>

        <!-- Custom fonts for this template-->
        <link href="./assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="./assets/css/sb-admin-2.min.css" rel="stylesheet">
        <script>
            const requiredInputs = document.querySelectorAll('input[required]');

            const isAnyInputEmpty = requiredInputs.some((input) => input.value === '');
        </script>
    </head>
    <body>
        <div class="bg-gradient">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5" style="margin: 0 auto; box-shadow: 0 1px 2px 0 rgba(0,0,0,.30)">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Product_Category Insert</h1>
                            </div>
                            <form class="user" action="insert" method="post">
                                <div class="form-group">
                                    <label for="productId">Product ID</label>
                                    <input type="text" name="productId" class="form-control form-control-user" id="productId" required>
                                </div>
                                <div class="form-group">
                                    <label for="categoryID">Category ID</label> 
                                    <input type="text" name="categoryID" class="form-control form-control-user" id="categoryID" required>
                                </div>
                                <c:if test="${not empty msg}">
                                    <h6 style="color: red">${msg}</h6>
                                </c:if>
                                <hr>
                                <button type="submit" class="btn btn-facebook btn-user btn-block">Insert</button>
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