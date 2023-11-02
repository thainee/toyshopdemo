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

        <title>Category Detail</title>

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
                                <h1 class="h4 text-gray-900 mb-4">Category Detail</h1>
                            </div>
                            <form class="user" action="update" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" name="name" class="form-control form-control-user" id="name" value="${category.name}">
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label> 
                                    <input type="text" name="description" class="form-control form-control-user" id="description" value="${category.description}">
                                </div>
                                <input type="hidden" name="id" value="${category.id}">
                                <hr>
                                <button type="submit" class="btn btn-facebook btn-user btn-block">Update</button>
                            </form>
                            <hr>
                            <form action="delete" method="post">
                                <input type="hidden" name="id" value="${category.id}">
                                <button type="submit" class="btn btn-danger btn-user btn-block">Delete</button>
                            </form>
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