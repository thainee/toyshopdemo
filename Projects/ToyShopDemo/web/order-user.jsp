<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Order</title>

        <!-- Custom fonts for this template -->
        <link href="./assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="./assets/css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="./assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->


                <div id="content">
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
                                            <th>Update at</th>
                                            <th>Total</th>
                                            <th>Status</th>
                                            <th>Function</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>ID</th>
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
                                                        <form action="userorderdetail" method="get">
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
                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Thaiprodep</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Bootstrap core JavaScript-->
        <script src="./assets/vendor/jquery/jquery.min.js"></script>
        <script src="./assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="./assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="./assets/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="./assets/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="./assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="./assets/js/demo/datatables-demo.js"></script>

    </body>

</html>
